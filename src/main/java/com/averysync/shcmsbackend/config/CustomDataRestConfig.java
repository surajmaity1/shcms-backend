package com.averysync.shcmsbackend.config;

import com.averysync.shcmsbackend.entity.Doctor;
import com.averysync.shcmsbackend.entity.Query;
import com.averysync.shcmsbackend.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class CustomDataRestConfig implements RepositoryRestConfigurer {
    private String allowedUrlForFrontend = "http://localhost:3000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration,
                                                     CorsRegistry corsRegistry) {
        HttpMethod[] blockHttpMethods = {
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.DELETE,
                HttpMethod.PUT};

        repositoryRestConfiguration.exposeIdsFor(Doctor.class);
        repositoryRestConfiguration.exposeIdsFor(Review.class);
        repositoryRestConfiguration.exposeIdsFor(Query.class);

        disableHttpMethods(Doctor.class, repositoryRestConfiguration, blockHttpMethods);
        disableHttpMethods(Review.class, repositoryRestConfiguration, blockHttpMethods);
        disableHttpMethods(Query.class, repositoryRestConfiguration, blockHttpMethods);

        /* Configure CORS Mapping */
        corsRegistry.addMapping(repositoryRestConfiguration.getBasePath() + "/**")
                .allowedOrigins(allowedUrlForFrontend);
    }

    private void disableHttpMethods(Class theClass,
                                    RepositoryRestConfiguration repositoryRestConfiguration,
                                    HttpMethod[] blockedHttpMethods) {
        repositoryRestConfiguration.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) ->
                        httpMethods.disable(blockedHttpMethods))
                .withCollectionExposure((metdata, httpMethods) ->
                        httpMethods.disable(blockedHttpMethods));
    }
}
