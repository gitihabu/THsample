package com.laioffer.twitch.favorite;

public class FavoriteController {
    private final FavoriteService favoriteService;


    private final UserEntity userEntity = new UserEntity(1L, "user0", "Foo", "Bar", "password");


    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }


    @GetMapping
    public TypeGroupedItemList getFavoriteItems() {
        return favoriteService.getGroupedFavoriteItems(userEntity);
    }


         favoriteService.unsetFavoriteItem(userEntity, body.favorite().twitchId());
}
 @PostMapping
public void setFavoriteItem(@RequestBody FavoriteRequestBody body) {
        try {
        favoriteService.setFavoriteItem(userEntity, body.favorite());
        } catch (DbActionExecutionException e) {
        Throwable cause = e.getCause();
        if (cause instanceof DataIntegrityViolationException) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate entry for favorite record", e);
        } else {
        throw e;
        }
        }
        }


@DeleteMapping
public void unsetFavoriteItem(@RequestBody FavoriteRequestBody body) {



        }
