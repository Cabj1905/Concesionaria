package Clases.Builder;

public class Camion extends Vehiculo {
    private float impuestoNacional = 0.25f;
    private float impuestoProvincial = 0.15f;

    public Camion(String marca,  String modelo,String chasis, int impuesto, String motor) {
        super(marca,modelo  ,chasis, impuesto, motor);
    }

    public Camion() {
        super();
    }

    
    public float getImpuestoNacional(){
        return impuestoNacional;
    }

    
    public float getImpuestoProvincial(){
        return impuestoProvincial;
    }

    @Override
    public float CalculoImpuesto() {
        return impuesto * (1 + impuestoNacional + impuestoProvincial);
    }

    @Override
    public String toString() {
        return "Camion{" +
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

