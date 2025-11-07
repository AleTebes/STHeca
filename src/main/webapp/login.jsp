<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - STHeca</title>
    <%@ include file="/includes/header.jsp" %>
</head>
<body class="d-flex flex-column min-vh-100" style="background-color: #e6f7ff;">

<!-- Contenedor centrado similar al resto del sitio -->
<div class="container flex-grow-1 d-flex align-items-center justify-content-center py-5">
    <div class="row w-100 justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-sm" style="border-radius:12px; overflow:hidden;">
                <div class="p-4" style="background-color:#99ddff;">
                    <div class="text-center mb-3">
                        <img src="${pageContext.request.contextPath}/images/STH.png"
                             alt="Logo STHeca"
                             style="width:100px; border-radius:8px;">
                    </div>
                    <h3 class="text-center fw-bold mb-2">Iniciar sesión</h3>
                    <p class="text-center text-muted mb-0">Ingresa tu correo y contraseña</p>
                </div>
                <div class="card-body p-4" style="background-color:white;">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">${error}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control rounded-3" id="email" name="email" required autofocus>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña</label>
                            <input type="password" class="form-control rounded-3" id="password" name="password" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg rounded-3">Entrar</button>
                        </div>
                    </form>

                </div>
            </div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
