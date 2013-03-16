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
        <form method="POST" action="/MuseumServer/ValidateUserDetails.do">
            <b>Title:</b><select>
                <option name="title" value="Mr">Mr</option>
                <option name="title" value="Mrs">Mrs</option>
                <option name="title" value="Miss">Miss</option>
                <option name="title" value="Dr">Dr</option>
            </select> <br>
            <b>First name:</b><input type="text" name="firstName"><br>
            <b>Last name:</b><input type="text" name="lastName"><br>
            <b>Age:</b><select>
                <option name="age" value="5-10">5-10</option>
                <option name="age" value="11-15">11-15</option>
                <option name="age" value="16-20">16-20</option>
                <option name="age" value="21-25">21-25</option>
                <option name="age" value="26-30">26-30</option>
                <option name="age" value="31-40">31-40</option>
                <option name="age" value="41-50">41-50</option>
                <option name="age" value="51-60">51-60</option>
                <option name="age" value="61+">61+</option>
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
