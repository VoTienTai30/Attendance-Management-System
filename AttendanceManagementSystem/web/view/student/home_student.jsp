<%-- 
    Document   : home_student
    Created on : Feb 20, 2022, 9:09:04 AM
    Author     : midni
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="../css/HomeStudentStyle.css" rel="stylesheet" type="text/css"/>
        <%Account acc = (Account) request.getSession().getAttribute("account");%>
    </head>
    <body>
        <header>
            <a href="../student/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: <%=acc.getDisplayName()%> | <a href="../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="#">Home</a>
            <a href="../student/view-attendance">View Attendance</a>
        </div>

        <div id="background">
            <span>Welcome to <hr style="width:400px"> Student Attendance Management System</span>
        </div>
    </body>
</html>
