import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NodoAPrefijo<E> {
    // Estructura solicitada
    Map<Character, NodoAPrefijo<E>> hijos;
    boolean finNombre;
    LinkedList<E> datos;
    Character character; // Aquí está tu variable para identificar el nodo

    // Constructor para la raíz (vacío)
    public NodoAPrefijo() {
        this.hijos = new HashMap<>();
        this.finNombre = false;
        this.datos = new LinkedList<>();
        this.character = null;
    }

    // Constructor para los hijos (con letra)
    public NodoAPrefijo(Character character) {
        this.hijos = new HashMap<>();
        this.finNombre = false;
        this.datos = new LinkedList<>();
        this.character = character;
    }

    public void insertar(String palabra, E dato) {
        NodoAPrefijo<E> actual = this;
        palabra = palabra.toLowerCase();

        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);

            // si la letra no existe, creamos un nuevo NodoAPrefijo y le pasamos el caracter
            if (!actual.hijos.containsKey(letra)) {
                actual.hijos.put(letra, new NodoAPrefijo<>(letra));
            }
            // Bajamos al hijo correspondiente
            actual = actual.hijos.get(letra);
        }

        actual.finNombre = true; // marcamos fin de palabra
        actual.datos.add(dato);  // guardamos el contacto
    }

    // Buscar por prefijo que devuelve los datos (Ideal para la agenda)
    public LinkedList<E> buscarPrefijo(String prefijo) {
        LinkedList<E> resultado = new LinkedList<>();
        if (prefijo == null || prefijo.isBlank()) return resultado;

        prefijo = prefijo.toLowerCase();
        int i = 0;
        NodoAPrefijo<E> p = this;

        // Viajamos por las letras del prefijo
        while (i < prefijo.length()) {
            if (p == null) return null; // Si no existe el camino
            p = p.hijos.get(prefijo.charAt(i));
            i++;
        }

        // Al llegar a la última letra del prefijo, recolectamos todo
        if (p != null) {
            p.recorrePrefijo(resultado);
        }
        return resultado;
    }

    // Método auxiliar recursivo (DFS)
    private void recorrePrefijo(LinkedList<E> Lresultado) {
        if (this.finNombre) {
            Lresultado.addAll(this.datos);
        }
        for (Map.Entry<Character, NodoAPrefijo<E>> entry : this.hijos.entrySet()) {
            NodoAPrefijo<E> hijo = entry.getValue();
            hijo.recorrePrefijo(Lresultado);
        }
    }

    // Método de eliminación
    public void eliminar(String palabra) {
        if (palabra != null && !palabra.isBlank()) {
            eliminarRecursivo(this, palabra.toLowerCase(), 0);
        }
    }

    private boolean eliminarRecursivo(NodoAPrefijo<E> actual, String palabra, int index) {
        if (index == palabra.length()) {
            if (!actual.finNombre) return false;

            actual.finNombre = false;
            actual.datos.clear();
            return actual.hijos.isEmpty();
        }

        char letra = palabra.charAt(index);
        NodoAPrefijo<E> nodoHijo = actual.hijos.get(letra);

        if (nodoHijo == null) return false;

        boolean deberiaEliminarHijo = eliminarRecursivo(nodoHijo, palabra, index + 1);

        if (deberiaEliminarHijo) {
            actual.hijos.remove(letra);
            return !actual.finNombre && actual.hijos.isEmpty();
        }
        return false;
    }
}