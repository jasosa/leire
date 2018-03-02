package cat.hmobile.leire.GUI.ActivityStarters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import cat.hmobile.leire.GUI.Activities.ProductDetailActivity;
import cat.hmobile.leire.GUI.Helpers.ProductBundleHelper;
import cat.hmobile.leire.entities.products.Product;

public class ProductDetailActivityStarter {
	
	public static void startActivity(Activity activity, Product prod)
	{
		Intent i = new Intent(activity, ProductDetailActivity.class);
		Bundle b = ProductBundleHelper.createBundleFromProduct(prod);
		i.putExtras(b);
	    activity.startActivityForResult(i, 0); 
	}
}
