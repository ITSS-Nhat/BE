package com.ITSS.ITSS_NIHONGO.service;

import com.ITSS.ITSS_NIHONGO.Iservice.IDishRestaurant;
import com.ITSS.ITSS_NIHONGO.dto.response.DishRestaurant.DishRestaurantAll;
import com.ITSS.ITSS_NIHONGO.dto.response.DishRestaurant.DishRestaurantDetail;
import com.ITSS.ITSS_NIHONGO.model.DishRestaurant;
import com.ITSS.ITSS_NIHONGO.model.DishReview;
import com.ITSS.ITSS_NIHONGO.repository.DishRestaurantRepository;
import com.ITSS.ITSS_NIHONGO.repository.DishReviewRepository;
import com.ITSS.ITSS_NIHONGO.repository.FavouriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishRestaurantService implements IDishRestaurant {

    @Autowired
    private DishRestaurantRepository dishRestaurantRepository;
    @Autowired
    private DishReviewRepository dishReviewRepository;
    @Autowired
    private FavouriteRepository favouriteRepository;

    @Override
    public Page<DishRestaurantAll> getDishRestaurantAllList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DishRestaurant> dishRestaurantAllList = dishRestaurantRepository.findAll(pageable);
        if (dishRestaurantAllList.isEmpty()) {
            return null;
        }
        return dishRestaurantAllList.map(dr -> DishRestaurantAll.builder()
                .id(dr.getId())
                .dishesname(dr.getDish().getName())
                .restaurantname(dr.getRestaurant().getName())
                .imageUrl(dr.getDish().getImageUrl())
                .distance(dr.getRestaurant().getDistance())
                .build());
    }

    @Override
    public DishRestaurantDetail getDishRestaurantDetail(int id) {
        DishRestaurant dishRestaurant = dishRestaurantRepository.findById(id).orElse(null);
        if (dishRestaurant == null) {
            return null;
        }
        int countLike = favouriteRepository.countByDish_Id(dishRestaurant.getDish().getId()).size();

        DishRestaurantDetail detail = new DishRestaurantDetail();
        detail.id = dishRestaurant.getId();
        detail.dishId = dishRestaurant.getDish().getId();
        detail.restaurantId = dishRestaurant.getRestaurant().getId();
        detail.dishesname = dishRestaurant.getDish().getName();
        detail.restaurantname = dishRestaurant.getRestaurant().getName();
        detail.distance = dishRestaurant.getRestaurant().getDistance();
        detail.imageUrl = dishRestaurant.getDish().getImageUrl();
        detail.price = dishRestaurant.getPrice();
        detail.countLike = countLike;
        return detail;
    }
}
