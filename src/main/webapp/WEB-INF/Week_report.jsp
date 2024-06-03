<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.time.*"%>
<%@ page import="com.first.software_project.Faculty"%>
<%@ page import="com.first.software_project.Scheduled_class"%>
<%@ page import="com.first.software_project.Subjects"%>
<%@ page import="com.first.software_project.Room_allocation"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weekly Room Allocation Report</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            color: #333;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        header {
            text-align: center;
            padding-bottom: 20px;
            border-bottom: 1px solid #ddd;
        }

        header h1 {
            margin: 0;
            color: #2c3e50;
        }

        header p {
            margin: 5px 0;
            color: #777;
        }

        .report {
            margin-top: 20px;
        }

        .report h2 {
            margin-top: 0;
            color: #2c3e50;
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

        .footer {
            text-align: center;
            padding: 10px;
            background-color: #2c3e50;
            color: white;
        }

        @media print {
            .container {
                box-shadow: none;
                border-radius: 0;
            }
            header,
            .footer {
                page-break-before: always;
                page-break-after: always;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>Weekly Room Allocation Report</h1>
            <p>Techno India University - <span id="date-range"></span></p>
        </header>
        <section class="report">
            <h2>Room Allocation Summary</h2>
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
                <% List<Room_allocation> class_list= (List<Room_allocation>)request.getAttribute("class_report");
                   for(Room_allocation i:class_list){
                %>
                    <tr>
                        <td><%=i.getroom_no()%></td>
                        <td><%=i.getassigned_class().gettopic().getsubject_name()%></td>
                        <td><%=i.getassigned_class().getteacher().getname()%></td>
                        <td><%=i.getassigned_class().getbatch()%></td>
                        <td><%=i.getstarting_time()%>AM - <%=i.getending_time()%>AM</td>
                        <td><%=i.getdate()%></td>
                    </tr>
                <%}%>
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
        </section>
        <footer class="footer">
            <p>© 2024 Institution Name. All Rights Reserved.</p>
        </footer>
    </div>

    <script>
        document.querySelectorAll('.report-table tbody tr').forEach(row => {
            row.addEventListener('click', () => {
                alert('Row clicked: ' + row.cells[1].innerText);
            });
        });

        function getWeekRange() {
            const today = new Date();
            const firstDay = new Date(today.setDate(today.getDate() - today.getDay() + 1));
            const lastDay = new Date(today.setDate(today.getDate() - today.getDay() + 7));
            const options = { month: 'long', day: 'numeric', year: 'numeric' };

            return `${firstDay.toLocaleDateString('en-US', options)} - ${lastDay.toLocaleDateString('en-US', options)}`;
        }

        document.getElementById('date-range').innerText = getWeekRange();
    </script>
</body>
</html>
