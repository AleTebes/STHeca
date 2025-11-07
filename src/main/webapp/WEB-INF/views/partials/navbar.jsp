<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <nav class="navbar navbar-expand-lg navbar-dark mb-3 shadow-sm"
         style="background-color: #99ddff; border-radius: 15px;">
        <div class="container-fluid">
            <a class="navbar-brand fw-bold fs-4 text-uppercase" href="${pageContext.request.contextPath}/menu">
                STHeca
            </a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNav" aria-controls="navbarNav"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link fw-semibold text-dark" href="${pageContext.request.contextPath}/usuario">Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-semibold text-dark" href="${pageContext.request.contextPath}/dispositivo">Dispositivos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-semibold text-dark" href="${pageContext.request.contextPath}/ticket">Tickets</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-semibold text-dark" href="${pageContext.request.contextPath}/tipodispositivo">Tipos</a>
                    </li>
                </ul>

                <div class="d-flex align-items-center">
                    <c:choose>
                        <c:when test="${not empty sessionScope.usuario}">
                        <span class="me-3 fw-semibold text-dark bg-white rounded px-2 py-1 shadow-sm">
                            Hola, ${sessionScope.usuario.nombre}
                        </span>
                            <a class="btn btn-outline-dark fw-semibold" href="${pageContext.request.contextPath}/logout">
                                Cerrar sesion
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-light fw-semibold" href="${pageContext.request.contextPath}/login">
                                Entrar
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </nav>




    <!-- Flash message -->
    <c:if test="${not empty sessionScope.flash}">
        <div class="container">
            <div class="alert alert-${sessionScope.flash.type} alert-dismissible fade show" role="alert" id="flashMessage">
                ${sessionScope.flash.message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </div>
        <c:remove var="flash" scope="session" />
    </c:if>
</div>
