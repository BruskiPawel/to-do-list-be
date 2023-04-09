package de.bruski.todolist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListBeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ToDoListBeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("I'm running! ");
    }
}
