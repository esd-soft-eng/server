<%-- 
    Document   : viewRegisteredHandsets.jsp
    Created on : 21-Feb-2013, 17:08:35
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
    Desc       : Simple JSP to display the client handset MAC addresses stored
                 in the database
--%>

<%@page import="businessDomainObjects.HandsetAccessManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Registered Handsets</title>
        <link rel="stylesheet" href="/MuseumServer/styles.css"/>
    </head>
    <body>
        <%@include file="/header.jsp"%>
        <h1> View Registered Handsets </h1>
        <%
            HandsetAccessManager ham = (HandsetAccessManager) getServletContext().getAttribute("handsetAccessManager");
            if (ham == null) {
                out.println("<h2> No handsets are currently recorded in the database </h2>");
            }
            String[] registeredDevices = ham.getDeviceList();
            if (registeredDevices == null || registeredDevices.length == 0) {
                out.println("<h2> No handsets are currently recorded in the database </h2>");
            } else {
                out.println("<ul>");
                for (String device : registeredDevices) {
                    out.println("<li>" + device + "</li>");
                }
                out.println("</ul>");
            }
        %>
    </body>
</html>
