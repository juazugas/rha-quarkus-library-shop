package org.acme;

import java.time.Instant;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(ref = "Greetings")
@Path("ehlo")
public class EhloDbResource {

    @Inject
    EntityManager entityManager;

    @ConfigProperty(name = "app.version", defaultValue = "v1")
    String appVersion;

    @ConfigProperty(name = "app.host", defaultValue = "localhost")
    String appHost;

    @Operation(operationId = "ehlo-response",
            summary = "ehlo version response",
            description = "This operation retrieves the ehlo version from the server")
    @APIResponse(responseCode = "200", description = "Ok. Ehlo version")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ehlo() {
        return String.format("ehlo from version %s host %s", appVersion, appHost);
    }

    @APIResponses({
        @APIResponse(responseCode = "200", description = "Ok. Ehlo returned"),
        @APIResponse(responseCode = "500", description = "Internal error") })
    @GET
    @Path("/database")
    @Produces(MediaType.TEXT_PLAIN)
    public String ehloDatabase(@QueryParam("name") String name) {
        var message = entityManager.createNativeQuery("SELECT VERSION()").getSingleResult();
        return String.format("ehlo from %s", message);
    }

    @Entity
    @Table(name = "ehlo_message")
    public static class EhloMessage extends PanacheEntity {

        public Instant createdDt = Instant.now();
        public String message;
    }

}
