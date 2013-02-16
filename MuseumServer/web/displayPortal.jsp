<%-- 
    Document   : displayPortal
    Created on : 16-Feb-2013, 18:43:14
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="font-family:arial">
        <h1> You may access the following resources: </h1>
        <%
            ArrayList<String> accessibleFiles = new ArrayList<String>();
            accessibleFiles = (ArrayList<String>) request.getAttribute("accessibleFiles");
            for (String s : accessibleFiles) {
                out.println("<a href=\"" + s + "\">" + s + "</a></br>");
            }
        %>
    </body>
</html>
