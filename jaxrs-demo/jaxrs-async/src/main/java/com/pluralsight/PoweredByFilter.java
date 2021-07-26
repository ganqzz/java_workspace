package com.pluralsight;

import java.lang.annotation.Annotation;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
@PoweredBy
public class PoweredByFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) {
        for (Annotation a : responseContext.getEntityAnnotations()) {
            if (a.annotationType() == PoweredBy.class) {
                String value = ((PoweredBy) a).value();
                responseContext.getHeaders().add("X-Powered-By", value);
            }
        }
    }
}
