package de.mirb.pg.rest;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

/**
 */
@Provider
@Produces(MediaType.APPLICATION_OCTET_STREAM)
public class MultiBeanWriter implements MessageBodyWriter<List<Bean>> {
  @Override
  public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
    return List.class.isAssignableFrom(aClass);
  }

  @Override
  public long getSize(List<Bean> beans, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
    return 0;
  }

  @Override
  public void writeTo(List<Bean> beans, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
    StringBuilder b = new StringBuilder("[");

    for (Bean bean : beans) {
      if(b.length() > 1) {
        b.append(", ");
      }
      final byte[] contentAsBytes = bean.getContent().getBytes(Charset.forName("utf-8"));
      b.append(Base64.getEncoder().encodeToString(contentAsBytes));
    }
    b.append("]");

    outputStream.write(b.toString().getBytes(Charset.forName("utf-8")));
  }
}
