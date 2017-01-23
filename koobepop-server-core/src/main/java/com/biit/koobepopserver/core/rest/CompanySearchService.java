package com.biit.koobepopserver.core.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.biit.koobepopserver.logger.KoobepopLogger;
import com.biit.koobepopserver.persistence.dao.IBrandDao;
import com.biit.koobepopserver.persistence.dao.ICompanyDao;
import com.biit.koobepopserver.persistence.dao.IProductDao;
import com.biit.koobepopserver.persistence.dao.IServiceDao;
import com.biit.koobepopserver.persistence.entity.Company;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Rest service to provide sorted data to the mobile App
 * */
@Controller
@Path("/")
//@RestController
public class CompanySearchService {

	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private IServiceDao serviceDao;
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IBrandDao brandDao;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getCompanies")
	public Response getCompanySearch(@RequestParam String petition) {
		SearchFromJson parsedPetition;
		try {
			parsedPetition = parseSearchPetition(petition);
			List<Company> companies = getSortedCompanies(parsedPetition);

			GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
			Gson gson = gsonBuilder.create();

			String json = gson.toJson(companies);
			return Response.ok((String) json, MediaType.APPLICATION_JSON).build();

		} catch (JsonSyntaxException ex) {
			KoobepopLogger.errorMessage(this.getClass().getName(), ex);
			return Response.serverError().entity("{\"error\":\"Json syntax error\"}").build();
		}
	}

	private SearchFromJson parseSearchPetition(String petition) {
		if (petition == null || petition.length() == 0) {
			throw new JsonSyntaxException("Empty parameter not allowed.");
		}
		return new Gson().fromJson(petition, SearchFromJson.class);
	}

	/**
	 * Returns all companies that fulfill the searchCriteria
	 * 
	 * @param searchCriteria
	 * */
	private List<Company> getSortedCompanies(SearchFromJson searchCriteria) {
		List<Company> companies = new ArrayList<Company>();
		companies = companyDao.getAll(searchCriteria.getCountry(), searchCriteria.getBrand(), searchCriteria.getProduct(), searchCriteria.getService());
		Collections.sort(companies, new Comparator<Company>() {
			@Override
			public int compare(Company company1, Company company2) {
				if (company1.getPriority() == null) {
					return -1;
				} else if (company2.getPriority() == null) {
					return 1;
				} else if (company1.getPriority().equals(company2.getPriority())) {
					return company1.getName().compareTo(company2.getName());
				} else {
					return company1.getPriority().compareTo(company2.getPriority());
				}
			}
		});

		return companies;
	}
}
