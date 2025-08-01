package Clases.Builder;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehiculo {
    protected String marca;
    protected String modelo;
    protected String color;
    protected String chasis;
    protected int impuesto;
    protected String motor;
    protected List<String> coloresdisponibles = new ArrayList<>(List.of("Rojo", "Negro", "Gris", "Blanco"));
    

    

    public Vehiculo(String marca, String modelo,String chasis, int impuesto, String motor) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = 
        this.chasis = chasis;
        this.impuesto = impuesto;
        this.motor = motor;

    }

    public Vehiculo() {

    }

    public String getMarca() {
        return marca;
    }

    public String getColor() {
        return color;
    }

    public String getChasis() {
        return chasis;
    }

    public void setColor(String color) {
        this.color = color;
    }   

    public int getImpuesto() {
        return impuesto;
    }

    public String getMotor() {
        return motor;
    }

    

    public String getModelo() {
        return modelo;
    }   



    public abstract float CalculoImpuesto();

    public List<String>getColores(){
        return this.coloresdisponibles;
    }


public String toStringCatalogo() {
    return "Vehiculo{" +
            "marca='" + marca + '\'' +
            ", modelo='" + modelo + '\'' +
            ", chasis='" + chasis + '\'' +
            ", Precio=" + impuesto +
            ", motor='" + motor + '\'' +
            ", colores disponibles=" + coloresdisponibles.toString() +
            '}';
}

@Override
public String toString() {
    return "Vehiculo{" +
            "marca='" + marca + '\'' +
            ", modelo='" + modelo + '\'' +
            ", color asignado='" + (color != null ? color : "No asignado") + '\'' +
            ", chasis='" + chasis + '\'' +
            ", Precio=" + impuesto +
            ", motor='" + motor + '\'' +
            '}';
}

}

