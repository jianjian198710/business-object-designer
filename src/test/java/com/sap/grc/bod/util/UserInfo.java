package com.sap.grc.bod.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sap.grc.bod.constant.TestConstant;

import lombok.Data;

public @Data class UserInfo
{
	private String cliendId = TestConstant.CLIENT_ID;
	private Integer exp = Integer.MAX_VALUE;
	private ArrayNode scope = null;
	private String userName = TestConstant.USER_NAME;
	private String userId = TestConstant.USER_ID;
	private String email = TestConstant.EMAIL;
	private String zid = TestConstant.IDENTITY_ZONE;
	private String familyName = TestConstant.FAMILY_NAME;
	private String givenName = TestConstant.GIVEN_NAME;
}
