package cat.hmobile.leire.entities.microblogging;

import cat.hmobile.leire.business.microblogging.MicrobloggingException;

public class MicrobloggingUserNotInHolderException extends
		MicrobloggingException {

	public MicrobloggingUserNotInHolderException(int errorMessageCode) {
		super(errorMessageCode);
	}
	
	public MicrobloggingUserNotInHolderException(){
		super();
	}

}
