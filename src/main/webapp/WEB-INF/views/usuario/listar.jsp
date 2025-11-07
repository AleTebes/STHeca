<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Usuarios</title>
    <%@ include file="/includes/header.jsp" %>
</head>
<body class="d-flex flex-column min-vh-100" style="background-color: #e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />
<div class="container mt-5 p-4 shadow-sm" style="background-color: #99ddff; border-radius: 15px;">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1 class="fw-bold">Usuarios</h1>
        <a href="${pageContext.request.contextPath}/usuario?action=agregar" class="btn btn-light fw-semibold" style="border-radius:10px;">Agregar usuario</a>
    </div>
    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle mb-0" style="background-color: white; border-radius: 10px; overflow: hidden;">
            <thead style="background-color: #e6f7ff;">
            <tr class="text-center">
                <th>ID</th>
                <th>Nombre</th>
                <th>Email</th>
                <th>Rol</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${usuarios}" var="u">
                <tr>
                    <td class="text-center" style="font-weight:600;">${u.id}</td>
                    <td class="text-center" style="font-weight:600;">${u.nombre}</td>
                    <td class="text-center" style="font-weight:600;">${u.email}</td>
                    <td class="text-center" style="font-weight:600;">${u.rol}</td>
                    <td class="text-center">
                        <a href="${pageContext.request.contextPath}/usuario?action=editar&id=${u.id}" class="btn btn-sm" style="background-color:#007bff;color:white;border-radius:8px;">Editar</a>
                        <a href="${pageContext.request.contextPath}/usuario?action=eliminar&id=${u.id}" class="btn btn-sm" style="background-color:#dc3545;color:white;border-radius:8px;" onclick="return confirm('Eliminar usuario?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
