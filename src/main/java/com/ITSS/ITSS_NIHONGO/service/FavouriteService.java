package com.ITSS.ITSS_NIHONGO.service;

import com.ITSS.ITSS_NIHONGO.Iservice.IFavourite;
import com.ITSS.ITSS_NIHONGO.dto.response.Favourite.FavouriteResponse;
import com.ITSS.ITSS_NIHONGO.model.Favorite;
import com.ITSS.ITSS_NIHONGO.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteService implements IFavourite {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public List<FavouriteResponse> get3Favourite(int userId) {
        List<Favorite> favorites = favoriteRepository.findTop3ByUserIdOrderByCreatedAtDesc(userId);
        if (favorites.isEmpty()) {
            return null;
        }
        return favorites.stream().map(favoriteItem -> FavouriteResponse.builder()
                        .id(favoriteItem.getId())
                        .dishesname(favoriteItem.getDish().getName())
                        .imageUrl(favoriteItem.getDish().getImageUrl())
                        .distance(favoriteItem.getRestaurant().getDistance())
                        .restaurantname(favoriteItem.getRestaurant().getName())
                        .build())
                .toList();
    }
}
