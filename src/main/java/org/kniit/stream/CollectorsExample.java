package org.kniit.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsExample {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "HR", 10),
            new Employee("Bob", "IT", 20),
            new Employee("Charlie", "HR", 30),
            new Employee("David", "IT", 25)
        );

        var employeesByDepartment = employees.stream().toList();



        //.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)), opt -> opt.map(Employee::getSalary).orElse(0))));

        System.out.println(employeesByDepartment);
    }






    static class Employee {
        String name;
        String department;
        int salary;

        Employee(String name, String department, int salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() { return name; }
        public String getDepartment() { return department; }
        public int getSalary() {return salary;}
        public String toString() { return name; }
    }
}

