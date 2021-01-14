package com.orangesystems.at.movies;

import com.orangesystems.at.movies.controllers.HomeController;

import com.orangesystems.at.movies.controllers.MovieController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoviesApplicationTests {

    @Autowired
    private HomeController homeController;

    @Autowired
    private MovieController movieController;

    @Test
    void contextLoads() {
        Assert.assertNotNull(homeController);
        Assert.assertNotNull(movieController);
    }

}
