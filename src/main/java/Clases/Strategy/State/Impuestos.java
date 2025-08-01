package Clases.State;

public class Impuestos implements EstadoPedido {

    @Override
    public void siguiente(Pedido pedido) {
                pedido.setEstadoPedido(new Embarque());

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
    public String toString() {
        return "Ventas{" +
                "estado=" + this.getClass().getSimpleName() +
                '}';
    }

    @Override
    public String getNombre() {
        return "Impuestos";
    }

    
    
}
