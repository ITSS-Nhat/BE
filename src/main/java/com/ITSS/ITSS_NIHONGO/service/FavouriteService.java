package com.ITSS.ITSS_NIHONGO.service;

import com.ITSS.ITSS_NIHONGO.Iservice.IFavourite;
import com.ITSS.ITSS_NIHONGO.dto.request.Favourite.AddFavourite;
import com.ITSS.ITSS_NIHONGO.dto.response.Favourite.FavouriteResponse;
import com.ITSS.ITSS_NIHONGO.model.Dishes;
import com.ITSS.ITSS_NIHONGO.model.Favourite;
import com.ITSS.ITSS_NIHONGO.model.Restaurant;
import com.ITSS.ITSS_NIHONGO.model.Users;
import com.ITSS.ITSS_NIHONGO.repository.DishRepository;
import com.ITSS.ITSS_NIHONGO.repository.FavouriteRepository;
import com.ITSS.ITSS_NIHONGO.repository.RestaurantRepository;
import com.ITSS.ITSS_NIHONGO.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteService implements IFavourite {
    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishRepository dishRepository;

    @Override
    public List<FavouriteResponse> get3Favourite(int userId) {
        List<Favourite> favourites = favouriteRepository.findTop3ByUserIdOrderByCreatedAtDesc(userId);
        if (favourites.isEmpty()) {
            return null;
        }
        return favourites.stream().map(favouriteItem -> FavouriteResponse.builder()
                        .id(favouriteItem.getId())
                        .dishesname(favouriteItem.getDish().getName())
                        .imageUrl(favouriteItem.getDish().getImageUrl())
                        .distance(favouriteItem.getRestaurant().getDistance())
                        .restaurantname(favouriteItem.getRestaurant().getName())
                        .build())
                .toList();
    }

    @Override
    public List<FavouriteResponse> getAllFavourite(int userId) {
        List<Favourite> favourites = favouriteRepository.findByUser_IdOrderByDish_RateDesc(userId);
        if (favourites.isEmpty()) {
            return null;
        }
        return favourites.stream().map(favouriteItem -> FavouriteResponse.builder()
                        .id(favouriteItem.getId())
                        .dishesname(favouriteItem.getDish().getName())
                        .imageUrl(favouriteItem.getDish().getImageUrl())
                        .rate(favouriteItem.getDish().getRate())
                        .description(favouriteItem.getDish().getDescription())
                        .build())
                .toList();
    }

    @Override
    public boolean addFavourite(int userId, AddFavourite addFavourite) {
        Favourite favourite = favouriteRepository.findByUser_IdAndDish_IdAndRestaurant_Id(userId, addFavourite.dishId, addFavourite.restaurantId);
        if (favourite != null) {
            return false;
        }
        Users users = userRepository.findById(userId).orElse(null);
        if (users == null) {
            return false;
        }
        Dishes dishes = dishRepository.findById(addFavourite.dishId).orElse(null);
        if (dishes == null) {
            return false;
        }
        Restaurant restaurant = restaurantRepository.findById(addFavourite.restaurantId).orElse(null);
        if (restaurant == null) {
            return false;
        }
        Favourite newFavourite = Favourite.builder()
                .user(users)
                .dish(dishes)
                .restaurant(restaurant)
                .createdAt(java.time.LocalDateTime.now())
                .build();
        favouriteRepository.save(newFavourite);
        return true;
    }

    @Override
    public boolean deleteFavourite(int favouriteId) {
        Favourite favourite = favouriteRepository.findById(favouriteId).orElse(null);
        if (favourite == null) {
            return false;
        }
        favouriteRepository.delete(favourite);
        return true;
    }
}
