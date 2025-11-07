<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Editar Usuario</title>
    <%@ include file="/includes/header.jsp" %>
</head>
<body class="d-flex flex-column min-vh-100" style="background-color:#e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />
<div class="container mt-4 p-4 shadow-sm" style="background-color: #99ddff; border-radius: 15px;">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/menu">Menú</a></li>
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/usuario">Usuarios</a></li>
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
            <h2 class="card-title">Editar Usuario</h2>
            <form action="${pageContext.request.contextPath}/usuario" method="post" class="mt-3" novalidate id="form-usuario">
                <input type="hidden" name="action" value="actualizar" />
                <input type="hidden" name="id" value="${usuario.id}" />

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input id="nombre" type="text" class="form-control" name="nombre" value="${usuario.nombre}" required />
                    <div class="invalid-feedback">Ingrese el nombre.</div>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input id="email" type="email" class="form-control" name="email" value="${usuario.email}" required />
                    <div class="invalid-feedback">Ingrese un email válido.</div>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input id="password" type="password" class="form-control" name="password" value="" placeholder="Nueva contraseña" />
                </div>

                <div class="mb-3">
                    <label for="rol" class="form-label">Rol</label>
                    <select id="rol" name="rol" class="form-select">
                        <option value="USER" ${usuario.rol == 'USER' ? 'selected' : ''}>USER</option>
                        <option value="ADMIN" ${usuario.rol == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                    </select>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">Actualizar</button>
                    <a href="${pageContext.request.contextPath}/usuario" class="btn btn-light">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    (function () {
        'use strict'
        var form = document.getElementById('form-usuario')
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
