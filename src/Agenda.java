import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    public void cargarDesdeArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d.length >= 4) {
                    // Formato: Nombre, Apellido, Apodo, Telefono
                    Contacto nuevo = new Contacto(d[0].trim(), d[1].trim(), d[2].trim(), d[3].trim());
                    this.insertarContacto(nuevo);}}
            System.out.println("✅ Archivo '" + ruta + "' cargado con éxito.");
        } catch (IOException e) {
            System.out.println("❌ Error al cargar archivo: " + e.getMessage());}}


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