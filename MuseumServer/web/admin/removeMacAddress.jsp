<%-- 
    Document   : removeMacAddress
    Created on : 23-Feb-2013, 12:49:41
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
--%>

<%@page import="businessDomainObjects.HandsetAccessManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove handset from the database</title>
    </head>
    <body>
        <h1> Remove handset from the database </h1>
        <form action="/MuseumServer/removeDevice" method="post">
            <%
                HandsetAccessManager ham = (HandsetAccessManager) getServletContext().getAttribute("handsetAccessManager");
                if (ham == null) {
                    out.println("<h2> No handsets are currently recorded in the database </h2>");
                }
                String[] registeredDevices = ham.getDeviceList();
                if (registeredDevices == null || registeredDevices.length == 0) {
                    out.println("<h2> No handsets are currently recorded in the database </h2>");
                } else {
                    for (String device : registeredDevices) {
                        out.println("<input type='radio' name='mac' value='"+device+"'>" + device + "<br/>");
                    }
                }
            %>
        </form>
    </body>
</html>
