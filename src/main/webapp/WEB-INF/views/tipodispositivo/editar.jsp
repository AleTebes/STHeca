<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Editar Tipo de Dispositivo</title>
    <%@ include file="/includes/header.jsp" %>
</head>
<body class="d-flex flex-column min-vh-100" style="background-color:#e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />
<div class="container mt-5 p-4 shadow-sm" style="background-color:#99ddff;border-radius:15px;">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/menu">Menú</a></li>
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/tipodispositivo">Tipos</a></li>
            <li class="breadcrumb-item active" aria-current="page">Editar</li>
        </ol>
    </nav>

    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title">Editar Tipo de Dispositivo</h2>
            <form action="${pageContext.request.contextPath}/tipodispositivo" method="post" class="mt-3">
                <input type="hidden" name="action" value="actualizar" />
                <input type="hidden" name="id" value="${tipo.id}" />

                <div class="mb-3">
                    <label class="form-label">Nombre</label>
                    <input type="text" name="nombre" class="form-control" value="${tipo.nombre}" required />
                </div>
                <div class="mb-3">
                    <label class="form-label">Descripción</label>
                    <textarea name="descripcion" class="form-control" rows="3">${tipo.descripcion}</textarea>
                </div>
                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                    <a href="${pageContext.request.contextPath}/tipodispositivo" class="btn btn-light">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
