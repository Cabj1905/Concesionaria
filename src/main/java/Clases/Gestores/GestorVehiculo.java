package Clases.Gestores;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Clases.Builder.Auto;
import Clases.Builder.Camion;
import Clases.Builder.Camioneta;
import Clases.Builder.Moto;
import Clases.Builder.Vehiculo;


public class GestorVehiculo {

    private static GestorVehiculo instancia;
    private List<Vehiculo> listaVehiculos;

    private GestorVehiculo() {
        listaVehiculos = new ArrayList<>();
    }

    public static GestorVehiculo getInstancia() {
        if (instancia == null) {
            instancia = new GestorVehiculo();
        }
        return instancia;
    }

    public boolean agregarVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) return false;
        // Verifica si ya existe un vehículo con esa patente
        if (buscarPorModelo(vehiculo.getModelo()) != null) {
            System.out.println("Ya existe un vehículo de ese modelo: " + vehiculo.getModelo());
            return false;
        }
        listaVehiculos.add(vehiculo);
        return true;
    }

    public boolean eliminarVehiculo(Vehiculo vehiculo) {
        return listaVehiculos.remove(vehiculo);
    }

    public List<Vehiculo> obtenerVehiculos() {
        return new ArrayList<>(listaVehiculos);
    }

    // Método para buscar vehículo por patente, por ejemplo
    public Vehiculo buscarPorChasis(String patente) {
        for (Vehiculo v : listaVehiculos) {
            if (v.getChasis().equalsIgnoreCase(patente)) {
                return v;
            }
        }
        return null;
    }

    public Vehiculo buscarPorModelo(String modelo) {
        for (Vehiculo v : listaVehiculos) {
            if (v.getModelo().equalsIgnoreCase(modelo)) {
                return v;
            }
        }
        return null;
    }

    public void generarInformeVehiculos(String rutaArchivo) {
    try (PrintWriter writer = new PrintWriter(rutaArchivo)) {
        writer.println("Marca,Modelo,Precio Base,Impuesto Nacional,Impuesto Provincial,Costo Total");

        for (Vehiculo v : obtenerVehiculos()) {
            float base = v.getImpuesto(); // impuesto es precio base
            float impNac = 0f;
            float impProv = 0f;

            // Identificar tipo y acceder a sus impuestos
            if (v instanceof Auto auto) {
                impNac = auto.getImpuestoNacional();
                impProv = auto.getImpuestoProvincial();
            } else if (v instanceof Camion camion) {
                impNac = camion.getImpuestoNacional();
                impProv = camion.getImpuestoProvincial();
            } else if (v instanceof Moto moto) {
                impNac = moto.getImpuestoNacional();
                impProv = moto.getImpuestoProvincial();
            } else if (v instanceof Camioneta camioneta) {
                impNac = camioneta.getImpuestoNacional();
                impProv = camioneta.getImpuestoProvincial();
            }

            float impuestoTotal = base * (impNac + impProv);
            float costoTotal = base + impuestoTotal;

            writer.printf(Locale.US,"%s,%s,%.2f,%.2f,%.2f,%.2f\n",
                    v.getMarca(), v.getModelo(), base, impNac, impProv, costoTotal);
        }

        System.out.println("📁 Informe generado correctamente en: " + rutaArchivo);
    } catch (Exception e) {
        System.out.println("⚠ Error al generar el informe: " + e.getMessage());
    }
}


    




}
