package cat.hmobile.leire.data;

import java.util.ArrayList;
import java.util.List;

import cat.hmobile.leire.entities.restaurants.IRestaurantLoader;
import cat.hmobile.leire.entities.restaurants.Restaurant;

public class RestaurantLoaderStub implements IRestaurantLoader {

	@Override
	public List<Restaurant> loadAllRestaurants() {
		return getRestaurnats();
	}

	private List<Restaurant> getRestaurnats() {
		
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		restaurants.add(new Restaurant("Viena Pelai", "Pelai 16, 08001 Barcelona", "www.viena.es", true));
		restaurants.add(new Restaurant("Tra-Bal", "Balmes 203, 08006 Barcelona", "", false));
		restaurants.add(new Restaurant("Granja-Chocolater’a M. Viader", "Xucla 4, 08001 Barcelona", "www.granjaviader.cat", true));
		restaurants.add(new Restaurant("La Lluna", "Sant Bonaventura 7, 08002 Barcelona", "www.lallunarestaurant.com", true));
		restaurants.add(new Restaurant("Conesa Artesans de l'Entrepa", "Llibreteria 1-3 08002 Barcelona", "www.conesaentrepans.com", true));
		restaurants.add(new Restaurant("4 Capellans", "C/Dels capellans 4 08002 Barcelona", "www.barcelonacatedral.com", true));
		restaurants.add(new Restaurant("FRESCO", "Salvador Espriu 31-35 08005 Barcelona", "", true));
		restaurants.add(new Restaurant("Restaurant Comiols", "Madrazo 68 08006 - Barcelona", "www.comiols.es", true));

		return restaurants;
	}

}
