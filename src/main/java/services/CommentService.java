package services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import domain.Comment;

public class CommentService {

    private static List<Comment> comments = new ArrayList<Comment>();
    private static int currentId = 1;

    public List<Comment> getAll() {
        return comments;
    }

    public Comment get(int id) {
        for (Comment m : comments) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public List<Comment> getMovieComments(int movieId) {
        return comments.
                stream().
                filter(comment -> comment.getMovieId() == movieId).
                collect(Collectors.toList());
    }

    public void add(Comment comment) {
        comment.setId(currentId++);
        comments.add(comment);
    }

    public void delete(Comment comment) {
        comments.remove(comment);
    }

    public void update(Comment comment) {
        for (Comment m : comments) {
            if (m.getId() == comment.getId()) {
                m.setContent(comment.getContent());
            }
        }
    }

}
