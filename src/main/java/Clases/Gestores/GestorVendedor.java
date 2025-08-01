package Clases.Gestores;

import Clases.Personas.Administrador;
import Clases.Personas.Comprador;
import Clases.Personas.Usuario;
import Clases.Personas.Vendedor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GestorVendedor {
    private static GestorVendedor instancia;
    private List<Usuario> listaUsuarios;

    
    

    private GestorVendedor() {
        listaUsuarios = new ArrayList<>();
        
    }

    public static GestorVendedor getInstancia() {
        if (instancia == null) {
            instancia = new GestorVendedor();
        }
        return instancia;
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuario != null) {
            boolean existe = false;
            for (Usuario u : listaUsuarios) {
                if (u.getMail().equalsIgnoreCase(usuario.getMail())) { // O compara por mail si prefieres
                    existe = true;
                    System.out.println("El vendedor ya existe en la lista.");
                    break;
                }
            }
            if (!existe) {
                listaUsuarios.add(usuario);
            }
        }
    }

    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(listaUsuarios);
    }

    public Vendedor buscarVendedorPorNombre(String nombre) {
        for (Usuario u : listaUsuarios) {
            if (u instanceof Vendedor && u.getNombre().equalsIgnoreCase(nombre)) {
                return (Vendedor) u;
            }
        }
        return null; // No se encontró el vendedor
    }

    public void guardarUsuariosEnCSV(String rutaArchivo) {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(rutaArchivo), StandardCharsets.UTF_8))) {
            writer.println("tipo,nombre,contrasena");
            for (Usuario u : listaUsuarios) {
                String tipo = u.getClass().getSimpleName(); // Administrador, Comprador, Vendedor
                writer.printf("%s,%s,%s%n",
                    tipo,
                    u.getNombre(),
                    u.getMail()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public void cargarUsuariosDesdeCSV(String rutaArchivo) {
    listaUsuarios.clear();
    File archivo = new File(rutaArchivo);

    if (!archivo.exists()) {
        System.out.println("Archivo de usuarios no existe. Se creará al guardar.");
        return;
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
        String linea;
        boolean primera = true;
        while ((linea = reader.readLine()) != null) {
            if (primera) {
                primera = false;
                continue;
            }

            String[] partes = linea.split(",");
            if (partes.length >= 3) {
                String tipo = partes[0];
                String nombre = partes[1];
                String contrasena = partes[2];

                Usuario u = null;
                if (tipo.equalsIgnoreCase("Administrador")) {
                    u = new Administrador(nombre, contrasena);
                } else if (tipo.equalsIgnoreCase("Comprador")) {
                    u = new Comprador(nombre, contrasena);
                } else if (tipo.equalsIgnoreCase("Vendedor")) {
                    u = new Vendedor(nombre, contrasena);
                }

                if (u != null) {
                    listaUsuarios.add(u);
                }
            }
        }

        System.out.println("Usuarios cargados desde " + rutaArchivo);

    } catch (IOException e) {
        e.printStackTrace();
    }
}

}