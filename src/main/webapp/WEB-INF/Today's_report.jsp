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
    <title>Today's Reports</title>
</head>
<body>
    <div  id="heading">
        Today's Classes So Far
    </div>
    <%  List<Scheduled_class> class_list=(List<Scheduled_class>)request.getAttribute("class_list"); 
        boolean[] checklist=(boolean[])request.getAttribute("checklist"); 
        if(class_list.size()==0){ %>
            <div class="classes">There are no classes today.</div>
        <%}
        else{
            for(int i=0;i<class_list.size();i++){ %> 
                <div id="class_section">
                    <div class="classes">
                        <div><%=class_list.get(i).gettopic().getsubject_name()  %> </div>
                        <div><%=class_list.get(i).getteacher().getname()  %> </div>
                        <div><%=class_list.get(i).getstart()  %> </div>
                        <div><%=class_list.get(i).getend()  %> </div>
                        <%if(checklist[i]==true){%>
                            <div>Already Allocated </div>
                        <%}
                        else{%>
                            <div>Not allocated yet</div>
                        <%}%>
                    </div>
                </div> 
        <% }
        } %>
</body>
</html>