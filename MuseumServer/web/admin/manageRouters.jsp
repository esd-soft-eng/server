<%-- 
    Document   : manageRouters
    Created on : 07-Mar-2013, 19:15:47
    Author     : Simon Edwins
    Description: Allows for existing router attributes to be modified.
--%>

<%@page import="businessDomainObjects.Router"%>
<%@page import="businessDomainObjects.RouterManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessDomainObjects.UserManager"%>
<%@page import="businessDomainObjects.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MuseumServer/styles.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            // Provides success/error message logic.
            String message = (String) request.getAttribute("message");
            if(message == null){
                message = "";
            }
            out.println(message);
        %>
        <title>Manage Routers</title>
    </head>
    <body>
        <%@include file="/header.jsp"%>
        <h1>Manage Routers</h1>
        <form method="POST" action="/MuseumServer/ModifyRouter.do">
            <b>Select Router Mac Address to Modify </b>
                <select name="selectedRouter">
                    
                <%
                    // Obtain list of routers (full objects)
                    RouterManager rm = (RouterManager) request.getServletContext().getAttribute("routerManager");
                    ArrayList<Router> routerArrayList = rm.getRouterList();

                    // Create select box with all routers
                    for(Router thisRouter : routerArrayList){
                        String mac = thisRouter.getMACAddress();
                        out.println("<option value=\"" + mac + "\">" + mac + "</option>");
                    }

                %>
                </select>
                <br /><br />
                Enter the new details for router modification:<p>
                    <b>New Mac Address:</b> <input type="text" name="macAddress"><br />
                    <b>New Audio Location:</b>&nbsp;&nbsp; <input type="text" name="audioLocation"><br />
                    <b>New Description:</b>&nbsp;&nbsp; <input type="text" name="description"><br />
                <br><br>
                <input type="SUBMIT" value="Modify Router" align="left">
            </form>
    </body>
</html>
