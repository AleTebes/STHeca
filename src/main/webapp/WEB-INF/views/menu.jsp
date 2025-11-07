<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Menú - STHeca</title>
    <%@ include file="/includes/header.jsp" %>
</head>
<body class="d-flex flex-column min-vh-100" style="background-color: #e6f7ff;">

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<!-- Contenedor principal -->
<div class="container flex-grow-1 d-flex align-items-center">
    <div class="row w-100 justify-content-center">

        <!-- Card Usuarios -->
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm border-0 text-center"
                 style="background-color: #99ddff; border-radius: 15px;">
                <div class="card-body py-5">
                    <h5 class="card-title fw-bold text-uppercase">Usuarios</h5>
                    <p class="card-text">Administrar usuarios.</p>
                    <a href="${pageContext.request.contextPath}/usuario"
                       class="btn btn-light fw-semibold"
                       style="border-radius: 10px;">Ir a Usuarios</a>
                </div>
            </div>
        </div>

        <!-- Card Dispositivos -->
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm border-0 text-center"
                 style="background-color: #99ddff; border-radius: 15px;">
                <div class="card-body py-5">
                    <h5 class="card-title fw-bold text-uppercase">Dispositivos</h5>
                    <p class="card-text">Administrar dispositivos.</p>
                    <a href="${pageContext.request.contextPath}/dispositivo"
                       class="btn btn-light fw-semibold"
                       style="border-radius: 10px;">Ir a Dispositivos</a>
                </div>
            </div>
        </div>

        <!-- Card Tickets -->
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm border-0 text-center"
                 style="background-color: #99ddff; border-radius: 15px;">
                <div class="card-body py-5">
                    <h5 class="card-title fw-bold text-uppercase">Tickets</h5>
                    <p class="card-text">Administrar tickets.</p>
                    <a href="${pageContext.request.contextPath}/ticket"
                       class="btn btn-light fw-semibold"
                       style="border-radius: 10px;">Ir a Tickets</a>
                </div>
            </div>
        </div>

        <!-- Card Sectores -->
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm border-0 text-center"
                 style="background-color: #99ddff; border-radius: 15px;">
                <div class="card-body py-5">
                    <h5 class="card-title fw-bold text-uppercase">Sectores</h5>
                        <p class="card-text">Administrar Sectores.</p>
                    <a class="btn btn-light fw-semibold"
                       style="border-radius: 10px;">Proximamente</a>
                </div>
            </div>
        </div>

        <!-- Card Tipos de Dispositivo -->
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm border-0 text-center"
                 style="background-color: #99ddff; border-radius: 15px;">
                <div class="card-body py-5">
                    <h5 class="card-title fw-bold text-uppercase">Tipos de Dispositivo</h5>
                    <p class="card-text">Administrar tipos de dispositivo.</p>
                    <div class="d-flex justify-content-center gap-2">
                        <a href="${pageContext.request.contextPath}/tipodispositivo"
                           class="btn btn-light fw-semibold"
                           style="border-radius: 10px;">Ir a Tipos</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Card Software -->
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm border-0 text-center"
                    style="background-color: #99ddff; border-radius: 15px;">
                    <div class="card-body py-5">
                        <h5 class="card-title fw-bold text-uppercase">Software</h5>
                        <p class="card-text">Administrar softwares.</p>
                        <a class="btn btn-light fw-semibold"
                           style="border-radius: 10px;">Proximamente</a>
                    </div>
            </div>
        </div>
</div>

<!-- Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
