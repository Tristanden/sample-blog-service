package fr.liksi.blog.iam.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiBusiness() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo("Identity Authorization Mgmt", "" +
                        "Service in charge of handling accounts.", "1.0", "",  new Contact("Liksi", "", ""), "license name", "", ApiInfo.DEFAULT.getVendorExtensions()))
                .securitySchemes(Lists.newArrayList(basicAuth()))
                .securityContexts(Lists.newArrayList(securityContext()))
                .protocols(Sets.newHashSet("http","https"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.liksi.blog.iam.api"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.METHOD)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private BasicAuth basicAuth() {
        return new BasicAuth("basicAuth");
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope globalScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[] {globalScope};
        return Arrays.asList(new SecurityReference("basicAuth", authorizationScopes));
    }
}
