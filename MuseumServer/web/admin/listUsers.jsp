<%-- 
    Document   : listUsers
    Created on : 02-Mar-2013, 14:46:39
    Author     : Alex Cooper <alexander2.cooper@live.uwe.ac.uk>
    Desc.      : Creates a web page that displays a list of all users currently
                 recorded in the system.
--%>

<%@page import="businessDomainObjects.UserTypes"%>
<%@page import="java.util.HashSet"%>
<%@page import="businessDomainObjects.UserTypes.UserType"%>
<%@page import="businessDomainObjects.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessDomainObjects.UserManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">List Users</h1>
        <p>
        <%
                ServletContext ctx = request.getServletContext();
                UserManager um = (UserManager) ctx.getAttribute("userManager");
               
                if (um == null) {
                    out.println("<h2> No users are currently recorded in the database </h2>");
                }
                 
                String[] listOfUsers = um.getAllUserStrings();
                ArrayList<User> allUsers = um.getAllUsers();
                
                if (listOfUsers == null || listOfUsers.length == 0) {
                    out.println("<h2> No users are currently recorded in the database </h2>");
                } else {
                    out.println("Here is a list of users: <br>");
                    for (User user : allUsers) {
                        out.println("<b>" + user.getUserID() + ", " + user.getUserName() + "</b>");
                        
                        HashSet<UserTypes.UserType> types = user.getTypes();
                        out.println("<ul>");
                        for (UserTypes.UserType type : types) {
                            String s = type.toString();
                            out.println("<li>" + s + "</li>");
                        }
                        out.println("</ul>");
                    }
                }
            %>
    </body>
</html>
