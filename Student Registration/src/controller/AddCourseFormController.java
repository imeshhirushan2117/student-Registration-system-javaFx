package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import module.Course;
import util.Validation;
import view.tm.CourseTm;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddCourseFormController {
    //public JFXTextField txtCourseId;
    public JFXTextField txtCourseName;
    public JFXTextField txtCourseDuration;
    public AnchorPane anchorAddCourse;
    public TableView<CourseTm> tblCourse;

    public TableColumn colCourseId;
    public TableColumn colCourseName;
    public TableColumn colCourseDuration;
    public Label lblUpdateCourseId;
    public Label lblCourseId;
    public JFXButton btnAdd;

    //=================================
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap();
    Pattern courseName = Pattern.compile("^[A-Z]{1}[a-z]{2,} (Course)|[A-Z]{2,} (Course)$");
    Pattern timePeriod = Pattern.compile("^[A-Z]{1}[a-z]{2,} [A-Z][a-z]{2,}$");

    public void initialize() throws SQLException, ClassNotFoundException {
        setCourseTable();
        setCourseId();
        storeValidation();
    }

    private void storeValidation(){
        map.put(txtCourseName,courseName);
        map.put(txtCourseDuration,timePeriod);
    }

    public void btnClearDataOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void  clearField(){
        lblUpdateCourseId.setText("");
        txtCourseName.clear();
        txtCourseDuration.clear();

        txtCourseName.requestFocus();
    }

    public void btnAddCourseOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Course c1 = new Course(
                lblCourseId.getText(),
                txtCourseName.getText(),
                txtCourseDuration.getText()

        );
        if (new CourseSever().saveCourse(c1)){
           // new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
            setCourseId();
            setCourseTable();
            clearField();
            new AddNotificationForm().
                    sceneNotifications("Saved Course Data","Successfully Saved Course Data",0,0);

        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
       // Stage stage = (Stage) anchorAddCourse.getScene().getWindow(); stage.close();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CourseTm course = tblCourse.getSelectionModel().getSelectedItem();
        String courseId = course.getCourseId();

        if (new CourseSever().deleteCourse(courseId)){
            //new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
            new AddNotificationForm().
                    sceneNotifications("Delete Course Data","Successfully Deleted Course Data",0,2);
            setCourseId();
            setCourseTable();

        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
        clearField();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        CourseTm course = new CourseTm(
                lblUpdateCourseId.getText(),
                txtCourseName.getText(),
                txtCourseDuration.getText()
        );

        if (new CourseSever().updateCourse(course)){
            //new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
            new AddNotificationForm().
                    sceneNotifications("Updated Course Data ","Successfully Update Courses Data",0,6);
            setCourseTable();

        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
        clearField();
    }



    private void setCourseTable() throws SQLException, ClassNotFoundException {

        ObservableList<CourseTm> courseList = new CourseSever().getCourseList();
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCourseDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        tblCourse.setItems(courseList);
    }

    public void setCourseId(){
        try {
            lblCourseId.setText(new CourseSever().getCoursesId());
        } catch (SQLException e) {
           e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clickMouseOnAction(MouseEvent mouseEvent) {
        CourseTm course = null;
        course = tblCourse.getSelectionModel().getSelectedItem();
        lblUpdateCourseId.setText(course.getCourseId());
        txtCourseName.setText(course.getCourseName());
        txtCourseDuration.setText(course.getDuration());

    }

    public void btnCourseRelease(KeyEvent keyEvent) {
        btnAdd.setDisable(true);
        Object response = Validation.validate(map, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TextField error = (TextField) response;
            error.requestFocus();
        } else if (response instanceof Boolean) {
        }
    }
}
