package org.kniit.patterns;

public class FactoryMethodExample {

    public static void main(String[] args) {
        Fabric fabric = new RoadFabric();
        fabric.planDelivery();

        fabric = new SeaFabric();
        fabric.planDelivery();
    }
}

interface Transport {
    void deliver();
}
class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Доставка грузовиком по суше");
    }
}

class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Доставка кораблём по морю");
    }
}

abstract class Fabric {
    protected abstract Transport createTransport();

    public void planDelivery() {
        Transport t = createTransport();
        System.out.println("Планируем доставку...");
        t.deliver();
    }
}

class RoadFabric extends Fabric {
    @Override
    protected Transport createTransport() {
        return new Truck();
    }
}

class SeaFabric extends Fabric {
    @Override
    protected Transport createTransport() {
        return new Ship();
    }
}

