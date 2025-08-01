package Clases.State;

import java.util.ArrayList;
import java.util.List;

public class HistorialPedido {
    private final List<PedidoMemento> historial = new ArrayList<>();

    public void agregar(PedidoMemento memento) {
        historial.add(memento);
    }

    public List<PedidoMemento> obtenerTodos() {
        return historial;
    }
}
