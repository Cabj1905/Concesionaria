package Clases.State;

public class Cancelado implements EstadoPedido {

    @Override
    public EstadoPedido getEstadoActual() {
        return this;
    }

    @Override
    public String toString() {
        return "Cancelado{" +
                "estado=" + this.getClass().getSimpleName() +
                '}';
    }

    @Override
    public void siguiente(Pedido pedido) {
       System.out.println("El pedido ya está cancelado, no se puede avanzar al siguiente estado.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        
    }

    @Override
    public String getNombre() {
        return "Cancelado";
    }
    
}
