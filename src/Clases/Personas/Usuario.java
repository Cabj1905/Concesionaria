package Clases.Personas;

import java.util.List;
import java.util.Scanner;

import Clases.Builder.Vehiculo;

public abstract class Usuario {
    private String nombre;
    private String mail;

    public Usuario(String nombre, String mail) {
        this.nombre = nombre;
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setContrasena(String contrasena) {
        this.mail = contrasena;
    }
public Vehiculo AsignarColor(List<Vehiculo> vehiculosDisponibles, String modeloSeleccionado) {
    Scanner scanner = new Scanner(System.in);

    // Buscar el vehículo en la lista
    Vehiculo vehiculoBase = vehiculosDisponibles.stream()
        .filter(v -> v.getModelo().equalsIgnoreCase(modeloSeleccionado))
        .findFirst()
        .orElse(null);

    if (vehiculoBase == null) {
        System.out.println("❌ Modelo no encontrado.");
        return null;
    }

    // Mostrar colores disponibles
    List<String> colores = vehiculoBase.getColores();
    while (true) {
        System.out.println("🎨 Colores disponibles para el modelo " + vehiculoBase.getModelo() + ":");
        for (String color : colores) {
            System.out.println("- " + color);
        }

        System.out.print("Ingrese el color que desea asignar: ");
        final String colorElegido = scanner.nextLine();

        // Validar si el color ingresado está en la lista
        if (colores.stream().anyMatch(c -> c.equalsIgnoreCase(colorElegido))) {
            vehiculoBase.setColor(capitalizar(colorElegido));
            break;
        } else {
            System.out.println("❌ Color no válido. Por favor, elija uno de la lista.");
        }
    }

    return vehiculoBase;
}


    private String capitalizar(String texto) {
    if (texto == null || texto.isEmpty()) return texto;
    return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
}

}
