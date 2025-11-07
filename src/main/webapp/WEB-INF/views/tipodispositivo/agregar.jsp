<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Agregar Tipo de Dispositivo</title>
    <%@ include file="/includes/header.jsp" %>
</head>
<body class="d-flex flex-column min-vh-100" style="background-color:#e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />
<div class="container mt-5 p-4 shadow-sm" style="background-color:#99ddff;border-radius:15px;">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold mb-0">Nuevo Tipo de Dispositivo</h2>
        <a href="${pageContext.request.contextPath}/tipodispositivo" class="btn btn-light fw-semibold" style="border-radius:10px;">Volver</a>
    </div>

    <form action="${pageContext.request.contextPath}/tipodispositivo" method="post" class="mt-3">
        <input type="hidden" name="action" value="guardar" />
        <div class="mb-3">
            <label class="form-label">Nombre</label>
            <input type="text" name="nombre" class="form-control" placeholder="Nombre del tipo" required />
        </div>
        <div class="mb-3">
            <label class="form-label">Descripción</label>
            <textarea name="descripcion" class="form-control" rows="3" placeholder="Descripción opcional"></textarea>
        </div>
        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">Crear</button>
            <a href="${pageContext.request.contextPath}/tipodispositivo" class="btn btn-light">Cancelar</a>
        </div>
    </form>
</div>
</body>
</html>
