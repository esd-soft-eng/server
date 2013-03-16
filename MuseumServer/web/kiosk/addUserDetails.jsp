<%-- 
    Document   : addUserDetails
    Created on : 16-Mar-2013, 13:15:57
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add user details</title>
    </head>
    <body>
        <h1>Add User Details</h1>
        <form method="POST" action="./SelectTourAndHandsetNumber.do">
            <b>Title:</b> <select>
                <option value="title">Mr</option>
                <option value="title">Mrs</option>
                <option value="title">Miss</option>
                <option value="title">Dr</option>
            </select> <br>
            <b>First name:</b><input type="text" name="firstName"><br>
            <b>Last name:</b><input type="text" name="lastName"><br>
        </form>
    </body>
</html>
