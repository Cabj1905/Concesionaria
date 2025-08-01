package Clases.State;

public class Ventas implements EstadoPedido {

    @Override
    public void siguiente(Pedido pedido) {
        pedido.setEstadoPedido(new Cobranzas());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstadoPedido(new Cancelado());
    }


    @Override
    public String toString() {
        return "Ventas{" +
                "estado=" + this.getClass().getSimpleName() +
                '}';
    }

    @Override
    public EstadoPedido getEstadoActual() {
        return this;
    }

    @Override
    public String getNombre() {
        return "Ventas";
    }
    
}
