package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        User user1 = new User("Name1", "Surname1", (byte) 1);
        User user2 = new User("Name2", "Surname2", (byte) 2);
        User user3 = new User("Name3", "Surname3", (byte) 3);
        User user4 = new User("Name4", "Surname4", (byte) 4);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
