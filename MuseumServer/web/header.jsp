<%-- 
    Document   : header
    Created on : 20-May-2013, 14:59:03
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
--%>
<%@page import="utility.Redirector"%>
<%@page import="businessDomainObjects.User"%>
<%
   User currUser = (User)session.getAttribute("currentUser");
   if(currUser == null)
   {
       Redirector.redirect(request, response, "/index.jsp");
   }    
%>

<div id="headerDiv">
    <center><img src="/MuseumServer/logo.jpg"></img></center>
    <a href="/MuseumServer/displayPortal.do">PORTAL</a> | <a href="/MuseumServer/Logout">LOGOUT</a>
</div>