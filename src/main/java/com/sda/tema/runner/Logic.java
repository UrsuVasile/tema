package com.sda.tema.runner;

import com.sda.tema.dto.UserDTO;
import com.sda.tema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Logic {
    @Autowired
    UserService userService;

    Scanner scanner;
    UserDTO userDTO;

    public Logic() {
        scanner = new Scanner(System.in);
        userDTO = new UserDTO();
    }

    public void action() {

        System.out.println("1.Log in");
        System.out.println("2.Sign up");
        int firstCommand = scanner.nextInt();
        if (firstCommand == 1) {
            login();
        } else if (firstCommand == 2) {
            signup();
        } else {
            System.out.println("Plese insert 1 or 2");
            action();
        }
    }

    public void login() {
        System.out.println("Please, enter username:");
        String userName = scanner.next();
        System.out.println("Please, enter password:");
        String password = scanner.next();

        if (userService.findUserAndPasswordFromDatabase(userName, password)) {
            System.out.println("Login successfully!");
            runApp();
        } else {
            System.out.println("The user is not valid!");
            System.out.println("Please re-enter credentials, or sign up!");
            action();
        }
    }

    public void signup() {
        System.out.println("This command will be create a user");
        System.out.println("Enter First Name:");
        String firstName = scanner.next();
        userDTO.setFirstName(firstName);
        System.out.println("Enter Last Name:");
        String lastName = scanner.next();
        userDTO.setLastName(lastName);
        System.out.println("Enter Username:");
        String userName = scanner.next();
        userDTO.setUserName(userName);
        System.out.println("Enter email:");
        String email = scanner.next();
        userDTO.setEmail(email);
        System.out.println("Enter password:");
        String password = scanner.next();
        userDTO.setPassword(password);

        userService.insertUser(userDTO);
        System.out.println("You have successfully created the user!");
        login();
    }

    public void runApp() {
        System.out.println("1.Delete User");
        System.out.println("2.Change password");
        System.out.println("3.Exit");

        int command = scanner.nextInt();
        executeCommand(command);

    }

    public void executeCommand(int command) {
        boolean isRunnig = true;
        while (isRunnig) {
            switch (command) {
                case 1:
                    System.out.println("This command wil be delete the user");
                    System.out.println("Insert the username you want to delete");
                    String usernameToDelete = scanner.next();
                    userService.deleteUser(usernameToDelete);
                    System.out.println("You deleted the user successfully!");
                    runApp();
                    break;
                case 2:
                    System.out.println("This command will update the password");
                    System.out.println("Please, enter username:");
                    String usernameUpdate = scanner.next();

                    System.out.println("Please, enter new password:");
                    String userPasswordUpdate = scanner.next();

                    UserDTO userDTO = new UserDTO();

                    userDTO.setPassword(userPasswordUpdate);
                    userDTO.setFirstName(userService.findUserByUserName(usernameUpdate).getFirstName());
                    userDTO.setLastName(userService.findUserByUserName(usernameUpdate).getLastName());
                    userDTO.setUserName(userService.findUserByUserName(usernameUpdate).getUserName());
                    userDTO.setEmail(userService.findUserByUserName(usernameUpdate).getEmail());

                    userService.updateUserPassword(userDTO);
                    System.out.println("You updated password succsessfully!");
                    runApp();
                case 3:
                    isRunnig = false;
                default:
                    System.out.println("Goodbye!");
            }
        }
    }

}
