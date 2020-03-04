/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author shubham
 */
//CDI
//@BasicAuthenticationMechanismDefinition(
//        realmName = "jdbc"
//)

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "login.jsp"
        )
)

//@FormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/login.jsp",
//                errorPage = "/loginerror.jsp"
//        )
//)
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/jaas",
        callerQuery = "select Password from UserTB where UserId=?",
        groupsQuery = "select GroupName from GroupTB where UserId=?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30
)
@ApplicationScoped
@Named
public class Projectonfig {
    
}
