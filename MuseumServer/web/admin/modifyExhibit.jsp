<%-- 
    Document   : modifyExhibit
    Created on : 02-Mar-2013, 15:57:18
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
--%>

<%@page import="businessDomainObjects.Exhibit"%>
<%@page import="businessDomainObjects.ExhibitManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify an exhibit stored in the database</title>
    </head>
    <body>
        <h1>Modify an exhibit stored in the database</h1>
        <form action="/MuseumServer/modifyExhibit.do">
            <input type="hidden" name="stage" value="1"/>
            <%
                ExhibitManager manager = (ExhibitManager) getServletContext().getAttribute("exhibitManager");
                if (manager == null || manager.getListOfExhibits().isEmpty()) {
                    out.println("<h2 style='color:red;'> No exhibits currently recorded in the database.</h2>");
                } else {
                    for (Exhibit exhibit : manager.getListOfExhibits()) {
                        String name = exhibit.getName();
                        String descriptionSubset = exhibit.getDescription().length() > 50 ? exhibit.getDescription().substring(0, 100) + "...." : exhibit.getDescription();
                        int exhibitID = exhibit.getExhibitID();
                        out.println("<input type='radio' name='exhibitID' value='" + exhibitID + "'/><b>" + name + "</b>(<i>\"" + descriptionSubset + "\"</i>)<br/>");
                    }
                }
            %>
            <input type="submit" value="Modify">
        </form>
        <%
            String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
