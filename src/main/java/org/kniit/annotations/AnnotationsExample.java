package org.kniit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class AnnotationsExample {

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person(14);
        Validator.validate(person);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Min {

    long value() default 18;

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Max {

    long value() default 65;

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {

    long max() default 65;
    long min() default 18;

}

class Person {

    @Range
    private int age;

    public Person(int age) {
        this.age = age;
    }

}

class Validator {

    public static void validate(Object obj) throws IllegalAccessException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);

            if (field.isAnnotationPresent(Min.class)) {
                long min = field.getAnnotation(Min.class).value();
                if (Long.parseLong(value.toString()) < min) {
                    throw new IllegalArgumentException(field.getName() + " меньше " + min);
                }
            }
            if (field.isAnnotationPresent(Max.class)) {
                long max = field.getAnnotation(Max.class).value();
                if (Long.parseLong(value.toString()) > max) {
                    throw new IllegalArgumentException(field.getName() + " больше " + max);
                }
            }
            if (field.isAnnotationPresent(Range.class)) {
                long max = field.getAnnotation(Range.class).max();
                long min = field.getAnnotation(Range.class).min();
                long lValue = Long.parseLong(value.toString());
                if (lValue > max || lValue < min) {
                    throw new IllegalArgumentException(field.getName() + " вне диапазона " + min + " - " + max );
                }
            }
        }
    }

}
