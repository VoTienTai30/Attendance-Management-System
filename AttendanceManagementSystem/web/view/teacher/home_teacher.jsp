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
        <link href="../css/HomeTeacherStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <a href="../teacher/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: ${sessionScope.account.displayName} | <a href="../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="#">Home</a>
            <a href="../teacher/check-attendance">Check Attendance</a>
        </div>

        <div id="background">
            <span>Welcome to <hr style="width:400px"> Student Attendance Management System</span>
        </div>
    </body>
</html>
