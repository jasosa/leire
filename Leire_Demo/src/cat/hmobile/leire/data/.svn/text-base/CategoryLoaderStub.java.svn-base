package cat.hmobile.leire.data;

import java.util.ArrayList;
import java.util.List;

import cat.hmobile.leire.entities.categories.Category;
import cat.hmobile.leire.entities.categories.CategoryLeaf;
import cat.hmobile.leire.entities.categories.CategoryNotFoundException;
import cat.hmobile.leire.entities.categories.ICategoryLoader;

@Deprecated
public class CategoryLoaderStub implements ICategoryLoader
{
	List<Category> _parentCategories;
	List<Category> _childsFromCategory01;
	List<Category> _childsFromCategory02;
	List<Category> _childsFromCategory03;

	public CategoryLoaderStub()
	{
		_parentCategories = new ArrayList<Category>();
		_childsFromCategory01 = new ArrayList<Category>();
		_childsFromCategory02 = new ArrayList<Category>();
		_childsFromCategory03 = new ArrayList<Category>();
		
		Category c1 = new Category("1", "Cat01");
		Category c2 = new Category("2", "Cat02");
		Category c3 = new CategoryLeaf("3", "Cat03");
		Category c1_child1 = new CategoryLeaf("11", "c1_child1", c1);
		Category c1_child2 = new CategoryLeaf("12", "c1_child2", c1);
		Category c2_child1 = new CategoryLeaf("21", "c2_child1", c2);
		
		_parentCategories.add(c1);
		_parentCategories.add(c2);
		_parentCategories.add(c3);
		_childsFromCategory01.add(c1_child1);
		_childsFromCategory01.add(c1_child2);
		_childsFromCategory02.add(c2_child1);		
	
	}
	@Override
	public List<Category> loadMain() {
		return _parentCategories;
	}

	@Override
	public Category load(String categoryId) {
		// 
		if(categoryId.equals("1"))
			return _parentCategories.get(0);
		else if(categoryId.equals("2"))
			return _parentCategories.get(1);
		else if(categoryId.equals("3"))
			return _parentCategories.get(2);
		else if(categoryId.equals("11"))
			return _childsFromCategory01.get(0);
		else if(categoryId.equals("12"))
			return _childsFromCategory01.get(1);
		else if(categoryId.equals("21"))
			return _childsFromCategory02.get(0);
		else
			throw new CategoryNotFoundException(categoryId);
	}

	@Override
	public List<Category> loadChilds(String parentCategoryId) {
		
		if(parentCategoryId.equals("1"))
			return _childsFromCategory01;
		else if(parentCategoryId.equals("2"))
			return _childsFromCategory02;
		else
			return null;
		}
}