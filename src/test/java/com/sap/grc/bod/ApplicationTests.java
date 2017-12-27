package com.sap.grc.bod;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sap.grc.bod.model.BusinessObject;
import com.sap.grc.bod.model.BusinessObjectField;
import com.sap.grc.bod.model.BusinessObjectFieldId;
import com.sap.grc.bod.model.BusinessObjectFieldText;
import com.sap.grc.bod.repository.BusinessObjectFieldRepository;
import com.sap.grc.bod.repository.BusinessObjectRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {


	
	@Test
	public void contextLoads() {
	}
	
	
}