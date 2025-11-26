package com.ITSS.ITSS_NIHONGO.dto.response.Restaurant;

import lombok.Builder;

@Builder
public class RestaurantResponse {
    public int id;
    public String name;
    public int distance;
    public int minprice;
    public int maxprice;
    public String imageUrl;
}
