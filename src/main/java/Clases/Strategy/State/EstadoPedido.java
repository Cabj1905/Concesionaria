package Clases.State;

public interface EstadoPedido {
    void siguiente(Pedido pedido);
    void cancelar(Pedido pedido);

    EstadoPedido getEstadoActual();
    String getNombre();
    

    
}
