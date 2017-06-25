package services;

import java.util.ArrayList;
import java.util.List;
import domain.Actor;
import domain.Movie;

public class ActorService {

    private static List<Actor> actors = new ArrayList<Actor>();
    private static int currentId = 1;

    private MovieService movieService = new MovieService();

    public List<Actor> getAll() {
        return actors;
    }

    public Actor get(int id) {
        for (Actor m : actors) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    //filmy danego aktora
    public List<Movie> getMoviesOfActor(int actorId) {
        List<Movie> movies = new ArrayList<Movie>();
        actors.
                stream().
                filter(actor -> actor.getId() == actorId).
                map(actor -> actor.getMoviesIds()).
                flatMap(c -> c.stream()).
                forEach(movieId -> {
                    movies.add(movieService.get(movieId));

                });
        return movies;
    }

    //aktorzy danego filmu
    public List<Actor> getActorsOfMovie(int movieId) {
        List<Actor> result = new ArrayList<Actor>();
        actors.
                stream().
                forEach(actor -> {
                    if (actor.getMoviesIds().contains(movieId)) {
                        result.add(actor);
                    }
                });
        return result;
    }

    public void add(Actor actor) {
        actor.setId(currentId++);
        actor.setMoviesIds(new ArrayList<Integer>());
        actors.add(actor);
    }

    public void deleteMoviesFromActors(int movieId) {
        for (Actor ac : actors) {
            if (ac.getMoviesIds().contains(movieId)) {
                ac.getMoviesIds().remove(ac.getMoviesIds().indexOf(movieId));
            }
        }
    }

    public boolean addMovieToActor(int actorId, int movieId) {
        for (Actor ac : actors) {
            if (ac.getId() == actorId) {
                ac.getMoviesIds().add(movieId);
                return true;
            }
        }
        return true;
    }

    public void delete(Actor actor) {
        actors.remove(actor);
    }

    public void update(Actor actor) {
        for (Actor m : actors) {
            if (m.getId() == actor.getId()) {
                m.setName(actor.getName());
                m.setDateOfBirth(actor.getDateOfBirth());
            }
        }
    }

}