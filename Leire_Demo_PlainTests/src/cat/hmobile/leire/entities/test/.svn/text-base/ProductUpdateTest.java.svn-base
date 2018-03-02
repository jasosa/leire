package cat.hmobile.leire.entities.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cat.hmobile.leire.entities.ProductUpdateCategoryException;
import cat.hmobile.leire.entities.products.ProductUpdate;

public class ProductUpdateTest {

	@Test
	public void createWellFormedAddedUpdateProduct(){
		ProductUpdate product = new ProductUpdate();
		product.setCategoryId(ProductUpdate.ADDED_CATEGORY_ID);
		assertTrue(product.isAdded());
		assertFalse(product.isRemoved());
	}
	
	@Test
	public void createWellFormedRemovedUpdateProduct(){
		ProductUpdate product = new ProductUpdate();
		product.setCategoryId(ProductUpdate.REMOVED_CATEGORY_ID);
		assertFalse(product.isAdded());
		assertTrue(product.isRemoved());
	}
	
	@Test (expected = ProductUpdateCategoryException.class )
	public void createUpdateProductWithWrongCategory(){
		ProductUpdate product = new ProductUpdate();
		product.setCategoryId("325");
	}
	
	@Test (expected = ProductUpdateCategoryException.class )
	public void createUpdateProductWithNullCategory(){
		ProductUpdate product = new ProductUpdate();
		product.setCategoryId(null);
	}
}
