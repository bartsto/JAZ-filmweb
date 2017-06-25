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
import domain.Comment;
import services.CommentService;

@Path("comment")
public class CommentController {

    private CommentService commentService = new CommentService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Comment comment) {
        commentService.add(comment);
        return Response.ok("Dodano").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Comment result = commentService.get(id);
        if (result == null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @GET
    @Path("/movie/{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieComments(@PathParam("movieId") int movieId) {
        List<Comment> result = commentService.getMovieComments(movieId);
        if (result == null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Comment comment) {
        if (commentService.get(id) == null) {
            return Response.status(404).build();
        }
        comment.setId(id);
        commentService.update(comment);
        return Response.ok("Update succes!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Comment result = commentService.get(id);
        if (result == null) {
            return Response.status(404).build();
        }
        commentService.delete(result);
        return Response.ok().build();
    }

}