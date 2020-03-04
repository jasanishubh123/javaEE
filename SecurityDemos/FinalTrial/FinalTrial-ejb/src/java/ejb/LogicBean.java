/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author root
 */
@Stateless
@LocalBean
//@DeclareRoles({"admin","supervisor"})
public class LogicBean {

    // Add business logic below. (Right-click in editor and choose
 
    // "Insert Code > Add Business Method")
@PermitAll
public String sayHello()
{
    return "Hello World of EJB";
}

}
