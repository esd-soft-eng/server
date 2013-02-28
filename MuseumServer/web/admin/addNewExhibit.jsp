<%--
    Document   : addNewExhibit.jsp
    Created on : 28-Feb-2013, 15:59:39
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
--%>

<%@page import="businessDomainObjects.ExhibitManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a new exhibit to the database</title>
    </head>
    <body>
        <h1>Add a new exhibit to the database</h1>
        <form action="addExhibit.do">
            Name of exhibit: <input type="text" name="name"/><br/>
            Description of exhibit: <input type="text" name="description"/><br/>
            Audio files:<br/>


        </form>
        <%
            String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>