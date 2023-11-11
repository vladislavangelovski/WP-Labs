package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    List<Movie> movies;
    public MovieRepository() {
        initializeMovieList();
    }

    private void initializeMovieList() {
        movies = new ArrayList<>();
        movies.add(new Movie("Oppenheimer", "The story of American scientist, J. Robert Oppenheimer, and his role in the development of the atomic bomb.", 8.5));
        movies.add(new Movie("Barbie", "Barbie suffers a crisis that leads her to question her world and her existence.", 7.0));
        movies.add(new Movie("Fight Club", "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.", 8.8));
        movies.add(new Movie("Interstellar", "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.", 8.7));
        movies.add(new Movie("The Shawshank Redemption", "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.", 9.3));
        movies.add(new Movie("The Dark Knight", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", 9.0));
        movies.add(new Movie("Joker", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.", 8.4));
        movies.add(new Movie("Shutter Island", "Teddy Daniels and Chuck Aule, two US marshals, are sent to an asylum on a remote island in order to investigate the disappearance of a patient, where Teddy uncovers a shocking truth about the place.", 8.2));
        movies.add(new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", 8.2));
        movies.add(new Movie("Memento", "A man with short-term memory loss attempts to track down his wife's murderer.", 8.4));

    }

    public List<Movie> findAll() {
        return movies;
    }
    public List<Movie> searchMovies(String text) {
        return movies.stream()
                .filter(movie -> movie.title.contains(text) || movie.summary.contains(text))
                .collect(Collectors.toList());
    }
    public List<Movie> searchMoviesByRating(int rating) {
        return movies.stream()
                .filter(movie -> movie.getRating() >= rating)
                .collect(Collectors.toList());
    }
}
