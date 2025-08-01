package Clases.Builder;

public class Director {
    private VehiculoBuilder builder;

    public void setBuilder(VehiculoBuilder builder) {
        this.builder = builder;
    }

    public void construirVehiculoPersonalizado(String marca, String chasis, String motor, int impuesto,String modelo) {
        builder.reset();
        builder.setMarca(marca);
        builder.setChasis(chasis);
        builder.setMotor(motor);
        builder.setImpuesto(impuesto);
        builder.setModelo(modelo);
    }
}

