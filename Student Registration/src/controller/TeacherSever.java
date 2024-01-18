package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.Teacher;
import view.tm.TeacherTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherSever {

    public boolean saveTeacher(Teacher teacher) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO teacher VALUES(?,?,?,?,?,?)");

        stm.setObject(1, teacher.getTeacherId());
        stm.setObject(2, teacher.getTeacherName());
        stm.setObject(3, teacher.getTeacherAddress());
        stm.setObject(4, teacher.getTeacherEmail());
        stm.setObject(5, teacher.getGender());
        stm.setObject(6, teacher.getTeacherContact());

        return stm.executeUpdate() > 0;
    }

    public ObservableList<TeacherTm> getTeacherList() throws SQLException, ClassNotFoundException {
        ObservableList<TeacherTm> teachers = FXCollections.observableArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM teacher");
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            teachers.add(new TeacherTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(6),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return teachers;
    }

    public boolean deleteTeacher(String teacherId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().
                prepareStatement("DELETE FROM Teacher WHERE  Teacher_Id = '" + teacherId + "'").
                executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateTeacher(TeacherTm teacherTm) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE Teacher SET Teacher_Name=?, Teacher_Address=?, Email=?, Gender=?, Contact=? WHERE Teacher_Id=?");
        stm.setObject(1, teacherTm.getTeacherName());
        stm.setObject(2, teacherTm.getTeacherAddress());
        stm.setObject(3, teacherTm.getTeacherEmail());
        stm.setObject(4, teacherTm.getGender());
        stm.setObject(5, teacherTm.getTeacherContact());
        stm.setObject(6, teacherTm.getTeacherId());

        return stm.executeUpdate() > 0;

    }

    //setCmb

    public List<String> getAllTeacherId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM teacher ").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
            return ids;

    }

    //pass id text
    public Teacher passTeacher(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM teacher WHERE Teacher_Id = '" + id + "'").executeQuery();
        if (rst.next()) {
            return new Teacher(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        }else {
            return null;
        }
    }

    public String getTeacherId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM teacher ORDER BY Teacher_Id DESC LIMIT 1").executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("_")[1]);
            //tempId = tempId + 1;
            tempId += 1;

            if (tempId < 10) {
                return "TCH_00" + tempId;

            } else if (tempId < 100) {
                return "TCH_0" + tempId;

            } else {
                return "TCH" + tempId;
            }

        } else {
            return "TCH_001";
        }
    }
}