package de.bcxp.thesis.gitops.application.swagger;

import de.bcxp.thesis.gitops.application.config.ApplicationConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
@Configuration
public class OpenApiSpecificationConfiguration {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .tags(List.of(
            new Tag().name("1) Version Information"),
            new Tag().name("Utility")
        ))
        .info(new Info()
            .title("GitOps Evaluation - Spring Boot Microservice Example")
            .version("DRAFT")
            .description("""
                         Example application for the GitOps evaluation thesis.
                         
                         ## Available endpoints:
                         
                         * `GET /api/v1/version`
                           Application version information based on environment variables, which are set differently depending on the environment.
                           
                         
                         ## Usage
                         
                         1. Select an endpoint.
                         2. Click on the "Try it out" button.
                         3. Click on the "Execute" button.
                         """));
  }
}
