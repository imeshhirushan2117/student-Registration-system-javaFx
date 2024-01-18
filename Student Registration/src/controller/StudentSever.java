package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import module.Registration;
import module.Student;
import view.tm.StudentTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentSever {

    public boolean saveStudent(Student student)  {

        Connection con = null;
        try {
            con  = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.prepareStatement("INSERT INTO Student VALUES (?,?,?,?,?,?,?,?,?)");

            stm.setObject(1,student.getStudentId());
            stm.setObject(2,student.getStudentName());
            stm.setObject(3,student.getStudentCourseId());
            stm.setObject(4,student.getStudentAddress());
            stm.setObject(5,student.getStudentBatchId());
            stm.setObject(6,student.getGender());
            stm.setObject(7,student.getStudentContact());
            stm.setObject(8,student.getStudentEmail());
            stm.setObject(9,student.getAdmissionFee());

            if (stm.executeUpdate() > 0){
                if (saveRegister(student.getRegistration())){
                    con.commit();
                    return true;
                }else{
                    con.rollback();
                    return false;
                }
            }else{
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public boolean saveRegister(List<Registration> registration) throws SQLException, ClassNotFoundException {

        for (Registration temp : registration){
            PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Registration VALUES (?,?,?)");
            stm.setObject(1,temp.getStudentId());
            stm.setObject(2,temp.getBatchId());
            stm.setObject(3,temp.getDate());

            return stm.executeUpdate() > 0 ;
        }
        return true;
    }

    public ObservableList<StudentTm> getStudentList() throws SQLException, ClassNotFoundException {
        ObservableList<StudentTm> student = FXCollections.observableArrayList();
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM student").executeQuery();

        while (rst.next()){
            student.add(new StudentTm(
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
        return student;
    }

    public boolean deleteStudent(String studentId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().
                prepareStatement("DELETE FROM student WHERE  Student_Id = '" + studentId + "'").executeUpdate() > 0) {
            return true;
        }else{
            return false;
        }
    }

    public boolean updateStudent(StudentTm studentTm) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement("UPDATE student SET Student_Name =?,Course_Id=?, Address=?,  Batch_Id=?, Gender=?, Contact=?, Email=?, Admission_fee=? WHERE Student_Id=? ");

        stm.setObject(1,studentTm.getStudentName());
        stm.setObject(2,studentTm.getStudentCourseId());
        stm.setObject(3,studentTm.getStudentAddress());
        stm.setObject(4,studentTm.getStudentBatchId());
        stm.setObject(5,studentTm.getGender());
        stm.setObject(6,studentTm.getStudentContact());
        stm.setObject(7,studentTm.getStudentEmail());
        stm.setObject(8,studentTm.getAdmissionFee());

        stm.setObject(9,studentTm.getStudentId());


      return   stm.executeUpdate() > 0 ;
    }

    public String getStudentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM student ORDER BY Student_Id DESC LIMIT 1").executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("_")[1]);
            tempId = tempId + 1;

            if (tempId < 10) {
                return "STU_00" + tempId;

            } else if (tempId < 100) {
                return "STU_0" + tempId;

            } else {
                return "STU" + tempId;
            }

        } else {
            return "STU_001";
        }
    }

    //PassIdCmb
   /* public List<String> getAllStudentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT *FROM student").executeQuery();
        List<String> id = new ArrayList<>();
        while (rst.next()) {
            id.add(rst.getString(1));
        }
        return id;
    }*/

    //setIdTxt
    /*public Student passStudent(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM student WHERE  Student_Id = '" + id + "'").executeQuery();
        if (rst.next()){
            return new Student(
                   rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
                    //rst.getString(9)
            );
        }else{
            return null;
        }
    }*/
}