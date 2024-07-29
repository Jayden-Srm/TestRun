package App;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage primaryStage;
    private BorderPane mainLayout;
    
    

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Assessment Checker!");
        showMainVeiw();
        
    }

private void showMainVeiw() throws IOException { 
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(App.class.getResource("/AppView/AppView.fxml"));
    mainLayout = loader.load();
    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
} 



    
    public static void main(String[] args) {
        launch(args);
    }
}