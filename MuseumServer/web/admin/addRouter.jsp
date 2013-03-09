<%-- 
    Document   : addRouter
    Created on : 09-Mar-2013, 14:49:26
    Author     : Darkstar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Router</title>
    </head>
    <body>
        <h1>Add Router</h1>
            <form method="POST" action="/MuseumServer/AddRouter.do">
                Enter details for new router:<p />
                    <b>Mac Address:</b> <input type="text" name="macAddress" /><br />
                    <b>Audio Location:</b>&nbsp;&nbsp; <input type="text" name="audioLocation" /><br />
                    <b>Description:</b>&nbsp;&nbsp; <input type="text" name="description" /><br />
                <br /><br />
                <input type="SUBMIT" value="Add Router" align="left">
            </form>
    </body>
</html>
