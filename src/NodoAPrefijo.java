import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NodoAPrefijo<E> {

    //Estructura solicitada
    Map<Character, NodoAPrefijo<E>> hijos; //el mapa de Java
    boolean finNombre;
    Character character;
    LinkedList<E> datos; //la de Java

    //constructor con NodoAPrefijo vacio
    public NodoAPrefijo() {
        this.hijos=new HashMap<>();
        this.finNombre=false;
        this.datos=new LinkedList<>();
        this.character=null;
    }
    public NodoAPrefijo(Character character) {
        this.hijos=new HashMap<>();
        this.finNombre=false;
        this.datos=new LinkedList<>();
        this.character=character;
    }

    public Map<Character, NodoAPrefijo<E>> getHijos() {
        return hijos;
    }

    public void setHijos(Map<Character, NodoAPrefijo<E>> hijos) {
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
    public void addWord(String palabra, E dato){
        //caso base: palabra vacía
        if(palabra.isBlank()) return;

        char letraActual = palabra.charAt(0);
        //caso base 2: si el hijo no existe se lo crea
        this.hijos.putIfAbsent(letraActual,new NodoAPrefijo<>(letraActual));
        NodoAPrefijo<E> siguiente = this.hijos.get(letraActual);
        //caso base 3: si es la última letra de la palabra
        if(palabra.length()==1){
            siguiente.finNombre=true;
            siguiente.getDatos().add(dato);
        }
        //si no es la ultima letra, sigo insertando
        else {
            siguiente.addWord(palabra.substring(1),dato);
        }
    }

    public LinkedList<E> buscar(String palabra){
        //caso base 1: palabra vacía
        if(palabra.isBlank()) return null;
        char letraActual = palabra.charAt(0);
        NodoAPrefijo<E> hijo = this.hijos.get(letraActual);
        //caso base 2: pregunto si está la letra desde la raiz *
        if(hijo!=null){
            //caso base 3: llego a la palabra y retorno los datos
            if(palabra.length()==1){
                if (hijo.finNombre){
                    return hijo.datos;
                }
            }else {//todavía no acaba la palabra y debo seguir buscando
                return hijo.buscar(palabra.substring(1));
            }
        }
            return null;//no hay razón de seguir buscando
    }

    // 1. Función para navegar hasta donde termina el prefijo
    public LinkedList<String> findPrefix(String prefijoRestante, String prefijoCompleto) {
        // Caso base 1: Consumido todo el prefijo.
        if (prefijoRestante.isEmpty()) {
            LinkedList<String> resultados = new LinkedList<>();
            this.recorrePrefijo(prefijoCompleto, resultados);
            return resultados;
        }

        // Caso recursivo
        char letraActual = prefijoRestante.charAt(0);
        NodoAPrefijo<E> hijo = this.hijos.get(letraActual);

        // Caso base 2: El camino se corta antes de terminar el prefijo.
        if (hijo == null) {
            return new LinkedList<>(); // Retornamos lista vacía
        }

        // Llamada recursiva
        return hijo.findPrefix(prefijoRestante.substring(1), prefijoCompleto);
    }

    // 2. Función auxiliar recursiva para recolectar las palabras (DFS)
    private void recorrePrefijo(String palabraArmada, LinkedList<String> resultados) {
        // Si el nodo en el que estamos parados es el fin de una palabra, lo guardamos
        if (this.finNombre) {
            resultados.add(palabraArmada);
        }

        // Iteramos por todos los hijos (ramas) que salen de este nodo
        for (Map.Entry<Character, NodoAPrefijo<E>> entrada : this.hijos.entrySet()) {
            char letraHijo = entrada.getKey();
            NodoAPrefijo<E> nodoHijo = entrada.getValue();

            // Llamamos a la recursión sumando la letra del hijo a la palabra que estamos armando
            nodoHijo.recorrePrefijo(palabraArmada + letraHijo, resultados);
        }
    }


    }


