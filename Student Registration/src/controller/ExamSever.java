package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.Exam;
import module.Teacher;
import view.tm.ExamTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamSever {

    public ObservableList<ExamTm> getExamList() throws SQLException, ClassNotFoundException {
        ObservableList<ExamTm> exam = FXCollections.observableArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM exam");
        ResultSet rst = stm.executeQuery();

        while (rst.next()){
            exam.add(new ExamTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        return exam;
    }

    public boolean SaveExam(Exam exam) throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().
                prepareStatement("INSERT INTO exam VALUES (?,?,?)");
        stm.setObject(1,exam.getExamId());
        stm.setObject(2,exam.getDescription());
        stm.setObject(3,exam.getExamType());

        return stm.executeUpdate() > 0 ;
    }

    public boolean deleteExam(String examId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().
                prepareStatement("DELETE FROM exam WHERE  Exam_Id = '" + examId +"'").
                executeUpdate() > 0) {

            return true;
        }else{
            return false;
        }
    }

    public boolean updateExam(ExamTm examTm) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE exam SET Description = ?, Exam_Type= ? WHERE Exam_Id = ? ");

        stm.setObject(1,examTm.getExamType());
        stm.setObject(2,examTm.getDescription());
        stm.setObject(3,examTm.getExamId());

        return stm.executeUpdate() >0;
    }

    //passCmb
    public List<String> getAllExamId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM exam ").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }

    //pass id text
    public Exam passExam(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM exam WHERE  Exam_Id = '" + id + "'").executeQuery();
        if (rst.next()) {
            return new Exam(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)

            );
        }else {
            return null;
        }
    }

    public String getExamId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM exam ORDER BY Exam_Id DESC LIMIT 1").executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("_")[1]);
            //tempId = tempId + 1;
            tempId += 1;

            if (tempId < 10) {
                return "EXM_00" + tempId;

            } else if (tempId < 100) {
                return "EXM_0" + tempId;

            } else {
                return "EXM" + tempId;
            }

        } else {
            return "EXM_001";
        }
    }

}
