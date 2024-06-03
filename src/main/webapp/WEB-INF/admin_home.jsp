<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.first.software_project.Faculty"%>
<%@ page import="com.first.software_project.Scheduled_class"%>
<%@ page import="com.first.software_project.Subjects"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Home</title>
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
            margin: 20px 0;
            color: #2c3e50;
        }

        #report_section {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
            align-items: stretch;
            width: 90%;
            max-width: 1200px;
            margin: 20px 0;
        }

        .report_types {
            background: #ffffff;
            border: 1px solid #ddd;
            border-radius: 8px;
            width: 250px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .report_types:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }

        .report_types span {
            display: block;
            font-size: 1.2em;
            margin-bottom: 15px;
            color: #2c3e50;
            font-weight: 700;
        }

        .buttons {
            margin-top: 20px;
        }

        .buttons a {
            display: inline-block;
            padding: 10px 20px;
            text-decoration: none;
            color: #ffffff;
            background-color: #2c3e50;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .buttons a:hover {
            background-color: #1a252f;
        }

        .report {
            margin-top: 20px;
            width: 90%;
            max-width: 1200px;
        }

        .report h2 {
            color: #2c3e50;
            margin-bottom: 10px;
        }

        .report-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        .report-table th,
        .report-table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .report-table th {
            background-color: #2c3e50;
            color: white;
        }

        .report-table tbody tr:nth-child(odd) {
            background-color: #f9f9f9;
        }

        .report-table tbody tr:hover {
            background-color: #f1f1f1;
            cursor: pointer;
        }

        .form-section {
            text-align: center;
            margin: 20px 0;
        }

        .form-section input[type="date"] {
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 50%;
            max-width: 300px;
        }

        .form-section input[type="submit"] {
            padding: 10px 20px;
            text-decoration: none;
            color: #ffffff;
            background-color: #2c3e50;
            border: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            cursor: pointer;
        }

        .form-section input[type="submit"]:hover {
            background-color: #1a252f;
        }
    </style>
</head>
<body>
    <h2>Admin Home Page</h2>
    <div id="report_section">
        <div id="faculty" class="report_types">
            <span>Faculty Section</span>
            <div class="buttons"><a href="#">Faculty Monthly Report</a></div>
        </div>
        <div id="batch" class="report_types">
            <span>Batch Section</span>
            <div class="buttons"><a href="#">Batch Specific Report</a></div>
        </div>
        <div id="weekly_report" class="report_types">
            <span>Weekly Section</span>
            <div class="buttons">
                <form class="form-section" id="dateForm" action="/weekly_report">
                    <input type="date" name="starting_date" id="report_date" required>
                    <input type="submit">
                </form>
            </div>
        </div>
    </div>
    
    <div class="report">
        <h2>Today's Room Allocation Summary</h2>
        <table class="report-table">
            <thead>
                <tr>
                    <th>Room Number</th>
                    <th>Class Name</th>
                    <th>Instructor</th>
                    <th>Batch</th>
                    <th>Allocated Time</th>
                    <th>Days (Date)</th>
                </tr>
            </thead>
            <tbody>
            <% List<Scheduled_class> class_list = (List<Scheduled_class>) session.getAttribute("class_list");
               int[] checklist = (int[]) session.getAttribute("checklist");
               if(class_list.size()>0){
               for(int i=0;i<class_list.size();i++){
            %>
                <tr>
                    <td> <%=checklist[i]%> </td>
                    <td><%=class_list.get(i).gettopic().getsubject_name()%></td>
                    <td><%=class_list.get(i).getteacher().getname()%></td>
                    <td><%=class_list.get(i).getbatch()%></td>
                    <td>09:00 AM - 10:30 AM</td>
                    <td></td>
                </tr>
            <%  }
            }%>
            </tbody>
        </table>

        <h2>Rescheduled / Canceled Classes</h2>
        <table class="report-table">
            <thead>
                <tr>
                    <th>Room Number</th>
                    <th>Class Name</th>
                    <th>Instructor</th>
                    <th>Batch</th>
                    <th>Original Time</th>
                    <th>New Time</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>201</td>
                    <td>Software Engineering</td>
                    <td>Dr. William Lee</td>
                    <td>A</td>
                    <td>09:00 AM - 10:30 AM</td>
                    <td>11:00 AM - 12:30 PM</td>
                    <td>Rescheduled</td>
                </tr>
            </tbody>
        </table>
    </div>

    <%-- <div class="report" id="weeklyReportContent" style="display: none;">
        <h2>Weekly Report</h2>
        <div id="weeklyReportHeader"></div>
        <!-- Weekly report content will be generated here -->
    </div> --%>
</body>
</html>
