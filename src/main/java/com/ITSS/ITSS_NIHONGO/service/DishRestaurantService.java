package com.ITSS.ITSS_NIHONGO.service;

import com.ITSS.ITSS_NIHONGO.Iservice.IDishRestaurant;
import com.ITSS.ITSS_NIHONGO.dto.response.DishRestaurant.DishRestaurantAll;
import com.ITSS.ITSS_NIHONGO.model.DishRestaurant;
import com.ITSS.ITSS_NIHONGO.repository.DishRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishRestaurantService implements IDishRestaurant {

    @Autowired
    private DishRestaurantRepository dishRestaurantRepository;
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
}
