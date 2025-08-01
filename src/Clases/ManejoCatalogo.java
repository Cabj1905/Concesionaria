package Clases;

import java.util.ArrayList;
import java.util.List;

public class ManejoCatalogo {
     private List<Catalogo> vehiculosDisponibles;

    public ManejoCatalogo() {
        this.vehiculosDisponibles = new ArrayList<>();
    }

    public void agregarVehiculo(Catalogo vehiculo) {
        vehiculosDisponibles.add(vehiculo);
    }

    public List<Catalogo> obtenerVehiculos() {
        return new ArrayList<>(vehiculosDisponibles); // Retornamos copia para seguridad
    }

    // Método para buscar vehículos por tipo, marca, modelo, etc
    public List<Catalogo> buscarPorTipo(String tipo) {
        List<Catalogo> resultados = new ArrayList<>();
        for (Catalogo v : vehiculosDisponibles) {
            if (v.getTipoVehiculo().equalsIgnoreCase(tipo)) {
                resultados.add(v);
            }
        }
        return resultados;
    }

    public void cargaDefault(){
        // Carga de vehículos por defecto
        vehiculosDisponibles.add(new Catalogo("Auto", 30000, "Toyota", "Corolla", "CH12345", "MTR123"));
        vehiculosDisponibles.add(new Catalogo("Camioneta", 45000, "Ford", "Ranger", "CH67890", "MTR456"));
        vehiculosDisponibles.add(new Catalogo("Moto", 15000, "Honda", "CB500", "CH11223", "MTR789"));
        vehiculosDisponibles.add(new Catalogo("Auto", 32000, "Volkswagen", "Golf", "CH33445", "MTR101"));
        vehiculosDisponibles.add(new Catalogo("Camioneta", 47000, "Chevrolet", "S10", "CH55667", "MTR202"));
        vehiculosDisponibles.add(new Catalogo("Moto", 18000, "Yamaha", "MT-07", "CH77889", "MTR303"));
        vehiculosDisponibles.add(new Catalogo("Camion", 58000, "Iveco", "IveCamion", "CH66321", "MTR305"));
        vehiculosDisponibles.add(new Catalogo("Camioneta", 43000, "Toyota", "Hilux", "CH716232", "MTR306"));
        vehiculosDisponibles.add(new Catalogo("Moto", 19400, "Honda", "C90", "CH90123", "MTR703"));
        vehiculosDisponibles.add(new Catalogo("Auto", 20000, "Renault", "Clio", "CH098123", "MTR803"));
        
    }
}
