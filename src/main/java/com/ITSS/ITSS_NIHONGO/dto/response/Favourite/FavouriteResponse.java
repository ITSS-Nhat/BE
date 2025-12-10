package com.ITSS.ITSS_NIHONGO.dto.response.Favourite;

import lombok.Builder;

@Builder
public class FavouriteResponse {
    public int id;
    public String dishesname;
    public String restaurantname;
    public int distance;
    public String imageUrl;

    public String description;
    public float rate;
}
