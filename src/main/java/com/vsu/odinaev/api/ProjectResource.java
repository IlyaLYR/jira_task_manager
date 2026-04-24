package com.vsu.odinaev.api;

import com.vsu.odinaev.model.V1ProjectsPostRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/v1")
public class ProjectResource {

    @GET
    @Path("/projects")
    @Produces({ "application/json" })
    public Response v1ProjectsGet() {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/projects")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response v1ProjectsPost(@Valid @NotNull V1ProjectsPostRequest v1ProjectsPostRequest) {
        return Response.ok().entity("magic!").build();
    }

}
