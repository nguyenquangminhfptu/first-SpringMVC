<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management</title>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            background: #f4f7f9;
            padding: 40px;
            margin: 0;
        }
        .container {
            max-width: 1200px;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            margin: auto;
            box-shadow: 0 6px 18px rgba(0,0,0,0.05);
        }
        h1 {
            margin-top: 0;
            font-size: 26px;
            margin-bottom: 12px;
        }
        p.note {
            color: #6b7280;
            font-size: 13px;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 6px;
            overflow: hidden;
        }
        th, td {
            text-align: left;
            padding: 12px 10px;
            border-bottom: 1px solid #e5e7eb;
            font-size: 14px;
        }
        thead th {
            background: #f9fafb;
            color: #4b5563;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 12px;
        }
        tr:hover td {
            background: #f9fafb;
        }
        .txt {
            width: 100%;
            padding: 7px 9px;
            border: 1px solid #d1d5db;
            border-radius: 6px;
            box-sizing: border-box;
        }
        .txt:focus {
            outline: none;
            border-color: #2563eb;
            box-shadow: 0 0 0 2px rgba(37,99,235,0.2);
        }
        .btn {
            padding: 7px 14px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            display: inline-block;
        }
        .btn-primary {
            background: #2563eb;
            color: white;
        }
        .btn-cancel {
            background: #f3f4f6;
            color: #374151;
            border: 1px solid #d1d5db;
        }
        .btn-delete {
            color: #b91c1c;
        }
        .img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            object-fit: cover;
            background: #f3f4f6;
        }
        .placeholder {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            background: #e5e7eb;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Employee Management</h1>
    <p class="note">Add or edit employee inline below. Image uses an HTTPS URL only.</p>

    <!-- Inline form (Add / Edit) -->
    <form action="${pageContext.request.contextPath}/employees/save" method="post">
        <table>
            <thead>
            <tr>
                <th>Image</th>
                <th>ID</th>
                <th>Name</th>
                <th>Department</th>
                <th>Salary</th>
                <th>Image URL</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!-- Dòng đầu tiên: form Add/Edit -->
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${not empty formEmployee.imageUrl}">
                            <img class="img" src="${formEmployee.imageUrl}"
                                 onerror="this.src='https://via.placeholder.com/50?text=%20';">
                        </c:when>
                        <c:otherwise>
                            <span class="placeholder"></span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${isEditing}">${formEmployee.id}</c:when>
                        <c:otherwise>—</c:otherwise>
                    </c:choose>
                    <input type="hidden" name="id" value="${formEmployee.id}">
                </td>

                <td><input class="txt" type="text" name="name" value="${formEmployee.name}" placeholder="Full name" required></td>
                <td><input class="txt" type="text" name="department" value="${formEmployee.department}" placeholder="Department" required></td>
                <td><input class="txt" type="number" step="0.01" name="salary" value="${formEmployee.salary}" placeholder="Salary" required></td>
                <td><input class="txt" type="url" name="imageUrl" value="${formEmployee.imageUrl}" placeholder="https://..." pattern="https?://.*"></td>

                <td>
                    <button class="btn btn-primary" type="submit">
                        <c:choose>
                            <c:when test="${isEditing}">Update</c:when>
                            <c:otherwise>Add</c:otherwise>
                        </c:choose>
                    </button>
                    <c:if test="${isEditing}">
                        <a href="${pageContext.request.contextPath}/employees" class="btn btn-cancel">Cancel</a>
                    </c:if>
                </td>
            </tr>

            <!-- Các dòng dữ liệu -->
            <c:forEach var="emp" items="${employees}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${not empty emp.imageUrl}">
                                <img class="img" src="${emp.imageUrl}"
                                     onerror="this.src='https://via.placeholder.com/50?text=%20';">
                            </c:when>
                            <c:otherwise><span class="placeholder"></span></c:otherwise>
                        </c:choose>
                    </td>
                    <td>${emp.id}</td>
                    <td>${emp.name}</td>
                    <td>${emp.department}</td>
                    <td>${emp.salary}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty emp.imageUrl}">
                                <a href="${emp.imageUrl}" target="_blank">${emp.imageUrl}</a>
                            </c:when>
                            <c:otherwise>—</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/employees?editId=${emp.id}">Edit</a> |
                        <a class="btn-delete"
                           href="${pageContext.request.contextPath}/employees/delete?id=${emp.id}"
                           onclick="return confirm('Delete employee #${emp.id}?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
