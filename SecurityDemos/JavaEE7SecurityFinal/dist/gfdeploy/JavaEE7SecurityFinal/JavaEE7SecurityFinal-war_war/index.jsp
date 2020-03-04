<%-- 
    Document   : index
    Created on : 29 Feb, 2020, 2:18:43 PM
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
    <center>
        <form method="POST" action="j_security_check">
            Username : <input type="text" name="j_username"/><br>
            Password : <input type="password" name="j_password"><br>
            <input type="submit" value="login">
        </form>
    </center>
    </body>
</html>
