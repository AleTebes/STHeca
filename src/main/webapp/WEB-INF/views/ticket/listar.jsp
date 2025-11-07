<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tickets</title>
    <%@ include file="/includes/header.jsp" %>
</head>
<body class="d-flex flex-column min-vh-100" style="background-color:#e6f7ff;">
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container mt-5 p-4 shadow-sm" style="background-color: #99ddff; border-radius: 15px;">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="fw-bold text-uppercase mb-0">Tickets</h1>
        <c:choose>
            <c:when test="${hasDispositivos}">
                <a href="${pageContext.request.contextPath}/ticket?action=agregar" class="btn btn-light fw-semibold" style="border-radius:10px;">Crear ticket</a>
            </c:when>
            <c:otherwise>
                <button id="crearTicketBtn" class="btn btn-light fw-semibold" style="border-radius:10px;">Crear ticket</button>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle mb-0" style="background-color: white; border-radius: 10px; overflow: hidden;">
            <thead style="background-color: #e6f7ff;">
            <tr class="text-center">
                <th>ID</th>
                <th>Título</th>
                <th>Estado</th>
                <th>Dispositivo</th>
                <th>Usuario</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tickets}" var="t">
                <tr>
                    <td class="text-center" style="font-weight:600;">${t.id}</td>
                    <td class="text-center" style="font-weight:600;">${t.titulo}</td>
                    <td class="text-center" style="font-weight:600;">${t.estado}</td>
                    <td class="text-center" style="font-weight:600;">
                        <c:if test="${not empty t.dispositivo}">${t.dispositivo.nombre}</c:if>
                    </td>
                    <td  class="text-center" style="font-weight:600;">
                        <c:if test="${not empty t.usuario}">${t.usuario.nombre}</c:if>
                    </td>
                    <td class="text-center">
                        <a href="${pageContext.request.contextPath}/ticket?action=editar&id=${t.id}" class="btn btn-sm" style="background-color:#007bff;color:white;border-radius:8px;">Editar</a>
                        <a href="${pageContext.request.contextPath}/ticket?action=eliminar&id=${t.id}" class="btn btn-sm" style="background-color:#dc3545;color:white;border-radius:8px;" onclick="return confirm('¿Eliminar ticket?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Modal de advertencia si no hay dispositivos -->
<div class="modal fade" id="noDispModal" tabindex="-1" aria-labelledby="noDispModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-center w-100" id="noDispModalLabel">Crear Ticket</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>No existen dispositivos</p>
                <p>Para crear un ticket necesitas crear al menos un dispositivo.</p>
                <p>¿Deseas crear un dispositivo ahora?</p>
            </div>
            <div class="modal-footer justify-content-center">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <a id="confirmCreateDisp"
                   href="${pageContext.request.contextPath}/dispositivo?action=agregar"
                   class="btn btn-primary">
                    Crear dispositivo
                </a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    (function(){
        var btn = document.getElementById('crearTicketBtn');
        if (btn) {
            btn.addEventListener('click', function(){
                var modal = new bootstrap.Modal(document.getElementById('noDispModal'));
                modal.show();
            });
        }
    })();
</script>
</body>
</html>
