package com.vsu.odinaev;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

/**
 * Точка входа JAX-RS приложения.
 *
 * <p>Определяет метаданные OpenAPI (заголовок и версия API) и схему
 * безопасности {@code BearerAuth} — JWT-токен, передаваемый в заголовке
 * {@code Authorization: Bearer &lt;token&gt;}.</p>
 *
 * <p>Swagger UI доступен по адресу {@code /q/swagger-ui/} в режиме разработки.</p>
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Jira API",
                version = "1.0.0",
                description = "Jira like task management API"
        )
)
@SecurityScheme(
        securitySchemeName = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
//@SecurityScheme(
//        securitySchemeName = "BasicAuth",
//        type = SecuritySchemeType.HTTP,
//        scheme = "basic"
//)
public class JiraApplication extends Application {
}