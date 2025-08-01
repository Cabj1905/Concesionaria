package Clases.Builder;

public interface VehiculoBuilder {
    void reset();
    void setMarca(String marca);
    void setColor(String color);
    void setChasis(String chasis);
    void setMotor(String motor);
    void setImpuesto(int impuesto);
    void setModelo(String modelo);
    Vehiculo getResultado();

}
