package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/servlet/movies")
public class MovieListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final MovieService movieService;

    public MovieListServlet(SpringTemplateEngine springTemplateEngine, MovieService movieService) {
        this.springTemplateEngine = springTemplateEngine;
        this.movieService = movieService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("movies", movieService.listAll());
        String searchTitle = req.getParameter("searchMovies");
        String searchRating = req.getParameter("searchRating");
        if(searchTitle != null && !searchTitle.isEmpty())
        {
            List<Movie> filteredMovies = movieService.searchMovies(searchTitle);
            context.setVariable("movies", filteredMovies);
        }
        else{
            context.setVariable("movies", movieService.listAll());
        }
        if(searchRating != null && !searchRating.isEmpty())
        {
            List<Movie> filteredMoviesByRating = movieService.searchMoviesByRating(Integer.parseInt(searchRating));
            context.setVariable("movies", filteredMoviesByRating);
        }

        springTemplateEngine.process(
                "listMovies.html",
                context,
                resp.getWriter()
        );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("selectedMovie");
        String tickets = req.getParameter("numTickets");
        resp.sendRedirect("/ticketOrder?title=" + title + "&tickets=" + tickets);

    }
}
