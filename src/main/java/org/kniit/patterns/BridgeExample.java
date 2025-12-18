package org.kniit.patterns;

public class BridgeExample {

    public static void main(String[] args) {
        Shape redCircle    = new Circle(10, new RedColor());
        Shape blueCircle   = new Circle(5, new BlueColor());
        Shape redRectangle = new Rectangle(4, 6, new RedColor());

        redCircle.draw();
        blueCircle.draw();
        redRectangle.draw();
    }
}
interface Color {
    String fill();
}

class RedColor implements Color {
    @Override
    public String fill() {
        return "красным";
    }
}

class BlueColor implements Color {
    @Override
    public String fill() {
        return "синим";
    }
}

// Абстракция (Abstraction)
abstract class Shape {
    protected final Color color; // мост к реализации

    protected Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}

class Circle extends Shape {
    private final double radius;

    public Circle(double radius, Color color) {
        super(color);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Рисуем круг радиусом " + radius + " " + color.fill());
    }
}

class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height, Color color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Рисуем прямоугольник " + width + "x" + height + " " + color.fill());
    }
}


