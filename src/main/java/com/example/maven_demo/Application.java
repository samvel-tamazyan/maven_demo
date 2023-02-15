package com.example.maven_demo;

import com.example.maven_demo.manager.UserManager;
import com.example.maven_demo.manager.impl.UserManagerImpl;
import com.example.maven_demo.models.User;
import com.example.maven_demo.models.enums.Gender;

import java.util.Scanner;

public class Application {

    private User currentUser;

    private final static Scanner scanner = new Scanner(System.in);

    private final UserManager userManager = new UserManagerImpl();

    public void start() {
        welcomePage();
        String command = scanner.nextLine();
        switch (command) {
            case "0": {
                exit();
                break;
            }
            case "1": {
                login();
                break;
            }
            case "2": {
                register();
                break;
            }
            default: {
            }
        }


    }

    private void register() {
        System.out.println("Input your email");
        String email = scanner.nextLine();
        while (userManager.existByEmail(email)) {
            System.out.println("Email already used");
            System.out.println("Input your email");
            email = scanner.nextLine();
        }

        System.out.println("Input your name");
        String name = scanner.nextLine();

        System.out.println("Input your surname");
        String surname = scanner.nextLine();

        System.out.println("Input your password");
        String password = scanner.nextLine();

        System.out.println("Input your gender");
        String gender = scanner.nextLine();

        System.out.println("Input your age");
        int age = Integer.parseInt(scanner.nextLine());

        currentUser = userManager.save(User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .gender(Gender.valueOf(gender))
                .age(age)
                .build());
        userHome();

    }

    private void login() {
        System.out.println("Input your email");
        String email = scanner.nextLine();

        System.out.println("Input your password");
        String password = scanner.nextLine();
        User currentUser = userManager.getByEmailAndPassword(email, password);
        if (currentUser == null) {
            System.out.println("Incorrect email or password");
            start();
        } else {
            this.currentUser = currentUser;
            userHome();

        }
    }

    private void userHome() {
        System.out.println("For logout press 1");
        System.out.println("For add new book press 2");
        String command = scanner.nextLine();
        switch (command) {
            case "1": {
                currentUser = null;
                start();
            }
            case "2": {
            }
        }
    }

    private void exit() {
        System.out.println("By ... ");
        System.exit(0);
    }

    private void welcomePage() {
        System.out.println("For exit press 0");
        System.out.println("For login press 1.");
        System.out.println("For registration press 2.");
    }
}
