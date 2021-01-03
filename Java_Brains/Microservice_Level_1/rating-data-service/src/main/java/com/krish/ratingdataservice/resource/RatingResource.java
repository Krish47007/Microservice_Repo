package com.krish.ratingdataservice.resource;

import com.krish.ratingdataservice.models.Rating;
import com.krish.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId)
    {
        return new Rating(movieId,8);
    }

    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId)
    {
        List<Rating> ratings = Arrays.asList(

                new Rating("1234",8),
                new Rating("4567",9),
                new Rating("3489",7)
        );

        UserRating userRating = new UserRating();
        userRating.setRatings(ratings);
        return userRating;
    }
}
