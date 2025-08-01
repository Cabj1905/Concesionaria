package Clases.Strategy;

public class Contado implements MetodoPago {

    @Override
    public void procesarPago(float monto) {
        System.out.println("Pago realizado al contado"); 
    }

    public String getNombre(){
        return "Contado";
    }
    
}
