package domain;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Movie {

    private int id;
    private String title;
    private String productionDate;
    private String info;
    private Double avRating;
    @JsonIgnore
    private List<Integer> rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getAvRating() {
        return avRating;
    }

    public void setAvRating(Double avRating) {
        this.avRating = avRating;
    }

    public List<Integer> getRating() {
        return rating;
    }

    public void setRating(List<Integer> rating) {
        this.rating = rating;
    }

     
    
}
