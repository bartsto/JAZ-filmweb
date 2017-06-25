package rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import domain.Movie;
import services.MovieService;

@Path("movie")
public class MovieController {

    private MovieService movieService = new MovieService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Movie movie) {
        movieService.add(movie);
        return Response.ok("Dodano").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Movie result = movieService.get(id);
        if (result == null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @GET
    @Path("/{id}/rate/{rate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRating(@PathParam("id") int id, @PathParam("rate") int rate) {
        Movie result = movieService.get(id);
        if (result == null) {
            return Response.status(404).build();
        } else {
            movieService.addRating(id, rate);
            return Response.ok("Rate added").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Movie movie) {
        if (movieService.get(id) == null) {
            return Response.status(404).build();
        }
        movie.setId(id);
        movieService.update(movie);
        return Response.ok("Update succes!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Movie result = movieService.get(id);
        if (result == null) {
            return Response.status(404).build();
        }
        movieService.delete(result);
        return Response.ok().build();
    }

}