package com.vsu.odinaev.api;

import com.vsu.odinaev.model.CreateTaskRequest;
import com.vsu.odinaev.model.TaskStatus;
import com.vsu.odinaev.model.TasksTaskIdStatusPostRequest;
import com.vsu.odinaev.model.UpdateTaskRequest;
import com.vsu.odinaev.service.TaskService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.Operation;

import java.security.Principal;

@Path("/v1")
public class TaskResource {

    @Inject
    TaskService taskService;

    @Context
    SecurityContext securityContext;

    @Operation(summary = "Получить задачи (с пагинацией)")
    @GET
    @Path("/tasks")
    @Produces({"application/json"})
    public Response v1TasksGet(
            @QueryParam("page") @DefaultValue("1") Integer page,
            @QueryParam("limit") @DefaultValue("20") Integer limit,
            @QueryParam("projectId") String projectId,
            @QueryParam("status") TaskStatus status) {
        return Response.ok(taskService.getTasks(page, limit, projectId, status)).build();
    }

    @Operation(summary = "Создать задачу")
    @POST
    @Path("/tasks")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response v1TasksPost(@Valid @NotNull CreateTaskRequest createTaskRequest) {
        return Response.status(Response.Status.CREATED)
                .entity(taskService.createTask(createTaskRequest, currentUserLogin()))
                .build();
    }

    @Operation(summary = "Получить задачу по ID")
    @GET
    @Path("/tasks/{taskId}")
    @Produces({"application/json"})
    public Response v1TasksTaskIdGet(@PathParam("taskId") String taskId) {
        return Response.ok(taskService.getTaskById(taskId)).build();
    }

    @Operation(summary = "Обновить задачу по ID")
    @PATCH
    @Path("/tasks/{taskId}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response v1TasksTaskIdPatch(
            @PathParam("taskId") String taskId,
            @Valid UpdateTaskRequest updateTaskRequest) {
        return Response.ok(taskService.updateTask(taskId, updateTaskRequest)).build();
    }

    @Operation(summary = "Сменить статус задачи")
    @POST
    @Path("/tasks/{taskId}/status")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response v1TasksTaskIdStatusPost(
            @PathParam("taskId") String taskId,
            @Valid TasksTaskIdStatusPostRequest tasksTaskIdStatusPostRequest) {
        return Response.ok(taskService.changeStatus(taskId, tasksTaskIdStatusPostRequest.getStatus())).build();
    }

    private String currentUserLogin() {
        Principal principal = securityContext.getUserPrincipal();
        if (principal == null) {
            throw new NotAuthorizedException("Bearer realm=\"jira\"");
        }
        return principal.getName();
    }
}
