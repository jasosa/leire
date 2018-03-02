package cat.hmobile.leire.GUI.Dialogs;

import android.content.DialogInterface.OnClickListener;

public class DialogButtons {

	boolean arePositiveButton,areNegativeButton;
	int positiveButtonTextId,negativeButtonTextId;
	OnClickListener positiveListener,negativeListener;
	
	public boolean isPositiveButtonDefined() {
		return this.arePositiveButton;
	}
	
	public boolean isNegativeButtonDefined() {
		return areNegativeButton;
	}

	public int getPositiveButtonTextId() {
		// TODO Auto-generated method stub
		return positiveButtonTextId;
	}
	
	public int getNegativeButtonTextId() {
		// TODO Auto-generated method stub
		return negativeButtonTextId;
	}
	
	public OnClickListener getPositiveButtonListener() {
		return positiveListener;
	}
	
	public OnClickListener getNegativeButtonListener() {
		return negativeListener;
	}

	public void addPositiveButton(int posButtonTextId, OnClickListener onClickListener) {
		this.arePositiveButton = true;
		this.positiveButtonTextId = posButtonTextId;
		positiveListener = onClickListener;
	}
	
	public void addNegativeButton(int posButtonTextId, OnClickListener onClickListener) {
		this.areNegativeButton = true;
		this.negativeButtonTextId = posButtonTextId;
		negativeListener = onClickListener;
	}

}
