<%-- 
    Document   : index
    Created on : 14-Feb-2013, 14:54:17
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> System Login: </h1>
        <form action="displayuser.do" method="post">
            Username: <input type="text" name="username"/>
            Password: <input type="text" name="password"/>
            <input type="submit" name ="submit" text="submit"/>
        </form>

        <% if (request.getAttribute("message") != null) {
                out.println("<h2 style='color:red;'>"+request.getAttribute("message")+"</h2>");
            }
        %>
    </body>
</html>
