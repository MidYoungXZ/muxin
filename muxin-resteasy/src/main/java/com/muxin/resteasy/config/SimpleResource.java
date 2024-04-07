package com.muxin.resteasy.config;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
