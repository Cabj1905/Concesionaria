package Clases.Gestores;

import java.util.ArrayList;
import java.util.List;

import Clases.Cliente;


public class GestorCliente {
    private static GestorCliente instancia;
    private List<Cliente> listaClientes;

    private GestorCliente() {
        listaClientes = new ArrayList<>();
    }

    public static GestorCliente getInstancia() {
        if (instancia == null) {
            instancia = new GestorCliente();
        }
        return instancia;
    }



    public void agregarCliente(Cliente cliente) {
        if (cliente != null) {
            boolean existe = false;
            for (Cliente c : listaClientes) {
                if (c.getDocumento().equals(cliente.getDocumento())) {
                    existe = true;
                    System.out.println("El cliente ya existe en la lista.");
                    break;
                }
            }
            if (!existe) {
                listaClientes.add(cliente);
                System.out.println("Cliente agregado en la lista.");
            }
        }
    }

    public boolean eliminarCliente(Cliente cliente) {
        return listaClientes.remove(cliente);
    }

    public List<Cliente> obtenerCliente() {
        for (Cliente c : this.listaClientes) {
            System.out.println(c); // O c.getNombre(), etc.
        }
        return this.listaClientes;
    }


    // Buscar usuario por nombre (puede ajustar a otro campo)
    public Cliente buscarPorDocumento(String doc) {
        for (Cliente u : listaClientes) {
            if(u.getDocumento().equals(doc) ) {
                return u;
            }
        }
        return null;

    }

    
}
