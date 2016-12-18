package de.mirb.pg.rest.endpoint;

import de.mirb.pg.rest.data.Bean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
  @Path("single")
  @Produces({ MediaType.APPLICATION_XML })
  public Bean singleBeanWithAcceptXml() {

    return new Bean("hey", "XML bean!");
  }

  @GET
  @Path("single/{extension:.+}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM })
  public Bean singleBeanWithExtension(@PathParam("extension") String extension) {

    return new Bean("hey, I'am extended!", "bean ext:" + extension);
  }

  @GET
  @Path("single/{extension:.+}")
  @Produces({ MediaType.APPLICATION_XML })
  public Bean singleBeanWithExtensionAndAcceptXml(@PathParam("extension") String extension) {

    return new Bean("hey, I'am extended!", "Extended XML bean:" + extension);
  }

  @GET
  @Path("multi")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM })
  public List<Bean> multiBean(@Context UriInfo uriInfo) {
    if(uriInfo.getPath().endsWith("/")) {
      return createBeans(10);
    }
    return createBeans(1);
  }

  /*
   * XXX: This do not work:
   *
   * [[FATAL] A resource model has ambiguous (sub-)resource method for HTTP method GET and
   * input mime-types as defined by"@Consumes" and "@Produces" annotations at Java methods
   * public java.util.List de.mirb.pg.rest.endpoint.BeanEndpoint.multiBean(javax.ws.rs.core.UriInfo)
   * and public java.util.List de.mirb.pg.rest.endpoint.BeanEndpoint.multiBeanSlash()
   * at matching regular expression /multi.
   * These two methods produces and consumes exactly the same mime-types and therefore their invocation
   * as a resource methods will always fail.; source='org.glassfish.jersey.server.model.RuntimeResource@709c903f'].
   * Please see server.log for more details.
   */
//  @GET
//  @Path("multi/")
//  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM })
//  public List<Bean> multiBeanSlash() {
//    return createBeans(10);
//  }


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
