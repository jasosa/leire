package cat.hmobile.leire.entities.restaurants;

import java.util.List;

import cat.hmobile.leire.entities.products.Product;

public interface IRestaurantLoader {
	List<Restaurant> loadAllRestaurants();
}
