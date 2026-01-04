package com.ITSS.ITSS_NIHONGO.repository;

import com.ITSS.ITSS_NIHONGO.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Integer>
{
    List<Favourite> findTop3ByUserIdOrderByCreatedAtDesc(int userId);

    List<Favourite> findByUser_IdOrderByDish_RateDesc(int userId);

    Favourite findByUser_IdAndDish_IdAndRestaurant_Id(int userId, int dishId, int restaurantId);

    Collection<Object> countByDish_Id(int id);
}
