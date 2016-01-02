package com.github.mamont0904.mml;

import javafx.beans.property.*;

public class Movie {
    private BooleanProperty isWatched = new SimpleBooleanProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty director = new SimpleStringProperty();
    private IntegerProperty year = new SimpleIntegerProperty();
    private StringProperty country = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();

    public Movie() {
        isWatched.setValue(false);
        title.setValue("");
        director.setValue("");
        year.setValue(0);
        country.setValue("");
        description.setValue("");
    }

    public boolean getIsWatched() {
        return isWatched.get();
    }

    public BooleanProperty isWatchedProperty() {
        return isWatched;
    }

    public void setIsWatched(boolean isWatched) {
        this.isWatched.set(isWatched);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDirector() {
        return director.get();
    }

    public StringProperty directorProperty() {
        return director;
    }

    public void setDirector(String director) {
        this.director.set(director);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }
}
