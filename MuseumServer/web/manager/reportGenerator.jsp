<%-- 
    Document   : reportGenerator
    Created on : 21-Mar-2013, 14:31:16
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
    Desc       : This JSP allows a manager to select from various reporting 
                 criterion and times to display a table containing reports of 
                 logs created in that time period.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Generator</title>
    </head>
    <body>
        <form action="/MuseumServer/generateReport.do" method="get">
            <h1>Report Generator.</h1>
            <h2>Generate reports on the following criteria:</h2>
            <select name="reportType">
                <option value="audioLog">Audio related logs</option>
                <option value="exhibitLog">Exhibit related logs</option>
                <option value="handsetLog">Handset related logs</option>
                <option value="loginLog">Login related logs</option>
                <option value="registerLog">Registration related logs</option>
                <option value="routerLog">Router related logs</option>
                <option value="tourLog">Tour related logs</option>
                <option value="userLog">User related logs</option>
                <option value="wifiAudioLog">WIFI request related logs</option>            
            </select>

            <h2> For the period of: </h2>
            <select name="reportPeriod">
                <option value="week">Past week</option>
                <option value="fortnight">Past fortnight</option>
                <option value="month">Past month</option>
                <option value="twoMonths">Past two months</option>
                <option value="fourMonths">Past four months</option>
                <option value="sixMonths">Past six months</option>
                <option value="year">Past year</option>
                <option value="forever">Forever</option>
            </select>
            <br/><br/>
            <input type="submit" value="Generate Report">
        </form>

        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
                out.println(message);
            }

            String reportHtml = (String) request.getAttribute("report");
            if (reportHtml != null) {
                out.println("<hr/>");
                out.println("<h1>Report Results:</h1>");
                out.println(reportHtml);
            }
        %>
    </body>
</html>
