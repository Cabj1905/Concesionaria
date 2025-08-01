package Clases.Builder;

public class AutoBuilder implements VehiculoBuilder {
    private Auto auto;

    public AutoBuilder() {
        reset();
    }

    public void reset() {
        auto = new Auto();
    }

    public void setMarca(String marca) {
        auto.marca = marca;
    }

   
    public void setColor(String color) {
        auto.color = color;
    }

    public void setChasis(String chasis) {
        auto.chasis = chasis;
    }

    public void setMotor(String motor) {
        auto.motor = motor;
    }

    public void setImpuesto(int impuesto) {
        auto.impuesto = impuesto;
    }

    public Vehiculo getResultado() {
        return auto;
    }

    @Override
    public void setModelo(String modelo) {
        auto.modelo = modelo;
    }

    
}
