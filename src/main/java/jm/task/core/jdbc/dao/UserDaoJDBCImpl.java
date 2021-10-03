package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();

    public void createUsersTable() {
        try(Statement statement = util.getConnection()) {
            String createTable = "" +
                    "create table if not exists users\n" +
                    "(id int auto_increment primary key,\n" +
                    "name varchar(45) not null,\n" +
                    "lastName varchar(45) not null,\n" +
                    "age tinyint null);";
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Statement statement = util.getConnection()) {
            String dropUsersTable = "drop table if exists users;";
            statement.executeUpdate(dropUsersTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = util.getConnection()){
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(
                    "insert into users (name, lastName, age) values (?, ?, ?);"
            );
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.println("Юзер с именем " + name + " добавлен в базу.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try(Statement statement = util.getConnection()){
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(
                    "delete from users where id = ?;"
            );
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try(Statement statement = util.getConnection()){
            ResultSet resultSet = statement.executeQuery("select * from users;");
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try(Statement statement = util.getConnection()){
            statement.executeUpdate("truncate table users;");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
