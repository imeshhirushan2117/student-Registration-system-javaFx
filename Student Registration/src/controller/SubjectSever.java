package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.Subject;
import view.tm.ExamTm;
import view.tm.SubjectTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectSever {

    public ObservableList<SubjectTm> getSubjectList() throws SQLException, ClassNotFoundException {

        ObservableList<SubjectTm> subjects = FXCollections.observableArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM subject");
        ResultSet rst = stm.executeQuery();

        while (rst.next()){
            subjects.add(new SubjectTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }
        return subjects;
    }

    public boolean saveSubject(Subject subject) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("INSERT INTO subject VALUES (?,?,?,?,?,?,?,?,?)");
        stm.setObject(1,subject.getSubjectId());
        stm.setObject(2,subject.getSubjectName());
        stm.setObject(3,subject.getTeacherId());
        stm.setObject(4,subject.getExamId());
        stm.setObject(5,subject.getTimePeriod());
        stm.setObject(6,subject.getCourseId());
        stm.setObject(7,subject.getTeacherName());
        stm.setObject(8,subject.getExamName());
        stm.setObject(9,subject.getCourseName());

        return  stm.executeUpdate() > 0;
    }

    public boolean deleteSubject(String subjectId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().
                prepareStatement("DELETE FROM subject WHERE Subject_Id = '" + subjectId + "'").
                executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateSubject(SubjectTm subjectTm) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE subject SET Subject_Name =?, Teacher_Id =?,  Exam_Id=?, Time_Period=?, Course_Id=?,  Teacher_Name =?, Exam_Name =?, CourseName=? WHERE Subject_Id =?");
        stm.setObject(1,subjectTm.getSubjectName());
        stm.setObject(2,subjectTm.getTeacherId());
        stm.setObject(3,subjectTm.getExamId());
        stm.setObject(4,subjectTm.getTimePeriod());
        stm.setObject(5,subjectTm.getCourseId());
        stm.setObject(6,subjectTm.getTeacherName());
        stm.setObject(7,subjectTm.getExamName());
        stm.setObject(8,subjectTm.getCourseName());
        stm.setObject(9,subjectTm.getSubjectId());

        return stm.executeUpdate() > 0;

    }

    public String getSubjectId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM subject ORDER BY  Subject_Id DESC LIMIT 1").executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("_")[1]);
            //tempId = tempId + 1;
            tempId += 1;

            if (tempId < 10) {
                return "SUB_00" + tempId;

            } else if (tempId < 100) {
                return "SUB_0" + tempId;

            } else {
                return "SUB" + tempId;
            }

        } else {
            return "SUB_001";
        }
    }


}
