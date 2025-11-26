package com.ITSS.ITSS_NIHONGO.controller;

import com.ITSS.ITSS_NIHONGO.service.DishRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class DishRestaurantController {
    private final DishRestaurantService dishRestaurantService;
    @GetMapping("/dish-restaurant-all")
    public ResponseEntity<Map<String, Object>> getDishRestaurantAllList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = new HashMap<>();
        try {
            var dishRestaurantPage = dishRestaurantService.getDishRestaurantAllList(page, size);

            if (dishRestaurantPage == null || dishRestaurantPage.isEmpty()) {
                response.put("status", "fail");
                response.put("message", "No dish-restaurant associations found");
                return ResponseEntity.status(404).body(response);
            }

            response.put("status", "success");
            response.put("data", dishRestaurantPage.getContent());
            response.put("totalItems", dishRestaurantPage.getTotalElements());
            response.put("totalPages", dishRestaurantPage.getTotalPages());
            response.put("currentPage", dishRestaurantPage.getNumber());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred while fetching dish-restaurant associations");
            return ResponseEntity.status(500).body(response);
        }
    }
}
