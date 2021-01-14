package com.orangesystems.at.movies.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//        {
//                "adult": false,
//                "backdrop_path": "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
//                "genre_ids": [
//                14,
//                28,
//                12
//                ],
//                "id": 464052,
//                "original_language": "en",
//                "original_title": "Wonder Woman 1984",
//                "overview": "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
//                "popularity": 3906.05,
//                "poster_path": "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
//                "release_date": "2020-12-16",
//                "title": "Wonder Woman 1984",
//                "video": false,
//                "vote_average": 7.2,
//                "vote_count": 2553
//                },

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private int id;

    private String title;

    private String overview;

    @JsonProperty("backdrop_path")
    private String imageUrl;

    private Double popularity;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonProperty("release_date")
    private Date releaseDate;

}
