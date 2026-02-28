<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Carrito</h2>

<c:choose>

    <c:when test="${empty sessionScope.carrito or sessionScope.carrito.estaVacio()}">
        <p>El carrito está vacío</p>
    </c:when>

    <c:otherwise>

        <table border="1">
            <tr>
                <th>Producto</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Acciones</th>
            </tr>

            <c:forEach var="item" items="${sessionScope.carrito.items}">

                <tr>
                    <td>${item.producto.nombre}</td>
                    <td>${item.producto.precio} €</td>
                    <td>${item.cantidad}</td>
                    <td>${item.subtotal} €</td>

                    <td>

                        <form action="carrito" method="post" style="display:inline">
                            <input type="hidden" name="accion" value="agregar"/>
                            <input type="hidden" name="id" value="${item.producto.id}"/>
                            <button>+</button>
                        </form>

                        <form action="carrito" method="post" style="display:inline">
                            <input type="hidden" name="accion" value="disminuir"/>
                            <input type="hidden" name="id" value="${item.producto.id}"/>
                            <button>-</button>
                        </form>

                        <form action="carrito" method="post" style="display:inline">
                            <input type="hidden" name="accion" value="eliminar"/>
                            <input type="hidden" name="id" value="${item.producto.id}"/>
                            <button>Eliminar</button>
                        </form>

                    </td>
                </tr>

            </c:forEach>

        </table>

        <h3>Total: ${sessionScope.carrito.total} €</h3>

        <a href="pedido">Finalizar compra</a>

    </c:otherwise>

</c:choose>