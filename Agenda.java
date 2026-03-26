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
        this.comparador = new ComparadorFrecuencia();}

    public void insertarContacto(Contacto c) {
        if (c.getNombre() != null) arbolContactos.insertar(c.getNombre(), c);
        if (c.getApellido() != null) arbolContactos.insertar(c.getApellido(), c);
        if (c.getApodo() != null) arbolContactos.insertar(c.getApodo(), c);}

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

    public void buscarYMostrar(String prefijo) {
        LinkedList<Contacto> resultados = arbolContactos.buscarPrefijo(prefijo);

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("No hay coincidencias para: " + prefijo);
            return;}

        // Filtrar duplicados y aumentar frecuencia
        ArrayList<Contacto> listaUnica = new ArrayList<>();
        for (Contacto c : resultados) {
            if (!listaUnica.contains(c)) {
                c.setFrecuencia(c.getFrecuencia() + 1);
                listaUnica.add(c);}}

        // Usar Heap para ordenar por frecuencia (Max-Heap)
        // Usamos asc=false porque el ComparadorFrecuencia devuelve 1 si a > b
        Heap<Contacto> heap = new Heap<>(listaUnica.size() + 1, false, comparador);
        heap.construirHeap(listaUnica);

        // Imprimir resultados ordenados
        System.out.println("\n🔍 Sugerencias para '" + prefijo + "':");
        Contacto aux;
        while ((aux = heap.desencolar()) != null) {
            System.out.println("   > " + aux.getNombre() + " " + aux.getApellido() +
                    " [Frecuencia: " + aux.getFrecuencia() + "]");}}}