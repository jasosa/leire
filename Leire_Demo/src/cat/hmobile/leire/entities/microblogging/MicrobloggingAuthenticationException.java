package cat.hmobile.leire.entities.microblogging;

import cat.hmobile.leire.business.microblogging.MicrobloggingException;

public class MicrobloggingAuthenticationException extends MicrobloggingException {
		public MicrobloggingAuthenticationException(int errorCode){
			super(errorCode);
		}
}
