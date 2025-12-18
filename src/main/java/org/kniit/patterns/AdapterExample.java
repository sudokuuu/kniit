package org.kniit.patterns;

public class AdapterExample {

    public static void main(String[] args) {
        PaymentProcessor processor =
            new LegacyPaymentAdapter(new LegacyPaymentService());

        processor.pay(234);
    }
}
interface PaymentProcessor {
    void pay(double amount);
}
class LegacyPaymentService {
    public void makeTransaction(int cents) {
        System.out.println("Выполнен платёж на сумму " + cents + " центов");
    }
}
class LegacyPaymentAdapter implements PaymentProcessor {

    private final LegacyPaymentService legacyService;

    public LegacyPaymentAdapter(LegacyPaymentService legacyService) {
        this.legacyService = legacyService;
    }

    @Override
    public void pay(double amount) {
        int cents = (int) Math.round(amount * 100);
        System.out.println("Адаптер конвертирует " + amount + " в " + cents + " центов");
        legacyService.makeTransaction(cents);
    }
}


