package com.JB.Project.Coupons.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class Config {
    @Bean
    public OpenAPI defineOpenAPI(){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Project Coupons With Spring FrameWork");

        Contact myContact = new Contact();
        myContact.setName("Dimitri Makarkov");
        myContact.setEmail("dima9650@gmail.com");

        Info info = new Info()
                .title("Coupons Management System API")
                .version("1.0")
                .description("This API Exposes Endpoints To Manage Coupons")
                .contact(myContact);

        return new OpenAPI().info(info).servers(List.of(server));

    }
}
