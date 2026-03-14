import java.util.LinkedList;

public class Agenda<E> {
    private NodoAPrefijo<E> raiz;

    public Agenda() {
        this.raiz = new NodoAPrefijo<>('*');
    }
    public void insertar(String palabra, E dato) {
        raiz.addWord(palabra, dato);
    }

    public LinkedList<E> buscar(String palabra){
        return this.raiz.buscar(palabra);
    }
}
