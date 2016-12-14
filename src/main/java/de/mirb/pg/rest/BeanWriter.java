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

/**
 */
@Provider
@Produces(MediaType.APPLICATION_OCTET_STREAM)
public class BeanWriter implements MessageBodyWriter<Bean> {
  @Override
  public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
    return type == Bean.class;
  }

  @Override
  public long getSize(Bean bean, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
//    return bean.toString().getBytes(Charset.forName("utf-8")).length;
    return 0;
  }

  @Override
  public void writeTo(Bean bean, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType,
      MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream)
      throws IOException, WebApplicationException {
    // B
    final byte[] contentAsBytes = bean.getContent().getBytes(Charset.forName("utf-8"));
    outputStream.write(Base64.getEncoder().encode(contentAsBytes));
//    outputStream.write(bean.toString().getBytes(Charset.forName("utf-8")));
  }
}
