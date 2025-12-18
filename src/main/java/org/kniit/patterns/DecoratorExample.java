package org.kniit.patterns;

public class DecoratorExample {

    public static void main(String[] args) {
        Pizza simple = new Margherita();
        System.out.println(simple.getDescription() + " = " + simple.getCost());
        Pizza cheeseMargherita = new CheeseTopping(new Margherita());
        System.out.println(cheeseMargherita.getDescription() + " = " + cheeseMargherita.getCost());

        Pizza megaPizza = new MushroomTopping(
            new BaconTopping(
                new CheeseTopping(
                    new Pepperoni()
                )
            )
        );
        System.out.println(megaPizza.getDescription() + " = " + megaPizza.getCost());
    }
}
interface Pizza {
    String getDescription();
    double getCost();
}

class Margherita implements Pizza {
    @Override
    public String getDescription() {
        return "Маргарита";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

class Pepperoni implements Pizza {
    @Override
    public String getDescription() {
        return "Пепперони";
    }

    @Override
    public double getCost() {
        return 6.0;
    }
}

abstract class PizzaDecorator implements Pizza {
    protected final Pizza pizza;

    protected PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}
class CheeseTopping extends PizzaDecorator {
    public CheeseTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", дополнительный сыр";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.0;
    }
}

class BaconTopping extends PizzaDecorator {
    public BaconTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", бекон";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.5;
    }
}

class MushroomTopping extends PizzaDecorator {
    public MushroomTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", грибы";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 0.8;
    }
}


