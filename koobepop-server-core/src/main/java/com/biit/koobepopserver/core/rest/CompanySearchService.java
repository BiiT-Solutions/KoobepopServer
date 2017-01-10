package com.biit.koobepopserver.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.biit.koobepopserver.logger.KoobepopLogger;
import com.biit.koobepopserver.persistence.entity.Company;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


@Component
@Path("/")
public class CompanySearchService {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getCompanyInvoicePrices")
	public Response getCompanySearch(String petition){
	
		//TODO end this thing
		SearchFromJson parsedPetition;
		try {
			parsedPetition = parseSearchPetition(petition);
			List<Company> companies = searchCompanies(parsedPetition);
			
			String data = "'data':'some dummy data'";
			return Response.ok((String) data, MediaType.APPLICATION_XML).build();
		
		} catch (JsonSyntaxException ex) {
			KoobepopLogger.errorMessage(this.getClass().getName(), ex);
			return Response.serverError().entity("{\"error\":\"Json syntax error\"}").build();
		}
	}
	private List<Company> searchCompanies(SearchFromJson searchCriteria){
		
		return new ArrayList<Company>();
	}
	
	private SearchFromJson parseSearchPetition(String petition){
		if (petition == null || petition.length() == 0) {
			throw new JsonSyntaxException("Empty parameter not allowed.");
		}
		return new Gson().fromJson(petition, SearchFromJson.class);	
	}
}
