<!--
    Document   : addNewUserForm
    Created on : 16-Feb-2013, 15:26:27
    Author     : Alex
-->
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1 align="center">Add New User</h1>
        <form method="POST" action="../AddNewUser.do">
            Enter the details of the user you wish to add<p>
                <b>User name:</b> <input type="text" name="userName"><br>
                <b>Password:&nbsp;&nbsp;</b> <input type="password" name="password"><br><br>
                <b>User type:&nbsp;&nbsp;<br></b>
                Maintainer<input type="checkbox" name="userType" value="MAINTAINER"></input><br />
                Administrator<input type="checkbox" name="userType" value="ADMINISTRATOR"></input><br />
                Handset<input type="checkbox" name="userType" value="HANDSET"></input><br />
                Kiosk<input type="checkbox" name="userType" value="KIOSK"></input><br />
                Manager<input type="checkbox" name="userType" value="MANAGER"></input><br />
                <br><br>
                <input type="SUBMIT" value="Add New User" align="left">
        </form>

        <% String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>