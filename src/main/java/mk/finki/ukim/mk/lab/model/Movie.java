package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Movie {
    public String title;
    public String summary;
    public double rating;
    private Long id;
    private Production production;

    public Movie(String title, String summary, double rating, Production production) {
        this.id = (long) (Math.random()*1000);
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
    }
}
