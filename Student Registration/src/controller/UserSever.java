package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.User;
import view.tm.UserTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSever {
    public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?,md5(?),?)");

        stm.setObject(1,user.getFirstName());
        stm.setObject(2,user.getLastName());
        stm.setObject(3,user.getContact());
        stm.setObject(4,user.getEmail());
        stm.setObject(5,user.getUserName());
        stm.setObject(6,user.getPassword());
        stm.setObject(7,user.getRole());

        return stm.executeUpdate() >0 ;
    }

    public String logIn (User log) throws SQLException, ClassNotFoundException {
        String userName = log.getUserName();
        String password = log.getPassword();

        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM User WHERE  User_Name=? AND User_Password=? "
                );


        stm.setObject(1,userName);
        stm.setObject(2,password);

        ResultSet rst = stm.executeQuery();


        if (rst.next()){
            return  rst.getString(7);
        }else{
            return "";
        }

    }

    public ObservableList <UserTm>getUserList() throws SQLException, ClassNotFoundException {
        ObservableList<UserTm> users = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM `User`");
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            users.add(new UserTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return users;
    }

    public boolean deleteUser(String userName) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().
                prepareStatement("DELETE FROM `User` WHERE  User_Name = '"+userName+"'").
                executeUpdate() > 0) {
            return true;
        }else{
            return false;
        }
    }

   public boolean updateUser(UserTm usertm) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE `User` SET First_Name=?, Last_Name=?, Contact=?, Email=?, User_Password=?, `Role`=? WHERE User_Name=?");

        stm.setObject(1,usertm.getFirstName());
        stm.setObject(2,usertm.getLastName());
        stm.setObject(3,usertm.getContact());
        stm.setObject(4,usertm.getEmail());
        stm.setObject(5,usertm.getUserPassword());
        stm.setObject(6,usertm.getRole());
        stm.setObject(7,usertm.getUserName());

        return stm.executeUpdate() > 0 ;
    }

}
