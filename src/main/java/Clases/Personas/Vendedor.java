package Clases.Personas;

public class Vendedor extends Usuario{
    public Vendedor(String nombre, String contrasena) {
        super(nombre, contrasena);
    }

    public String getTipoUsuario() {
        return "Vendedor";
    }

    public String getNombre(){
        return super.getNombre();
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "nombre='" + getNombre() + '\'' +
                ", tipoUsuario='" + getTipoUsuario() + '\'' +
                '}';
    }

}
