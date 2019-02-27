package boot.com.action;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authorizetion")
public class OauthServer {
	
	@RequestMapping("accessCode")
	public String getAuthCode(HttpServletRequest request){
		try {
			OAuthAuthzRequest authzRequest = new OAuthAuthzRequest(request);
			if(!StringUtils.isEmpty(authzRequest.getClientId())){
				//设置授权 
				String authCode = "wpCode";
				//利用oauth授权请求设置responseType，目前仅支持CODE，另外还有TOKEN 
				String responseType = authzRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
				//进行OAuth响应构建
				OAuthASResponse.OAuthAuthorizationResponseBuilder builder= OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
				//设置授权
				builder.setCode(authCode);
				//得到到客户端重定向地
				String redirectUrl = authzRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
				//构建响应
				final OAuthResponse response = builder.location(redirectUrl).buildQueryMessage();
				String responseUrl = response.getLocationUri();
				//根据OAuthResponse返回ResponseEntity响应
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(new URI(responseUrl));
				//授权许可返回到客户端
				return "redirect:"+responseUrl;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("accessToken")
	public HttpEntity getAccessToken(HttpServletRequest request){
		OAuthIssuer oAuthIssuer = null;
		OAuthResponse response = null;
		try {
			//构建OAuth请求
			OAuthTokenRequest tokenRequest = new OAuthTokenRequest(request);
			String authCode = tokenRequest.getParam(OAuth.OAUTH_CODE);
			String clientSecrect = tokenRequest.getClientSecret();
			if(!StringUtils.isEmpty(clientSecrect)){
				//生成Access Token
				oAuthIssuer = new OAuthIssuerImpl(new MD5Generator());
				final String token = oAuthIssuer.accessToken(); 
				//生成OAuth响应
				response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK).setAccessToken(token).buildJSONMessage();
				//根据OAuthResponse生成ResponseEntity
	            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
