package Clases.State;

public class Logistica implements EstadoPedido {

   

    @Override
    public String toString() {
        return "Logistica{" +
                "estado=" + this.getClass().getSimpleName() +
                '}';
    }

    @Override
    public void siguiente(Pedido pedido) {
               pedido.setEstadoPedido(new Entrega());
 }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstadoPedido(new Cancelado());

    }

    @Override
    public EstadoPedido getEstadoActual() {
        return this;
    }

    @Override
    public String getNombre() {
        return "Logistica";
    }
    
}
