package org.kniit.patterns;

public class AbstractFactoryExample {

    public static void main(String[] args) {
        FurnitureFactory factory = new ModernFurnitureFactory();

        Chair chair = factory.createChair();
        Table table = factory.createTable();
        Sofa sofa = factory.createSofa();
        chair.sit();
        table.use();
        sofa.relax();

        factory = new VictorianFurnitureFactory();

        chair = factory.createChair();
        table = factory.createTable();
        sofa = factory.createSofa();

        chair.sit();
        table.use();
        sofa.relax();
    }
}

interface Chair {
    void sit();
}

interface Table {
    void use();
}

interface Sofa {
    void relax();
}
class ModernChair implements Chair {
    public void sit() {
        System.out.println("Сидим на современной стуле");
    }
}

class ModernTable implements Table {
    public void use() {
        System.out.println("Работаем за современным столом");
    }
}

class ModernSofa implements Sofa {
    public void relax() {
        System.out.println("Отдыхаем на современном диване");
    }
}

class VictorianChair implements Chair {
    public void sit() {
        System.out.println("Сидим на викторианском стуле");
    }
}

class VictorianTable implements Table {
    public void use() {
        System.out.println("Работаем за викторианским столом");
    }
}

class VictorianSofa implements Sofa {
    public void relax() {
        System.out.println("Отдыхаем на викторианском диване");
    }
}

interface FurnitureFactory {
    Chair createChair();
    Table createTable();
    Sofa createSofa();
}

class ModernFurnitureFactory implements FurnitureFactory {
    public Chair createChair() { return new ModernChair(); }
    public Table createTable() { return new ModernTable(); }
    public Sofa createSofa() { return new ModernSofa(); }
}

class VictorianFurnitureFactory implements FurnitureFactory {
    public Chair createChair() { return new VictorianChair(); }
    public Table createTable() { return new VictorianTable(); }
    public Sofa createSofa() { return new VictorianSofa(); }
}




