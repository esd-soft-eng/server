<%-- 
    Document   : manageRouters
    Created on : 07-Mar-2013, 19:15:47
    Author     : Darkstar
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="businessDomainObjects.UserManager"%>
<%@page import="businessDomainObjects.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            String message = (String) request.getAttribute("message");
            if(message == null){
                message = "";
            }
            out.println(message);
        %>
        <title>Manage Routers</title>
    </head>
    <body>
        <h1>Manage Routers</h1>
        <form method="POST" action="ModifyUser.do">
                
                <%
                    UserManager um = (UserManager) request.getServletContext().getAttribute("userManager");
                    for(User userString : um.getAllUsers()){
                        String thisString = userString.getUserName();
                        int userID = userString.getUserID();
                        
                        out.println(userID + ": " + thisString + "   <input type=\"radio\" name=\"userToModify\" value=" + userID + "><br />");
                    }
                %>
                <br /><br />
                Enter the new details of the user that you wish to modify:<p>
                    <b>New User Name:</b> <input type="text" name="userName"><br />
                    <b>New Password:</b>&nbsp;&nbsp; <input type="password" name="password"><br /><br />
                    <b><u>New User type</u></b>&nbsp;&nbsp;<br />
                    Maintainer<input type="checkbox" name="userType" value="1"></input><br />
                    Administrator<input type="checkbox" name="userType" value="2"></input><br />
                    Client Handset<input type="checkbox" name="userType" value="3"></input><br />
                    Kiosk<input type="checkbox" name="userType" value="4"></input><br />
                    Manager<input type="checkbox" name="userType" value="5"></input><br />
                <br><br>
                <input type="SUBMIT" value="Modify User" align="left">
            </form>
    </body>
</html>
