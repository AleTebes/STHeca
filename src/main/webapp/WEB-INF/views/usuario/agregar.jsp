<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Agregar Usuario</title>
    <%@ include file="/includes/header.jsp" %>
</head>
<body class="d-flex flex-column min-vh-100" style="background-color:#e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container mt-5 p-4 shadow-sm" style="background-color: #99ddff; border-radius: 15px;">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold mb-0">Agregar Usuario</h2>
        <a href="${pageContext.request.contextPath}/usuario" class="btn btn-light fw-semibold" style="border-radius:10px;">Volver</a>
    </div>

    <form action="${pageContext.request.contextPath}/usuario" method="post" class="mt-3">
        <input type="hidden" name="action" value="guardar" />
        <div class="mb-3">
            <label class="form-label">Nombre</label>
            <input type="text" class="form-control" name="nombre" placeholder="Nombre completo" required />
        </div>
        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" class="form-control" name="email" placeholder="usuario@dominio" required />
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" class="form-control" name="password" placeholder="Contraseña segura" required />
        </div>
        <div class="mb-3">
            <label class="form-label">Rol</label>
            <select name="rol" class="form-select">
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>
        </div>
        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="${pageContext.request.contextPath}/usuario" class="btn btn-light">Cancelar</a>
        </div>
    </form>
</div>

</body>
</html>
