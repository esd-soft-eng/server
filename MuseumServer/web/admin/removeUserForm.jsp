<%-- 
    Document   : removeUserForm
    Created on : 16-Feb-2013, 14:52:31
    Author     : Alex
--%>

<%@page import="businessDomainObjects.UserManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="persistance.PersistanceRepositoryUser"%>
<%@page import="persistance.DatabaseQueryExecutor"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">Remove User</h1>
        <p>
        <form method="POST" action="./RemoveUser.do" onsubmit="return confirm('This will delete the selected user. Continue?');">
            <%
                ServletContext ctx = request.getServletContext();
                UserManager um = (UserManager) ctx.getAttribute("userManager");
               
                if (um == null) {
                    out.println("<h2> No users are currently recorded in the database </h2>");
                }
                 
                String[] listOfUsers = um.getAllUserStrings();

                if (listOfUsers == null || listOfUsers.length == 0) {
                    out.println("<h2> No users are currently recorded in the database </h2>");
                } else {
                    out.println("Here is a list of users: <br>");
                    for (String user : listOfUsers) {
                        String[] userLine = user.split(",");
                        out.println("<input type='radio' name='userID' value='" + userLine[0] + "'>" + userLine[0] + ", " + userLine[1] + "<br/>");

                    }
                    out.println("<br>");
                    out.println("<input type='submit' value='Remove User' align='left'/>");
                }
            %>
        </form>

        <% String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
