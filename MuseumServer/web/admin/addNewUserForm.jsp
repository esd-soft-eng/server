<!--
    Document   : addNewUserForm
    Created on : 16-Feb-2013, 15:26:27
    Author     : Alex Cooper <alexander2.cooper@live.uwe.ac.uk>
    Desc.      : Creates a web page that lets an administrator add a new user
                 to the system, providing them with a username, password and
                 user type.
-->
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1 align="center">Add New User</h1>
        <form method="POST" action="/MuseumServer/AddNewUser.do">
            Enter the details of the user you wish to add<p>
                <b>User name:</b> <input type="text" name="userName"><br>
                <b>Password:&nbsp;&nbsp;</b> <input type="password" name="password"><br><br>
                <b>User type:&nbsp;&nbsp;<br></b>
                Maintainer<input type="checkbox" name="userType" value="1"></input><br />
                Administrator<input type="checkbox" name="userType" value="2"></input><br />
                Handset<input type="checkbox" name="userType" value="3"></input><br />
                Kiosk<input type="checkbox" name="userType" value="4"></input><br />
                Manager<input type="checkbox" name="userType" value="5"></input><br />
                <br><br>
                <input type="SUBMIT" value="Add New User" align="left">
        </form>

        <% 
            // Gets the error message from the servlet
            String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>