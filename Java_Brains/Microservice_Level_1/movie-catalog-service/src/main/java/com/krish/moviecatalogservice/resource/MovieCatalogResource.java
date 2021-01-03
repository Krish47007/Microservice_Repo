package com.krish.moviecatalogservice.resource;

import com.krish.moviecatalogservice.models.CatalogItem;
import com.krish.moviecatalogservice.models.Movie;
import com.krish.moviecatalogservice.models.Rating;
import com.krish.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")     //Whenever someone hits /catalog load this controller
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId)
    {
        //RestTemplate restTemplate = new RestTemplate();

        //Get all rated movie IDs for a User
        UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId,UserRating.class);

        return userRating.getRatings().stream().map( rating -> {

            //For each movie ID, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(),"This movie deals with origin of Batman",rating.getRating());

        }).collect(Collectors.toList());

        /*return Collections.singletonList(
                new CatalogItem("Batman: The Dark Knight","This movie is a sequel of Batman Origins",9)

        );*/
    }
}
