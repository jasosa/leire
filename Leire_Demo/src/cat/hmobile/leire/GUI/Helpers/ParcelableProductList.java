package cat.hmobile.leire.GUI.Helpers;

import java.util.ArrayList;
import java.util.List;

import cat.hmobile.leire.entities.products.Product;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableProductList extends ArrayList<Product> implements Parcelable {

	private static final long serialVersionUID = 663585476779879096L;
	 
    public ParcelableProductList(){
    }
    
	public ParcelableProductList(Parcel in)
	{
		readFromParcel(in);
	}
	
	public void setProducts(List<Product> products)
	{
		this.clear();
		this.addAll(products);
	}
	
	  private void readFromParcel(Parcel in) {
		  
		  this.clear();
          //First we have to read the list size
          int size = in.readInt();

          //Reading remember that we wrote first the Name and later the Phone Number.
          //Order is fundamental          
          for (int i = 0; i < size; i++) {
              Product p = new Product();
              p.setId(in.readString());
              p.setBarCode(in.readString());
              p.setCategoryId(in.readString());
              p.setBrand(in.readString());
              p.setName(in.readString());
              p.setManufacturer(in.readString());
              p.setCategoryName(in.readString());
              p.setFitType(in.readInt());
              this.add(p);
          }
	  }

	@SuppressWarnings("unchecked")
     public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

            @Override
			public ParcelableProductList createFromParcel(Parcel in) {
                     return new ParcelableProductList(in);
             }

            @Override
			public Object[] newArray(int arg0) {
                     return null;
             }
     };
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		int size = this.size();
        //We have to write the list size, we need him recreating the list
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            Product p = this.get(i);
            dest.writeString(p.getId());
            dest.writeString(p.getBarCode());
            dest.writeString(p.getCategoryId());
            dest.writeString(p.getBrand());
            dest.writeString(p.getName());
            dest.writeString(p.getManufacturer());
            dest.writeString(p.getCategoryName());
            dest.writeInt(p.getFitType());
        }
	}
}
