package com.online.crossroads.controllers;

import com.online.crossroads.type.StatusType;
import com.online.crossroads.util.builder.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 02-02-2016.
 */

@Component
@Path("/v1.0")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GET
    @Path("/users")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllCountries(
            @Context HttpServletRequest request) {
        try {
            List<StatusType> statusTypes = Arrays.asList(StatusType.values());
            return Response.ok().entity(ResponseBuilder
                    .build(Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase(), statusTypes))
                    .build();
        } catch (Exception ex) {
            logger.error("Failed to find all platforms", ex);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ResponseBuilder
//                    .build(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
//                            messageSource.getMessage("transaction.failure", null, request.getLocale()), null)).build();

            return null;

        }
    }
}
