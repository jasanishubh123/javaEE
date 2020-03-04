/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 *
 * @author root
 */
//@DeclareRoles({"admin","supervisor"})
@Stateless
public class MyLogicBean implements MyLogicBeanLocal {

    
   // @RolesAllowed("supervisor")
    @Override
    public String sayHello() {
        
    return "Hello from MyLogicBean";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
