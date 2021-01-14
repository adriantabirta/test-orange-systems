package com.orangesystems.at.movies.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orangesystems.at.movies.models.Movie;
;
import com.orangesystems.at.movies.models.ResponseWrapper;
import com.orangesystems.at.movies.models.paging.Paged;
import com.orangesystems.at.movies.models.paging.Paging;
import lombok.AllArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class MovieService {

    private static String apiKey = "dad8a59d86a2793dda93aa485f7339c1";

    private ObjectMapper mapper;

    private InputStream get(String url) {

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(new HttpGet(url));

            if (response.getStatusLine().getStatusCode() == 200) {
                return response.getEntity().getContent();
            } else {
                return null;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with movies api");
            return null;
        }
    }

    public Paged<Movie> findAll(Pageable pageable) {
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + apiKey + "&language=en-US&include_adult=true&include_video=true&page=" + pageable.getPageNumber();

        Stream<Movie> movieList;
        ResponseWrapper responseWrapper;

        try {
            responseWrapper = mapper.readValue(get(url), ResponseWrapper.class);
            movieList = responseWrapper.getResults().stream();

        } catch (IOException e) {
            System.out.println("Something went wrong with movies api");
            return new Paged<>();
        }

        if (pageable.getSort().getOrderFor("vote_count") != null) {
            movieList = movieList.sorted(Comparator.comparing(Movie::getVoteCount));
        }

        if (pageable.getSort().getOrderFor("release_date") != null) {
            movieList = movieList.sorted(Comparator.comparing(Movie::getReleaseDate));
        }

        if (pageable.getSort().getOrderFor("vote_average") != null) {
            movieList = movieList.sorted(Comparator.comparing(Movie::getVoteAverage));
        }

        Page<Movie> movies = new PageImpl<>(movieList.collect(Collectors.toList()), pageable, responseWrapper.getTotalResults());
        return new Paged<>(movies, Paging.of(responseWrapper.getTotalResults(), pageable.getPageNumber(), pageable.getPageSize()));

    }

    public Movie findById(Integer id) {
        try {
            return mapper.readValue(get("https://api.themoviedb.org/3/movie/76341?api_key=" + apiKey), Movie.class);
        } catch (IOException e) {
            return null;
        }
    }
}
