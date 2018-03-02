package cat.hmobile.leire.entities.restaurants;

import android.content.Context;
import cat.hmobile.leire.data.CategoryLoaderXml;
import cat.hmobile.leire.data.RestaurantLoaderStub;
import cat.hmobile.leire.entities.categories.ICategoryLoader;

public class RestaurantLoaderFactory {

	public static IRestaurantLoader createRestaurantLoader()
	{
		return new RestaurantLoaderStub();
	}
}
