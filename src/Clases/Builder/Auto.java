package Clases.Builder;

public class Auto extends Vehiculo {
    private float impuestoNacional = 0.2f;
    private float impuestoProvincial = 0.07f;

    public Auto(String marca, String modelo,String chasis, int impuesto, String motor) {
        super(marca, modelo,chasis, impuesto, motor);
    }

    
    public float getImpuestoNacional(){
        return impuestoNacional;
    }

    
    public float getImpuestoProvincial(){
        return impuestoProvincial;
    }

    public Auto() {
        super();
    }

    @Override
    public float CalculoImpuesto() {
        return impuesto * (1 + impuestoNacional + impuestoProvincial);
    }

 @Override
    public String toString() {
        return "Auto{" +
                "marca='" + getMarca() + '\'' +
                ", color='" + getColor() + '\'' +
                ", chasis='" + getChasis() + '\'' +
                ", Precio final=" + getImpuesto() +
                ", motor='" + getMotor() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", impuestoNacional=" + impuestoNacional +
                ", impuestoProvincial=" + impuestoProvincial +
                '}';
    }
}

