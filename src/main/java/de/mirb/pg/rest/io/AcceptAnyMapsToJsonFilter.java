package de.mirb.pg.rest.io;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 */
@Provider
@PreMatching
public class AcceptAnyMapsToJsonFilter implements ContainerRequestFilter {

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    final String accept = requestContext.getHeaderString("Accept");
    if(accept == null || accept.equals("") || accept.equals("*") || accept.equals("*/*")) {
      requestContext.getHeaders().putSingle("Accept", MediaType.APPLICATION_JSON);
    }
  }
}
