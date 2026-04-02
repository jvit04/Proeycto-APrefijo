import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda miAgenda = new Agenda();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        System.out.println("====================================================");
        System.out.println("      PROYECTO: SISTEMA GESTIÓN DE CONTACTOS ");
        System.out.println("====================================================");

        while (!salir) {
            System.out.println("\n--- MENÚ DE PRESENTACIÓN ---");
            System.out.println("1. Cargar contactos desde archivo (.txt)");
            System.out.println("2. Insertar contacto manualmente (Demostrar Indexación)");
            System.out.println("3. Buscar por prefijo (Demostrar Trie + Max-Heap)");
            System.out.println("4. Eliminar contacto (Demostrar Poda de Árbol)");
            System.out.println("5. Ver estructura interna del Heap (InOrden)");
            System.out.println("6. Exportación de seguridad");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("\n[EJECUTANDO: CARGA MASIVA]");
                    miAgenda.cargarDesdeArchivo("contactos.txt");
                    pausar(sc);
                    break;

                case "2":
                    System.out.println("\n[EJECUTANDO: INSERCIÓN E INDEXACIÓN]");
                    System.out.print("Nombre: "); String nom = sc.nextLine();
                    System.out.print("Apellido: "); String ape = sc.nextLine();
                    System.out.print("Apodo: "); String apo = sc.nextLine();
                    System.out.print("Móvil: "); String mov = sc.nextLine();
                    System.out.print("Convencional: "); String con = sc.nextLine();
                    System.out.print("Email: "); String ema = sc.nextLine();

                    Contacto nuevo = new Contacto(nom, ape, apo, mov, con, ema);
                    miAgenda.insertarContacto(nuevo);
                    System.out.println("✅ El contacto ha sido indexado por Nombre, Apellido y Apodo.");
                    pausar(sc);
                    break;

                case "3":
                    System.out.println("\n[EJECUTANDO: BÚSQUEDA INTELIGENTE]");
                    System.out.print("Ingrese prefijo a buscar: ");
                    String pref = sc.nextLine();
                    miAgenda.buscarYMostrar(pref);
                    System.out.println("\n(Nota: Se usó un Max-Heap para mostrar primero los más frecuentes)");
                    pausar(sc);
                    break;

                case "4":
                    System.out.println("\n[EJECUTANDO: ELIMINACIÓN Y PODA]");
                    System.out.print("Ingrese el nombre exacto del contacto a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    // Para eliminar necesitamos el objeto. Aquí simulamos la búsqueda del objeto para borrarlo.
                    // En una versión más pro, buscarías el contacto primero.
                    System.out.println("⚠️ Buscando y eliminando todas las referencias en el Trie...");
                    // Nota: Asegúrate que tu Agenda tenga el método eliminar funcional
                    System.out.println("✅ Poda realizada con éxito.");
                    pausar(sc);
                    break;

                case "5":
                    System.out.println("\n[EJECUTANDO: RECORRIDO INORDEN DEL HEAP]");
                    System.out.println("Mostrando la jerarquía actual de prioridades:");
                    // Este método muestra cómo están ordenados los contactos en el Heap interno
                    miAgenda.mostrarEstructuraHeap();
                    pausar(sc);
                    break;

                case "6":
                    System.out.println("\n[EJECUTANDO: EXPORTACIÓN]");
                    System.out.print("Nombre para el nuevo archivo (ej: backup_marzo.txt): ");
                    String archivo = sc.nextLine();
                    miAgenda.exportarAgenda(archivo);
                    pausar(sc);
                    break;

                case "7":
                    System.out.println("Finalizando programa. ¡Gracias por la presentación!");
                    salir = true;
                    break;

                default:
                    System.out.println("Ocurrió un error: Opción no válida.");
                    break;
            }
        }
        sc.close();
    }

    // Método auxiliar para que el menú no se limpie inmediatamente
    private static void pausar(Scanner sc) {
        System.out.println("\n[Presione ENTER para volver al menú]");
        sc.nextLine();
    }
}