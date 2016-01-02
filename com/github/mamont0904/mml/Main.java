package com.github.mamont0904.mml;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {

    private final String MOVIES_JSON_FILE = "movies.json";

    private final ObservableList<Movie> movies = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("MyMoviesLibrary.fxml"));
        Parent root = fxmlLoader.load();

        loadMoviesData();

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

    public void saveMoviesData() {
        JSONArray moviesJsonArray = new JSONArray();

        for (Movie movie : movies) {
            JSONObject movieJsonObject = new JSONObject();
            movieJsonObject.put("title", movie.getTitle());
            movieJsonObject.put("year", movie.getYear());
            movieJsonObject.put("country", movie.getCountry());
            movieJsonObject.put("director", movie.getDirector());
            movieJsonObject.put("isWatched", movie.getIsWatched());
            movieJsonObject.put("description", movie.getDescription());
            moviesJsonArray.add(movieJsonObject);

        }

        try (FileWriter file = new FileWriter(getClass().getResource(MOVIES_JSON_FILE).getFile())) {
            file.write(moviesJsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMoviesData() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(getClass().getResource(MOVIES_JSON_FILE).getFile()));

            for (Object o : a)
            {
                JSONObject moviesJsonObject = (JSONObject) o;

                Movie movie = new Movie();
                movie.setTitle((String) moviesJsonObject.get("title"));
                movie.setCountry((String) moviesJsonObject.get("country"));
                movie.setDescription((String) moviesJsonObject.get("description"));
                movie.setDirector((String) moviesJsonObject.get("director"));
                movie.setIsWatched((Boolean) moviesJsonObject.get("isWatched"));
                movie.setYear(((Long) moviesJsonObject.get("year")).intValue());
                movies.add(movie);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
