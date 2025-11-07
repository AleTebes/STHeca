<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dispositivos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/estilos.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100" style="background-color:#e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container mt-5 p-4 shadow-sm" style="background-color:#99ddff;border-radius:15px;">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="fw-bold text-uppercase mb-0">Dispositivos</h1>
        <a href="${pageContext.request.contextPath}/dispositivo?action=agregar" class="btn btn-light fw-semibold" style="border-radius:10px;">Agregar dispositivo</a>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle mb-0" style="background-color:white;border-radius:10px;overflow:hidden;">
            <thead style="background-color:#e6f7ff;">
            <tr class="text-center">
                <th>ID</th>
                <th>Nombre</th>
                <th>Tipo</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dispositivos}" var="d">
                <tr>
                    <td class="text-center" style="font-weight:600;">${d.id}</td>
                    <td>${d.nombre}</td>
                    <td>${d.tipo.nombre}</td>
                    <td class="text-center">
                        <a href="${pageContext.request.contextPath}/dispositivo?action=editar&id=${d.id}" class="btn btn-sm" style="background-color:#007bff;color:white;border-radius:8px;">Editar</a>
                        <a href="${pageContext.request.contextPath}/dispositivo?action=eliminar&id=${d.id}" class="btn btn-sm" style="background-color:#dc3545;color:white;border-radius:8px;" onclick="return confirm('¿Eliminar dispositivo?')">Eliminar</a>
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
