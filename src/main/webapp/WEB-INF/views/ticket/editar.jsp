<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Editar Ticket</title>
    <%@ include file="/includes/header.jsp" %>
</head>
<body class="d-flex flex-column min-vh-100" style="background-color:#e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container mt-5 p-4 shadow-sm" style="background-color:#99ddff;border-radius:15px;">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/menu">Menú</a></li>
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ticket">Tickets</a></li>
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

    <div class="card shadow-sm border-0">
        <div class="card-body">
            <h2 class="card-title">Editar Ticket</h2>
            <form action="${pageContext.request.contextPath}/ticket" method="post" class="mt-3" id="form-ticket" novalidate>
                <input type="hidden" name="action" value="actualizar" />
                <input type="hidden" name="id" value="${ticket.id}" />

                <div class="mb-3">
                    <label for="titulo" class="form-label">Título</label>
                    <input id="titulo" type="text" name="titulo" class="form-control" value="${ticket.titulo}" required />
                    <div class="invalid-feedback">El título es obligatorio.</div>
                </div>

                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción</label>
                    <textarea id="descripcion" name="descripcion" class="form-control" rows="4" required>${ticket.descripcion}</textarea>
                    <div class="invalid-feedback">La descripción es obligatoria.</div>
                </div>

                <div class="mb-3">
                    <label for="estado" class="form-label">Estado</label>
                    <select id="estado" name="estado" class="form-select" required>
                        <option value="">-- Seleccionar estado --</option>
                        <option value="ABIERTO" ${ticket.estado == 'ABIERTO' ? 'selected' : ''}>ABIERTO</option>
                        <option value="EN_PROCESO" ${ticket.estado == 'EN_PROCESO' ? 'selected' : ''}>EN_PROCESO</option>
                        <option value="CERRADO" ${ticket.estado == 'CERRADO' ? 'selected' : ''}>CERRADO</option>
                    </select>
                    <div class="invalid-feedback">Seleccione un estado.</div>
                </div>

                <div class="mb-3">
                    <label for="dispositivoId" class="form-label">Dispositivo</label>
                    <select id="dispositivoId" name="dispositivoId" class="form-select">
                        <option value="">-- Ninguno --</option>
                        <c:forEach items="${dispositivos}" var="d">
                            <option value="${d.id}" ${not empty ticket.dispositivo and d.id == ticket.dispositivo.id ? 'selected' : ''}>${d.id} - ${d.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="usuarioId" class="form-label">Usuario</label>
                    <select id="usuarioId" name="usuarioId" class="form-select">
                        <option value="">-- Ninguno --</option>
                        <c:forEach items="${usuarios}" var="u">
                            <option value="${u.id}" ${not empty ticket.usuario and u.id == ticket.usuario.id ? 'selected' : ''}>${u.id} - ${u.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">Actualizar</button>
                    <a href="${pageContext.request.contextPath}/ticket" class="btn btn-light">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    (function () {
        'use strict'
        var form = document.getElementById('form-ticket')
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })()
</script>
</body>
</html>
