package org.kniit.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ReflectionExample {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> clazz = Person.class;

        System.out.println(clazz.getSuperclass());

        int classModifiers = clazz.getModifiers();
        System.out.println("Модификаторы класса: " + Modifier.toString(classModifiers));

        System.out.println("Поля класса:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(" " + field.getName() + ", модификатор: " + Modifier.toString(field.getModifiers()) + ", тип: " + field.getType().getName());
        }

        System.out.println("Методы класса:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(" " + method.getName() + ", модификатор: " + Modifier.toString(method.getModifiers()));
        }

        Method greetMethod = clazz.getMethod("greet", String.class);
        System.out.println(greetMethod.getName());
        for (Parameter parameter : greetMethod.getParameters()) {
            System.out.println(parameter);
        }

        System.out.println("Конструкторы класса:");
        for (Constructor<?> constructor : clazz.getConstructors()) {
            System.out.println(" Конструктор: " + constructor);
        }

        Person p1 = clazz.getConstructor(String.class, int.class).newInstance("Ivan", 33);
        greetMethod.invoke(p1, "Alex");

        Method secretMethod = clazz.getDeclaredMethod("secret");
        secretMethod.setAccessible(true);
        secretMethod.invoke(p1);

        int newLength = 7;
        int[] newArray = (int[]) Array.newInstance(int.class, newLength);

        for (int i = 0; i < newLength; i++) {
            Array.set(newArray, i, i * 10);
        }

        System.out.print("Новый массив, созданный через рефлексию: ");
        for (int i = 0; i < newLength; i++) {
            System.out.print(Array.get(newArray, i) + " ");
        }
    }
}

final class Person {
    private String name;
    public int age;

    public Person() {
        this.name = "NoName";
        this.age = 0;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    private void secret() {
        System.out.println("Это приватный метод!");
    }
    public void greet(String name) {
        System.out.println(this.name + " приветствует " + name);
    }
}
