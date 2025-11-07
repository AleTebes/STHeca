<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Editar Dispositivo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/estilos.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100" style="background-color:#e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />
<div class="container mt-4 p-4 shadow-sm" style="background-color:#99ddff;border-radius:15px;">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/menu">Menú</a></li>
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/dispositivo">Dispositivos</a></li>
            <li class="breadcrumb-item active" aria-current="page">Editar</li>
        </ol>
    </nav>

    <c:if test="${not empty message}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${error}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
        </div>
    </c:if>

    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title">Editar Dispositivo</h2>
            <form action="${pageContext.request.contextPath}/dispositivo" method="post" class="mt-3" novalidate id="form-dispositivo">
                <input type="hidden" name="action" value="actualizar" />
                <input type="hidden" name="id" value="${dispositivo.id}" />

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input id="nombre" type="text" class="form-control" name="nombre" value="${dispositivo.nombre}" placeholder="Nombre descriptivo" required />
                    <div class="invalid-feedback">Ingrese un nombre válido.</div>
                </div>

                <div class="mb-3">
                    <label for="tipo" class="form-label">Tipo</label>
                    <select id="tipo" name="tipoId" class="form-select">
                        <option value="">-- Ninguno --</option>
                        <c:forEach items="${tipos}" var="tp">
                            <option value="${tp.id}" ${not empty dispositivo.tipo and tp.id == dispositivo.tipo.id ? 'selected' : ''}>${tp.id} - ${tp.nombre}</option>
                        </c:forEach>
                    </select>
                    <div class="invalid-feedback">Ingrese un tipo.</div>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">Actualizar</button>
                    <a href="${pageContext.request.contextPath}/dispositivo" class="btn btn-light">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Bootstrap client-side validation
    (function () {
        'use strict'
        var forms = document.querySelectorAll('#form-dispositivo')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>
</body>
</html>
