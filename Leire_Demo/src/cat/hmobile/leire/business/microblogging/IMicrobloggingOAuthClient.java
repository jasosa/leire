package cat.hmobile.leire.business.microblogging;

import java.net.URI;

import cat.hmobile.leire.thirdparty.jtwitter.Twitter.IHttpClient;
import oauth.signpost.basic.DefaultOAuthProvider;

public interface IMicrobloggingOAuthClient {
	public URI authorizeUrl();
	public void setAuthorizationCode(String verifier);
	public boolean canAuthenticate();
	public String getAccessToken();
	public String getAccessTokenSecret();
	public void setProvider(String urlRequestToken, String urlAccesToken, String urlAuthorize);
	public IHttpClient getIHttpClient();
}
