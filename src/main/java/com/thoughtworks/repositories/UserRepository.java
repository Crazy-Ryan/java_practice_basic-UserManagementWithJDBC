package com.thoughtworks.repositories;

import com.thoughtworks.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository implements UserRepositoryI {

    private final String TABLE_NAME = "user_info";


    @Override
    public boolean userRegister(User user) {
        Connection connection = DatabaseUtil.getConnection();
        try {
            String sqlCmd = "INSERT INTO " + TABLE_NAME +
                    "(username,phone,email,password)" +
                    "values(" + "?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCmd);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        Connection connection = DatabaseUtil.getConnection();
        try {
            String sqlQuery = "SELECT " +
                    "id,username,phone,email,password,failed_login_count,locked " +
                    "FROM " + TABLE_NAME + " WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            User fetchedUser = new User();
            while (resultSet.next()) {
                fetchedUser = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("failed_login_count"),
                        resultSet.getBoolean("locked"));
            }
            if (password.equals(fetchedUser.getPassword())) {
                return fetchedUser;
            } else {
                return new User();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new User();
        }
    }

}
