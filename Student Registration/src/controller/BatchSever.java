package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.Batch;
import view.tm.BatchTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchSever {

    public ObservableList<BatchTm> getBatchList() throws SQLException, ClassNotFoundException {

        ObservableList<BatchTm> batch = FXCollections.observableArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM batch");
        ResultSet rst = stm.executeQuery();

        while (rst.next()){
            batch.add(new BatchTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return batch;
    }

    //PassIdCmb
    public List<String>getAllBatchId() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM batch").executeQuery();
        List<String>name = new ArrayList<>();
        while (rst.next()){
            name.add(rst.getString(1));
        }
        return name;
    }

    //setText
    public Batch passBatch(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM batch WHERE Batch_Id='" + id + "'").executeQuery();
        if (rst.next()){
            return new Batch(
                  rst.getString(1),
                  rst.getString(2),
                  rst.getString(3),
                  rst.getString(4)
            );
        }else{
            return null;
        }

    }

    public boolean saveBatch(Batch batch) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("INSERT INTO batch VALUES (?,?,?,?)");

        stm.setObject(1,batch.getBatchId());
        stm.setObject(2,batch.getBatchName());
        stm.setObject(3,batch.getSelectCourseId());
        stm.setObject(4,batch.getCourseName());

        return stm.executeUpdate() > 0;
    }

    public boolean deleteBatch(String batchId ) throws SQLException, ClassNotFoundException {

       if (DbConnection.getInstance().getConnection().
               prepareStatement("DELETE FROM Batch WHERE Batch_Id = '" + batchId +"'").
               executeUpdate() > 0) {
           return true;
       }else {
           return false;
       }
    }

    public boolean updateBatch(BatchTm batchTm) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE Batch SET Batch_Name = ?, Course_Id=?, CourseName=? WHERE Batch_Id=?");

        stm.setObject(1,batchTm.getBatchName());
        stm.setObject(2,batchTm.getCourseId());
        stm.setObject(3,batchTm.getCourseName());
        stm.setObject(4,batchTm.getBatchId());

        return stm.executeUpdate() > 0 ;
    }

    public String getBatchId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM batch ORDER BY Batch_Id DESC LIMIT 1").executeQuery();

        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("_")[1]);
            tempId += 1;

            if (tempId < 10) {
                return "BTH_00" + tempId;

            } else if (tempId < 100) {
                return "BTH_0" + tempId;

            } else {
                return "BTH" + tempId;
            }
        }else{
            return "BTH_001";

        }
    }
}