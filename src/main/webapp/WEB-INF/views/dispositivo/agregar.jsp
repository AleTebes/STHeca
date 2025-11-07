<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Agregar Dispositivo</title>
    <%@ include file="/includes/header.jsp" %>
</head>
<body class="d-flex flex-column min-vh-100" style="background-color:#e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />
<div class="container mt-5 p-4 shadow-sm" style="background-color:#99ddff;border-radius:15px;">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold mb-0">Agregar Dispositivo</h2>
        <a href="${pageContext.request.contextPath}/dispositivo" class="btn btn-light fw-semibold" style="border-radius:10px;">Volver</a>
    </div>

    <form action="${pageContext.request.contextPath}/dispositivo" method="post" class="mt-3">
        <input type="hidden" name="action" value="guardar" />
        <div class="mb-3">
            <label class="form-label">Nombre</label>
            <input type="text" class="form-control" name="nombre" required />
        </div>
        <div class="mb-3">
            <label class="form-label">Tipo</label>
            <select name="tipoId" class="form-select">
                <option value="">-- Ninguno --</option>
                <c:forEach items="${tipos}" var="tp">
                    <option value="${tp.id}">${tp.id} - ${tp.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="${pageContext.request.contextPath}/dispositivo" class="btn btn-light">Cancelar</a>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
