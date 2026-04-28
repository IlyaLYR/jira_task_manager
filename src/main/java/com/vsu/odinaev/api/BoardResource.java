package com.vsu.odinaev.api;

import com.vsu.odinaev.model.BoardResponse;
import com.vsu.odinaev.service.BoardService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

/**
 * JAX-RS ресурс для работы с доской задач (Kanban-доской) проекта.
 *
 * <p>Базовый путь: {@code /v1}. Все эндпоинты требуют Bearer JWT-токен.</p>
 *
 * <ul>
 *   <li>{@code GET /v1/board?projectId=...} — получить доску по query-параметру</li>
 *   <li>{@code GET /v1/board/{id}}           — получить доску по UUID в пути</li>
 * </ul>
 */
@Path("/v1")
public class BoardResource {

    @Inject
    BoardService boardService;

    /**
     * Возвращает доску задач проекта по UUID, переданному как query-параметр.
     *
     * @param projectId UUID проекта (обязательный query-параметр)
     * @return HTTP 200 с DTO доски, либо 400/404 при ошибке
     */
    @Operation(summary = "Получить доску по projectId")
    @SecurityRequirement(name = "BearerAuth")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = BoardResponse.class)))
    @APIResponse(responseCode = "404", description = "Project not found")
    @GET
    @Path("/board")
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1BoardGet(@QueryParam("projectId") String projectId) {
        return Response.ok(boardService.getBoardByProjectId(projectId)).build();
    }

    /**
     * Возвращает доску задач проекта по UUID, переданному в пути URL.
     *
     * @param id UUID проекта (path-параметр)
     * @return HTTP 200 с DTO доски, либо 400/404 при ошибке
     */
    @Operation(summary = "Получить доску по ID проекта")
    @SecurityRequirement(name = "BearerAuth")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = BoardResponse.class)))
    @APIResponse(responseCode = "404", description = "Project not found")
    @GET
    @Path("/board/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1BoardIdGet(@PathParam("id") String id) {
        return Response.ok(boardService.getBoardById(id)).build();
    }
}
