<%-- 
    Document   : modifyQuestionSet
    Created on : 28-Feb-2013, 17:59:36
    Author     : neil
--%>

<%@page import="QuestionsAndAnswers.QuestionSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Questions and Question Sets</title>
    </head>
    <body>
        <h1>Add new Question Set</h1>
        <form method="post" action="/MuseumServer/modifyQuestionSet.do">
            <input type="hidden" name="questionSet" value="1"/>
            Name of Question Set: <input maxlength="50" size="50" type="text" name="setName"/> <input type="submit" name="submit" value="submit"/>
        </form>
        <h1>Current Question Sets</h1>
        <%
            QuestionSet[] qs = (QuestionSet[]) request.getAttribute("questionSets");
            if (qs[0] != null) {


        %><table><tr><th>Question Set Name</th><th></th><th></th></tr><%

            for (int i = 0; i < qs.length; i++) {
            %><tr>
                <td><% out.println(qs[i].getName());%></td>
                <td><% out.println("<a href=\"" + qs + "\">" + qs + "</a></br>");%></td>
                <td><% out.println("<a href=\"" + qs + "\">" + qs + "</a></br>");%></td>
            </tr><%
                }
            %><table><%
             }

                %>


                <form>

                </form>
                <% String messageFromServlet = (String) request.getAttribute("response");

                    if (messageFromServlet == "addQuestion") {
                        out.println(messageFromServlet);
                    }


                %>
                </body>
                </html>
