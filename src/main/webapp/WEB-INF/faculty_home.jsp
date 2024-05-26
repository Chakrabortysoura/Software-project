<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
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
</head>
<body>
    <h2>My Dashboard</h2>
    <div style=" display: flexbox; justify-content:space-between">
        <!-- This is the faculty name -->
        <div style="background-color: rgb(29, 92, 92); font-size: 22px; color: white; display:inline-block; padding:10px;">Faculty Name: <%=((Faculty)session.getAttribute("faculty_details")).getname() %></div>
        <% LocalDate todaydate=LocalDate.now();
		    DayOfWeek day=todaydate.getDayOfWeek(); %>
        <div style="background-color: rgb(29,92,92); font-size: 22px; display:inline-block;color: white; padding:10px"> <%=day.toString() %> </div>
    </div>
    <h2>Today's Classes</h2>
    <div style="margin-top:8px; ">
        <%
        if(session.getAttribute("class_list")==null){
            System.out.println("There was an exception in fetching the data.\n");
        } 
        else{
            List<Scheduled_class> list=(List<Scheduled_class>)session.getAttribute("class_list"); 
            Allocation_done[] checklist=(Allocation_done[])session.getAttribute("allocation_checklist");
            for(int i=0;i<list.size();i++){
        %>
        <div style="border: 2px solid brown;height: 140px; border-radius: 8px; display: inline-block; width: 25%; text-align: center; padding-top: 52px; background-color: rgb(255, 187, 153);">
            Batch: <%=(list.get(i).getbatch()) %>
            <br>Topic: <%=list.get(i).gettopic().getsubject_name() %>
            <br>Starting time: <%=list.get(i).getstart() %>
            <br>End time: <%=list.get(i).getend() %>
            <% if(checklist[i].getallocation_done()!=-1){%>
                <br>Allocated Already At Room no: <%=checklist[i].getallocation_done()%>
            <%}
            else{%>
                <br>
                <%=checklist[i].getallocation_done() %>
                <%  
                    session.setAttribute("class_to_allocate",list.get(i));
                %>
                <form action="/allocation_details">
                    <input type="submit" value="Allocate Room">
                </form>
            <%}%>
        </div>
        <% }
        } %>
    </div>
</body>
</html>