package cat.hmobile.leire.GUI.Dialogs;

import cat.hmobile.leire.GUI.Activities.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LeireDialogBuilder {
 

		private Context m_context;
        private String title;
        private String m_message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
 
        private DialogInterface.OnClickListener 
                        positiveButtonClickListener,
                        negativeButtonClickListener;
 
        public LeireDialogBuilder(Context ctx) {
        	m_context = ctx;
    	}
 
        public LeireDialogBuilder setMessage(CharSequence message) {
        	m_message = message.toString();
        	return this;
        };
 
        public LeireDialogBuilder setMessage(int message) {
            m_message =  (String) m_context.getText(message);;
            return this;
        }
 
        public LeireDialogBuilder setTitle(int title) {
        	this.title = (String) m_context.getText(title);
			return this;
		}
 
        public LeireDialogBuilder setTitle(CharSequence title) {
           this.title = title.toString();
           return this;
        }
 
        public LeireDialogBuilder setView(View v) {
        	this.contentView = v;
        	return this;
}
 
        public LeireDialogBuilder setPositiveButton(int positiveButtonText,
                DialogInterface.OnClickListener listener) {
        	this.positiveButtonText = (String) m_context
            .getText(positiveButtonText);
		    this.positiveButtonClickListener = listener;
		    return this;
        }
 
        public LeireDialogBuilder setPositiveButton(CharSequence positiveButtonText,
                DialogInterface.OnClickListener listener) {
        	this.positiveButtonText = positiveButtonText.toString();
            this.positiveButtonClickListener = listener;
            return this;
            }
 
       
        public LeireDialogBuilder setNegativeButton(int negativeButtonText,
                DialogInterface.OnClickListener listener) {
        	 this.negativeButtonText = (String) m_context
             .getText(negativeButtonText);
	     this.negativeButtonClickListener = listener;
	     return this;
        }
 
        
        public LeireDialogBuilder setNegativeButton(CharSequence negativeButtonText,
                DialogInterface.OnClickListener listener) {
        	this.negativeButtonText = negativeButtonText.toString();
            this.negativeButtonClickListener = listener;
            return this;
        }
 
        public LeireAlertDialog createCustom(View layout){
        	final LeireAlertDialog dialog = new LeireAlertDialog(m_context, R.style.Leire_Dialog_Alert);
        	setContentView(dialog, layout);
        	return dialog;
        }
        
        /**
         * Create the custom dialog
         */
        public LeireAlertDialog create() {
        	
            LayoutInflater inflater = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final LeireAlertDialog dialog = new LeireAlertDialog(m_context, R.style.Leire_Dialog_Alert);
            View layout = inflater.inflate(R.layout.alertdialog, null);

            setDialogTitle(layout);
            setConfirmButton(dialog, layout);
            setCancelButton(dialog, layout);
            setContentMessage(layout); 
            setContentView(dialog, layout);
            
            return dialog;
        }

		private void setContentView(final LeireAlertDialog dialog, View layout) {
			dialog.setContentView(layout);
		}

		private void setContentMessage(View layout) {
			if (messageAssigned()) {
            	setMessage(layout);
            }
		}

		private void setDialogTitle(View layout) {
			((TextView) layout.findViewById(R.id.title)).setText(title);
		}

		private void setCancelButton(final LeireAlertDialog dialog, View layout) {
			if (negativeButtonAssigned()) {
                setNegativeButtonText(layout);
                if (negativeButtonListenerAssigned()) {
                    setNegativeButtonListener(dialog, layout);
                }

            }
            else {
                setNegativeButtonInvisible(layout);
            }
		}

		private void setConfirmButton(final LeireAlertDialog dialog, View layout) {
			if (positiveButtonAssigned()) {
                setPositiveButtonText(layout);
                if (positiveButtonListenerAssigned()) {
                    setPositiveButtonListener(dialog, layout);
                }
            } else {
                setPositiveButtonInvisible(layout);
            }
		}

		private void setMessage(View layout) {
			((TextView) layout.findViewById(
					R.id.message)).setText(m_message);
		} 
  
		private boolean messageAssigned() {
			return m_message != null;
		}

		private void setNegativeButtonInvisible(View layout) {
			layout.findViewById(R.id.negativeButton).setVisibility(
			        View.GONE);
		}

		private void setNegativeButtonListener(final LeireAlertDialog dialog,
				View layout) {
			((Button) layout.findViewById(R.id.negativeButton))
			        .setOnClickListener(new View.OnClickListener() {
			            public void onClick(View v) {
			                positiveButtonClickListener.onClick(
			                		dialog, 
			                        DialogInterface.BUTTON_NEGATIVE);
			            }
			        });
		}

		private boolean negativeButtonListenerAssigned() {
			return negativeButtonClickListener != null;
		}

		private void setNegativeButtonText(View layout) {
			((Button) layout.findViewById(R.id.negativeButton))
			        .setText(negativeButtonText);
		}

		private boolean negativeButtonAssigned() {
			return negativeButtonText != null;
		}

		private void setPositiveButtonListener(final LeireAlertDialog dialog,
				View layout) {
			((Button) layout.findViewById(R.id.positiveButton))
			        .setOnClickListener(new View.OnClickListener() {
			            public void onClick(View v) {
			                positiveButtonClickListener.onClick(
			                		dialog, 
			                        DialogInterface.BUTTON_POSITIVE);
			            }
			        });
		}

		private boolean positiveButtonListenerAssigned() {
			return positiveButtonClickListener != null;
		}

		private void setPositiveButtonText(View layout) {
			((Button) layout.findViewById(R.id.positiveButton))
			        .setText(positiveButtonText);
		}

		private boolean positiveButtonAssigned() {
			return positiveButtonText != null;
		}

		private void setPositiveButtonInvisible(View layout) {
			layout.findViewById(R.id.positiveButton).setVisibility(
			        View.GONE);
		}
}

