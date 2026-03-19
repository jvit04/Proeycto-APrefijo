import java.util.LinkedList;

public class Agenda {
    private NodoAPrefijo<Contacto> raiz;

    public Agenda() {
        this.raiz = new NodoAPrefijo<>('*');
    }
    //
    public void insertar(String palabra,Contacto contacto ) {
        raiz.addWord(palabra.toLowerCase(), contacto);
    }
    public void insertarContacto(Contacto contacto){
        if(contacto==null) return;
        // 1. Indexamos por Nombre
        insertar(contacto.getNombre(), contacto);

        // 2. Indexamos por Apellido
        insertar(contacto.getApellido(), contacto);

        // 3. Indexamos por Apodo
        insertar(contacto.getApodo(), contacto);
    }

    public LinkedList<Contacto> buscar(String palabra){
        if(palabra == null || palabra.isBlank()) return null;
        return this.raiz.buscar(palabra.toLowerCase());
    }

    public void eliminar(String palabra) {
        if (palabra != null && !palabra.isBlank()) {
            raiz.eliminarPalabra(palabra.toLowerCase());
        }
    }
}
