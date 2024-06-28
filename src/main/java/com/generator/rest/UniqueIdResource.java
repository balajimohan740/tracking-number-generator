package com.generator.rest;

import com.generator.service.CustomSnowflakeIdGenerator;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/unique-id")
public class UniqueIdResource {

    @Inject
    CustomSnowflakeIdGenerator idGenerator;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String generateUniqueId() {
        long id = idGenerator.generateUniqueId();
        return String.valueOf(id);
    }
}
