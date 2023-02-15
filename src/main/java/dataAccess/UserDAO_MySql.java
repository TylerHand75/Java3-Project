package dataAccess;




import Ch5.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_MySql implements DAO_MySql<User>{

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection()){
            if (connection.isValid(2)){
                System.out.println("Connection successful");
            }
        }catch(SQLException e){
            System.out.println("Connection Failed");
            System.out.println(e.getMessage());
        }
        return users;
    }



}
