package cat.hmobile.leire.GUI.Activities;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import cat.hmobile.leire.GUI.Adapters.ProductListAdapter;
import cat.hmobile.leire.GUI.Adapters.ProductUpdateListAdapter;
import cat.hmobile.leire.GUI.Helpers.ProductBundleHelper;
import cat.hmobile.leire.entities.products.Product;
import cat.hmobile.leire.entities.products.ProductUpdate;

public class ProductUpdateListActivity extends ProductListActivity {

    private static final String LOG_TAG = "ProductUpdateListActivity";

	@Override
	protected void initializeAdapter() {
		 this.m_adapter = new ProductUpdateListAdapter(this, R.layout.productrow, (List<ProductUpdate>) m_productsList);
			ListView v = (ListView) findViewById(R.id.productsListView);
			v.setAdapter(m_adapter);
			v.setOnItemClickListener(this);
     }
}
