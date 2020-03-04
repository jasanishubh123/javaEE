/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.MyLogicBeanLocal;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author root
 */
@DeclareRoles({"admin","supervisor"})
@Path("generic")
public class MyRest {
    @EJB MyLogicBeanLocal lb;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MyRest
     */
    public MyRest() {
    }

    /**
     * Retrieves representation of an instance of rest.MyRest
     * @return an instance of java.lang.String
     */
    @RolesAllowed("admin")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHello() {
        //TODO return proper representation object
        
        return "From Rest "+lb.sayHello();
    }

    /**
     * PUT method for updating or creating an instance of MyRest
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
