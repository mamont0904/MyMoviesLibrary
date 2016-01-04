package com.github.mamont0904.mml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class MyMoviesLibrary implements Initializable{
    @FXML
    private TableView<Movie> movieTableView;
    @FXML
    private TableColumn<Movie, Boolean> isWatchedTableColumn;
    @FXML
    private TableColumn<Movie, String> titleTableColumn;
    @FXML
    private TableColumn<Movie, Integer> yearTableColumn;
    @FXML
    private TableColumn<Movie, String> directorTableColumn;
    @FXML
    private TableColumn<Movie, String> countryTableColumn;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button addMovieButton;

    @FXML
    private Button deleteMovieButton;

    @FXML
    private Button saveChangesButton;

    private Main main;

    public MyMoviesLibrary() {
    }

    public void setMainApp(Main main) {
        this.main = main;

        movieTableView.setItems(main.getMovies());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isWatchedTableColumn.setCellValueFactory(cellData -> cellData.getValue().isWatchedProperty());
        isWatchedTableColumn.setCellFactory(ts -> new CheckBoxTableCell<Movie, Boolean>());
        titleTableColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        titleTableColumn.setCellFactory(ts -> new TextFieldTableCell<Movie, String>(new DefaultStringConverter()));
        yearTableColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearTableColumn.setCellFactory(ts -> new TextFieldTableCell<Movie, Integer>(new IntegerStringConverter()));
        directorTableColumn.setCellValueFactory(cellData -> cellData.getValue().directorProperty());
        directorTableColumn.setCellFactory(ts -> new TextFieldTableCell<Movie, String>(new DefaultStringConverter()));
        countryTableColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        countryTableColumn.setCellFactory(ts -> new TextFieldTableCell<Movie, String>(new DefaultStringConverter()));

        movieTableView.setRowFactory(tv -> {
            TableRow<Movie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Movie rowData = row.getItem();
                    descriptionTextArea.setText(rowData.getDescription());
                }
            });
            return row;
        });

        descriptionTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equals(newValue))
                movieTableView.getSelectionModel().getSelectedItem().descriptionProperty().setValue(newValue);
        });


        addMovieButton.setOnMouseClicked(event -> {
            main.getMovies().add(new Movie());
        });

        deleteMovieButton.setOnMouseClicked(event -> {
            main.getMovies().remove(movieTableView.getSelectionModel().getSelectedIndex());
        });

        saveChangesButton.setOnMouseClicked(event -> {
            main.saveMoviesData();
        });


    }
}
