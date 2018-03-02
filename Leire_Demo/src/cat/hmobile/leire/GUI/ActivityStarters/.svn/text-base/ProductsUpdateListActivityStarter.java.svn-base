package cat.hmobile.leire.GUI.ActivityStarters;

import cat.hmobile.leire.GUI.Activities.ProductListActivity;
import cat.hmobile.leire.GUI.Activities.ProductUpdateListActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ProductsUpdateListActivityStarter extends
		ProductsListActivityStarter {
	
	private static ProductsListActivityStarter m_productsListStarter;
	
	public static ProductsListActivityStarter getInstance(){
		if(m_productsListStarter == null)
			m_productsListStarter = new ProductsUpdateListActivityStarter();
		
		return m_productsListStarter;
	}

	@Override
	protected void createIntentAndStartActivity(Activity activity, Bundle b, int requestCode) {
		Intent i = new Intent(activity, ProductUpdateListActivity.class);
		i.putExtras(b);
		activity.startActivityForResult(i, requestCode);
	}
	
}
