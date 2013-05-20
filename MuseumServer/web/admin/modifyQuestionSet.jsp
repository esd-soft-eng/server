<%-- 
    Document   : modifyQuestionSet
    Created on : 28-Feb-2013, 17:59:36
    Author     : Neil and Simon
--%>

<%@page import="QuestionsAndAnswers.Answer"%>
<%@page import="QuestionsAndAnswers.Question"%>
<%@page import="QuestionsAndAnswers.DisplayQuestion"%>
<%@page import="QuestionsAndAnswers.QuestionSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Questions and Question Sets</title>
        <link rel="stylesheet" href="/MuseumServer/styles.css"/>
    </head>
    <body>
        <%@include file="/header.jsp"%>
        <h1>Add new Question Set</h1>
        <form method="post" action="/MuseumServer/modifyQuestionSet.do">
            <input type="hidden" name="questionSetAction" value="1"/>
            Name of Question Set: <input maxlength="50" size="50" type="text" name="setName"/> <input type="submit" name="submit" value="submit"/>
        </form>
        <h1>Current Question Sets</h1>
        <%
            QuestionSet[] qs = (QuestionSet[]) request.getAttribute("questionSets");

            //show question sets
            if (qs == null) {
        %>
        <form method="post" action="/MuseumServer/modifyQuestionSet.do">
            <input type="hidden" name="questionSetAction" value="10"/>
            <input type="submit" name="show current question sets" value="View"/>
        </form>
        <%  } else {
        %><table><tr><th>Question Set Name</th><th></th><th></th></tr><%
            for (QuestionSet questionSet : qs) {
                out.print("<tr><td>" + questionSet.getName() + "</td>"
                        + "<td><a href=\"/MuseumServer/modifyQuestionSet.do?questionSetId=" + questionSet.getId() + "&questionSetAction=2\">ModifyQuestions</a></td>"
                        + "<td><a href=\"/MuseumServer/modifyQuestionSet.do?questionSetId=" + questionSet.getId() + "&questionSetAction=3\">Delete</a></td></tr>");

            }
            %>
            <table>
                    <%
                    if (request.getAttribute("displayQuestions") != null) {
                    
                            String questionSetId = (String) request.getAttribute("questionSetId");
                        //Display Add question
                    %>
                    <h2>Add question to question set</h2>
                        <p>Minimum of one answer required:</p>
                        <form>
                            <input type="hidden" name="questionSetAction" value="4"/>
                            <input type="hidden" name="questionSetId" value="<%out.print(questionSetId);%>"/>
                            Question Text: <input maxlength="50" size="50" type="text" name="questionText"/><br/>
                            Answer 1 Text: <input maxlength="50" size="20" type="text" name="answer1"/>
                            Value: <input maxlength="2" size="2" type="text" name="value1"/><br/>
                            Answer 2 Text: <input maxlength="50" size="20" type="text" name="answer2"/>
                            Value: <input maxlength="2" size="2" type="text" name="value2"/><br/>
                            Answer 3 Text: <input maxlength="50" size="20" type="text" name="answer3"/>
                            Value: <input maxlength="2" size="2" type="text" name="value3"/><br/>
                            Answer 4 Text: <input maxlength="50" size="20" type="text" name="answer4"/>
                            Value: <input maxlength="2" size="2" type="text" name="value4"/><br/>
                            Answer 5 Text: <input maxlength="50" size="20" type="text" name="answer5"/>
                            Value: <input maxlength="2" size="2" type="text" name="value5"/><br/>
                            Answer 6 Text: <input maxlength="50" size="20" type="text" name="answer6"/>
                            Value: <input maxlength="2" size="2" type="text" name="value6"/><br/>
                            <input type="submit" name="Add Question" value="submit"/>
                        </form><%
                        //Display Questions + allow removal
                        
                        for (QuestionSet questionSet : qs) {
                            if (questionSetId.equalsIgnoreCase(Integer.toString(questionSet.getId()))) {
                                
                                out.print("<h2>Questions for: " + questionSet.getName() + "</h2>");
                                for (Question q : questionSet.getQuestions()) {
                                    out.print("<div style=\"border-style:solid;border-width:1px;padding:0px 10px 5px 10px;\">");
                                    out.print("<h3>" + q.getQuestionText() + "</h3>");
                                    out.print("<ul>");
                                    for (Answer a : q.getAnswerListAsArray()) {
                                        out.print("<li>" + a.answerText + " : value " + a.value + "</li>");
                                    }
                                    out.print("</ul>");
                                    out.print("<a href=\"/MuseumServer/modifyQuestionSet.do?questionSetId=" + questionSet.getId() 
                                            + "&questionId="+ q.getQuestionId()
                                            + "&questionSetAction=5\">Delete Question</a></td></tr>");
                                    out.print("</div>");
                                }
                            }
                        }
                    }
                }



                %>


                </body>
                </html>
