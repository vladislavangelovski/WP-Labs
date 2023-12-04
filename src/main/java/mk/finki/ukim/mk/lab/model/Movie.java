package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String title;
    public String summary;
    public double rating;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Production production;

    public Movie(String title, String summary, double rating, Production production) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
    }

    public Movie() {
    }
}
