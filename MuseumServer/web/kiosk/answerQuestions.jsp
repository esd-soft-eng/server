<%-- 
    Document   : answerQuestions
    Created on : 16-Mar-2013, 14:17:20
    Author     : Alex
--%>

<%@page import="QuestionsAndAnswers.DisplayQuestion"%>
<%@page import="persistance.PersistanceRepositoryQuestions"%>
<%@page import="QuestionsAndAnswers.QuestionSetManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question answer page</title>
    </head>
    <body>
        <h1>Question Answer Page</h1>
        <form method="POST" action="./AnswerQuestions.do">
            <%
                DisplayQuestion[] questions = (DisplayQuestion[]) request.getAttribute("questions");
                for (int i = 0; i <= questions.length; i++) {
                    out.println(questions[i].questionText);
                    String[] answers = questions[i].answers;
                    for (int x = 0; x <= answers.length; i++) {
                        out.println(answers[i]);
                    }
                    
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
