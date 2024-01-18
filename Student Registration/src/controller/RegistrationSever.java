package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.RegistrationTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationSever {
   /*public ObservableList<RegistrationTm> getRegisterList() throws SQLException, ClassNotFoundException {
        ObservableList<RegistrationTm> register = FXCollections.observableArrayList();
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM registration");
        ResultSet rst = stm.executeQuery();

        while (rst.next()){
            register.add(new RegistrationTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        return register;
    }*/

}
