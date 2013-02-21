<%-- 
    Document   : addMacAddress
    Created on : 21-Feb-2013, 18:24:26
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register new handset in the database</title>
    </head>
    <body>
        <h1> Register new handset in the database </h1>
        <form method="post" action="../addNewDevice.do">
            Handset MAC address: <input type="text" name="MAC"/><br />
            <input type="submit" name="submit" value="submit"/>
        </form>
        <% String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
