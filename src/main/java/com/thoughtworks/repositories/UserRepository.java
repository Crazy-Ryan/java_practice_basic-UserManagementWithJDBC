package com.thoughtworks.repositories;

import com.thoughtworks.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository implements UserRepositoryI {

    private final String TABLE_NAME = "user_info";

    @Override
    public boolean userRegister(User user) {
        Connection connection = DatabaseUtil.connectToDB();
        PreparedStatement preparedStatement = null;
        try {
            String sqlCmd = "INSERT INTO " + TABLE_NAME +
                    "(username,phone,email,password)" +
                    "values(" + "?,?,?,?)";
            preparedStatement = connection.prepareStatement(sqlCmd);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseUtil.releaseSource(connection, preparedStatement);
        }
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        Connection connection = DatabaseUtil.connectToDB();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sqlQuery = "SELECT " +
                    "id,username,phone,email,password,failed_login_count,locked " +
                    "FROM " + TABLE_NAME + " WHERE username = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User fetchedUser = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("failed_login_count"),
                        resultSet.getBoolean("locked"));

                if (fetchedUser.isLocked()) {
                    return new User(0, "", "", "", "", 0, true);
                } else {
                    if (password.equals(fetchedUser.getPassword())) {
                        String sqlCmd = "UPDATE user_info SET failed_login_count = 0 "
                                + " WHERE id = "
                                + fetchedUser.getId();
                        connection.createStatement().execute(sqlCmd);
                        return fetchedUser;
                    } else {
                        int failedLoginCount = fetchedUser.getFailedLoginCount();
                        if (failedLoginCount < 2) {
                            String sqlCmd = "UPDATE user_info SET failed_login_count = "
                                    + (failedLoginCount + 1)
                                    + " WHERE id = "
                                    + fetchedUser.getId();
                            connection.createStatement().execute(sqlCmd);
                            return new User();
                        } else {
                            String sqlCmd = "UPDATE user_info SET failed_login_count ="
                                    + (failedLoginCount + 1)
                                    + ", locked = true"
                                    + " WHERE id = "
                                    + fetchedUser.getId();
                            connection.createStatement().execute(sqlCmd);
                            return new User(0, "", "", "", "", 0, true);
                        }
                    }
                }
            } else {
                return new User();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new User();
        } finally {
            DatabaseUtil.releaseSource(connection, preparedStatement, resultSet);
        }
    }

}
