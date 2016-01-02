package com.github.mamont0904.mml;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;

public class Main extends Application {

    private final ObservableList<Movie> movies = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("MyMoviesLibrary.fxml"));
        Parent root = fxmlLoader.load();

        /*
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "foo");
        jsonObject.put("num", new Integer(100));
        jsonObject.put("balance", new Double(1000.21));
        jsonObject.put("is_vip", new Boolean(true));

        System.out.print(jsonObject);
        */

        // Only for test purposes
        Movie movie = new Movie();
        movie.setIsWatched(true);
        movie.setCountry("США");
        movie.setYear(0);
        movie.setDirector("Нил Бёргер");
        movie.setTitle("Limitless");
        movie.setDescription("Фильм очень хороший!");
        movies.add(movie);


        ((Controller)(fxmlLoader.getController())).setMainApp(this);

        primaryStage.setTitle("MyMoviesLibrary");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Movie> getMovies() {
        return movies;
    }
}
