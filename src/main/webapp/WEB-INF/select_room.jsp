<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="com.first.software_project.Rooms"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<script>
    
</script>
<body>
    <h2>Select a room to be allocated for this class</h2>
    <div>
        <form action="/allocate_room">
            <%
                List<Rooms> available_rooms=(List<Rooms>) session.getAttribute("rooms_list");
                for(int i=0;i<available_rooms.size();i++){
            %>
                <span><%=available_rooms.get(i).getroom_no() %></span>
                <input type="checkbox" name="room_no" id="" value="<%=available_rooms.get(i).getroom_no() %>">
            <%}%>
            <input type="hidden" name="starting" value="<%=Integer.parseInt((request).getParameter("starting_time"))%>">
            <input type="hidden" name="ending" value="<%=Integer.parseInt((request).getParameter("ending_time"))%>">
            <input type="submit" value="Allocate Room">
        </form>  
    </div>
</body>
</html>