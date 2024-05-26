<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ page import="com.first.software_project.Faculty"%>
<%@ page import="com.first.software_project.Scheduled_class"%>
<%@ page import="com.first.software_project.Subjects"%>
<%@ page import="com.first.software_project.Allocation_done"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class allocation details</title>
</head>
<body>
    <div>Set the desired timings for the Class to be allocated</div>
    <div>
        <span>
            Class ID: 
        </span><%=((Scheduled_class)session.getAttribute("class_to_allocate")).getclass_id() %> <br>
        <span>Batch: </span><%=((Scheduled_class)session.getAttribute("class_to_allocate")).getbatch() %> <br>
        <span>Topic: </span><%=((Scheduled_class)session.getAttribute("class_to_allocate")).gettopic().getsubject_name() %> <br>
        <form action="/room_selection">
            <input type="number" name="starting_time" min="<%=((Scheduled_class)session.getAttribute("class_to_allocate")).getstart()  %>" max="<%=((Scheduled_class)session.getAttribute("class_to_allocate")).getstart()+60  %>" value="<%=((Scheduled_class)session.getAttribute("class_to_allocate")).getstart()  %>">
            
            <input type="number" name="ending_time" min="<%=((Scheduled_class)session.getAttribute("class_to_allocate")).getstart()  %>" max="<%=((Scheduled_class)session.getAttribute("class_to_allocate")).getend()+15  %>" value="<%=((Scheduled_class)session.getAttribute("class_to_allocate")).getend()  %>">
            <input type="submit" value="ALLOCATE">
        </form>
    </div>
    <div>Choose the Room</div>
</body>
</html>