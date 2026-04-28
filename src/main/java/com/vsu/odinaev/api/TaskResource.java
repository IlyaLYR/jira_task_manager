package com.vsu.odinaev.api;

import com.vsu.odinaev.model.*;
import com.vsu.odinaev.service.TaskService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.security.Principal;

/**
 * JAX-RS ресурс для управления задачами.
 *
 * <p>Базовый путь: {@code /v1}. Все эндпоинты требуют Bearer JWT-токен.</p>
 *
 * <ul>
 *   <li>{@code GET    /v1/tasks}                  — список задач с пагинацией и фильтрами</li>
 *   <li>{@code POST   /v1/tasks}                  — создать задачу</li>
 *   <li>{@code GET    /v1/tasks/{taskId}}          — получить задачу по UUID</li>
 *   <li>{@code PATCH  /v1/tasks/{taskId}}          — частично обновить задачу</li>
 *   <li>{@code POST   /v1/tasks/{taskId}/status}   — сменить статус задачи</li>
 * </ul>
 */
@Path("/v1")
public class TaskResource {

    @Inject
    TaskService taskService;

    @Context
    SecurityContext securityContext;

    /**
     * Возвращает постраничный список задач с необязательной фильтрацией.
     *
     * @param page      номер страницы (по умолчанию 1)
     * @param limit     размер страницы (по умолчанию 20)
     * @param projectId UUID проекта для фильтрации; {@code null} — без фильтра
     * @param status    статус для фильтрации; {@code null} — без фильтра
     * @return HTTP 200 с DTO пагинированного списка задач
     */
    @Operation(summary = "Получить задачи (с пагинацией)")
    @SecurityRequirement(name = "BearerAuth")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = PaginatedTasksResponse.class)))
    @GET
    @Path("/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1TasksGet(
            @QueryParam("page") @DefaultValue("1") Integer page,
            @QueryParam("limit") @DefaultValue("20") Integer limit,
            @QueryParam("projectId") String projectId,
            @QueryParam("status") TaskStatus status) {
        return Response.ok(taskService.getTasks(page, limit, projectId, status)).build();
    }

    /**
     * Создаёт новую задачу в указанном проекте.
     *
     * <p>Автором задачи автоматически становится текущий аутентифицированный пользователь.</p>
     *
     * @param createTaskRequest тело запроса с данными задачи (валидируется)
     * @return HTTP 201 с DTO созданной задачи
     */
    @Operation(summary = "Создать задачу")
    @SecurityRequirement(name = "BearerAuth")
    @APIResponse(responseCode = "201",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = TaskResponse.class)))
    @POST
    @Path("/tasks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1TasksPost(@Valid @NotNull CreateTaskRequest createTaskRequest) {
        return Response.status(Response.Status.CREATED)
                .entity(taskService.createTask(createTaskRequest, currentUserLogin()))
                .build();
    }

    /**
     * Возвращает задачу по её UUID.
     *
     * @param taskId UUID задачи (path-параметр)
     * @return HTTP 200 с DTO задачи, либо 404 если не найдена
     */
    @Operation(summary = "Получить задачу по ID")
    @SecurityRequirement(name = "BearerAuth")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = TaskResponse.class)))
    @APIResponse(responseCode = "404", description = "Not found")
    @GET
    @Path("/tasks/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1TasksTaskIdGet(@PathParam("taskId") String taskId) {
        return Response.ok(taskService.getTaskById(taskId)).build();
    }

    /**
     * Частично обновляет задачу (PATCH-семантика).
     *
     * <p>Поля, не переданные в теле запроса (равные {@code null}), остаются без изменений.</p>
     *
     * @param taskId            UUID задачи (path-параметр)
     * @param updateTaskRequest тело запроса с полями для обновления (валидируется)
     * @return HTTP 200 с DTO обновлённой задачи, либо 404 если не найдена
     */
    @Operation(summary = "Обновить задачу (PATCH)")
    @SecurityRequirement(name = "BearerAuth")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = TaskResponse.class)))
    @APIResponse(responseCode = "404", description = "Not found")
    @PATCH
    @Path("/tasks/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1TasksTaskIdPatch(
            @PathParam("taskId") String taskId,
            @Valid UpdateTaskRequest updateTaskRequest) {
        return Response.ok(taskService.updateTask(taskId, updateTaskRequest)).build();
    }

    /**
     * Устанавливает новый статус задачи.
     *
     * @param taskId                       UUID задачи (path-параметр)
     * @param tasksTaskIdStatusPostRequest тело запроса с новым статусом (валидируется)
     * @return HTTP 200 с DTO задачи с обновлённым статусом, либо 404 если не найдена
     */
    @Operation(summary = "Сменить статус задачи")
    @SecurityRequirement(name = "BearerAuth")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = TaskResponse.class)))
    @APIResponse(responseCode = "404", description = "Not found")
    @POST
    @Path("/tasks/{taskId}/status")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1TasksTaskIdStatusPost(
            @PathParam("taskId") String taskId,
            @Valid TasksTaskIdStatusPostRequest tasksTaskIdStatusPostRequest) {
        return Response.ok(taskService.changeStatus(taskId, tasksTaskIdStatusPostRequest.getStatus())).build();
    }

    /**
     * Извлекает логин текущего пользователя из контекста безопасности.
     *
     * @return логин аутентифицированного пользователя
     * @throws NotAuthorizedException если токен отсутствует или недействителен
     */
    private String currentUserLogin() {
        Principal principal = securityContext.getUserPrincipal();
        if (principal == null) {
            throw new NotAuthorizedException("Bearer realm=\"jira\"");
        }
        return principal.getName();
    }
}
