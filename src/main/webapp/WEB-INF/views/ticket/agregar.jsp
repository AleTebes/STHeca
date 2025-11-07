<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Crear Ticket</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/estilos.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100" style="background-color:#e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container mt-5 p-4 shadow-sm" style="background-color:#99ddff;border-radius:15px;">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold mb-0">Crear Ticket</h2>
        <a href="${pageContext.request.contextPath}/ticket" class="btn btn-light fw-semibold" style="border-radius:10px;">Volver</a>
    </div>

    <form action="${pageContext.request.contextPath}/ticket" method="post" class="mt-3">
        <input type="hidden" name="action" value="guardar" />
        <div class="mb-3">
            <label class="form-label">Título</label>
            <input type="text" name="titulo" class="form-control" required />
        </div>
        <div class="mb-3">
            <label class="form-label">Descripción</label>
            <textarea name="descripcion" class="form-control" rows="4" required></textarea>
        </div>
        <div class="mb-3">
            <label class="form-label">Estado</label>
            <input type="text" name="estado" class="form-control" value="ABIERTA" required />
        </div>
        <div class="mb-3">
            <label class="form-label">Dispositivo</label>
            <select name="dispositivoId" class="form-select">
                <option value="">-- Ninguno --</option>
                <c:forEach items="${dispositivos}" var="d">
                    <option value="${d.id}">${d.id} - ${d.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Usuario</label>
            <select name="usuarioId" class="form-select">
                <option value="">-- Ninguno --</option>
                <c:forEach items="${usuarios}" var="u">
                    <option value="${u.id}">${u.id} - ${u.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">Crear</button>
            <a href="${pageContext.request.contextPath}/ticket" class="btn btn-light">Cancelar</a>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
