package com.ITSS.ITSS_NIHONGO.service;

import com.ITSS.ITSS_NIHONGO.Iservice.IRestaurant;
import com.ITSS.ITSS_NIHONGO.dto.response.Restaurant.RestaurantResponse;
import com.ITSS.ITSS_NIHONGO.model.Restaurant;
import com.ITSS.ITSS_NIHONGO.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService implements IRestaurant {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Page<RestaurantResponse> getRestaurantrecentlyList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Restaurant> restaurants = restaurantRepository.findByDistanceLessThan(1000, pageable);
        return restaurants.map(restaurant -> RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .imageUrl(restaurant.getImageUrl())
                .distance(restaurant.getDistance())
                .build());
    }

}
