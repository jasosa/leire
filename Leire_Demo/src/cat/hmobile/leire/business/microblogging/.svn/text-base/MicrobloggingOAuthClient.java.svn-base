package cat.hmobile.leire.business.microblogging;

import java.net.URI;

import cat.hmobile.leire.thirdparty.jtwitter.OAuthSignpostClient;
import cat.hmobile.leire.thirdparty.jtwitter.Twitter.IHttpClient;

import oauth.signpost.basic.DefaultOAuthProvider;

public class MicrobloggingOAuthClient implements IMicrobloggingOAuthClient {

	public static final String  OAUTH_KEY = "1de28b2fd6c886c615f26b0fc5ca4925";
	public static final String OAUTH_SECRET = "b5bee7bdd7a7b6203b54c9c8603d1fba";
	
	OAuthSignpostClient m_SignpostClient;
	
	public MicrobloggingOAuthClient(OAuthSignpostClient signpostClient){
		m_SignpostClient = signpostClient;
	}
	
	@Override
	public URI authorizeUrl() {
		return m_SignpostClient.authorizeUrl();
	}

	@Override
	public void setAuthorizationCode(String verifier) {
		m_SignpostClient.setAuthorizationCode(verifier);
	}

	@Override
	public boolean canAuthenticate() {
		return m_SignpostClient.canAuthenticate();
	}

	@Override
	public String getAccessToken() {
		return m_SignpostClient.getAccessToken()[0];
	}

	@Override
	public String getAccessTokenSecret() {
		return m_SignpostClient.getAccessToken()[1];
	}

	@Override
	public void setProvider(String urlRequestToken, String urlAccesToken,
			String urlAuthorize) {
		
		DefaultOAuthProvider provider = new DefaultOAuthProvider(urlRequestToken, urlAccesToken, urlAuthorize);
		m_SignpostClient.setProvider(provider);
		
	}

	@Override
	public IHttpClient getIHttpClient() {
		return m_SignpostClient;
	}

}
