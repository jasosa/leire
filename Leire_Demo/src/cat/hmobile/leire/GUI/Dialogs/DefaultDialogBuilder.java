package cat.hmobile.leire.GUI.Dialogs;

import android.content.Context;

public class DefaultDialogBuilder {
	
	public static LeireAlertDialog create(Context ctx, int titleResourceId, int messageResourceId){
		
		LeireDialogBuilder builderDialog = new LeireDialogBuilder(ctx);
	    builderDialog.setTitle(ctx.getString(titleResourceId));
	    builderDialog.setMessage(ctx.getString(messageResourceId));
		LeireAlertDialog dialog = builderDialog.create();	
		return dialog;
	}
	
	public static LeireAlertDialog create(Context ctx, int titleResourceId, String message){
		
		LeireDialogBuilder builderDialog = new LeireDialogBuilder(ctx);
	    builderDialog.setTitle(ctx.getString(titleResourceId));
	    builderDialog.setMessage(message);
		LeireAlertDialog dialog = builderDialog.create();	
		return dialog;
	}
	
	public static LeireAlertDialog create(Context ctx, String title, String message){
		
		LeireDialogBuilder builderDialog = new LeireDialogBuilder(ctx);
	    builderDialog.setTitle(title);
	    builderDialog.setMessage(message);
		LeireAlertDialog dialog = builderDialog.create();	
		return dialog;
	}
	
	public static LeireAlertDialog create(Context ctx, int titleResourceId, int messageResourceId, DialogButtons buttons){
		
		LeireDialogBuilder builderDialog = new LeireDialogBuilder(ctx);
	    builderDialog.setTitle(ctx.getString(titleResourceId));
	    builderDialog.setMessage(ctx.getString(messageResourceId));
	    if(buttons.isPositiveButtonDefined())
	    	builderDialog.setPositiveButton(buttons.getPositiveButtonTextId(), buttons.getPositiveButtonListener());
	    if(buttons.isNegativeButtonDefined())
	    	builderDialog.setNegativeButton(buttons.getNegativeButtonTextId(), buttons.getNegativeButtonListener());
		LeireAlertDialog dialog = builderDialog.create();	
		return dialog;
	}
    

}

			