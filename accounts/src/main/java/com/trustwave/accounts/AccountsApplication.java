package com.trustwave.accounts;

import com.trustwave.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API documentation",
                version = "v1",
                description = "TrustWave Bank Accounts microservice REST API documentation",
                contact = @Contact(
                        name = "Harshal Ganbote",
                        url = "https://trustwavebank.com",
                        email = "admin@trustwavebank.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://trustwavebank.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description =  "TrustWave Bank Accounts microservice REST API Documentation",
                url = "https://www.trustwavebank.com/swagger-ui.html"
        )
)
public class AccountsApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountsApplication.class, args);
  }

}
