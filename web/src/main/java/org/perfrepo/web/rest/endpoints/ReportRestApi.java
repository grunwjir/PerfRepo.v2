package org.perfrepo.web.rest.endpoints;

import org.perfrepo.dto.report.ReportDto;
import org.perfrepo.dto.report.ReportSearchCriteria;
import org.perfrepo.dto.util.SearchResult;
import org.perfrepo.web.adapter.ReportAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Jiri Grunwald (grunwjir@gmail.com)
 */
@Path("/reports")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReportRestApi {

   @Inject
   private ReportAdapter reportAdapter;

   @GET
   @Path("/{id}")
   public Response get(@PathParam("id") Long reportId) {
      ReportDto report = reportAdapter.getReport(reportId);

      return Response.ok(report).build();
   }

   @POST
   @Path("/search")
   public Response search(ReportSearchCriteria searchParams) {
      SearchResult<ReportDto> result = reportAdapter.searchReports(searchParams);

      return Response
              .status(Response.Status.OK)
              .header("X-Pagination-Total-Count", result.getTotalCount())
              .header("X-Pagination-Current-Page", result.getCurrentPage())
              .header("X-Pagination-Page-Count", result.getPageCount())
              .header("X-Pagination-Per-Page", result.getPerPage())
              .entity(result.getData()).build();
   }

   @DELETE
   @Path("/{id}")
   public Response delete(@PathParam("id") Long reportId) {
      reportAdapter.removeReport(reportId);

      return Response.noContent().build();
   }

   @GET
   public Response getAll() {
      List<ReportDto> allReports = reportAdapter.getAllReports();

      return Response.ok().entity(allReports).build();
   }
}