package Clases.Builder;

public class Moto extends Vehiculo{
    private float impuestoNacional = 0.1f;
    private float impuestoProvincial = 0.01f;

    public Moto(String marca,  String modelo,String chasis, int impuesto, String motor) {
        super(marca,  modelo,chasis, impuesto, motor);
    }

    
    public float getImpuestoNacional(){
        return impuestoNacional;
    }

    
    public float getImpuestoProvincial(){
        return impuestoProvincial;
    }

    public Moto() {
        super();
    }

    @Override
    public float CalculoImpuesto() {
        return impuesto * (1 + impuestoNacional + impuestoProvincial);
    }

     @Override
    public String toString() {
        return "Moto{" +
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
