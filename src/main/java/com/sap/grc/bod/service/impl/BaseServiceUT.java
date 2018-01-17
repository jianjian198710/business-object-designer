package com.sap.grc.bod.service.impl;

import org.mockito.MockitoAnnotations;

public class BaseServiceUT
{
    protected void setUp(Object testClass){
        MockitoAnnotations.initMocks(testClass);
    }

}
