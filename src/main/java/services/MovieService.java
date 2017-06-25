package services;

import java.util.ArrayList;
import java.util.List;
import domain.Movie;

public class MovieService {

    private static List<Movie> movies = new ArrayList<Movie>();
    private static int currentId = 1;

    public List<Movie> getAll() {
        return movies;
    }

    public Movie get(int id) {
        for (Movie m : movies) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public void add(Movie movie) {
        movie.setId(currentId++);
        movie.setRating(new ArrayList<Integer>());
        movies.add(movie);
    }

    public void addRating(int movieId, int rate) {
        Movie mov = get(movieId);
        mov.getRating().add(rate);
        int suma = 0;
        for (Integer i : mov.getRating()) {
            suma = suma + i;
        }
        mov.setAvRating(Math.round((suma / (double) mov.getRating().size()) * 100) / 100.0);
    }

    public void delete(Movie movie) {
        movies.remove(movie);
    }

    public void update(Movie movie) {
        for (Movie m : movies) {
            if (m.getId() == movie.getId()) {
                m.setInfo(movie.getInfo());
                m.setProductionDate(movie.getProductionDate());
                m.setTitle(movie.getTitle());
            }
        }
    }

}