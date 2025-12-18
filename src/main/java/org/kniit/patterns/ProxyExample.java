package org.kniit.patterns;

public class ProxyExample {

    public static void main(String[] args) {
        MessageService realService = new RealMessageService();

        MessageService adminProxy  = new MessageServiceProxy(realService, "ADMIN");
        MessageService userProxy   = new MessageServiceProxy(realService, "USER");

        adminProxy.send("kniit", "Привет!");  // будет отправлено
        userProxy.send("kniit", "Это не должно уйти");
    }
}
interface MessageService {
    void send(String user, String text);
}

class RealMessageService implements MessageService {
    @Override
    public void send(String user, String text) {
        System.out.println("Отправка сообщения пользователю " + user + ": " + text);
    }
}
class MessageServiceProxy implements MessageService {

    private final MessageService target;
    private final String currentUserRole;

    public MessageServiceProxy(MessageService target, String currentUserRole) {
        this.target = target;
        this.currentUserRole = currentUserRole;
    }

    @Override
    public void send(String user, String text) {
        if (!"ADMIN".equals(currentUserRole)) {
            System.out.println("Доступ запрещён: роль " + currentUserRole + " не может отправлять сообщения");
            return;
        }
        System.out.println("[LOG] Перед отправкой: user=" + user + ", text=" + text);
        target.send(user, text);
        System.out.println("[LOG] Сообщение отправлено");
    }
}
