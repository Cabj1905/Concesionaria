package Clases.Builder;

public class Camioneta extends Vehiculo {
    private float impuestoNacional = 0.2f;
    private float impuestoProvincial = 0.12f;

    public Camioneta(String marca, String modelo,String chasis, int impuesto, String motor) {
        super(marca, modelo,chasis, impuesto, motor);
    }

    public float getImpuestoNacional(){
        return impuestoNacional;
    }

    
    public float getImpuestoProvincial(){
        return impuestoProvincial;
    }

    public Camioneta() {
        super();
    }

    @Override
    public float CalculoImpuesto() {
        return impuesto * (1 + impuestoNacional + impuestoProvincial);
    }

     @Override
    public String toString() {
        return "Camioneta{" +
                "marca='" + getMarca() + '\'' +
                ", color='" + getColor() + '\'' +
                ", chasis='" + getChasis() + '\'' +
                ", impuesto=" + getImpuesto() +
                ", motor='" + getMotor() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", impuestoNacional=" + impuestoNacional +
                ", impuestoProvincial=" + impuestoProvincial +
                '}';
    }

  

}

