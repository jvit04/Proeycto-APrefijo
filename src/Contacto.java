public class Contacto {

    private String nombre;
    private String apellido;
    private String apodo;
    private String telefono;
    private int frecuencia;

    public Contacto(String nombre, String apellido, String apodo, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.telefono = telefono;
        this.frecuencia = 0;
    }

    public String getNombre(){
        return nombre;
    }

    public String getTelefono(){
        return telefono;
    }
    public int getFrecuencia() {return frecuencia;}
    public void setFrecuencia(int f){ frecuencia=f;}
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    @Override
    public String toString(){
        return nombre + " - " + telefono;
    }
}