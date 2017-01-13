package com.biit.koobepopserver.core.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.biit.koobepopserver.core.jsoncreator.CompanySerializer;
import com.biit.koobepopserver.logger.KoobepopLogger;
import com.biit.koobepopserver.persistence.dao.IBrandDao;
import com.biit.koobepopserver.persistence.dao.ICompanyDao;
import com.biit.koobepopserver.persistence.dao.IProductDao;
import com.biit.koobepopserver.persistence.dao.IServiceDao;
import com.biit.koobepopserver.persistence.entity.Company;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

@Controller
@Path("/")
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
	public Response getCompanySearch(String petition) {
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
	 * 
	 * */
	private List<Company> getSortedCompanies(SearchFromJson searchCriteria) {
		List<Company> companies = companyDao.getAll(null, searchCriteria.getCountry());
		if (searchCriteria.getService() != null && searchCriteria.getService() != "") {
			companies.retainAll(serviceDao.getAll(searchCriteria.getService()));
		}
		if (searchCriteria.getProduct() != null && searchCriteria.getProduct() != "") {
			companies.retainAll(productDao.getAll(searchCriteria.getProduct()));
		}
		if (searchCriteria.getBrand() != null && searchCriteria.getBrand() != "") {
			companies.retainAll(brandDao.getAll(searchCriteria.getBrand()));
		}
		return companies;
	}

}
