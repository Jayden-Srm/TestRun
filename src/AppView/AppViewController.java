package AppView;

import App.Assess;
import App.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppViewController {

    @FXML
    private TextField courseNameField;

    @FXML
    private DatePicker assignmentDatePicker;

    @FXML
    private DatePicker testDatePicker;

    @FXML
    private TableView<Assess> courseTable;

    @FXML
    private TableColumn<Assess, String> courseNameColumn;

    @FXML
    private TableColumn<Assess, String> assignmentDateColumn;

    @FXML
    private TableColumn<Assess, String> testDateColumn;

    @FXML
    private TableColumn<Assess, String> statusColumn;

    @FXML
    private Button addCourseButton;

    @FXML
    private Button updateCourseButton;

    @FXML
    private Button markCompletedButton;

    @FXML
    private Button deleteCourseButton;

    private ObservableList<Assess> courseList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        assignmentDateColumn.setCellValueFactory(cellData -> cellData.getValue().assignmentDateProperty());
        testDateColumn.setCellValueFactory(cellData -> cellData.getValue().testDateProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        loadCourses();
    }

    private void loadCourses() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM courses";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Assess course = new Assess();
                course.setCourseName(resultSet.getString("course_name"));
                course.setAssignmentDate(resultSet.getDate("assignment_date").toString());
                course.setTestDate(resultSet.getDate("test_date").toString());
                course.setStatus(resultSet.getString("status"));
                courseList.add(course);
            }
            courseTable.setItems(courseList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddCourse() {
        // Add assessment
        String courseName = courseNameField.getText();
        String assignmentDate = assignmentDatePicker.getValue().toString();
        String testDate = testDatePicker.getValue().toString();
        String status = "Pending";  

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "INSERT INTO courses (course_name, assignment_date, test_date, status) VALUES ('"
                    + courseName + "', '" + assignmentDate + "', '" + testDate + "', '" + status + "')";
            statement.executeUpdate(query);

            // Add assessment to list
            Assess course = new Assess();
            course.setCourseName(courseName);
            course.setAssignmentDate(assignmentDate);
            course.setTestDate(testDate);
            course.setStatus(status);
            courseList.add(course);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateCourse() {
        // Update assessment
        Assess selectedCourse = courseTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            selectedCourse.setCourseName(courseNameField.getText());
            selectedCourse.setAssignmentDate(assignmentDatePicker.getValue().toString());
            selectedCourse.setTestDate(testDatePicker.getValue().toString());

            try (Connection connection = DatabaseConnection.getConnection();
                 Statement statement = connection.createStatement()) {

                String query = "UPDATE courses SET course_name='" + selectedCourse.getCourseName() + 
                               "', assignment_date='" + selectedCourse.getAssignmentDate() + 
                               "', test_date='" + selectedCourse.getTestDate() + 
                               "' WHERE course_id=" +  selectedCourse.getId();
                statement.executeUpdate(query);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleMarkCompleted() {
        // Mark assessment completed
        Assess selectedCourse = courseTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            selectedCourse.setStatus("Completed");

            try (Connection connection = DatabaseConnection.getConnection();
                 Statement statement = connection.createStatement()) {

                String query = "UPDATE courses SET status='Completed' WHERE course_id=" + selectedCourse.getId();
                statement.executeUpdate(query);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleDeleteCourse() {
        // Delete assessment
        Assess selectedCourse = courseTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            courseList.remove(selectedCourse);

            try (Connection connection = DatabaseConnection.getConnection();
                 Statement statement = connection.createStatement()) {

                String query = "DELETE FROM courses WHERE course_id=" +  selectedCourse.getId();
                statement.executeUpdate(query);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}