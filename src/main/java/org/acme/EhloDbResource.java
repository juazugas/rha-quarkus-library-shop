package org.acme;

import java.time.Instant;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("ehlo")
public class EhloDbResource {

    @Inject
    EntityManager entityManager;

    @GET
    @Path("/database")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloDatabase(@QueryParam("name") String name) {
        var message = entityManager.createNativeQuery("SELECT 'hello'").getSingleResult();
        return String.valueOf(message);
    }

    @Entity
    public static class EhloMessage extends PanacheEntity {

        public Instant createdDt = Instant.now();
        public String message;
    }

}
