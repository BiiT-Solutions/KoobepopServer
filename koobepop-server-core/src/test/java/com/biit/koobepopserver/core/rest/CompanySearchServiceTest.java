package com.biit.koobepopserver.core.rest;


import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.PrintingResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/test/webapp")
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Test(groups = { "companySearchServiceTest" })
public class CompanySearchServiceTest  extends AbstractTransactionalTestNGSpringContextTests {
	@Autowired
	private WebApplicationContext applicationContext;
	
	private MockMvc mockMvc;
	  
	@Before
	public void createClient(){
		 this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	
	@Test
	public void testCompanySearch() throws Exception{
		System.out.println("Tests ");
		System.out.println(this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/getCompanies")
											.contentType(MediaType.APPLICATION_JSON)
											.content("{\"cads\"6546+84684Commonwealth"))
											.andExpect(MockMvcResultMatchers.status().isOk()));
		   
		    
	}

	
}
