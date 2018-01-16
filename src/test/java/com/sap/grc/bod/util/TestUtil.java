package com.sap.grc.bod.util;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.grc.bod.constant.TestConstant;

public class TestUtil
{
	public static String toJson( Object object )
		throws JsonProcessingException
	{
		return new ObjectMapper().writeValueAsString(object);
	}

	public static MockHttpServletRequestBuilder buildPostRequest( String url, Object content, String jwt )
		throws Exception
	{
		return post(url).content(TestUtil.toJson(content)).contentType(APPLICATION_JSON_UTF8).header(
			TestConstant.HEADER_Authorization,
			jwt);
	}

	public static MockHttpServletRequestBuilder buildGetRequest( String url, String jwt )
		throws Exception
	{
		return get(url).contentType(APPLICATION_JSON_UTF8).header(TestConstant.HEADER_Authorization, jwt);
	}

	public static MockHttpServletRequestBuilder buildPutRequest( String url, Object content, String jwt )
		throws Exception
	{
		return put(url).content(TestUtil.toJson(content)).contentType(APPLICATION_JSON_UTF8).header(
			TestConstant.HEADER_Authorization,
			jwt);
	}

	public static MockHttpServletRequestBuilder buildDeleteRequest( String url, String jwt )
		throws Exception
	{
		return delete(url).contentType(APPLICATION_JSON_UTF8).header(TestConstant.HEADER_Authorization, jwt);
	}
}
