<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ page import="java.time.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="faculty_home_page">
        <span>
            Give your Faculty ID:
        </span>
        <input type="text" name="id" id=""><br>
        <% LocalDate todaydate=LocalDate.now();
		    DayOfWeek day=todaydate.getDayOfWeek(); %>
        <input type="hidden" name="day" value="<%=day.toString() %>" >
        <input type="submit" value="LOGIN">
    </form>
    <p>
    H1
    </p>
</body>
</html>