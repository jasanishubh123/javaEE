/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author root
 */
@DeclareRoles({"Admin","Supervisor"})

@Stateless
@LocalBean
public class HelloBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

@RolesAllowed("Supervisor")    
public String sayHello()
{
    return "Hello World of EJB";
}

}
