import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Gestiona las operaciones principales de la agenda de contactos.
 * Utiliza un Árbol Prefijo para almacenar y buscar contactos de manera eficiente,
 * y se apoya en una estructura Heap para ordenar los resultados de búsqueda según
 * la frecuencia con la que los contactos son consultados.
 */
public class Agenda {
    private NodoAPrefijo<Contacto> arbolContactos;
    private ComparadorFrecuencia comparador;

    /**
     * Constructor principal de la clase Agenda.
     * Inicializa el árbol de prefijos vacío y el comparador de frecuencias
     * necesario para el funcionamiento del Heap.
     */
    public Agenda() {
        this.arbolContactos = new NodoAPrefijo<>();
        this.comparador = new ComparadorFrecuencia();
    }

    /**
     * Inserta un nuevo contacto en el árbol de prefijos.
     * El contacto es indexado de manera independiente por su nombre,
     * apellido y apodo, lo que permite buscarlo posteriormente usando
     * cualquiera de estos tres criterios.
     *
     * @param c El objeto Contacto a insertar en la agenda.
     */
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

    /**
     * Lee un archivo de texto y carga los contactos en la agenda.
     * Se espera que el archivo tenga el siguiente formato separado por comas:
     * Nombre, Apellido, Apodo, Teléfono Móvil, Teléfono Convencional, Correo Electrónico.
     *
     * @param ruta La ruta o nombre del archivo (.txt o .csv) a leer.
     */
    public void cargarDesdeArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                // Se verifica que la línea contenga al menos los 6 campos requeridos
                if (d.length >= 6) {
                    Contacto nuevo = new Contacto(
                            d[0].trim(), // Nombre
                            d[1].trim(), // Apellido
                            d[2].trim(), // Apodo
                            d[3].trim(), // Teléfono móvil
                            d[4].trim(), // Teléfono convencional
                            d[5].trim()  // Correo electrónico
                    );
                    this.insertarContacto(nuevo);
                }
            }
            System.out.println("✅ Archivo '" + ruta + "' cargado con éxito.");
        } catch (IOException e) {
            System.out.println("❌ Error al cargar archivo: " + e.getMessage());
        }
    }

    /**
     * Busca contactos en el árbol que coincidan con un prefijo dado y los muestra
     * en consola ordenados de mayor a menor según su frecuencia de búsqueda.
     * Además, incrementa en 1 la frecuencia de todos los contactos encontrados.
     *
     * @param prefijo La cadena de texto (letras iniciales) a buscar en la agenda.
     */
    public void buscarYMostrar(String prefijo) {
        LinkedList<Contacto> resultados = arbolContactos.buscarPrefijo(prefijo);

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("❌ No se encontraron contactos para: " + prefijo);
            return;
        }

        System.out.println("🔍 Resultados para '" + prefijo + "' (Ordenados por frecuencia):");

        //Aumenta la frecuencia de todos los contactos encontrados y filtra duplicados
        ArrayList<Contacto> listaParaHeap = new ArrayList<>();
        for (Contacto c : resultados) {
            if (!listaParaHeap.contains(c)) {
                c.setFrecuencia(c.getFrecuencia() + 1);
                listaParaHeap.add(c);
            }
        }

        //Crea el Max-Heap (asc = false) para que los de MAYOR frecuencia salgan primero
        Heap<Contacto> heapFrecuencias = new Heap<>(listaParaHeap.size() + 10, false, comparador);
        heapFrecuencias.construirHeap(listaParaHeap);

        //Desencola e imprime los resultados ordenados
        Contacto extraido;
        while ((extraido = heapFrecuencias.desencolar()) != null) {
            System.out.println("   -> " + extraido.getNombre() + " " + extraido.getApellido() +
                    " (Frecuencia: " + extraido.getFrecuencia() + ") - Tel: " + extraido.getTelefonoMovil());
        }
    }

    /**
     * Elimina un contacto específico de la agenda en todas sus referencias.
     * Remueve el contacto buscando por su nombre, apellido y apodo.
     * @param c El contacto exacto que se desea eliminar.
     */
    public void eliminarContactoEspecifico(Contacto c) {
        if (c == null) return;

        if (c.getNombre() != null && !c.getNombre().isEmpty()) {
            arbolContactos.eliminarContacto(c.getNombre(), c);
        }
        if (c.getApellido() != null && !c.getApellido().isEmpty()) {
            arbolContactos.eliminarContacto(c.getApellido(), c);
        }
        if (c.getApodo() != null && !c.getApodo().isEmpty()) {
            arbolContactos.eliminarContacto(c.getApodo(), c);
        }
        System.out.println("✅ Contacto '" + c.getNombre() + "' eliminado de la agenda.");
    }
    /**
     * Muestra la estructura actual del heap por frecuencias
     */
    public void mostrarEstructuraHeap() {
        LinkedList<Contacto> lista = arbolContactos.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("⚠️ La agenda está vacía, no hay nada en el Heap.");
            return;
        }

        ArrayList<Contacto> unicos = new ArrayList<>();
        for (Contacto c : lista) {
            if (!unicos.contains(c)) {
                unicos.add(c);
            }
        }

        Heap<Contacto> heapTemp = new Heap<>(unicos.size() + 10, false, comparador);
        heapTemp.construirHeap(unicos);

        System.out.println("\n--- ESTRUCTURA REAL DEL HEAP ---");
        System.out.println("Arreglo en memoria: " + heapTemp.datos);

        System.out.println("\n--- EXTRACCIÓN DEL HEAP (De Mayor a Menor Frecuencia) ---");
        Contacto extraido;
        int ranking = 1;
        while ((extraido = heapTemp.desencolar()) != null) {
            System.out.println(ranking + ". " + extraido.getNombre() + " " + extraido.getApellido() +
                    " -> Frecuencia: " + extraido.getFrecuencia());
            ranking++;
        }
        System.out.println("---------------------------------------------------------");
    }
    /**
     * Permite la exportación de archivo de contactos.
     * @param nombreArchivo ruta del archivo a exportar.
     */
    public void exportarAgenda(String nombreArchivo) {
        LinkedList<Contacto> listaNodos = arbolContactos.obtenerTodos();
        ArrayList<Contacto> unicos = new ArrayList<>();
        for (Contacto c : listaNodos) {
            if (!unicos.contains(c)) {
                unicos.add(c);
            }
        }

        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(nombreArchivo))) {
            for (Contacto c : unicos) {
                bw.write(c.getNombre() + ", " + c.getApellido() + ", " + c.getApodo() + ", " +
                        c.getTelefonoMovil() + ", " + c.getTelefonoConvencional() + ", " + c.getCorreoElectronico());
                bw.newLine();
            }
            System.out.println("✅ Archivo guardado correctamente en: " + nombreArchivo);
        } catch (java.io.IOException e) {
            System.out.println("❌ Error al escribir el archivo: " + e.getMessage());
        }
    }
}