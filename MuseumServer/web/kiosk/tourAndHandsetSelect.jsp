<%-- 
    Document   : tourAndHandsetSelect
    Created on : 14-Mar-2013, 15:03:59
    Author     : Alex
--%>

<%@page import="QuestionsAndAnswers.Answer"%>
<%@page import="QuestionsAndAnswers.DisplayQuestion"%>
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

        
        
        <%
        // *****
        // This is the initial part of the page, shows when there's no current sign up in progress
        // It just kicks off the process with a button click and sets the signup status to 0
        // *****
        String visitorSignupStatus = (String) request.getAttribute("visitorSignupStatus"); 
        if (visitorSignupStatus == null){
            %><form method="POST" action="/MuseumServer/SelectTourAndHandsetNumber.do"><%
                %><h1>Sign up for a Tour!</h1><%
                    %><input type="hidden" name="visitorSignupStatus" value="0"/><%
                    %><input type="submit" name="submit" value="Sign me up!"/><%
            %><form><%
        }
        
        // *****
        // Else we start the process of signing people up
        // *****
        else {
            
            // *****
            // Depending on the status of the signup we'll see different parts of the signup process
            // *****
            int status = Integer.parseInt(visitorSignupStatus);
            out.print("<b>"+status+ "</b>");
            // *****
            // The select tour/number of people form if we actually have a status set 
            // *****
            if (status > -1){
                
                Tour[] tours = (Tour[]) request.getAttribute("tours");                
                    if (tours.length > 0) {
                        %><h1>Select your tour and the number of people who will be on it</h1><%
                        %><form method="POST" action="/MuseumServer/SelectTourAndHandsetNumber.do"><%
                        %><input type="hidden" name="visitorSignupStatus" value="1"/><%
                        
                        %><label for="tourId"><b>Tour: </b></label><%
                        %><select id="tour" name="tourId"><%
                        for (Tour tour : tours) {
                            %><option value="<% out.print(tour.getTourID()); %>"><% out.print(tour.getName()); %></option><%
                        }                        
                        %></select><%
                        
                        %><br/><br/><%                        
                        %><label for="numberOfPeople"><b>Number of Persons: </b></label><%
                        %><select id="numberOfPeople" name="numberOfPeople"><%
                        for (int i = 1; i <= 10; i++) {
                            %><option value="<%out.print(i);%>">" + i + "</option><%
                        }
                        %></select><%
                        
                        %><br/><br/><%                        
                        %><input type="submit" name="submit" value="Continue"/><%
                        %></form><%
                    }
                    else {
                        out.println("<h2> No tours are currently available, we're very sorry!</h2>");
                    }
              }
              // *****
              // If we have a tour and number of people then we can proceed to get their information.
              // *****
              if(status == 1){
              
                %><h1>Let's get your details eh?</h1><%
                %><form method="POST" action="/MuseumServer/SelectTourAndHandsetNumber.do"><%
                %><input type="hidden" name="visitorSignupStatus" value="2"/><%
                  
                %><b>Title:</b><select  name="title"><%
                    %><option value="Mr">Mr</option><%
                    %><option value="Mrs">Mrs</option><%
                    %><option value="Miss">Miss</option><%
                    %><option value="Dr">Dr</option><%
                %></select><br/><%
                %><b>First name :</b><input type="text" name="firstName"><br/><%
                %><b>Last name :</b><input type="text" name="lastName"><br/><%
                %><b>Age: </b><input type="text" name="lastName" size="3" maxlength="3"><br/><%
                %><input type="submit" name="submit" value="Decide my audio!"/><%
              %></form><%
              }
            
              // *****
              // When we have a person we need to get their answers and decide on their level!
              // *****
            
            if(status == 2){
                String visitorName = (String) request.getAttribute("visitorName");
                DisplayQuestion[] questions = (DisplayQuestion[]) request.getAttribute("questions");
                
                %><h1>Ok <%out.print(visitorName);%> let's try to figure out which audio is best for you!</h1><%
                %><h2>Answer the questions below:</h2><%
                %><form method="POST" action="/MuseumServer/SelectTourAndHandsetNumber.do"><%
                %><input type="hidden" name="visitorSignupStatus" value="3"/><%
                
                // *****
                // We print out each question and then eash of it's answers in two loops
                // *****
                for (int i = 0; i < questions.length; i++){
                    %><h3>Question: <%out.print(i + 1);%></h3><%
                    
                      DisplayQuestion question = questions[i];
                      out.print(question.questionText);
                      
                      for(int j = 0; j < question.answers.length; j++){
                          String answer = question.answers[j];
                        %><input type="radio" name="question<%out.print(i);%>" value="<%out.print(j);%>"><% out.print(answer); %><br><%
                      }
                }
                %><input type="submit" name="submit" value="Suggest my level!"/><%
                %></form<%
                
                
                if (status == 3){
                    
                }
            }
                              
        }%>
        
    </body>
</html>
