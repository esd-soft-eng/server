<%-- 
    Document   : addAudio
    Created on : 28-Feb-2013, 17:01:20
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a new audio item to the database</title>
    </head>
    <body>
        <h1>Add a new audio item to the database</h1>
        <form action="/MuseumServer/addAudio.do" method="post" enctype="multipart/form-data">
            Name of audio file (please indicate level of knowledge required to understand): <input type="text" name="audioName"/><br/>
            Upload: <input type="file" name="file"/><br/>
            <input type="submit"/>
        </form>
        <%
            String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
