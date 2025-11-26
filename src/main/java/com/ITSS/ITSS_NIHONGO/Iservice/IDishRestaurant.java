package com.ITSS.ITSS_NIHONGO.Iservice;

import com.ITSS.ITSS_NIHONGO.dto.response.DishRestaurant.DishRestaurantAll;
import org.springframework.data.domain.Page;

public interface IDishRestaurant {
    Page<DishRestaurantAll> getDishRestaurantAllList(int page, int size);
}
