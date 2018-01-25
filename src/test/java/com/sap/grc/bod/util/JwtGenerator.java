package com.sap.grc.bod.util;

public class JwtGenerator
{
//	
//	public String getTokenForAuthorizationHeader( UserInfo userInfo ){
//		return "Bearer " + getToken(userInfo);
//	}
//	
//	private String getToken( UserInfo userInfo ){
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode root = mapper.createObjectNode();
//		root.put("client_id", userInfo.getCliendId());
//		root.put("exp", userInfo.getExp());
//		root.set("scope", userInfo.getScope());
//		root.put("user_name", userInfo.getUserName());
//		root.put("user_id", userInfo.getUserId());
//		root.put("family_name", userInfo.getFamilyName());
//		root.put("given_name", userInfo.getGivenName());
//		root.put("email", userInfo.getEmail());
//		root.put("zid", userInfo.getZid());
//		return getTokenForClaims(root.toString());
//	}
//	
//	private String getTokenForClaims( String claims ){
////		RsaSigner signer = new RsaSigner(readFromFile("/privateKey.txt"));
////		return JwtHelper.encode(claims, signer).getEncoded();
//		return "";
//	}
//	
//	private String readFromFile( String path ){
//		InputStream is = null;
//		try {
//			is = getClass().getResourceAsStream(path);
//			return IOUtils.toString(is, Charset.forName("UTF-8"));
//		}
//		catch( IOException exception ) {
//			throw new IllegalStateException(exception);
//		}
//		finally {
//			IOUtils.closeQuietly(is);
//		}
//	}
}
