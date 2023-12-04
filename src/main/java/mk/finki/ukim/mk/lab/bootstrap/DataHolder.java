package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Movie> movies = null;
    public static List<Production> productions = null;

    @PostConstruct
    public void init() {
        System.out.println("Initializing data in DataHolder...");
        movies = new ArrayList<>();
        productions = new ArrayList<>();


        productions.add(new Production("Warner Bros.", "United States", "Burbank, California"));
        productions.add(new Production("Paramount Pictures", "United States", "Hollywood, Los Angeles, California"));
        productions.add(new Production("Universal Pictures", "United States", "Universal City, California"));
        productions.add(new Production("20th Century Studios", "United States", " Los Angeles, California"));
        productions.add(new Production("Disney", "United States", " Los Angeles, California"));

        movies.add(new Movie("Oppenheimer", "The story of American scientist, J. Robert Oppenheimer, and his role in the development of the atomic bomb.", 8.5, productions.get(0)));
        movies.add(new Movie("Barbie", "Barbie suffers a crisis that leads her to question her world and her existence.", 7.0, productions.get(3)));
        movies.add(new Movie("Fight Club", "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.", 8.8, productions.get(4)));
        movies.add(new Movie("Interstellar", "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.", 8.7, productions.get(1)));
        movies.add(new Movie("The Shawshank Redemption", "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.", 9.3, productions.get(3)));
        movies.add(new Movie("The Dark Knight", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", 9.0, productions.get(2)));
        movies.add(new Movie("Joker", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.", 8.4, productions.get(3)));
        movies.add(new Movie("Shutter Island", "Teddy Daniels and Chuck Aule, two US marshals, are sent to an asylum on a remote island in order to investigate the disappearance of a patient, where Teddy uncovers a shocking truth about the place.", 8.2, productions.get(4)));
        movies.add(new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", 8.2, productions.get(4)));
        movies.add(new Movie("Memento", "A man with short-term memory loss attempts to track down his wife's murderer.", 8.4,productions.get(0)));
    }
}
