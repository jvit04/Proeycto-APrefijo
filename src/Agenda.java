import java.util.ArrayList;
import java.util.LinkedList;

public class Agenda {
    private NodoAPrefijo<Contacto> arbolContactos;
    private ComparadorFrecuencia comparador;

    public Agenda() {
        this.arbolContactos = new NodoAPrefijo<>();
        this.comparador = new ComparadorFrecuencia();
    }

    public void insertarContacto(Contacto c) {
        if (c.getNombre() != null && !c.getNombre().isEmpty()) {
            arbolContactos.insertar(c.getNombre(), c);
        }
        if (c.getApellido() != null && !c.getApellido().isEmpty()) {
            arbolContactos.insertar(c.getApellido(), c);
        }
        if (c.getApodo() != null && !c.getApodo().isEmpty()) {
            arbolContactos.insertar(c.getApodo(), c);
        }

    }

    // Método principal de búsqueda que utiliza el HEAP
    public void buscarYMostrar(String prefijo) {
        LinkedList<Contacto> resultados = arbolContactos.buscarPrefijo(prefijo);

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("❌ No se encontraron contactos para: " + prefijo);
            return;
        }

        System.out.println("🔍 Resultados para '" + prefijo + "' (Ordenados por frecuencia):");

        // 1. Aumentamos la frecuencia de todos los contactos encontrados
        // (Podrías hacer que el usuario elija a cuál aumentarle, pero aquí se le aumenta a los listados)
        ArrayList<Contacto> listaParaHeap = new ArrayList<>();
        for (Contacto c : resultados) {
            c.setFrecuencia(c.getFrecuencia() + 1);
            // Evitar duplicados si un contacto coincide por nombre y apellido a la vez
            if(!listaParaHeap.contains(c)){
                listaParaHeap.add(c);
            }
        }

        // 2. Creamos el Max-Heap (asc = false) para que los de MAYOR frecuencia salgan primero
        Heap<Contacto> heapFrecuencias = new Heap<>(listaParaHeap.size() + 10, false, comparador);
        heapFrecuencias.construirHeap(listaParaHeap);

        // 3. Desencolamos e imprimimos ordenado
        Contacto extraido;
        while ((extraido = heapFrecuencias.desencolar()) != null) {
            System.out.println("   -> " + extraido.getNombre() + " " + extraido.getApellido() +
                    " (Frecuencia: " + extraido.getFrecuencia() + ") - Tel: " + extraido.getTelefono());
        }
    }
}