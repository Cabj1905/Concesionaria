package Clases.State;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;



import Clases.Cliente;
import Clases.Observador;
import Clases.Builder.Vehiculo;
import Clases.Personas.Administrador;
import Clases.Personas.Usuario;
import Clases.Personas.Vendedor;
import Clases.Strategy.Contado;
import Clases.Strategy.MetodoPago;


public class Pedido {
    private String nombreConcionario;
    private String CUIT;
    private int numero;
    private LocalDate fechaCreacion;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private float precioFinal;
    private Usuario vendedor;
    private EstadoPedido estadoPedido;
    private List<Observador> listaObservadores;
    private MetodoPago metodoPago;
    private static int contadorPedidos = 1;
    private String confadicional;
    private String razonSocial;
    private String direccion;
    private String Cuitsocial;
    private final HistorialPedido historial = new HistorialPedido();

    public Pedido( Cliente cliente, Vehiculo vehiculo, float precioFinal, Usuario usuarioLogueado,MetodoPago metodoPago) {
        this.nombreConcionario = "Concesionario UADE";
        this.CUIT = "20-12345678-9"; 
        this.numero = contadorPedidos++;
        this.fechaCreacion = LocalDate.now();
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.precioFinal = precioFinal+ vehiculo.CalculoImpuesto();
        this.vendedor = usuarioLogueado;
        this.estadoPedido = new Ventas();
        this.listaObservadores=new ArrayList<>();
        this.metodoPago = metodoPago;
        listaObservadores.add(cliente); // El cliente es un observador por defecto
        this.confadicional=null;
        this.razonSocial=null;
        this.Cuitsocial=null;
        this.direccion=null;
    }



    // Getters y setters

    public int getNumero() {
        return numero;
    }

    public String getRazonSocial(){
        return razonSocial;
    }

    public void setRazonSocial(String razon){
        this.razonSocial=razon;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String dir){
        this.direccion=dir;
    }

    public String getCUILSocial(){
        return Cuitsocial;
    }

    public void setCuitSocial(String cuit){
        this.Cuitsocial=cuit;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombreConcionario() {
        return nombreConcionario;
    }

    public void setNombreConcionario(String nombreConcionario) {
        this.nombreConcionario = nombreConcionario;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public float getTotal() {
        return precioFinal;
    }

    public void setTotal(float total) {
        this.precioFinal = total;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
        notificarObservadores();
        //guardarEstadoEnHistorial(); // automático cada vez que cambia el estado
        historial.agregar(new PedidoMemento(estadoPedido.getNombre()));

    }
    

    private void guardarEstadoEnHistorial() {
        historial.agregar(new PedidoMemento(estadoPedido.getNombre()));
    }

    public HistorialPedido obtenerHistorial() {
        return historial;
    }


    public List<Observador> getListaObservadores() {
        return listaObservadores;
    }

    public void setconfadicional(String conf){
        this.confadicional=conf;
    }

    public void agregarObservador(Observador observador) {
        if (!listaObservadores.contains(observador)) {
            listaObservadores.add(observador);
        }
    }

    public void removerObservador(Observador observador) {
        listaObservadores.remove(observador);
    }

    public void notificarObservadores() {
        for (Observador obs : listaObservadores) {
            obs.update(this);
        }
    }

    @Override
public String toString() {
    return "Pedido{" +
            "numero=" + numero +
            ", fechaCreacion=" + fechaCreacion +
            ", cliente=" + (cliente != null ? cliente.toString() : "null") +
            ", vehiculo=" + (vehiculo != null ? vehiculo.toString() : "null") +
            ", precioFinal=" + precioFinal +
            ", vendedor=" + (vendedor != null ? vendedor.toString() : "null") +
            ", estadoPedido=" + (estadoPedido != null ? estadoPedido.toString() : "null") +
            ", configuracionAdicional='" + (confadicional != null && !confadicional.isBlank() ? confadicional : "Sin configuración adicional") + '\'' +
            ", Razon social='" + (razonSocial != null && !razonSocial.isBlank() ? razonSocial : "Sin Razon social") + '\'' +
            ", Direccion social='" + (direccion != null && !direccion.isBlank() ? direccion : "Sin Direccion social") + '\'' +
            ", Cuit/Cuil='" + (Cuitsocial != null && !Cuitsocial.isBlank() ? Cuitsocial : "Sin Cuit/Cuil") + '\'' +
            '}';
}


    public Object getMetodoPago() {
        return metodoPago.getClass().getSimpleName();
    }

    public void setMetodoPago(MetodoPago metodoPagoStr) {
        this.metodoPago = metodoPagoStr;
    }

    public void setNombreConcesionario(String nombreConcesionario) {
        this.nombreConcionario = nombreConcesionario;
    }

    public void setMetodoPago(String metodoPagoStr) {
        if (metodoPagoStr.equalsIgnoreCase("Contado")) {
            this.metodoPago = new Contado();
        } else {
            // Aquí podrías agregar más lógica para otros métodos de pago
            System.out.println("Método de pago no reconocido: " + metodoPagoStr);
        }
    }   

    


    

}
