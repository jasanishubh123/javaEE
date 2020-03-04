<%-- 
    Document   : login.jsp
    Created on : 3 Mar, 2020, 11:33:53 AM
    Author     : shubham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<!--        <form method="POST" action="j_security_check">
            Username <input type="text" name="j_username"/><br><br>
            Password <input type="password" name="j_password"><br><br>
            <input type="submit" value="login">
        </form>-->
         <form method="POST" action="jaas/jaaslogin.jsp">
            Username <input type="text" name="username"/><br><br>
            Password <input type="password" name="password"><br><br>
            <input type="submit" value="login">
        </form>
    </body>
</html>
