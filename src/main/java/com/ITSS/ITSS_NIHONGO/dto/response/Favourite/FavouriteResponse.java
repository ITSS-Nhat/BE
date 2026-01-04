package com.ITSS.ITSS_NIHONGO.dto.response.Favourite;

import lombok.Builder;

@Builder
public class FavouriteResponse {
    public int id;
    public String dishesname;
    public int distance;
    public String imageUrl;

    public String description;
    public int likes;
}
