package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.Course;
import view.tm.CourseTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseSever {

    public boolean saveCourse(Course course) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("INSERT INTO course VALUES (?,?,?)");
        stm.setObject(1,course.getCourseId());
        stm.setObject(2,course.getCourseName());
        stm.setObject(3,course.getDuration());

        return stm.executeUpdate() > 0 ;

    }

    //PassIdCmb
    public List<String>getAllCourseIds() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM course").executeQuery();
        List<String>ids = new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }

    //pass id txt
    public Course passCourse(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM course WHERE Course_Id='" + id + "'").executeQuery();
        if(rst.next()){
            return new Course(
                   rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }else{
            return null;
        }
    }

    public ObservableList<CourseTm> getCourseList() throws SQLException, ClassNotFoundException {

        ObservableList<CourseTm> course = FXCollections.observableArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM course");
        ResultSet rst = stm.executeQuery();

        while (rst.next()){
            course.add(new CourseTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        return course;
    }

    public boolean deleteCourse(String courseId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().
                prepareStatement("DELETE FROM course WHERE Course_Id = '" + courseId + "'").
                executeUpdate() > 0 ) {
            return true;
        }else{
            return false;
        }
    }

    public boolean updateCourse(CourseTm courseTm) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE course SET  Course_Name =?, Duration=? WHERE  Course_Id=?");
        stm.setObject(1,courseTm.getCourseName());
        stm.setObject(2,courseTm.getDuration());
        stm.setObject(3,courseTm.getCourseId());

        return stm.executeUpdate() > 0;
    }

    public String getCoursesId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM  course ORDER BY Course_Id DESC LIMIT 1").executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("_")[1]);
            //tempId = tempId + 1;
            tempId += 1;

            if (tempId < 10) {
                return "COS_00" + tempId;

            } else if (tempId < 100) {
                return "COS_0" + tempId;

            } else {
                return "COS" + tempId;
            }

        } else {
            return "COS_001";
        }
    }

}

