package com.ITSS.ITSS_NIHONGO.controller;

import com.ITSS.ITSS_NIHONGO.config.JwtService;
import com.ITSS.ITSS_NIHONGO.service.FavouriteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FavouriteController {

    private final JwtService jwtService;
    private final FavouriteService favouriteService;

    @GetMapping("/favourite-top3" )
    public ResponseEntity<Map<String, Object>> get3Favourite(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("Authorization").substring(7);
        Integer userId = jwtService.extractUserId(token);
        try {
            var favouriteList = favouriteService.get3Favourite(userId);
            if (favouriteList == null || favouriteList.isEmpty()) {
                map.put("status", "fail");
                map.put("message", "No favourite items found");
                return ResponseEntity.status(404).body(map);
            } else {
                map.put("status", "success");
                map.put("data", favouriteList);
                return ResponseEntity.ok(map);
            }
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", "An error occurred while fetching favourite items");
            return ResponseEntity.status(500).body(map);
        }
    }
}
