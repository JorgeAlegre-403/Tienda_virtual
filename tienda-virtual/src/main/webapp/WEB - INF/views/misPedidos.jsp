<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Mis pedidos</h2>

<c:choose>
    <c:when test="${empty pedidos}">
        <p>No tienes pedidos todavía.</p>
    </c:when>

    <c:otherwise>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Estado</th>
                <th>Importe</th>
                <th>IVA</th>
            </tr>

            <c:forEach var="p" items="${pedidos}">
                <tr>
                    <td>${p.idPedido}</td>
                    <td>${p.fecha}</td>
                    <td>${p.estado}</td>
                    <td>${p.importe} €</td>
                    <td>${p.iva} €</td>
                </tr>
            </c:forEach>
        </table>

    </c:otherwise>
</c:choose>