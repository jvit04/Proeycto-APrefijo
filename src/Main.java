public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   🧪 INICIANDO PRUEBAS DEL SISTEMA APREFIJO");
        System.out.println("==================================================\n");

        // 1. Instanciamos la agenda
        Agenda miAgenda = new Agenda();

        // 2. PRUEBA DE CARGA DE ARCHIVO
        System.out.println("--- PRUEBA 1: CARGA DE ARCHIVOS ---");
        // Asegúrate de tener el archivo contactos.txt en la raíz del proyecto
        miAgenda.cargarDesdeArchivo("contactos.txt");
        System.out.println();

        // 3. PRUEBA DE INSERCIÓN MANUAL
        System.out.println("--- PRUEBA 2: INSERCIÓN MANUAL ---");
        Contacto c1 = new Contacto("Mariano", "Gomez", "Mari", "0991111111", "042111111", "mari@email.com");
        Contacto c2 = new Contacto("Giovanni", "Perez", "Yoyo", "0992222222", "042222222", "mariana@email.com");
        Contacto c3 = new Contacto("Emiliano", "Lopez", "Emi", "0984444444", "042333333", "marcos@email.com");

        miAgenda.insertarContacto(c1);
        miAgenda.insertarContacto(c2);
        miAgenda.insertarContacto(c3);
        System.out.println("✅ Contactos manuales (Mariano, Giovanni, Emiliano) insertados con éxito.\n");

        // 4. PRUEBA DEL HEAP Y FRECUENCIAS
        System.out.println("--- PRUEBA 3: BÚSQUEDA Y HEAP DE FRECUENCIAS ---");
        System.out.println("Buscando 'mariana' una vez (Aumenta su frecuencia)...");
        miAgenda.buscarYMostrar("mariana");

        System.out.println("\nBuscando 'mar' (Deberían salir los 3, pero Mariana primero por tener más frecuencia)...");
        miAgenda.buscarYMostrar("mar");
        System.out.println();

        // 5. PRUEBA DE ELIMINACIÓN MODULAR
        System.out.println("--- PRUEBA 4: ELIMINACIÓN ESPECÍFICA ---");
        System.out.println("Eliminando a 'Mariano' del sistema...");

        // Llamamos al método de eliminación de la agenda enviando el objeto exacto
        miAgenda.eliminarContactoEspecifico(c1);


        System.out.println("\nBuscando 'mar' nuevamente (Mariano ya no debería aparecer)...");
        miAgenda.buscarYMostrar("mar");

        System.out.println("\n==================================================");
        System.out.println("   ✅ PRUEBAS FINALIZADAS");
        System.out.println("==================================================");
    }
}