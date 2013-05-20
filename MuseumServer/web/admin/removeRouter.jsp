<%-- 
    Document   : removeRouter
    Created on : 09-Mar-2013, 12:35:15
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
    Desc       : Simple JSP allowing an administrator to remove a router from the
                 database.
--%>

<%@page import="businessDomainObjects.Router"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessDomainObjects.RouterManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove a router held in the database</title>
        <link rel="stylesheet" href="/MuseumServer/styles.css"/>
    </head>
    <body>
        <%@include file="/header.jsp"%>
        <h1>Remove a router held in the database</h1>

        <form action="/MuseumServer/removeRouter.do">
            <%
                RouterManager rm = (RouterManager) getServletContext().getAttribute("routerManager");
                ArrayList<Router> routers = rm.getRouterList();
                if (routers.isEmpty()) {
                    out.println("<p>No routers are currently held in the database.</p>");
                    out.println("<p>To add a router, please go <a href='/MuseumServer/admin/addRouter.jsp'>here</a></p>");
                } else {
                    out.println("<h3> The following routers are held in the database: </h3>");
                    for (Router r : routers) {
                        out.println("<input type='radio' name='routerMAC' value='" + r.getMACAddress() + "'/>" + r.getMACAddress() + "(<i>" + r.getDescription() + "</i>)");
                    }
                    out.println("<input type='submit' value='remove'/>");
                }
            %>
        </form>
        <% String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
