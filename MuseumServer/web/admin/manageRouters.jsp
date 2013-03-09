<%-- 
    Document   : manageRouters
    Created on : 07-Mar-2013, 19:15:47
    Author     : Darkstar
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
        <form method="POST" action="ModifyRouter.do">
                <select name="selectedRouter">
                <%
                    RouterManager rm = (RouterManager) request.getServletContext().getAttribute("routerManager");
                    ArrayList<Router> routerArrayList = rm.getRouterList();

                    for(Router thisRouter : routerArrayList){
                        String mac = thisRouter.getMACAddress();
                        out.println("<option value=\"" + mac + "\">" + mac + "</option>");
                    }

                %>
                </select>
                <br /><br />
                Enter the new details for router modification:<p>
                    <b>New Mac Address:</b> <input type="text" name="macAddress"><br />
                    <b>New Audio Location:</b>&nbsp;&nbsp; <input type="text" name="audioLocation"><br /><br />
                    <b>New Description:</b>&nbsp;&nbsp; <input type="text" name="description"><br /><br />
                <br><br>
                <input type="SUBMIT" value="Modify Mac Address" align="left">
            </form>
    </body>
</html>
