package com.github.mamont0904.mml;


public class Controller {
    private final Model model = new Model();
    private final MovieDataService movieDataService = new MovieDataService();

    public Model getModel() {
        return model;
    }

    public void addMovie() {
        model.getMovies().add(new Movie());
    }

    public void removeMovie(int index) {
        model.getMovies().remove(index);
    }

    public void saveMoviesData() {
        movieDataService.saveMoviesData(model.getMovies());
    }

    public void loadMoviesData() {
        model.setMovies(movieDataService.loadMoviesData());
    }
}
