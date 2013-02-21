<%-- 
    Document   : removeUserForm
    Created on : 16-Feb-2013, 14:52:31
    Author     : Alex
--%>

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
            <%
                // Need to run query to get list of users
                DatabaseQueryExecutor dbQuery = new DatabaseQueryExecutor("jdbc:mysql://localhost:3306/museum_server_database", "root", "");
                
                ResultSet rs = dbQuery.executeStatement("SELECT userID, userName FROM user");
                ArrayList<String> allUsers = new ArrayList();
                String line;
                int numberOfColumns = rs.getMetaData().getColumnCount();
                
                while(rs.next()) {
                    line = "";
                    for (int i = 1; i <= numberOfColumns; i++) {
                        line += rs.getString(i) + ", ";
                    }
                    // remove final ":"
                    line = line.substring(0, line.length() - 2);
                    System.out.println("Line: " + line);
                    allUsers.add(line);
                }
                                                       
                Iterator it = allUsers.iterator();
                out.print("<br>Here is the list of users: <br>");
                while(it.hasNext()) {
                    out.print(it.next() + "<br>");
                }
            %>
        <br>    
        <form method="POST" action="RemoveUser.do">
            Enter the ID of the user to remove<p>
                User ID: <input type="text" name="userID"><br>                
            <br><br>
            <input type="SUBMIT" value="Remove User" align="left">
        </form>
    </body>
</html>
