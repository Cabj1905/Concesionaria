package Clases.Builder;

public class CamionetaBuilder implements VehiculoBuilder {
    private Camioneta camioneta;

    public CamionetaBuilder() {
        reset();
    }

    public void reset() {
        camioneta = new Camioneta();
    }

    public void setMarca(String marca) {
        camioneta.marca = marca;
    }

    public void setColor(String color) {
        camioneta.color = color;
    }

   
    

    public void setChasis(String chasis) {
        camioneta.chasis = chasis;
    }

    public void setMotor(String motor) {
        camioneta.motor = motor;
    }

    public void setImpuesto(int impuesto) {
        camioneta.impuesto = impuesto;
    }

    public Vehiculo getResultado() {
        return camioneta;
    }

    @Override
    public void setModelo(String modelo) {
        camioneta.modelo = modelo;
    }
}
