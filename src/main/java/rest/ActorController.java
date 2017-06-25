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
import domain.Actor;
import domain.Movie;
import services.ActorService;

@Path("actor")
public class ActorController {

    private ActorService actorService = new ActorService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getAll() {
        return actorService.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Actor actor) {
        actorService.add(actor);
        return Response.ok("Dodano").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Actor result = actorService.get(id);
        if (result == null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @GET
    @Path("/{actorId}/movie")    //filmy danego aktora
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesOfActor(@PathParam("actorId") int actorId) {
        List<Movie> result = actorService.getMoviesOfActor(actorId);
        if (result == null) {
            return Response.ok("brak informacji").build();
        }
        return Response.ok(result).build();
    }

    @GET
    @Path("/movie/{movieId}")    //aktorzy danego filmu
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorsOfMovie(@PathParam("movieId") int movieId) {
        List<Actor> result = actorService.getActorsOfMovie(movieId);
        if (result == null) {
            return Response.ok("brak danych").build();
        }
        return Response.ok(result).build();
    }

    @GET
    @Path("/append/{actorId}/movie/{movieId}")    //przypisanie filmu do aktora
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovieToActor(@PathParam("actorId") int actorId, @PathParam("movieId") int movieId) {
        if (!actorService.addMovieToActor(actorId, movieId)) {
            return Response.status(404).build();
        }
        return Response.ok("DONE").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Actor actor) {
        if (actorService.get(id) == null) {
            return Response.status(404).build();
        }
        actor.setId(id);
        actorService.update(actor);
        return Response.ok("Update succes!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Actor result = actorService.get(id);
        if (result == null) {
            return Response.status(404).build();
        }
        actorService.delete(result);
        return Response.ok().build();
    }

}