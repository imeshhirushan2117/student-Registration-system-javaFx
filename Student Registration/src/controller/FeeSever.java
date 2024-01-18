package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.Fee;
import view.tm.FeeTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeeSever {
    public boolean addFee(Fee fee) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("INSERT INTO fee VALUES (?,?,?,?,?)");

        stm.setObject(1,fee.getFeeId());
        stm.setObject(2,fee.getSelectCourseId());
        stm.setObject(3,fee.getCash());
        stm.setObject(4,fee.getDate());
        stm.setObject(5,fee.getCourseName());

        return stm.executeUpdate() > 0;
    }

    public ObservableList<FeeTm> getFeeList() throws SQLException, ClassNotFoundException {
        ObservableList<FeeTm> fee = FXCollections.observableArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM fee");
        ResultSet rst = stm.executeQuery();

        while (rst.next()){
            fee.add(new FeeTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return fee;
    }

    public boolean deleteFee(String feeId) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().
                prepareStatement("DELETE FROM Fee WHERE Fee_Id = '" + feeId +"'").
                executeUpdate() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean updateFee(FeeTm feeTm) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE Fee SET Course_Id =?, Cash=?, Date= ?, CourseName=? WHERE Fee_Id=?");

        stm.setObject(1,feeTm.getCourseId());
        stm.setObject(2,feeTm.getCash());
        stm.setObject(3,feeTm.getDate());
        stm.setObject(4,feeTm.getCourseName());
        stm.setObject(5,feeTm.getFeeId());

        return stm.executeUpdate() > 0 ;
    }

    public String getFeeId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM fee ORDER BY Fee_Id DESC LIMIT 1").executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("_")[1]);
            //tempId = tempId + 1;
            tempId += 1;

            if (tempId < 10) {
                return "FEE_00" + tempId;

            } else if (tempId < 100) {
                return "FEE_0" + tempId;

            } else {
                return "FEE" + tempId;
            }

        } else {
            return "FEE_001";
        }
    }
}
