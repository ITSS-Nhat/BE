package com.ITSS.ITSS_NIHONGO.service;

import com.ITSS.ITSS_NIHONGO.Iservice.IDishesService;
import com.ITSS.ITSS_NIHONGO.dto.response.Dishes.DishResponse;
import com.ITSS.ITSS_NIHONGO.model.Dishes;
import com.ITSS.ITSS_NIHONGO.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService implements IDishesService {
    @Autowired
    private DishRepository dishRepository;
    @Override
    public List<DishResponse> getDishfamousList() {
        List<Dishes> dishes = dishRepository.findTop3ByOrderByRateDesc(PageRequest.of(0,3));
        if (dishes.isEmpty()) {
            return null;
        }
        return dishes.stream().map(dish -> DishResponse.builder()
                        .id(dish.getId())
                        .name(dish.getName())
                        .imageUrl(dish.getImageUrl())
                        .rate(dish.getRate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<DishResponse> getAllDishfamousList() {
        List<Dishes> dishes = dishRepository.findAllByOrderByRateDesc();
        if (dishes.isEmpty()) {
            return null;
        }
        return dishes.stream().map(dish -> DishResponse.builder()
                        .id(dish.getId())
                        .name(dish.getName())
                        .imageUrl(dish.getImageUrl())
                        .rate(dish.getRate())
                        .description(dish.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
