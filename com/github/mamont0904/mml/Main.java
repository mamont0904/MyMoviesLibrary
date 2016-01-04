package com.github.mamont0904.mml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private final Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("MyMoviesLibrary.fxml"));
        Parent root = fxmlLoader.load();

        controller.loadMoviesData();

        ((MyMoviesLibrary)(fxmlLoader.getController())).setMainApp(this);

        primaryStage.setTitle("MyMoviesLibrary");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public Controller getController() {
        return controller;
    }
}
