package com.sap.grc.bod.service;

import java.util.List;

import com.sap.grc.bod.controller.dto.BusinessObjectDTO;
import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.UserBean;

public interface BusinessObjectService
{
	public BusinessObject createBusinessObject(BusinessObjectDTO businessObjectDTO, UserBean user);
	public BusinessObject updateBusinessObject(String businessObjectId, BusinessObjectDTO businessObjectDTO);
	public List<BusinessObject> getAllBusinessObject();
	public BusinessObject findBybusinessObjectId(String businessObjectId);
	public BusinessObject findBybusinessObjectName(String businessObjectName);
	public void deleteBybusinessObjectId(String businessObjectId);
}
