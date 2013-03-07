<%-- 
    Document   : removeAudio
    Created on : 02-Mar-2013, 12:34:35
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
--%>

<%@page import="businessDomainObjects.Audio"%>
<%@page import="businessDomainObjects.AudioManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove an audio file from the database</title>
    </head>
    <body>
        <h1>Remove an audio file from the database</h1>
        <form action="/MuseumServer/removeAudio.do">
            <%
                AudioManager manager = (AudioManager) getServletContext().getAttribute("audioManager");
                if (manager == null) {
                    out.println("<h2 style='color:red;'> No audio is currently held in the database </h2>");
                } else if (manager.getListOfAudio() == null || manager.getListOfAudio().isEmpty()) {
                    out.println("<h2 style='color:red;'> No audio is currently held in the database </h2>");
                } else  {
                    for (Audio audio : manager.getListOfAudio()) {
                        out.println("<input type='radio' name='audioID' value='" + audio.getAudioID() + "'><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                    }
                    out.println("<input type='submit'/>");
                }
            %>
        </form>
        <%
            String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
