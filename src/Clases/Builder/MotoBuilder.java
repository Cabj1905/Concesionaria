package Clases.Builder;

public class MotoBuilder implements VehiculoBuilder {
    private Moto moto;

    public MotoBuilder() {
        reset();
    }

    public void reset() {
        moto = new Moto();
    }

    public void setMarca(String marca) {
        moto.marca = marca;
    }

    

    public void setColor(String color) {
        moto.color = color;
    }

    public void setChasis(String chasis) {
        moto.chasis = chasis;
    }

    public void setMotor(String motor) {
        moto.motor = motor;
    }

    public void setImpuesto(int impuesto) {
        moto.impuesto = impuesto;
    }

    public Vehiculo getResultado() {
        return moto;
    }

    @Override
    public void setModelo(String modelo) {
        moto.modelo = modelo;
    }

    

  
}
