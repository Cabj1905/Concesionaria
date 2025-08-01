package Clases.Strategy;

import java.util.Date;

public class Pago {

    private float monto;
    private Date fechaPago;
    private MetodoPago metodoPago;  // Estrategia

    public Pago(float monto, MetodoPago metodoPago) {
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = new Date();
    }

    public void ejecutarPago() {
        metodoPago.procesarPago(monto);
    }

    public void cambiarMetodoPago(MetodoPago nuevoMetodo) {
        this.metodoPago = nuevoMetodo;
    }

    // Getters y setters

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
}
