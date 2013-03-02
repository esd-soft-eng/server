<%--
    Document   : addNewExhibit.jsp
    Created on : 28-Feb-2013, 15:59:39
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
--%>

<%@page import="businessDomainObjects.Audio"%>
<%@page import="businessDomainObjects.AudioManager"%>
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
            <%
                AudioManager manager = (AudioManager) getServletContext().getAttribute("audioManager");
                if (manager == null) {
                    out.println("<h2 style='color:red;'> No audio is currently held in the database </h2>");
                } else if (manager.getListOfAudio() == null || manager.getListOfAudio().isEmpty()) {
                    out.println("<h2 style='color:red;'> No audio is currently held in the database </h2>");
                }
            %>
            Audio file for level 1:<br/>
            <%
                for (Audio audio : manager.getListOfAudio()) {
                    out.println("<input type='radio' name='audioIDLevel1' value='" + audio.getAudioID() + "'><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                }
            %>
            Audio file for level 2:<br/>
            <%
                for (Audio audio : manager.getListOfAudio()) {
                    out.println("<input type='radio' name='audioIDLevel2' value='" + audio.getAudioID() + "'><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                }
            %>
            Audio file for level 3:<br/>
            <%
                for (Audio audio : manager.getListOfAudio()) {
                    out.println("<input type='radio' name='audioIDLevel3' value='" + audio.getAudioID() + "'><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                }
            %>
            Audio file for level 4:<br/>
            <%
                for (Audio audio : manager.getListOfAudio()) {
                    out.println("<input type='radio' name='audioIDLevel4' value='" + audio.getAudioID() + "'><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                }
            %>
            <input type='submit'/>
        </form>
        <%
            String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>