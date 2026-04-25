package com.vsu.odinaev.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/v1")
public class BoardResource {

    @Operation(summary = "Получить доску по проекту (ID)")
    @GET
    @Path("/board")
    @Produces({"application/json"})
    public Response v1BoardGet(@QueryParam("projectId") String projectId) {
        return Response.ok().entity("magic!").build();
    }

    @Operation(summary = "Получить доску по ID")
    @GET
    @Path("/board/{id}")
    @Produces({"application/json"})
    public Response v1BoardIdGet(@PathParam("id") String id) {
        return Response.ok().entity("magic!").build();
    }

}
