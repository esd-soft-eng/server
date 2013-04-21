<%-- 
    Document   : enterPaymentDetails
    Created on : 21-Mar-2013, 17:16:56
    Author     : Alex
    Desc.      : Creates a web page that lets the visitor pay for their handsets
                 with a credit card. Lets the visitor provide their card type, 
                 card number, expiry date and security number.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Enter Payment Details</h1>
        <form method="POST" action="/MuseumServer/PaymentProcessor.do">
            <b>Card type</b><select name="cardType">
                <option value="Visa">Visa</option>
                <option value="MasterCard">MasterCard</option>
                <option value="AmericanExpress">American Express</option>
                <option value="Solo">Solo</option>
            </select><br/><br/>
            <b>Card number</b><input type="text" name="cardNumber" size="16" maxlength="16"><br/><br/>
            <b>Expiry date</b><select name="expiryMonth">
                <option value="1">01</option>
                <option value="2">02</option>
                <option value="3">03</option>
                <option value="4">04</option>
                <option value="5">05</option>
                <option value="6">06</option>
                <option value="7">07</option>
                <option value="8">08</option>
                <option value="9">09</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
            <select name="expiryYear">
                <option value="2014">2014</option>
                <option value="2015">2015</option>
                <option value="2016">2016</option>
                <option value="2017">2017</option>
                <option value="2018">2018</option>
                <option value="2019">2019</option>
                <option value="2020">2020</option>
            </select><br/><br/>
            <b>Security number</b><input type="text" name="securityNumber" size="3" maxlength="3"><br/>
            <br/><br/>
            <input type='submit' value='Submit' align='left'/>
        </form>
        <form method="POST" action="/MuseumServer/SelectTourAndHandsetNumber.do">
            <input type="hidden" name="visitorSignupStatus" value="0"/>
            <input type="submit" name="submit" value="Cancel"/>
        </form>
        
        <% String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
