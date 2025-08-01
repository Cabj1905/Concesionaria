package Clases.Personas;

public class Administrador extends Usuario{

    public Administrador(String nombre, String contrasena) {
        super(nombre, contrasena);
    }

    public String getTipoUsuario() {
        return "Administrador";
    }

    public String getNombre(){
        return super.getNombre();
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nombre='" + getNombre() + '\'' +
                ", tipoUsuario='" + getTipoUsuario() + '\'' +
                '}';
    }

    
}
