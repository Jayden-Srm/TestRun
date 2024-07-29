package App;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Assess {
    private final StringProperty courseName;
    private final StringProperty assignmentDate;
    private final StringProperty testDate;
    private final StringProperty status;

    public Assess() {
        this.courseName = new SimpleStringProperty();
        this.assignmentDate = new SimpleStringProperty();
        this.testDate = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
    }

    public String getCourseName() {
        return courseName.get();
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public String getAssignmentDate() {
        return assignmentDate.get();
    }

    public void setAssignmentDate(String assignmentDate) {
        this.assignmentDate.set(assignmentDate);
    }

    public StringProperty assignmentDateProperty() {
        return assignmentDate;
    }

    public String getTestDate() {
        return testDate.get();
    }

    public void setTestDate(String testDate) {
        this.testDate.set(testDate);
    }

    public StringProperty testDateProperty() {
        return testDate;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getId() {
       
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}
