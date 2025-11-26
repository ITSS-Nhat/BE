package com.ITSS.ITSS_NIHONGO.Iservice;

import com.ITSS.ITSS_NIHONGO.dto.response.Favourite.FavouriteResponse;

import java.util.List;

public interface IFavourite {
    List<FavouriteResponse> get3Favourite(int userId);
}
