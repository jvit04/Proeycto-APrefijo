public class Main {
    public static void main(String[] args) {
        // 1. Instanciamos la agenda que ahora usa NodoAPrefijo y Heap
        Agenda miAgenda = new Agenda();

        System.out.println("--- 📥 INSERTANDO CONTACTOS ---");
        // Creamos contactos que compartan prefijos para ver la magia del Heap
        Contacto c1 = new Contacto("Maria", "Gomez", "Mari", "0991111111");
        Contacto c2 = new Contacto("Mariana", "Perez", "Marian", "0992222222");
        Contacto c3 = new Contacto("Marcos", "Lopez", "Mark", "0984444444");
        Contacto c4 = new Contacto("Mateo", "Diaz", "Teo", "0975555555");

        miAgenda.insertarContacto(c1);
        miAgenda.insertarContacto(c2);
        miAgenda.insertarContacto(c3);
        miAgenda.insertarContacto(c4);
        System.out.println("Contactos insertados con éxito.\n");

        System.out.println("--- 🔍 SIMULANDO BÚSQUEDAS (AUMENTANDO FRECUENCIAS) ---");

        // Buscamos "maria" dos veces. Su frecuencia llegará a 2.
        System.out.println("Buscando 'Maria' (1ra vez)...");
        miAgenda.buscarYMostrar("maria");
        System.out.println("\nBuscando 'Maria' (2da vez)...");
        miAgenda.buscarYMostrar("maria");

        // Buscamos "mariana" una vez. Su frecuencia llegará a 1.
        System.out.println("\nBuscando 'Mariana' (1ra vez)...");
        miAgenda.buscarYMostrar("mariana");

        // Marcos y Mateo no los buscamos, su frecuencia se mantiene en 0 por ahora.

        System.out.println("\n=======================================================");
        System.out.println("--- 🏆 PRUEBA FINAL: BÚSQUEDA POR PREFIJO 'mar' ---");
        System.out.println("=======================================================");
        // Al buscar "mar", el árbol debe encontrar a Maria, Mariana y Marcos.
        // El método buscarYMostrar les sumará +1 a la frecuencia de los tres en este momento.
        // El Heap debería ordenarlos así: Maria (3), Mariana (2), Marcos (1).

        miAgenda.buscarYMostrar("mar");

        System.out.println("\n=======================================================");
        System.out.println("--- 🏆 PRUEBA EXTRA: BÚSQUEDA POR PREFIJO 'ma' ---");
        System.out.println("=======================================================");
        // Al buscar "ma", también se incluye a Mateo.
        miAgenda.buscarYMostrar("ma");
    }
}