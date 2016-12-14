package de.mirb.pg.rest.endpoint;

import de.mirb.pg.rest.data.NotSupportedBean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("none")
public class NoneEndpoint {

  @GET
  @Path("nothing")
  public Response doNothing() {
    return Response.ok().entity("Nothing to see.").header("Content-Type", "application/nothing").build();
  }

  @GET
  @Path("json")
  @Produces(MediaType.APPLICATION_JSON)
  public Response doNothingAsJson() {
    return Response.ok().entity("{ \"Say\": \"Nothing to see.\" }").build();
  }

  @GET
  @Path("plain")
  @Produces(MediaType.TEXT_PLAIN)
  public Response doBean() {

    return Response.ok().entity(new NotSupportedBean("Not supported", "Nothing to see."))
        .build();
  }

}
