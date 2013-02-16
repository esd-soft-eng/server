<%-- 
    Document   : displayUser
    Created on : 14-Feb-2013, 20:55:47
    Author     : Alex
--%>

<%@page import="domainObjects.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Worldsdad!</h1>            
            <%
                User user = (User) request.getAttribute("user");
                if(user == null)
                                       {
                    
                }
                out.println("Here is our user: " + user.getUser());
            %>
            <br />
            <%
                out.println("Here is their password: " + user.getPassword());
            %>
            <br />
    </body>
</html>
