<h2>Confirmar pedido</h2>

<p>Total: ${sessionScope.carrito.total} €</p>

<form action="pedido" method="post">
    <button type="submit">Confirmar compra</button>
</form>

<a href="carrito">Volver al carrito</a>
<c:if test="${not empty mensaje}">
    <p style="color:green">${mensaje}</p>
</c:if>