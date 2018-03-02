package cat.hmobile.leire.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cat.hmobile.leire.entities.barcode.BarCodeValidator;
import cat.hmobile.leire.entities.barcode.layouts.BarCodeLayoutFactory;
import cat.hmobile.leire.entities.categories.Category;
import cat.hmobile.leire.entities.categories.CategoryLeaf;
import cat.hmobile.leire.entities.categories.CategoryMap;
import cat.hmobile.leire.entities.categories.CategoryNotFoundException;
import cat.hmobile.leire.entities.categories.ICategoryLoader;
import cat.hmobile.leire.entities.products.BrandNotFoundException;
import cat.hmobile.leire.entities.products.IProductLoader;
import cat.hmobile.leire.entities.products.Product;

public class Catalog implements ICatalogNavigator {
	
	public static final String ROOT_CATEGORY_NAME = "RootCategory";
	private CategoryMap m_categoriesMap;
	private List<Category> m_currentCategories = new ArrayList<Category>();
	private ICategoryLoader m_catLoader;
	private IProductLoader m_prodLoader;
	private String m_currentCategory = Category.ROOT_ID;
		   
	   public Catalog(ICategoryLoader categoryLoader, IProductLoader productLoader) {
		   	m_catLoader = categoryLoader;
			m_prodLoader = productLoader;
	   }
	   
	
	@Override
	public void loadRootCategories() {
		if(m_categoriesMap == null)
		{
			m_categoriesMap = new CategoryMap();
			this.addMainCategoriesToMap(m_catLoader.loadMain());
		}
		
		m_currentCategories = m_categoriesMap.getMainCategories();
		m_currentCategory = Category.ROOT_ID;
	}
	
	@Override
	public void navigateToParentCategory() {
		
		if(!this.isCurrentCategoryRoot())
		{
			Category currentCategory = m_categoriesMap.getCategory(m_currentCategory);
			
			if(currentCategory.getParent()==null || currentCategory.getParent().isRoot())
			{ 
				this.loadRootCategories();
			}
			else
			{
				this.navigateToSubCategoriesOf(currentCategory.getParent().getId());
			}
		}
	}


	@Override
	public void navigateToSubCategoriesOf(String parentCategory)
	{	
		Category targetCategory = m_categoriesMap.getCategory(parentCategory);
		this.loadChildsOf(targetCategory);
		m_currentCategories = targetCategory.getChilds();
		m_currentCategory = parentCategory;
	}
	

	@Override
	public List<Category> getCurrentSubCategories() {
		return m_currentCategories;
	}
	
	@Override
	public List<Product> getProductsByAproxText(String searchText)
	{
		if(this.isCurrentCategoryRoot())
			return m_prodLoader.loadByAproxTextSearch(searchText);
		else
			return m_prodLoader.loadByAproxTextSearchAndCategory(searchText, m_currentCategory);
	}

	@Override
	public List<Product> getProductsOfCurrentCategory() 
	{
		if(this.isCurrentCategoryRoot())
			return m_prodLoader.loadAllProducts();
		else
			return m_prodLoader.loadByCategory(m_currentCategory);
	}
	


	@Override
	public List<Product> getProductsByCategory(String categoryId) {
		List<Product> products = m_prodLoader.loadByCategory(categoryId);
		if(products.size()==0) 
			throw new CategoryNotFoundException(categoryId);
		return products;
	}


	@Override
	public List<Product> getProductsByBrand(String brand) {
		List<Product> products = m_prodLoader.loadByBrand(brand);
		if(products.size()==0) 
			throw new BrandNotFoundException(brand);
		return products;
	}

	@Override
	public boolean isCurrentCategoryLeaf()
	{
		Category currentCategory = m_categoriesMap.getCategory(m_currentCategory);
		return currentCategory.isLeaf();
	}
	
	@Override
	public boolean isCurrentCategoryRoot()
	{
		Category currentCategory = m_categoriesMap.getCategory(m_currentCategory);
		return currentCategory.isRoot();
	}
	
	@Override
	public String getCurrentCategoryId()
	{
		return m_currentCategory;
	}
	
	@Override
	public String getCurrentCategoryName()
	{
		return m_categoriesMap.getCategory(m_currentCategory).getName();
	}
	
	@Override
	public Product getProductByEan(String eanCode) {
		BarCodeValidator.validate(eanCode, BarCodeLayoutFactory.build(eanCode));
		return m_prodLoader.loadByEAN(eanCode);
	}	
	
	private List<Product> getProductsFromTreeOfCategories(
			Category currentCategory) {
		
		List<Product> productOfCategory = new ArrayList<Product>();
		this.loadChildsOf(currentCategory);
		
		Iterator<Category> categories = currentCategory.getChilds().iterator();
		while(categories.hasNext())
		{
			productOfCategory.addAll(this.getProductsFromCategory(categories.next()));
		}
		return productOfCategory;
	}


	private List<Product> getProductsFromCategory(Category category) {
		List<Product> productOfCategory = new ArrayList<Product>();
		
		if(category.isLeaf())
		{
			List<Product> products = this.getProductsFromSingleCategory((CategoryLeaf)category);
			if(products!=null) productOfCategory.addAll(products);
		}
		else
		{
			List<Product> products = this.getProductsFromTreeOfCategories(category);
			productOfCategory.addAll(products);
		}
		return productOfCategory;
	}


	private List<Product> getProductsFromSingleCategory(CategoryLeaf currentCategory) {
		
		if(!currentCategory.hasProducts())
			loadProductsOfCategory(currentCategory);
		
		return currentCategory.getProducts(false);
	}


	private void loadChildsOf(Category currentCategory) {
		
		if(currentCategory.getChilds() == null && !currentCategory.isLeaf() 
				&& !currentCategory.isRoot())
		{
			List<Category> categories = m_catLoader.loadChilds(currentCategory.getId());
			if(categories!=null)
				addChildsCategoriesToCurrentCategory(currentCategory, categories);
		}
	}
	
	private void addMainCategoriesToMap(List<Category> categoriesList) {
		
		for(int i = 0;i<categoriesList.size();i++)
		{
			Category c = categoriesList.get(i);
			m_categoriesMap.add(c);
		}
	}

	private void addChildsCategoriesToCurrentCategory(Category currentCategory,
			List<Category> childCategories) {

		//this.addAllProductsCategoryAsAChildOf(currentCategory);
		
		for(int i = 0;i<childCategories.size();i++)
		{	
			Category c = childCategories.get(i);
			currentCategory.addChild(c);
		}
	}

	private void loadProductsOfCategory(CategoryLeaf categoryLeaf) {
		List<Product> productsOfTheCategory = m_prodLoader.loadByCategory(categoryLeaf.getId());
		addProductsToCategory(categoryLeaf, productsOfTheCategory);
	}


	private void addProductsToCategory(CategoryLeaf currentCategory,
			List<Product> productsOfTheCategory) {
		for(int i = 0;i<productsOfTheCategory.size();i++)
		{
			currentCategory.addProduct(productsOfTheCategory.get(i));
		}
	}

}
