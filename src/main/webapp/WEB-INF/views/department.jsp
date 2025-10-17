<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Department Management</title>
</head>
<style>
    .back-btn {
        display: inline-block;
        margin-bottom: 15px;
        background-color: #02CAA9;
        color: white;
        padding: 8px 16px;
        border-radius: 6px;
        text-decoration: none;
        font-weight: bold;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
        transition: background-color 0.3s;
    }

    .back-btn:hover {
        background-color: #009f8a;
    }

</style>
<body>
<h2>Department Management</h2>
<!-- Nút quay lại trang Home -->
<a href="${pageContext.request.contextPath}/home" class="back-btn">← Back to Home</a>


<form:form modelAttribute="department" method="post" action="${pageContext.request.contextPath}/departmentAction">
    <form:hidden path="id"/>
    <label>Department Name:</label>
    <form:input path="departmentName"/> <br>
    <input type="submit" name="action" value="Add">
    <input type="submit" name="action" value="Update">
</form:form>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="d" items="${list}">
        <tr>
            <td>${d.id}</td>
            <td>${d.departmentName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/editDepartment?id=${d.id}">Edit</a> |
                <a href="${pageContext.request.contextPath}/deleteDepartment?id=${d.id}"
                   onclick="return confirm('Delete this department?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
