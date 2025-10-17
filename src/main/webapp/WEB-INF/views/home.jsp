<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Employee Management</title>

    <style>
        .warn {
            color: red;
            text-align: center;
            font-weight: bold;
            margin-bottom: 10px;
        }



        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8f8;
            margin: 0;
            padding: 30px;
            color: #333;
        }

        h2 {
            color: #02CAA9;
            text-align: center;
            font-size: 24px;
            margin-bottom: 30px;
        }

        /* ====== FORM ====== */
        form {
            background-color: #ffffff;
            padding: 20px 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: 0 auto 30px auto;
        }

        label {
            display: inline-block;
            width: 110px;
            font-weight: bold;
            margin-bottom: 5px;
            color: #02CAA9;
        }

        input[type="text"] {
            width: 200px;
            padding: 6px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            background-color: #02CAA9;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 5px;
            margin-top: 10px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #009f8a;
        }

        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            background-color: white;
        }

        th {
            background-color: #02CAA9;
            color: white;
            padding: 10px;
            text-align: left;
        }

        td {
            padding: 8px 10px;
            border-bottom: 1px solid #ddd;
        }

        tr:nth-child(even) {
            background-color: #f7f7f7;
        }

        tr:hover {
            background-color: #e6f9f6;
        }

        a {
            color: #02CAA9;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        @media (max-width: 600px) {
            form {
                width: 90%;
                padding: 15px;
            }

            label {
                width: 100%;
                display: block;
            }

            input[type="text"] {
                width: 100%;
            }

            table {
                width: 100%;
                font-size: 12px;
            }
        }

        .logout-btn {
            position: fixed;
            top: 20px;
            left: 20px;
            background-color: #f0f8f8;;
            color: #02CAA9;
            padding: 8px 14px;
            border-radius: 6px;
            text-decoration: none;
            font-weight: bold;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s;
        }

    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/logout" class="logout-btn">Logout</a>
<h2>Welcome, ${username}!</h2>
<h2> ${role}</h2>
<%--test role--%>
<c:if test="${not empty role}">
    <div class="warn">${role}</div>
</c:if>


<form:form modelAttribute="employee" method="post"
           action="${pageContext.request.contextPath}/employeeAction">
    <form:hidden path="id"/>
    <label>Empl ID:</label>
    <form:input path="employeeID"/> <br>
    <label>Empl name:</label>
    <form:input path="name"/> <br>
    <label>Empl salary:</label>
    <form:input path="salary"/> <br>
    <label>Department:</label>

    <form:select path="department.id">
        <form:options items="${departments}" itemValue="id" itemLabel="departmentName"/>
    </form:select> <br>

    <input type="submit" name="action" value="Add">
    <input type="submit" name="action" value="Update">

</form:form>

<table border="1">

    <tr>
        <th>ID</th>
        <th>EmployeeID</th>
        <th>Name</th>
        <th>Salary</th>
        <th>department</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="emp" items="${list}">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.employeeID}</td>
            <td>${emp.name}</td>
            <td>${emp.salary}</td>
            <td>${emp.department}</td>
            <td>
                <a href="${pageContext.request.contextPath}/edit?id=${emp.id}">Edit</a> |
                <a href="${pageContext.request.contextPath}/delete?id=${emp.id}"
                   onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>

