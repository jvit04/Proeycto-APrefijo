import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda miAgenda = new Agenda();
        Scanner sc = new Scanner(System.in);
        int opcion;

        System.out.println("====================================================");
        System.out.println("      SISTEMA DE GESTIÓN DE CONTACTOS (TRIE + HEAP)");
        System.out.println("====================================================");

        do {
            System.out.println("\n--- MENÚ DE PRESENTACIÓN ---");
            System.out.println("1. Cargar contactos desde archivo (.txt)");
            System.out.println("2. Insertar contacto manualmente (Demostrar Indexación)");
            System.out.println("3. Buscar por prefijo (Demostrar Trie + Max-Heap)");
            System.out.println("4. Eliminar contacto (Demostrar Poda de Árbol)");
            System.out.println("5. Ver estructura interna del Heap (InOrden)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n[PASO 1: CARGA MASIVA]");
                    // Aquí se explica que el sistema lee el TXT y separa los datos por comas
                    miAgenda.cargarDesdeArchivo("contactos.txt");
                    break;

                case 2:
                    System.out.println("\n[PASO 2: INSERCIÓN MANUAL]");
                    System.out.println("Por favor, ingrese los datos del nuevo contacto:");

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();

                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine().trim();

                    System.out.print("Apodo: ");
                    String apodo = sc.nextLine().trim();

                    System.out.print("Teléfono Móvil: ");
                    String telMovil = sc.nextLine().trim();

                    System.out.print("Teléfono Convencional: ");
                    String telConvencional = sc.nextLine().trim();

                    System.out.print("Correo Electrónico: ");
                    String correo = sc.nextLine().trim();

                    // Instanciamos el contacto con los datos ingresados por el usuario
                    Contacto nuevoContacto = new Contacto(nombre, apellido, apodo, telMovil, telConvencional, correo);

                    // Insertamos el contacto en el TDA
                    miAgenda.insertarContacto(nuevoContacto);

                    System.out.println("\n✅ ¡Contacto creado y guardado exitosamente!");
                    System.out.println("-> Explicación: El sistema acaba de guardar este contacto 3 veces en el Árbol Prefijo (indexado por su nombre, apellido y apodo) para agilizar futuras búsquedas.");
                    break;

                case 3:
                    System.out.println("\n[PASO 3: BÚSQUEDA INTELIGENTE]");
                    System.out.print("Ingrese el prefijo a buscar (ej: 'mar'): ");
                    String prefijo = sc.nextLine();
                    // Aquí se explica que buscarYMostrar aumenta la frecuencia y usa el HEAP para ordenar
                    miAgenda.buscarYMostrar(prefijo);
                    System.out.println("\n-> Explicación: El árbol encontró las coincidencias y el Heap las ordenó por relevancia.");
                    break;

                case 4:
                    System.out.println("\n[PASO 4: ELIMINACIÓN Y PODA]");
                    System.out.println("Buscando primero a 'Maria' para obtener la referencia...");
                    // En un escenario real buscarías el objeto, aquí simulamos con uno conocido del txt
                    Contacto aEliminar = new Contacto("Maria", "Gomez", "Mari", "0991111111", "042111111", "mari.gomez@email.com");
                    miAgenda.eliminarContactoEspecifico(aEliminar);
                    System.out.println("-> Explicación: Se eliminó el objeto y se podaron las ramas del árbol que quedaron vacías.");
                    break;

                case 5:
                    System.out.println("\n[PASO 5: ESTRUCTURA DEL HEAP]");
                    System.out.println("Esta opción demuestra cómo se ve el Heap internamente usando el recorrido InOrden.");
                    // Nota: Esta función requiere que tengas el método en Agenda o accedas al Heap directamente
                    System.out.println("Recorrido técnico del árbol de prioridades actual...");
                    miAgenda.mostrarEstructuraHeap(); // Si decides implementar un getter del heap
                    break;

                case 6:
                    System.out.println("Cerrando sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        sc.close();
    }
}