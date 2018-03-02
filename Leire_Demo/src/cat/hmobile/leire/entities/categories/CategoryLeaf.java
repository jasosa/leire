package cat.hmobile.leire.entities.categories;

import java.util.ArrayList;
import java.util.List;

import cat.hmobile.leire.entities.products.Product;

public class CategoryLeaf extends Category {

	private List<Product> m_products;
	
	public CategoryLeaf(String id, String name) {
		super(id, name);
		
		this.m_products = null;
	}

	public CategoryLeaf(String id, String name, Category parent) {
		super(id, name, parent);
		
		this.m_products = null;
	}
	
	@Override
	public boolean isLeaf()
	{
		return true;
	}
	
	public void addProduct(Product product)
	{
		if(this.m_products == null) this.m_products = new ArrayList<Product>();
		
		if(!this.m_products.contains(product))
		{
			this.m_products.add(product);
			product.setCategoryId(this.getId());
		}
	}
	
	public void removeProduct(Product productToRemove)
	{
		if(this.m_products != null)
		{
			if(this.m_products.contains(productToRemove))
			{
				this.m_products.remove(productToRemove);
				productToRemove.setCategoryId(null);
			}
		}
	}
	
	public List<Product> getProducts(boolean recursiveSearch)
	{
		return m_products;
	}
	
	public boolean hasProducts()
	{
		return m_products != null;
	}
	
	@Override
	public void addChild(Category child) 
	{
		throw new CategoryNotAllowChildsException(111);//R.string.leafCategoryNotAllowChilds);
	}
	
	@Override
	public void removeChild(Category childToRemove)
	{
		//nothing to do! by Jasosa
	}
	
	@Override
	public List<Category> getChilds()
	{
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = super.equals(obj);
			
		if (!(obj instanceof CategoryLeaf))
		{
			result = false;
		}
		else
		{
			CategoryLeaf other = (CategoryLeaf) obj;
			result = result && this.checkEquals(this.m_products, other.m_products);
		}
		return result;
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		hash = 31 * hash + (null == m_products ? 0 : m_products.hashCode());
		return hash;
	}
}
