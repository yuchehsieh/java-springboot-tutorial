package com.example.practice;

import java.util.Optional;

public class optional {

    public static void main(String[] args) {

        Person person = new Person("Murphy Hsieh", "murphy@gmail.com");

        /** 方法1 **/
        String email = person.getEmail()
                .map(s -> {
                    System.out.println(s.getClass()); // class java.lang.String
                    System.out.println(s);
                    return s.toUpperCase();
                })
                .orElse("please provide an email");

        System.out.println(email);

        /** 方法2 **/
//        if(person.getEmail().isPresent()) {
//            String email = person.getEmail().get();
//            System.out.println(email);
//        } else {
//            System.out.println("please provide an email");
//        }

        /** 方法3 **/
//        person.getEmail()
//                .ifPresentOrElse(System.out::println, () -> {
//                    System.out.println("Please Provide a valid email");
//                });

    }
}

class Person {
    private final String name;
    private final String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}
