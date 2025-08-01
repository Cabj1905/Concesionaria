package Clases;

public class Catalogo {
    private String tipoVehiculo;
    private float precioFinal;   // Ya con impuestos incluidos
    private String marca;
    private String modelo;
    private String chasis;
    private String motor;

    public Catalogo(String tipoVehiculo, float precioFinal, String marca, String modelo, String chasis, String motor) {
        this.tipoVehiculo = tipoVehiculo;
        this.precioFinal = precioFinal;
        this.marca = marca;
        this.modelo = modelo;
        this.chasis = chasis;
        this.motor = motor;
    }

    // Getters y setters
    public String getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(String tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }

    public float getPrecioFinal() { return precioFinal; }
    public void setPrecioFinal(float precioFinal) { this.precioFinal = precioFinal; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getChasis() { return chasis; }
    public void setChasis(String chasis) { this.chasis = chasis; }

    public String getMotor() { return motor; }
    public void setMotor(String motor) { this.motor = motor; }

    @Override
    public String toString() {
        return "CatalogoVehiculo{" +
                "tipoVehiculo='" + tipoVehiculo + '\'' +
                ", precioFinal=" + precioFinal +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", chasis='" + chasis + '\'' +
                ", motor='" + motor + '\'' +
                '}';
    }

    

}
