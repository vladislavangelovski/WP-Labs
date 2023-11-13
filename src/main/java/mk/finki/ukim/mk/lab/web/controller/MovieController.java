package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ProductionService productionService;

    public MovieController(MovieService movieService, ProductionService productionService) {
        this.movieService = movieService;
        this.productionService = productionService;
    }

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error, Model model) {
        List<Movie> movies = movieService.listAll();
        model.addAttribute("movies", movies);
        return "listMovies";
    }
    @PostMapping("/add")
    public String saveMovie(@RequestParam String movieTitle,
                            @RequestParam String summary,
                            @RequestParam double rating,
                            @RequestParam Long productionId) {
        this.movieService.saveMovie(movieTitle, summary, rating, productionId);
        return "redirect:/movies";
    }
    @PostMapping("/movies/edit/{movieId}")
    public String editMovie(@PathVariable Long movieId,
                            @RequestParam String movieTitle,
                            @RequestParam String summary,
                            @RequestParam double rating,
                            @RequestParam Long productionId) {
        Movie movie = this.movieService.findById(movieId).get();
        movie.setTitle(movieTitle);
        movie.setSummary(summary);
        movie.setRating(rating);
        movie.setProduction(productionService.findById(productionId).get());
        return "redirect:/movies";
    }
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditMovieForm(@PathVariable Long id, Model model) {
        if(this.movieService.findById(id).isPresent()) {
            Movie movie = this.movieService.findById(id).get();
            List<Production> productions = this.productionService.findAll();
            model.addAttribute("productions", productions);
            model.addAttribute("movie", movie);
            return "add-movie";
        }
        return "redirect:/movies?error=MovieNotFound";
    }
    @GetMapping("/add-form")
    public String getAddMoviePage(Model model) {
        List<Production> productions = this.productionService.findAll();
        model.addAttribute("productions", productions);
        return "add-movie";
    }


}
