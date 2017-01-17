package de.mirb.pg.rest.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 */
@Path("message")
public class StringEndpoint {

  @GET
  @Path("/plain")
  @Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM })
  public String plainTest() {

    return "hey duke!";
  }

  @GET
  @Path("{rawpath:.+}")
  @Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM })
  public String rawPath(@PathParam("rawpath") String path, @Context HttpHeaders headers) {
    final String result = "hey duke@path: " + path;
    if(headers.getAcceptableMediaTypes().contains(MediaType.APPLICATION_OCTET_STREAM_TYPE)) {
      return Base64.getEncoder().encodeToString(result.getBytes(Charset.forName("utf-8")));
    }
    return result;
  }

  @GET
  @Path("{rawpath:.+}")
  @Produces({ MediaType.APPLICATION_JSON})
  public String rawJsonPath(@PathParam("rawpath") String path) {

    return "{ \"hey\": { \"duke@path\": \"" + path + "\" } }";
  }

  @GET
  @Path("{headpath:.+}/extension/{trailpath:.*}")
  @Produces({ MediaType.APPLICATION_JSON})
  public String rawJsonHeadExtensionTrail(@PathParam("headpath") String headpath, @PathParam("trailpath") String trailpath) {

    return "{ \"hey\": { \"duke@path-extension\": { \"head\": \"" + headpath + "\", \"trail\": \"" + trailpath + "\" } } }";
  }

  @GET
  @Path("{path:.+}/extension")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
  public String rawJsonPathExtension(@PathParam("path") String path) {

    return "{ \"hey\": { \"duke@path-extension\": \"" + path + "\" } }";
  }

  @GET
  @Path("{rawpath:.+}")
  @Produces(MediaType.WILDCARD)
  public Response pathWithWildcard(@PathParam("rawpath") String path) {
    // default is mapped to json
    return Response.ok(rawJsonPath(path))
        .header("Content-Type", MediaType.APPLICATION_JSON)
        .header("x-modified", "default-mapping")
        .build();
  }
}
