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
        <form method="POST" action="./ValidateUserDetails.do">
            <b>Title:</b><select>
                <option value="title">Mr</option>
                <option value="title">Mrs</option>
                <option value="title">Miss</option>
                <option value="title">Dr</option>
            </select> <br>
            <b>First name:</b><input type="text" name="firstName"><br>
            <b>Last name:</b><input type="text" name="lastName"><br>
            <b>Age:</b><select>
                <option value="age">5-10</option>
                <option value="age">11-15</option>
                <option value="age">16-20</option>
                <option value="age">21-25</option>
                <option value="age">26-30</option>
                <option value="age">31-40</option>
                <option value="age">41-50</option>
                <option value="age">51-60</option>
                <option value="age">61+</option>
            </select><br>
            <br>
            <input type='submit' value='Submit' align='left'/>
        </form>
        
        <% String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
