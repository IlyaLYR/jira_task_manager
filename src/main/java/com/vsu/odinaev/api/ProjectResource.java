package com.vsu.odinaev.api;

import com.vsu.odinaev.model.ProjectsPostRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/v1")
public class ProjectResource {

    @Operation(summary = "Получить проекты")
    @GET
    @Path("/projects")
    @Produces({"application/json"})
    public Response v1ProjectsGet() {
        return Response.ok().entity("magic!").build();
    }

    @Operation(summary = "Создать проект")
    @POST
    @Path("/projects")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response v1ProjectsPost(@Valid ProjectsPostRequest projectsPostRequest) {
        return Response.ok().entity("magic!").build();
    }

}
