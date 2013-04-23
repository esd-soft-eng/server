<%-- 
    Document   : index
    Created on : 14-Feb-2013, 14:54:17
    Author     : Alex Cooper <alexander2.cooper@live.uwe.ac.uk>
    Desc.      : Creates a web page that allows a user to login to the server
                 side of the MADS system, where they have to provide their
                 username and password login credentials. This is the start page
                 where all museum staff who have access to the system will login.
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
