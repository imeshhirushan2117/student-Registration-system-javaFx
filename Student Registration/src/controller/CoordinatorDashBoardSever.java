package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.FeeTm;
import view.tm.StudentTm;
import view.tm.SubjectTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoordinatorDashBoardSever {

    public ObservableList<StudentTm> getStudentData() throws SQLException, ClassNotFoundException {
        ObservableList<StudentTm> studentList = FXCollections.observableArrayList();
//        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT COUNT(Student_Id), b. Batch_Name, c.Course_Name from batch b, course c, student s WHERE  c.Course_Id=s.Course_Id AND b.Batch_Id =s.Batch_Id GROUP BY  b.Batch_Name");
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT COUNT(s.student_id) AS Student_Count, b.batch_name, c.course_name FROM student s JOIN course c ON c.course_id = s.course_id JOIN batch b ON b.batch_id = s.batch_id GROUP BY b.batch_name, c.course_name");
                /*prepareStatement("SELECT COUNT(Student_Id), b.Batch_Id, c.Course_Name from batch b, course c, student s WHERE  c.Course_Id=s.Course_Id AND b.Batch_Id =s.Batch_Id GROUP BY Batch_Id");*/


        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            studentList.add(new StudentTm(
                    rst.getString(3),
                    rst.getString(2),
                    rst.getString(1)
            ));
        }
        return studentList;
    }

    public ObservableList<SubjectTm> getSubjectData() throws SQLException, ClassNotFoundException {
        ObservableList<SubjectTm> subjectList = FXCollections.observableArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT  Subject_Name, Teacher_Name,Time_Period FROM subject");

        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            subjectList.add(new SubjectTm(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getString(2)
            ));
        }
        return subjectList;
    }

    public ObservableList<FeeTm> getFeeData() throws SQLException, ClassNotFoundException {
        ObservableList<FeeTm> feeList = FXCollections.observableArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT Cash,CourseName FROM fee");

        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            feeList.add(new FeeTm(
                    rst.getDouble(1),
                    rst.getString(2)
            ));
        }
        return feeList;
    }

}
