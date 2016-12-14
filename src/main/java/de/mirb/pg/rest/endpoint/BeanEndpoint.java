package de.mirb.pg.rest.endpoint;

import de.mirb.pg.rest.data.Bean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Path("bean")
public class BeanEndpoint {

  @GET
  @Path("response/single")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM })
  public Response single() {

    return Response.ok(new Bean("hey", "Response Bean!")).build();
  }

  @GET
  @Path("single")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM })
  public Bean singleBean() {

    return new Bean("hey", "bean!");
  }

  @GET
  @Path("multi")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM })
  public List<Bean> multiBean() {

    return createBeans(10);
  }

  @GET
  @Path("response/multi")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM })
  public Response multi() {

    return Response.ok(createBeans(10)).build();
  }

  private List<Bean> createBeans(int amount) {
    List<Bean> beans = new ArrayList<>();

    for (int i = 0; i < amount; i++) {
       beans.add(new Bean("Bean_" + i, "Content_" + i));
    }

    return beans;
  }
}
