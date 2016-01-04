package com.github.mamont0904.mml;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class MovieDataService {

    private String MOVIES_JSON_FILE = "movies.json";
    private final ExceptionDialog exceptionDialog = new ExceptionDialog();


    public void saveMoviesData(ObservableList<Movie> movies) {
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
        } catch (Exception e) {
            exceptionDialog.setException(e);
            exceptionDialog.showAndWait();
        }
    }

    public ObservableList<Movie> loadMoviesData() {
        ObservableList<Movie> movies = FXCollections.observableArrayList();

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
            exceptionDialog.setException(e);
            exceptionDialog.showAndWait();
        } catch (ParseException e) {
            exceptionDialog.setException(e);
            exceptionDialog.showAndWait();
        } catch (IOException e) {
            exceptionDialog.setException(e);
            exceptionDialog.showAndWait();
        }

        // Проверка на вывод диалога о возникновении исключения
        //MOVIES_JSON_FILE = "nonexistent_file.json";

        return movies;
    }
}
