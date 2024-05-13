<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.first.software_project.Classes_for_batches"%>
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

</style>
<body>
    <div  id="heading">
        Today's Classes
    </div>
    <%  List<Classes_for_batches> class_list=(List<Classes_for_batches>)request.getAttribute("class_list"); 
        boolean[] checklist=(boolean[])request.getAttribute("allocation_checklist"); 
        if(class_list.size()==0){ %>
            <div class="classes">There are no classes today.</div>
        <%}
        else{
            for(int i=0;i<class_list.size();i++){ %> 
                <div id="class_section">
                    <div class="classes">
                        <div><%=class_list.get(i).getsubject_name()  %> </div>
                        <div><%=class_list.get(i).getname()  %> </div>
                        <div><%=class_list.get(i).getexpected_start()  %> </div>
                        <div><%=class_list.get(i).getexpected_end()  %> </div>
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