<%-- 
    Document   : removeUserForm
    Created on : 16-Feb-2013, 14:52:31
    Author     : Alex
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">Remove User</h1>
        <p>
            <%
                // Need to run query to get list of users
                dbQuery = 
                List sites = (List)request.getAttribute("sites");
                
                Iterator it = sites.iterator();
                out.print("<br>Here is the list of users: <br>");
                while(it.hasNext()) {
                    out.print(it.next() + "<br>");
                }
            %>
        <br>    
        <form method="POST" action="RemoveUser.do">
            Enter the ID of the user to remove<p>
                User ID: <input type="text" name="userID"><br>                
            <br><br>
            <input type="SUBMIT" value="Remove User" align="left">
        </form>
    </body>
</html>
