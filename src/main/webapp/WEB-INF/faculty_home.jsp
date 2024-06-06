<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="com.first.software_project.Faculty"%>
<%@ page import="com.first.software_project.Scheduled_class"%>
<%@ page import="com.first.software_project.Subjects"%>
<%@ page import="com.first.software_project.Allocation_done"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Faculty Home Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            color: #333;
        }

        h2 {
            color: #2c3e50;
        }

        .header, .date {
            background-color: rgb(29, 92, 92);
            font-size: 22px;
            color: white;
            padding: 10px;
            border-radius: 8px;
            margin: 10px;
        }

        .header {
            display: inline-block;
        }

        .date {
            display: inline-block;
            margin-left: 20px;
        }

        .class-container {
            margin-top: 8px;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            width: 100%;
        }

        .class-box {
            border: 2px solid brown;
            height: 140px;
            border-radius: 8px;
            text-align: center;
            padding-top: 52px;
            background-color: rgb(255, 187, 153);
            width: 22%;
            margin: 10px;
        }

        form {
            margin-top: 10px;
        }

        input[type="submit"] {
            padding: 8px 16px;
            background-color: #2c3e50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #1a252f;
        }
    </style>
</head>
<body>
    <h2>My Dashboard</h2>
    <div style="display: flex; justify-content: space-between; width: 90%;">
        <div class="header">Faculty Name: <%=((Faculty)session.getAttribute("faculty_details")).getname() %></div>
        <% 
            LocalDate todaydate = LocalDate.now();
            DayOfWeek day = todaydate.getDayOfWeek();
        %>
        <div class="date"><%= day.toString() %></div>
    </div>
    <h2>Today's Classes</h2>
    <div class="class-container">
        <%
        if (session.getAttribute("class_list") == null) {
            System.out.println("There was an exception in fetching the data.\n");
        } else {
            List<Scheduled_class> list = (List<Scheduled_class>)session.getAttribute("class_list");
            Allocation_done[] checklist = (Allocation_done[])session.getAttribute("allocation_checklist");
            for (int i = 0; i < list.size(); i++) {
        %>
        <div class="class-box">
            Batch: <%= list.get(i).getbatch() %>
            <br>Topic: <%= list.get(i).gettopic().getsubject_name() %>
            <br>Starting time: <%= list.get(i).getstart() %>
            <br>End time: <%= list.get(i).getend() %>
            <% if (checklist[i].getallocation_done() != -1) { %>
                <br>Allocated Already At Room no: <%= checklist[i].getallocation_done() %>
            <% } else { %>
                <br><span>Class Yet To Be Allocated a Room</span>
                <% session.setAttribute("class_to_allocate", list.get(i)); %>
                <form action="/allocation_details">
                    <input type="submit" value="Allocate Room">
                </form>
            <% } %>
        </div>
        <% }
        } %>
    </div>
</body>
</html>
