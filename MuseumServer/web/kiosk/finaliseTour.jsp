<%-- 
    Document   : enterPaymentDetails
    Created on : 21-Mar-2013, 17:16:56
    Author     : Alex
--%>

<%@page import="visitorsAndGroups.Visitor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>        
        <% String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
        
        <h1>Your Handset Information</h1>
        <form method="POST" action="/MuseumServer/LeadDecider.do">
            <h3>Enter your pin number into the handset. Pins are valid for up to 8 hours</h3>
            <table>
                <tr><th><center>Visitor</center></th><th><center>Pin number</center></th></tr>
                <%
                    Visitor[] visitors = (Visitor[]) request.getAttribute("visitors");
                    
                    for(Visitor v : visitors){
                        %><tr><td><center><% out.print(v.title + ". " + v.forename + " " + v.surname); %></center></td>
                              <td><center><% out.print(v.pin); %></td></center></tr><%
                    }
                         
                %>
            </table>
            
            <% int multicastGroup = (Integer) request.getAttribute("multicastGroup"); 
               
                if (multicastGroup == 1){ %>
                    <h2>Who will control the MultiCast Group?</h2>
                    <b>Group Leader: </b><select name="groupLeader">
                        <%
                        for(Visitor v : visitors){
                            %><option value="<% out.print(v.pin); %>">
                            <% out.print(v.title + ". " + v.forename + " " + v.surname + " : " + v.pin); %>
                            </option><%
                        }
                        %>                        
                    </select><br/><br/>
            <%}
            %>
                <input type='submit' value='Finish!' align='left'/>
            </form>        
        
    </body>
</html>
