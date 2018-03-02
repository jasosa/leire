package cat.hmobile.leire.entities.categories;

import java.util.ArrayList;
import java.util.List;

import cat.hmobile.leire.entities.CommonEntity;

public class Category extends CommonEntity {
	
	public static final String ROOT_ID = "-2";
	
	protected String m_id;
	protected String m_name;
	protected Category m_parent;
	protected List<Category> m_childs;
	
	public Category (String id,String name)
	{
		this.m_name = name;
		this.m_id = id;
		this.m_parent = null;
	}
	
	public Category(String id,String name, Category parent)
	{
		this.m_name = name;
		this.m_id = id;
		this.m_parent = parent;
	}
	
	public String getName ()
	{
		return this.m_name;
	}
	
	public String getId()
	{
		return this.m_id;
	}
	
	public Category getParent()
	{
		return this.m_parent;
	}
	
	public void setParent(Category parent)
	{
		this.m_parent = parent;
	}
	
	public boolean isLeaf()
	{
		return false;
	}
	
	public boolean isRoot() {
		return this.m_parent == null;
	}
	
	public void addChild(Category child)
	{
		if(this.m_childs==null)
		{
			this.m_childs = new ArrayList<Category>();
		}
		
		if(!this.m_childs.contains(child))
		{
			this.m_childs.add(child);
			child.setParent(this);
		}
	}
	
	public void removeChild(Category childToRemove)
	{
		if(this.m_childs !=null && this.m_childs.contains(childToRemove))
		{
			this.m_childs.remove(childToRemove);
			childToRemove.setParent(null);
		}
	}
	
	public List<Category> getChilds()
	{
		return this.m_childs;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!(obj instanceof Category))
			return false;
		
		Category other = (Category) obj;
		boolean result = this.checkEquals(this.m_id, other.m_id);
		result = result && this.checkEquals(this.m_name, other.m_name);
		result = result && this.checkEquals(this.m_parent, other.m_parent);
		result = result && this.checkEquals(this.m_childs, other.m_childs);
		
		return result;
	}
	
	@Override
	public int hashCode() 
	{
		int hash = 7;

		hash = 31 * hash + (null == m_id ? 0 : m_id.hashCode());
		hash = 31 * hash + (null == m_name ? 0 : m_name.hashCode());
		hash = 31 * hash + (null == m_parent ? 0 : m_parent.hashCode());
		hash = 31 * hash + (null == m_childs ? 0 : m_childs.hashCode());

		return hash;
	}
	
	@Override
	public String toString()
	{
		return m_name;
	}
}
