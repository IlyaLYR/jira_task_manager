package com.vsu.odinaev.api;

import com.vsu.odinaev.model.RegisterRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/v1")
public class AuthResource {
    @POST
    @Path("/auth/register")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response v1AuthRegisterPost(@Valid RegisterRequest registerRequest) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/auth/token")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces({"application/json"})
    public Response v1AuthTokenPost(@FormParam(value = "grant_type") String grantType, @FormParam(value = "username") String username, @FormParam(value = "password") String password) {
        return Response.ok().entity("magic!").build();
    }
}
