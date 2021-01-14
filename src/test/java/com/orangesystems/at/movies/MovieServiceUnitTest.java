package com.orangesystems.at.movies;


import com.orangesystems.at.movies.models.Movie;
import com.orangesystems.at.movies.models.paging.Paged;
import com.orangesystems.at.movies.models.paging.Paging;
import com.orangesystems.at.movies.services.MovieService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;


@SpringBootTest
public class MovieServiceUnitTest {

    @Test
    public void findAllTest() throws Exception {
        Pageable pageable = PageRequest.of(1, 20);

        Movie movie1 = new Movie(1, "", "", "", 1.0, 1.0, 1, new Date());
        Movie movie2 = new Movie(2, "", "", "", 1.0, 1.0, 1, new Date());

        final Page<Movie> movies = new PageImpl<>(Arrays.asList(movie1, movie2), pageable, 2);

        Paged<Movie> pagedMovies = new Paged<Movie>(movies, Paging.of(1, 1, 20));

        MovieService service = mock(MovieService.class);
        when(service.findAll(pageable)).thenReturn(pagedMovies);

        Assert.assertEquals(service.findAll(pageable), pagedMovies);
    }

    @Test
    public void findByIdTest() throws Exception {
        Movie movie = new Movie(1, "", "", "", 1.0, 1.0, 1, new Date());
        MovieService service = mock(MovieService.class);
        when(service.findById(1)).thenReturn(movie);

        Assert.assertEquals(service.findById(1), movie);
    }
}
