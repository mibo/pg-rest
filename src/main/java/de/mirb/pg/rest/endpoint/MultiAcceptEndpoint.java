package de.mirb.pg.rest.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("multi")
@Produces(MediaType.APPLICATION_JSON)
public class MultiAcceptEndpoint {

  @GET
  public Response defaultFromClass() {
    return Response.ok().entity(buildJson("Method", "Default from class.")).build();
  }


  /*
   * Not allowed because 'defaultFromClass' has same @Produces
   */
//  @GET
//  @Produces(MediaType.APPLICATION_JSON)
//  public Response someAsClass() {
//    return Response.ok().entity(buildJson("Method", "Same as class.")).build();
//  }

  @GET
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
  public Response differentFromClass() {

    return Response.ok().entity(buildJson("Method", "Different From Class.")).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_XML)
  public Response differentFromClassSingle() {

    return Response.ok().entity(buildJson("Method", "Different From Class Single.")).build();
  }

  private String buildJson(String ... values) {

    if(values.length % 2 > 0) {
      return "{}";
    }

    Map<String, String> para2Value = new HashMap<>();
    for (int i = 0; i < values.length; i+=2) {
      para2Value.put(values[i], values[i+1]);
    }

    return buildJson(para2Value);
  }

  private String buildJson(Map<String, String> para2Value) {
    StringBuilder b = new StringBuilder("{");
    para2Value.forEach((k, v) -> {
      if(b.length() > 1) {
        b.append(",");
      }
      b.append("\"").append(k).append("\":\"").append(v).append("\"");
    });
    return b.append("}").toString();
  }
}
