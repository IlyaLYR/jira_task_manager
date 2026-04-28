package com.vsu.odinaev.api;

import com.vsu.odinaev.model.ProjectResponse;
import com.vsu.odinaev.model.ProjectsPostRequest;
import com.vsu.odinaev.service.ProjectService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.security.Principal;

/**
 * JAX-RS ресурс для управления проектами.
 *
 * <p>Базовый путь: {@code /v1}. Все эндпоинты требуют Bearer JWT-токен.</p>
 *
 * <ul>
 *   <li>{@code GET  /v1/projects} — получить список всех проектов</li>
 *   <li>{@code POST /v1/projects} — создать новый проект</li>
 * </ul>
 */
@Path("/v1")
public class ProjectResource {

    @Inject
    ProjectService projectService;

    @Context
    SecurityContext securityContext;

    /**
     * Возвращает список всех проектов.
     *
     * @return HTTP 200 с массивом DTO проектов
     */
    @Operation(summary = "Получить проекты")
    @SecurityRequirement(name = "BearerAuth")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = ProjectResponse.class)))
    @GET
    @Path("/projects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1ProjectsGet() {
        return Response.ok(projectService.getProjects()).build();
    }

    /**
     * Создаёт новый проект.
     *
     * <p>Владельцем проекта становится текущий аутентифицированный пользователь
     * (логин извлекается из JWT через {@link SecurityContext}).</p>
     *
     * @param projectsPostRequest тело запроса с названием и описанием проекта (валидируется)
     * @return HTTP 201 с DTO созданного проекта
     */
    @Operation(summary = "Создать проект")
    @SecurityRequirement(name = "BearerAuth")
    @APIResponse(responseCode = "201",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProjectResponse.class)))
    @POST
    @Path("/projects")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1ProjectsPost(@Valid ProjectsPostRequest projectsPostRequest) {
        return Response.status(Response.Status.CREATED)
                .entity(projectService.createProject(projectsPostRequest, currentUserLogin()))
                .build();
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
