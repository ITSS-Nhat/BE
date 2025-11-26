package com.ITSS.ITSS_NIHONGO.Iservice;

import com.ITSS.ITSS_NIHONGO.dto.response.Dishes.DishResponse;

import java.util.List;

public interface IDishesService {
    List<DishResponse> getDishfamousList();
}
