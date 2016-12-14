package de.mirb.pg.rest.io;

import de.mirb.pg.rest.data.Bean;

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
public class BeanWriter {
  @Provider
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public static class SingleBeanWriter implements MessageBodyWriter<Bean> {
    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
      return type == Bean.class;
    }

    @Override
    public long getSize(Bean bean, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
      return -1;
    }

    @Override
    public void writeTo(Bean bean, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream)
        throws IOException, WebApplicationException {

      final byte[] contentAsBytes = bean.getContent().getBytes(Charset.forName("utf-8"));
      outputStream.write(Base64.getEncoder().encode(contentAsBytes));
    }
  }

  @Provider
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public static class MultiBeanWriter implements MessageBodyWriter<List<Bean>> {
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

      beans.forEach(bean -> {
        if(b.length() > 1) {
          b.append(", ");
        }
        final byte[] contentAsBytes = bean.getContent().getBytes(Charset.forName("utf-8"));
        b.append(Base64.getEncoder().encodeToString(contentAsBytes));
      });
      b.append("]");

      outputStream.write(b.toString().getBytes(Charset.forName("utf-8")));
    }
  }

}
