import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Representa un nodo dentro de la estructura de datos Árbol Prefijo (Trie).
 * Cada nodo almacena un carácter, enlaces a sus nodos hijos, un indicador de fin de palabra
 * y una lista de datos (contactos) para permitir nombres duplicados.
 *
 * @param <E> El tipo de dato que almacenará el nodo (generalmente objetos de tipo Contacto).
 */
public class NodoAPrefijo<E> {
    /** Mapa que enlaza un carácter con su respectivo nodo hijo. */
    Map<Character, NodoAPrefijo<E>> hijos;
    /** Indica si el nodo actual representa el final de una palabra insertada. */
    boolean finNombre;
    /** Lista que almacena los datos asociados a la palabra. Permite múltiples elementos (duplicados). */
    LinkedList<E> datos;
    /** Carácter que identifica específicamente a este nodo dentro del árbol. */
    Character character;

    /**
     * Constructor para el nodo raíz del árbol.
     * Inicializa las estructuras vacías y establece el carácter en nulo,
     * ya que la raíz no representa ninguna letra específica.
     */
    public NodoAPrefijo() {
        this.hijos = new HashMap<>();
        this.finNombre = false;
        this.datos = new LinkedList<>();
        this.character = null;
    }

    /**
     * Constructor para los nodos hijos del árbol.
     * Inicializa las estructuras e identifica al nodo con un carácter específico.
     *
     * @param character El carácter que representará este nodo.
     */
    public NodoAPrefijo(Character character) {
        this.hijos = new HashMap<>();
        this.finNombre = false;
        this.datos = new LinkedList<>();
        this.character = character;
    }

    /**
     * Obtiene todos los contactos almacenados en el árbol.
     * Útil para procesos de exportación o copias de seguridad.
     * @return Lista con todos los elementos E del árbol.
     */
    public LinkedList<E> obtenerTodos() {
        LinkedList<E> todos = new LinkedList<>();
        recorrePrefijo(todos); // Usa el método DFS que ya tienes en tu clase
        return todos;
    }
    /**
     * Inserta una palabra en el árbol, creando los nodos necesarios letra por letra.
     * Al finalizar el recorrido, marca el último nodo como fin de palabra y almacena el dato.
     *
     * @param palabra La cadena de texto (nombre, apellido o apodo) a indexar.
     * @param dato El objeto (contacto) que se asociará a la palabra.
     */
    public void insertar(String palabra, E dato) {
        NodoAPrefijo<E> actual = this;
        palabra = palabra.toLowerCase();

        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);

            // si la letra no existe, se crea un nuevo NodoAPrefijo y se le pasa el mismo.
            if (!actual.hijos.containsKey(letra)) {
                actual.hijos.put(letra, new NodoAPrefijo<>(letra));
            }
            // Bajamos al hijo correspondiente
            actual = actual.hijos.get(letra);
        }

        actual.finNombre = true; // marcamos fin de palabra
        actual.datos.add(dato);  // guardamos el contacto
    }

    /**
     * Busca y recupera todos los datos asociados a un prefijo específico.
     * Navega hasta el último carácter del prefijo y luego recolecta recursivamente
     * todos los datos que cuelgan de dicho subárbol.
     *
     * @param prefijo La cadena de texto que se desea buscar.
     * @return Una lista enlazada con todos los datos (contactos) encontrados, o null si el camino no existe.
     */
    public LinkedList<E> buscarPrefijo(String prefijo) {
        LinkedList<E> resultado = new LinkedList<>();
        if (prefijo == null) return resultado;

        prefijo = prefijo.toLowerCase();
        int i = 0;
        NodoAPrefijo<E> p = this;

        // Viaja por las letras del prefijo
        while (i < prefijo.length()) {
            if (p == null) return null; // Si no existe el camino
            p = p.hijos.get(prefijo.charAt(i));
            i++;
        }

        // Si se alcanzó el final del prefijo exitosamente, recolecta la información
        if (p != null) {
            p.recorrePrefijo(resultado);
        }
        return resultado;
    }

    /**
     * Recorre recursivamente el subárbol a partir del nodo actual (búsqueda en profundidad - DFS).
     * Acumula en una lista todos los datos de los nodos que están marcados como fin de palabra.
     *
     * @param Lresultado La lista donde se irán agregando los datos encontrados.
     */
    private void recorrePrefijo(LinkedList<E> Lresultado) {
        // Si el nodo actual es una palabra válida, agrega sus datos a la lista
        if (this.finNombre) {
            Lresultado.addAll(this.datos);
        }
        // Continúa la recursión para todos los hijos del nodo actual
        for (Map.Entry<Character, NodoAPrefijo<E>> entry : this.hijos.entrySet()) {
            NodoAPrefijo<E> hijo = entry.getValue();
            hijo.recorrePrefijo(Lresultado);
        }
    }

    /**
     * Elimina un contacto específico del árbol de prefijos.
     * Es el método de entrada público que usa la clase Agenda y que inicia el proceso de búsqueda y eliminación.
     *
     * @param palabra La cadena de texto (nombre, apellido o apodo) asociada al contacto.
     * @param contacto El objeto exacto que se desea eliminar de la lista.
     */
    public void eliminarContacto(String palabra, E contacto) {
        if (palabra == null || palabra.isBlank() || contacto == null) return;
        eliminarRecursivo(this, palabra.toLowerCase(), 0, contacto);
    }

    /**
     * Método central que controla la navegación recursiva por el árbol.
     * Delega las tareas de eliminación y poda a métodos auxiliares especializados.
     *
     * @param actual El nodo en el que se encuentra la recursión.
     * @param palabra La palabra completa que dicta el camino.
     * @param index La posición de la letra actual.
     * @param contacto El objeto específico a remover.
     * @return true si el nodo actual quedó inútil y debe ser podado por su padre.
     */
    private boolean eliminarRecursivo(NodoAPrefijo<E> actual, String palabra, int index, E contacto) {
        // 1. Delegación: Procesar el nodo final si se alcanzó el fin de la palabra
        if (index == palabra.length()) {
            return eliminarUltimo(actual, contacto);
        }

        char letra = palabra.charAt(index);
        NodoAPrefijo<E> nodoHijo = actual.hijos.get(letra);

        // Si el camino no existe, la recursión termina
        if (nodoHijo == null) return false;

        // 2. Navegación: Llamada recursiva bajando al siguiente nivel
        //Ej:
        // ℗
        //↓
        //©
        boolean deberiaPodarHijo = eliminarRecursivo(nodoHijo, palabra, index + 1, contacto);

        // 3. Delegación: Procesar la poda de la rama al regresar de la recursión
        return evaluarYPoda(actual, letra, deberiaPodarHijo);
    }

    /**
     * Se encarga exclusivamente remover el contacto y desmarcar el nodo si es necesario.
     *
     * @param nodo El nodo final de la palabra.
     * @param contacto El contacto a remover.
     * @return true si el nodo quedó completamente vacío y es candidato a poda.
     */
    private boolean eliminarUltimo(NodoAPrefijo<E> nodo, E contacto) {
        if (!nodo.finNombre) return false;

        // Se remueve la instancia específica
        nodo.datos.remove(contacto);

        // Si ya no quedan duplicados en este nodo, se desmarca
        if (nodo.datos.isEmpty()) {
            nodo.finNombre = false;
            // Es candidato a poda solo si no tiene más ramas que dependan de él
            return nodo.hijos.isEmpty();
        }

        return false;
    }

    /**
     * Analiza el resultado de los nodos hijos y se encarga exclusivamente
     * de cortar las conexiones (podar) si las ramas hijas quedaron inútiles.
     *
     * @param padre El nodo actual que evalúa a su hijo.
     * @param letraHijo La letra que conecta al padre con el hijo.
     * @param deberiaPodar Resultado de la evaluación del hijo.
     * @return true si este nodo padre también quedó inútil tras la poda del hijo.
     */
    private boolean evaluarYPoda(NodoAPrefijo<E> padre, char letraHijo, boolean deberiaPodar) {
        if (deberiaPodar) {
            // Se corta la rama inútil
            padre.hijos.remove(letraHijo);

            // Se evalúa si el padre también debe ser podado por su ancestro
            return !padre.finNombre && padre.hijos.isEmpty();
        }
        return false;
    }
}