package Clases.Builder;

public class CamionBuilder implements VehiculoBuilder {
    private Camion camion;

    public CamionBuilder() {
        reset();
    }

    public void reset() {
        camion = new Camion();
    }

    public void setMarca(String marca) {
        camion.marca = marca;
    }

    public void setColor(String color) {
        camion.color = color;
    }

    public void setChasis(String chasis) {
        camion.chasis = chasis;
    }

    public void setMotor(String motor) {
        camion.motor = motor;
    }

    public void setImpuesto(int impuesto) {
        camion.impuesto = impuesto;
    }


    public Vehiculo getResultado() {
        return camion;
    }

    @Override
    public void setModelo(String modelo) {
        camion.modelo = modelo;
    }

    
}
