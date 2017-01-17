package de.mirb.pg.rest.io;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 */
@Provider
public class AnyResponseFilter implements ContainerResponseFilter {
  @Override
  public void filter(ContainerRequestContext containerRequestContext,
      ContainerResponseContext containerResponseContext)
        throws IOException {

    final String accept = containerRequestContext.getHeaderString("Accept");
    if(accept != null && accept.contains("*")) {
      containerResponseContext.setEntity(containerResponseContext.getEntity(),
          new Annotation[0], MediaType.APPLICATION_JSON_TYPE);
    }
  }
}
