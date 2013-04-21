<%-- 
    Document   : tourAndHandsetSelect
    Created on : 14-Mar-2013, 15:03:59
    Author     : Neil
--%>

<%@page import="visitorsAndGroups.Visitor"%>
<%@page import="QuestionsAndAnswers.Levels"%>
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
            out.print("<b>"+ status + "</b>");
            // *****
            // The select tour/number of people form if we actually have a status set 
            // *****
            if (status == 0){
                
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
                            %><option value="<% out.print(i); %>"><% out.println(i); %></option><%
                        }
                        %></select><%
                        %><br/><br/><%                        
                        %><label for="multicastGroup"><b>Do you want a group tour? (One person controls the audio): </b></label><br/><%
                        %><input type="radio" name="multicastGroup" value="1" checked>Yes<br/><%
                        %><input type="radio" name="multicastGroup" value="0">No<br/><%
                        
                        
                        %><br/><br/><%                        
                        %><input type="submit" name="submit" value="Continue"/><%
                        %></form><%
                    }
                    else {
                        out.println("<h2> No tours are currently available, we're very sorry!</h2>");
                    }
              }
            
              if (status > 0){
                  %><form method="POST" action="/MuseumServer/SelectTourAndHandsetNumber.do"><%
                    %><input type="hidden" name="visitorSignupStatus" value="0"/><%
                    %><input type="submit" name="submit" value="Start again!"/><%
                  %></form><%
              }
            
              // *****
              // If we have a tour and number of people then we can proceed to get their information.
              // *****
              if(status == 1){
                Integer visitorNumber = (Integer) request.getAttribute("displayCurrentVisitor");
                Integer numberOfVisitors = (Integer) request.getAttribute("numberOfVisitors");
                %><h2>Visitor number <%out.print(visitorNumber);%> of <%out.print(numberOfVisitors);%></h2><%
                %><h2>Let's get your details eh?</h2><%
                %><form method="POST" action="/MuseumServer/SelectTourAndHandsetNumber.do"><%
                %><input type="hidden" name="visitorSignupStatus" value="2"/><%
                  
                %><b>Title:</b><select  name="title"><%
                    %><option value="Mr">Mr</option><%
                    %><option value="Mrs">Mrs</option><%
                    %><option value="Miss">Miss</option><%
                    %><option value="Dr">Dr</option><%
                %></select><br/><%
                %><b>First name :</b><input type="text" name="forename"><br/><%
                %><b>Last name :</b><input type="text" name="surname"><br/><%
                %><b>Age: </b><input type="text" name="age" size="3" maxlength="3"><br/><%
                %><input type="submit" name="submit" value="Decide my audio!"/><%
              %></form><%
              }
            
              // *****
              // When we have a person we need to get their answers and decide on their level!
              // *****
            
            if(status == 2){
                String visitorName = (String) request.getAttribute("visitorName");
                DisplayQuestion[] questions = (DisplayQuestion[]) request.getAttribute("questions");
                
                %><h2>Ok <%out.print(visitorName);%>, let's try to figure out which audio is best for you!</h2><%
                %><h2>Answer the questions below:</h2><%
                %><form method="POST" action="/MuseumServer/SelectTourAndHandsetNumber.do"><%
                %><input type="hidden" name="visitorSignupStatus" value="3"/><%
                
                // *****
                // We print out each question and then eash of it's answers in two loops
                // *****
                for (int i = 0; i < questions.length; i++){
                    %><div style="border: solid; border-width: 1px;"><%
                    %><h3>Question: <%out.print(i + 1);%></h3><%
                    
                      DisplayQuestion question = questions[i];
                      out.print(question.questionText);
                      %><br/><%
                      for(int j = 0; j < question.answers.length; j++){
                          String answer = question.answers[j];
                        %><input type="radio" name="question<%out.print(i);%>" value="<%out.print(j);%>" checked><% out.print(answer); %><br><%
                      }
                     %></div><%
                }
                %><input type="submit" name="submit" value="Suggest my level!"/><%
                %></form<%
                
              }
              
            // *****
            // Let the visitor confirm the level of audio which they want to hear. default selection is based on their question score
            // *****
            if (status == 3){
                String visitorName = (String) request.getAttribute("visitorName");
                String score = (String) request.getAttribute("score");
                String level = (String) request.getAttribute("level");
                Levels[] levels = (Levels[]) request.getAttribute("levels");
                
                %><h2>Well <%out.print(visitorName);%>, you scored <%out.print(score);%>!</h2><%
                %><p>We think you'd enjoy the <%out.print(level);%> audio commentary best, but you're free to choose whichever you like!</p><%
                
                    %><form method="POST" action="/MuseumServer/SelectTourAndHandsetNumber.do"><%
                    %><input type="hidden" name="visitorSignupStatus" value="4"/><%
                   
                    %><b>Select Level of Commentary:</b><select  name="level"><%
                        for(Levels l : levels){
                            %><option value="<%out.print(l);%>" 
                                <%if(l.name().equalsIgnoreCase(level)){
                                  %>selected<%
                                }%>> 
                            <%out.print(l);%></option><%
                        }
                    %></select><br/><br/><%
                    %><input type="submit" name="submit" value="Confirm my audio!"/><%
                %></form><%
             }

            // *****
            // Show confirmation of the number of handsets, details and price (almost like a booking confirmation)
            // *****
            
             if (status == 4){
                                
                Double pricePerHandset = (Double) request.getAttribute("pricePerHandset");
                Visitor[] visitors = (Visitor[]) request.getAttribute("visitors");               
                String tourName = (String) request.getAttribute("tourName");
                
                %><h2>Please check the details below.</h2><%
                %><p>If you are satisfied, click Continue, else you can start over with the button at the top.</p><%
                
                %><h2>Tour: <%out.println(tourName);%></h2><%
                
                for (int i = 0; i < visitors.length; i++){
                    Visitor visitor = visitors[i];
                    %><div style="border: solid; border-width: 1px;"><%
                    %><h3><%out.println("Visitor: " + i);%></h3><%
                    out.print("Name: " + visitor.title + ". " + visitor.forename + " " + visitor.surname);
                    %><br/><%
                    out.print("Age: " + visitor.age);
                    %><br/><%
                    out.print("Commentary: " + visitor.level.name());
                    %></div><%
                }
                %><h2>Pricing:</h2><%
                %>Number of handsets: <% out.print(visitors.length);
                %><br/><%
                %>Cost per handset: <% out.print("£" + pricePerHandset);
                %><br/><%
                %>Total cost of visit: <% out.print("£" + (double)visitors.length * pricePerHandset);
                %><form method="POST" action="/MuseumServer/SelectTourAndHandsetNumber.do"><%
                %><input type="hidden" name="visitorSignupStatus" value="5"/><%
                %><input type="submit" name="submit" value="Continue to payment!"/><%
             }
        }%>
        
    </body>
</html>
