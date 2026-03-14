import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class APrefijo<E> {

    //Estructura solicitada
    Map<Character, APrefijo<E>> hijos; //el mapa de Java
    boolean finNombre;
    Character character;
    LinkedList<E> datos; //la de Java

    //constructor con APrefijo vacio
    public APrefijo() {
        this.hijos=new HashMap<>();
        this.finNombre=true;
        this.datos=new LinkedList<>();
        this.character=null;
    }
    public APrefijo(Character character) {
        this.hijos=new HashMap<>();
        this.finNombre=true;
        this.datos=new LinkedList<>();
        this.character=character;
    }

    public Map<Character, APrefijo<E>> getHijos() {
        return hijos;
    }

    public void setHijos(Map<Character, APrefijo<E>> hijos) {
        this.hijos = hijos;
    }

    public boolean isFinNombre() {
        return finNombre;
    }

    public void setFinNombre(boolean finNombre) {
        this.finNombre = finNombre;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public LinkedList<E> getDatos() {
        return datos;
    }

    public void setDatos(LinkedList<E> datos) {
        this.datos = datos;
    }



    //Metodo insertar palabra
    public void addWord(String palabra){
        if(palabra.isBlank()) return;
        this.addHijo(palabra.charAt(0),palabra.substring(1));
    }
    private void addHijo(char c, String subcadena){
        this.hijos.put(c,new APrefijo<>(c));
        APrefijo<E> siguiente = this.hijos.get(c);
        siguiente.addWord(subcadena);
    }

    private boolean existe
}