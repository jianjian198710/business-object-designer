package com.sap.grc.bod.service;

import org.mockito.MockitoAnnotations;

public class BaseServiceUT
{
	protected void setUp(Object testClass){
		MockitoAnnotations.initMocks(testClass);
	}
}
