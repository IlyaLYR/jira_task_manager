package com.vsu.odinaev.api;

import com.vsu.odinaev.model.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/v1")
public class TaskResource {

    @GET
    @Path("/tasks")
    @Produces({"application/json"})
    public Response v1TasksGet(@QueryParam("page") @DefaultValue("1") Integer page, @QueryParam("limit") @DefaultValue("20") Integer limit, @QueryParam("projectId") String projectId, @QueryParam("status") TaskStatus status) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/tasks")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response v1TasksPost(@Valid @NotNull CreateTaskRequest createTaskRequest) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/tasks/{taskId}")
    @Produces({"application/json"})
    public Response v1TasksTaskIdGet(@PathParam("taskId") String taskId) {
        return Response.ok().entity("magic!").build();
    }

    @PATCH
    @Path("/tasks/{taskId}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response v1TasksTaskIdPatch(@PathParam("taskId") String taskId, @Valid UpdateTaskRequest updateTaskRequest) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/tasks/{taskId}/status")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response v1TasksTaskIdStatusPost(@PathParam("taskId") String taskId, @Valid TasksTaskIdStatusPostRequest tasksTaskIdStatusPostRequest) {
        return Response.ok().entity("magic!").build();
    }

}
