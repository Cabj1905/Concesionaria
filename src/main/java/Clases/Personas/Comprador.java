package Clases.Personas;

public class Comprador extends Usuario{
    public Comprador(String nombre, String contrasena) {
        super(nombre, contrasena);
    }

    public String getTipoUsuario() {
        return "Comprador";
    }

    public String getNombre(){
        return super.getNombre();
    }

    @Override
    public String toString() {
        return "Comprador{" +
                "nombre='" + getNombre() + '\'' +
                '}';
    }

}
