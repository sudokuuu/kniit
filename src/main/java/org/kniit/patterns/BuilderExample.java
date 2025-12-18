package org.kniit.patterns;

public class BuilderExample {

    public static void main(String[] args) {
        User u1 = new User.Builder("kniit", "kniit@example.com")
            .build();

        User u2 = new User.Builder("java_dev", "dev@example.com")
            .firstName("Ivan")
            .lastName("Petrov")
            .age(25)
            .build();

        System.out.println(u1.getUsername() + " / " + u1.getEmail());
        System.out.println(u2.getUsername() + " / " + u2.getFirstName() + " " + u2.getLastName());
    }
}

class User {
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final int age;

    private User(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }

    public static class Builder {
        private final String username;
        private final String email;

        private String firstName = "";
        private String lastName = "";
        private int age = 0;

        public Builder(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
