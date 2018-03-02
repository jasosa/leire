package cat.hmobile.leire.entities.products;

import cat.hmobile.leire.entities.CommonEntity;

public class Product extends CommonEntity {

	public static final int FITTYPE_FACE = 1;
	public static final int FITTYPE_BRAND = 2;
	
	protected String m_id;
	protected String m_name;
	protected String m_brand;
	protected String m_barCode;
	protected String m_categoryId;
	protected String m_categoryName;
	protected String m_manufacturer;
	protected int m_fitType;
	private String m_dischargeDate;
	
	public void setId(String id) {
		this.m_id = id;
	}

	public String getId() {
		return m_id;
	}
	
	public void setName(String name) {
		this.m_name = name;
	}
	public String getName() {
		return m_name;
	}
	
	public void setBrand(String brand) {
		this.m_brand = brand;
	}
	
	public String getBrand() {
		return m_brand;
	}
	
	public void setBarCode(String barCode) {
		this.m_barCode = barCode;
	}
	
	public String getBarCode() {
		return m_barCode;
	}
	
	public void setCategoryId(String categoryId) {
		this.m_categoryId = categoryId;
	}
	public String getCategoryId() {
		return m_categoryId;
	}
	
	public void setCategoryName(String categoryName) {
		this.m_categoryName = categoryName;
	}
	public String getCategoryName() {
		return m_categoryName;
	}
	
	
	public void setManufacturer(String manufacturer) {
		this.m_manufacturer = manufacturer;
	}
	public String getManufacturer() {
		return m_manufacturer;
	}
	
	public int getFitType() {
		return m_fitType;
	}
	
	public void setFitType(int fitType) {
		this.m_fitType = fitType;
	}
	
	public String getDischargeDate() {
		return this.m_dischargeDate;
	}
	
	public void setDischargeDate(String dischargeDate) {
			this.m_dischargeDate = dischargeDate;
	}
	
	public boolean isFaceFitType()
	{
		return (this.m_fitType == FITTYPE_FACE);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((m_barCode == null) ? 0 : m_barCode.hashCode());
		result = prime * result + ((m_brand == null) ? 0 : m_brand.hashCode());
		result = prime * result
				+ ((m_categoryId == null) ? 0 : m_categoryId.hashCode());
		result = prime * result + ((m_id == null) ? 0 : m_id.hashCode());
		result = prime * result + ((m_name == null) ? 0 : m_name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!(obj instanceof Product))
			return false;
		
		Product other = (Product) obj;
		boolean result = this.checkEquals(this.m_id, other.m_id);
		result = result && this.checkEquals(this.m_fitType, other.m_fitType);
		result = result && this.checkEquals(this.m_name, other.m_name);
		result = result && this.checkEquals(this.m_barCode, other.m_barCode);
		result = result && this.checkEquals(this.m_brand, other.m_brand);
		result = result && this.checkEquals(this.m_categoryId, other.m_categoryId);
		result = result && this.checkEquals(this.m_categoryName, other.m_categoryName);
		result = result && this.checkEquals(this.m_manufacturer, other.m_manufacturer);
		result = result && this.checkEquals(this.m_dischargeDate, other.m_dischargeDate);
		
		return result;
	}
}
