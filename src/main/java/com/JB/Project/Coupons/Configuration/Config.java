package com.JB.Project.Coupons.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class Config {
    @Bean
    public OpenAPI defineOpenAPI(@Value("springdoc-openapi-ui") String serviceTitle, @Value("1.6.12") String serviceVersion){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Project Coupons With Spring FrameWork");

        Contact myContact = new Contact();
        myContact.setName("Dimitri Makarkov");
        myContact.setEmail("dima9650@gmail.com");

        Info info = new Info()
                .title("Coupons Management System API")
                .version("2.0")
                .description("This API Exposes Endpoints To Manage Coupons")
                .contact(myContact);

        final String securitySchemeName = "bearerAuth";
//        return new OpenAPI().info(info).servers(List.of(server));
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(
                                securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )

//                .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
                .info(info.version(serviceVersion)).servers(List.of(server));
    }
}
