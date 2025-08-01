package Clases.Strategy;

public class TarjetaCredito implements MetodoPago {

    @Override
    public void procesarPago(float monto) {
        // Lógica para procesar el pago con tarjeta de crédito
        System.out.println("Procesando pago con tarjeta de crédito por un monto de: " + monto);
        // Aquí se podría agregar la lógica específica para realizar un pago con tarjeta de crédito
    }

    
    public String getNombre(){
        return "Tarjeta de Crédito";
    }
    
}
