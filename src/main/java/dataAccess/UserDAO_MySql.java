package dataAccess;

import Ch5.User;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO_MySql implements DAO_MySql<User> {

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection()) {
            if (connection.isValid(2)) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from users");
                //get data
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    char[] password = resultSet.getString("password").toCharArray();
                    String status = resultSet.getString("status");
                    String privileges = resultSet.getString("privileges");
                    User user = new User(id, firstName, lastName, email, password, status, privileges);
                    users.add(user);
                }
                resultSet.close();
                statement.close();

            }

        } catch (SQLException e) {
            System.out.println("Get all users failed ");
            System.out.println(e.getMessage());
        }
        return users;
    }
    public int add (User user){
        int numRowsAffected = 0;
        try (Connection connection = getConnection()){
            if (connection.isValid(2)){
                String sql = "INSERT INTO users (first_name,last_name, email, password, status)" +
                        "VALUES (?,?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, user.getFirst_name());
                statement.setString(2, user.getLast_name());
                statement.setString(3, user.getEmail());
                statement.setString(4, new String(user.getPassword()));
                statement.setString(5, "active");
                numRowsAffected = statement.executeUpdate();
                statement.close();

            }

        }catch (SQLException ex){
            System.out.println("Add user failed");
            System.out.println(ex.getMessage());
        }
        return numRowsAffected;
    }


}
