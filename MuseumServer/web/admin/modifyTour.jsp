<%-- 
    Document   : modifyTour
    Created on : 07-Mar-2013, 16:43:54
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
               : Simon Edwins <Darkstar>
--%>

<%@page import="QuestionsAndAnswers.QuestionSet"%>
<%@page import="QuestionsAndAnswers.QuestionSetManager"%>
<%@page import="businessDomainObjects.Tour"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessDomainObjects.TourManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify a tour held in the database.</title>
    </head>
    <body>
        <h1>Modify a tour held in the database</h1>
        <form action="/MuseumServer/modifyTour.do">
            <input type="hidden" name="stage" value="1"/>
            <%
                TourManager tm = (TourManager) getServletContext().getAttribute("tourManager");
                ArrayList<Tour> tours = tm.getListOfTours();
                if (tours.isEmpty()) {
                    out.println("<h2>No tours are currently recorded in the database</h2>");
                } else {
                    out.println("<h2>Select a tour to modify.</h2>");
                    for (Tour t : tours) {
                        String tourName = t.getName();
                        String tourDescription = t.getDescription().length() > 100 ? t.getDescription().substring(0, 100) + "...." : t.getDescription();
                        int tourID = t.getTourID();
                        String questionSetID = Integer.toString(t.getQuestionSetID());
                        if(questionSetID.equals("-1")){
                            questionSetID = "None";
                        }
                        out.println("<input type='radio' name='tourID' value='" + tourID + "'/>" + tourName + "(<i>Description:" + tourDescription + "|QuestionSetID:" + questionSetID + "</i>)<br/>");
                    }
                    out.println("<input type='submit' value='modify'/>");
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
