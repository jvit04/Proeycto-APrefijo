import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Ahora la agenda maneja la clase directamente
        Agenda miAgenda = new Agenda();

        System.out.println("--- 📥 INSERTANDO CONTACTOS ---");
        Contacto c1 = new Contacto("Alberto", "Perez", "Beto", "0991234567");
        Contacto c2 = new Contacto("Tomas", "Mendoza", "Tommy", "0987654321");

        miAgenda.insertarContacto(c1);
        miAgenda.insertarContacto(c2);
        System.out.println("Contactos insertados.\n");

        System.out.println("--- 🔍 BUSCANDO EL MISMO CONTACTO DE 3 FORMAS ---");
        // Buscamos por nombre
        buscarEImprimir(miAgenda, "Alberto");
        // Buscamos por apellido
        buscarEImprimir(miAgenda, "Perez");
        // Buscamos por apodo
        buscarEImprimir(miAgenda, "Beto");

        System.out.println("\n--- 🔍 BUSCANDO A TOMAS ---");
        buscarEImprimir(miAgenda, "Tommy"); // Buscar por apodo
    }

    private static void buscarEImprimir(Agenda agenda, String palabra) {
        LinkedList<Contacto> resultado = agenda.buscar(palabra);
        System.out.print("Buscando '" + palabra + "': ");
        if (resultado != null && !resultado.isEmpty()) {
            // Se imprimirá usando el método toString() que definiste en tu clase Contacto
            System.out.println("✅ Encontrado -> " + resultado);
        } else {
            System.out.println("❌ No encontrado");
        }
    }
}