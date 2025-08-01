package Clases.State;

public class Entrega implements EstadoPedido {

    @Override
    public void siguiente(Pedido pedido) {
        System.out.println("El paquete ya fue entregado");
    }

    @Override
    public void cancelar(Pedido pedido) {
              pedido.setEstadoPedido(new Logistica());

    }

    @Override
    public EstadoPedido getEstadoActual() {
        return this;
    }

    @Override
    public String getNombre() {
        return "Entregado";
    }
    
}
