<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="com.first.software_project.Faculty"%>
<%@ page import="com.first.software_project.Scheduled_class"%>
<%@ page import="com.first.software_project.Subjects"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Faculty Home Page</title>
</head>
<body>
    <h2>My Dashboard</h2>
    <div style=" display: flexbox; justify-content:space-between">
        <!-- This is the faculty name -->
        <div style="background-color: rgb(29, 92, 92); font-size: 22px; color: white; display:inline-block; padding:10px;">Faculty Name: <%=((Faculty)request.getAttribute("faculty_details")).getname() %></div>
        <% LocalDate todaydate=LocalDate.now();
		    DayOfWeek day=todaydate.getDayOfWeek(); %>
        <div style="background-color: rgb(29,92,92); font-size: 22px; display:inline-block;color: white; padding:10px"> <%=day.toString() %> </div>
    </div>
    <h2>Today's Classes</h2>
    <div style="margin-top:12px; ">
        <% List<Scheduled_class> list=(List<Scheduled_class>)request.getAttribute("class_list"); 
        for(int i=0;i<list.size();i++){
        %>
        <div style="border: 2px solid brown;height: 140px; border-radius: 8px; display: inline-block; width: 25%; text-align: center; padding-top: 52px; background-color: rgb(255, 187, 153);">
            Batch: <%=(list.get(0).getbatch()) %>
            <br>Topic: <%=list.get(0).gettopic().getsubject_name() %>
            <br>Starting time: <%=list.get(0).getstart() %>
            <br>End time: <%=list.get(0).getend() %>
        </div>
        <%} %>
    </div>
</body>
</html>