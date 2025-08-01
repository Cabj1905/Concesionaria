package Clases;


import Clases.State.Pedido;

public class Cliente implements Observador {

    private String nombre;
    private String documento;
    private String correo;
    private String telefono;

    public Cliente(String nombre, String documento, String correo, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    @Override
    public void update(Pedido pedido) {
        System.out.println("Cliente " + nombre + " ha sido notificado: el pedido N° " 
            + pedido.getNumero() + " cambió a estado " 
            + pedido.getEstadoPedido().getEstadoActual());
    }
}
