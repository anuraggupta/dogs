package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("dogs")
public class Dogs {

	private LocalDB localdb = new LocalDB();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DogDetails getDog(@PathParam("id") String id) {

		return localdb.getById(id);

	}

	@GET
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DogDetails> searchDogs(@QueryParam("sizeMin") int sizeMin, @QueryParam("sizeMax") int sizeMax,
			@QueryParam("hair") String hair, @QueryParam("breed") String breed, @QueryParam("pageSize") int pageSize,
			@QueryParam("page") int page) {

		List<DogDetails> result = new ArrayList<>();

		List<DogDetails> search = localdb.searchBy(sizeMin, sizeMax, hair, breed);

		if (search.size() == 0) {
			return result;
		}

		// Sub list as per result page size and page number

		if (pageSize < 1) {
			// default value for page size
			pageSize = 3;
		}
		if (page < 1) {
			page = 1;
		}

		if (page > 0) {

			int startIndex = pageSize * page - pageSize;
			int endIndex = startIndex + pageSize;

			if (endIndex >= search.size()) {
				endIndex = search.size();
			}

			result.addAll(search.subList(startIndex, endIndex));

		}

		return result;

	}

}
