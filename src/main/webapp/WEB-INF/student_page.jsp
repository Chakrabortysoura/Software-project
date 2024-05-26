<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.first.software_project.Scheduled_class"%>
<%@ page import="com.first.software_project.Faculty"%>
<%@ page import="com.first.software_project.Subjects"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>student</title>
</head>
<script>
    
</script>
<style>
body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }
        #heading {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 20px;
            font-size: 24px;
        }
        .classes {
            background-color: white;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
        }
        .class_section {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
        }
        .class_section:last-child {
            border-bottom: none;
        }
        .class_section div {
            flex: 1;
            text-align: center;
        }
        .allocated {
            color: green;
            font-weight: bold;
        }
        .not-allocated {
            color: red;
            font-weight: bold;
        }
</style>
<body>
    <div id="heading">Today's Classes</div>
    <% List<Scheduled_class> class_list = (List<Scheduled_class>) request.getAttribute("class_list");
        boolean[] checklist = (boolean[]) request.getAttribute("allocation_checklist");
        if (class_list == null) { %>
            <div class="classes">There are no classes today.</div>
    <% } else {
            for (int i = 0; i < class_list.size(); i++) { %> 
                <div class="classes">
                    <div class="class_section">
                        <div>Subject<br><%= class_list.get(i).gettopic().getsubject_name() %></div>
                        <div>Faculty<br><%= class_list.get(i).getteacher().getname() %></div>
                        <div>Start_Time<br><%= class_list.get(i).getstart() %></div>
                        <div>End_Time<br><%= class_list.get(i).getend() %></div>
                        <div class="<%= checklist[i] ? "allocated" : "not-allocated" %>">
                            <%= checklist[i] ? "Already Allocated" : "Not allocated yet" %>
                        </div>
                    </div>
                </div>
        <% }
        } %>
</body>
</html>