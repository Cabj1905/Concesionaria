package Clases.State;

public class PedidoMemento {
    private final String nombreEstado;

    public PedidoMemento(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    @Override
    public String toString() {
        return nombreEstado;
    }
    
}
