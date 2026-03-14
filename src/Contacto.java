public class Contacto {

    private String nombre;
    private String telefono;
    private int frecuencia;

    public Contacto(String nombre, String telefono, int freq){
        this.nombre = nombre;
        this.telefono = telefono;
        frecuencia=freq;
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