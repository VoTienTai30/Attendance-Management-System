<%-- 
    Document   : home_admin
    Created on : Jan 25, 2022, 9:50:37 PM
    Author     : midni
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="../css/HomeAdminStyle.css" rel="stylesheet" type="text/css"/>
        <%Account acc = (Account) request.getSession().getAttribute("account");%>
    </head>
    <body>
        <header>
            <a href="../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: <%=acc.getDisplayName()%> | <a href="../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="#">Home</a>
            <a href="../admin/subject">Subject</a>
            <a href="#">Class</a>
            <a href="#">Teacher</a>
            <a href="#">Student</a>
            <a href="#">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="#">Total Account</a>
        </div>

        <div id="background">
            <span>Welcome to <hr style="width:400px"> Student Attendance Management System</span>
        </div>
    </body>
</html>
