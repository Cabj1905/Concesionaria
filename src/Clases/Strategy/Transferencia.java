package Clases.Strategy;

public class Transferencia implements MetodoPago {

    @Override
    public void procesarPago(float monto) {
        // Lógica para procesar el pago por transferencia
        System.out.println("Procesando pago por transferencia de: " + monto);
        // Aquí se podría agregar la lógica específica para realizar una transferencia bancaria
    }

    
    public String getNombre(){
        return "Transferencia";
    }
    
}
