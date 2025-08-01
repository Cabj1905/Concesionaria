package Clases;

import Clases.Gestores.GestorCliente;
import Clases.Gestores.GestorPedido;
import Clases.Gestores.GestorVehiculo;
import Clases.Gestores.GestorVendedor;

public class Sistema {
    private static Sistema instancia;

    private GestorCliente gestorCliente;
    private GestorVehiculo gestorVehiculo;
    private GestorPedido gestorPedido;
    private GestorVendedor gestorVendedor;

    private Sistema() {
        gestorCliente = GestorCliente.getInstancia();
        gestorVehiculo = GestorVehiculo.getInstancia();
        gestorPedido = GestorPedido.getInstancia(); // <- USAR EL SINGLETON CORRECTAMENTE
        gestorVendedor = GestorVendedor.getInstancia();
    }

    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public GestorCliente getGestorCliente() {
        return gestorCliente;
    }

    public GestorVehiculo getGestorVehiculo() {
        return gestorVehiculo;
    }

    public GestorPedido getGestorPedido() {
        return gestorPedido;
    }

    public GestorVendedor getGestorVendedor() {
        return gestorVendedor;
    }
}
