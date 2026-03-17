package com.muxin.resteasy.config;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @projectname: muxin
 * @filename: SimpleResource
 * @author: yangxz
 * @data:2024/3/6 14:19
 * @description:
 */


@Path("")
public class SimpleResource {


    @GET
    @Path(value = "/actuator/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello() {
        return "Hello, World!";
    }
}
