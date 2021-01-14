package com.orangesystems.at.movies.controllers;

import com.orangesystems.at.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/popular")
    public String popular(@PageableDefault(page = 1, size = 20)
                          @SortDefault.SortDefaults({
                                  @SortDefault(sort = "vote_count", direction = Sort.Direction.DESC),
                                  @SortDefault(sort = "release_date", direction = Sort.Direction.DESC)
                          }) Pageable pageable, Model model) {
        model.addAttribute("movies", movieService.findAll(pageable));
        return "popularMovies";
    }

    @GetMapping("/top-rated")
    public String topRated(@PageableDefault(page = 1, size = 20)
                           @SortDefault(sort = "vote_average", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("movies", movieService.findAll(pageable));
        return "topRatedMovies";
    }

    @GetMapping("/{id}")
    public String movieById(@PathVariable Integer id, Model model) {
        model.addAttribute("movie", movieService.findById(id));
        return "detailed";
    }
}
