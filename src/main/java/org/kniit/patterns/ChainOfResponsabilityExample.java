package org.kniit.patterns;

import java.util.ArrayList;
import java.util.List;

public class ChainOfResponsabilityExample {

    public static void main(String[] args) {
        FilterChain chain = new FilterChain()
            .addFilter(new LoggingFilter())
            .addFilter(new AuthFilter());

        System.out.println("=== Пример 1: корректный запрос ===");
        RequestContext okRequest = new RequestContext("hello token, bad word inside");
        chain.doFilter(okRequest);

        System.out.println("\n=== Пример 2: запрос без токена ===");
        FilterChain chain2 = new FilterChain()
            .addFilter(new LoggingFilter())
            .addFilter(new AuthFilter());

        RequestContext badRequest = new RequestContext("no auth here, bad word");
        chain2.doFilter(badRequest);
    }
}

class RequestContext {
    private String body;
    private boolean authenticated;
    private boolean blocked;

    public RequestContext(String body) {
        this.body = body;
    }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public boolean isAuthenticated() { return authenticated; }
    public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }

    public boolean isBlocked() { return blocked; }
    public void setBlocked(boolean blocked) { this.blocked = blocked; }
}

interface RequestFilter {
    void doFilter(RequestContext ctx, FilterChain chain);
}

class FilterChain {
    private final List<RequestFilter> filters = new ArrayList<>();
    private int currentIndex = 0;

    public FilterChain addFilter(RequestFilter filter) {
        filters.add(filter);
        return this;
    }

    public void doFilter(RequestContext ctx) {
        if (currentIndex < filters.size()) {
            RequestFilter nextFilter = filters.get(currentIndex++);
            nextFilter.doFilter(ctx, this);
        } else {
            handleRequest(ctx);
        }
    }

    private void handleRequest(RequestContext ctx) {
        if (ctx.isBlocked()) {
            System.out.println("Запрос заблокирован фильтрами, обработка не выполняется");
        } else {
            System.out.println("Обработка запроса в конечной точке. Тело: " + ctx.getBody());
        }
    }
}

class LoggingFilter implements RequestFilter {
    @Override
    public void doFilter(RequestContext ctx, FilterChain chain) {
        System.out.println("[LoggingFilter] Входящий запрос: " + ctx.getBody());
        chain.doFilter(ctx);
        System.out.println("[LoggingFilter] Ответ отправлен");
    }
}

class AuthFilter implements RequestFilter {
    @Override
    public void doFilter(RequestContext ctx, FilterChain chain) {
        boolean ok = ctx.getBody().contains("token");
        ctx.setAuthenticated(ok);
        System.out.println("[AuthFilter] Аутентификация: " + (ok ? "успех" : "провал"));

        if (!ok) {
            ctx.setBlocked(true);
            System.out.println("[AuthFilter] Блокируем запрос");
            return;
        }
        chain.doFilter(ctx);
    }
}
