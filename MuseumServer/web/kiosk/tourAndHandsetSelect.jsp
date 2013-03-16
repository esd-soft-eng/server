<%-- 
    Document   : tourAndHandsetSelect
    Created on : 14-Mar-2013, 15:03:59
    Author     : Alex
--%>

<%@page import="businessDomainObjects.Tour"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessDomainObjects.TourManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tour and handset number selection</title>
    </head>
    <body>
        <h1>Tour and Handset Number Selection</h1>
        <form method="POST" action="./SelectTourAndHandsetNumber.do">
            <b>Please select from the available tours:</b> <br>
            <select>
                <%
                    ServletContext ctx = request.getServletContext();
                    TourManager tm = (TourManager) ctx.getAttribute("tourManager");

                    if (tm == null) {
                        out.println("<h2> No tours are currently recorded in the database </h2>");
                    }

                    ArrayList<Tour> tours = tm.getListOfTours();

                    for (Tour tour : tours) {
                        out.println("<option value=\"tour\">"
                                + tour.getName() + "</option>");
                    }
                %>                  
            </select>
            <br><br>

            <b>Please select the number of handsets you require:</b> <br>
            <select>
                <%
                    for (int i = 1; i <= 10; i++) {
                        out.println("<option value=\"handset\">" + i + "</option>");
                    }
                %>
            </select>
            <br><br>

            <input type='submit' value='Submit' align='left'/>
        </form>

        <% String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
