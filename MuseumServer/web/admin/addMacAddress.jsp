<%-- 
    Document   : addMacAddress
    Created on : 21-Feb-2013, 18:24:26
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
    Desc       : This JSP file allows an administrator to add a MAC address of a
                 client handset to the database.                 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register a new handset in the database</title>
    </head>
    <body>
        <h1> Register a new handset in the database </h1>
        <form method="post" action="/MuseumServer/addNewDevice.do">
            Handset MAC address: <input maxlength="2" size="2" type="text" name="MAC1"/>:<input maxlength="2" size="2" type="text" name="MAC2"/>:<input maxlength="2" size="2" type="text" name="MAC3"/>:<input maxlength="2" size="2" type="text" name="MAC4"/>:<input maxlength="2" size="2" type="text" name="MAC5"/>:<input maxlength="2" size="2" type="text" name="MAC6"/><br />
            <input type="submit" name="submit" value="submit"/>
        </form>
        <% String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
