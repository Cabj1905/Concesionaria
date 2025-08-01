package Clases.Gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Clases.State.HistorialPedido;
import Clases.State.Pedido;

public class GestorPedido {

    private static GestorPedido instancia;
    private List<Pedido> listaPedidos;

    private List<HistorialPedido> historiales = new ArrayList<>();
    private final Map<Pedido, HistorialPedido> historiales2 = new HashMap<>();



    private GestorPedido() {
        listaPedidos = new ArrayList<>();

    }

    public static GestorPedido getInstancia() {
        if (instancia == null) {
            instancia = new GestorPedido();
        }
        return instancia;
    }

    

    public void agregarPedido(Pedido pedido) {
        if (pedido != null && !listaPedidos.contains(pedido)) {
            listaPedidos.add(pedido);
            historiales2.put(pedido
                , pedido.obtenerHistorial());
        }
    }

    public Map<Pedido, HistorialPedido> obtenerHistoriales() {
        return historiales2;    
    }


    public boolean eliminarPedido(Pedido pedido) {
        return listaPedidos.remove(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return new ArrayList<>(listaPedidos); // Retorna copia para no modificar lista original
    }

    // Ejemplo: buscar pedido por número
    public Pedido buscarPorNumero(int numero) {
        for (Pedido p : listaPedidos) {
            if (p.getNumero() == numero) {
                return p;
            }
        }
        return null;
    }

    public void avanzarEstado(Pedido pedido) {
        pedido.getEstadoPedido().siguiente(pedido);
    }

    public void cancelarPedido(Pedido pedido){
        pedido.getEstadoPedido().cancelar(pedido);
    }

    public List<Pedido> generarReporte(LocalDate desde, LocalDate hasta, String estadoFiltro) {
    List<Pedido> resultado = new ArrayList<>();

    for (Pedido p : listaPedidos) {
        boolean fechaValida = !p.getFechaCreacion().isBefore(desde) && !p.getFechaCreacion().isAfter(hasta);
        boolean estadoValido = estadoFiltro == null || p.getEstadoPedido().getNombre().equalsIgnoreCase(estadoFiltro);

        if (fechaValida && estadoValido) {
            resultado.add(p);
        }
    }

    return resultado;
}

    


}
