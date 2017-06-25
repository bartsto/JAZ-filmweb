package domain;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Actor {

    private int id;
    private String name;
    private String dateOfBirth;
    @JsonIgnore
    private List<Integer> moviesIds;

    public List<Integer> getMoviesIds() {
        return moviesIds;
    }

    public void setMoviesIds(List<Integer> moviesId) {
        this.moviesIds = moviesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
