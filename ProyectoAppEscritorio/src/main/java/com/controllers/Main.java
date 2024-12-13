package com.controllers; //Modificar al package correcto

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vistas/Main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> controller.combKeys(event));  
        scene.getStylesheets().add(getClass().getResource("/style.css").toString());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("HomeTransfer");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("./images/logo.png")));
        primaryStage.show();
    }

}
