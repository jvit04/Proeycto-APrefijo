public class Main {
    public static void main(String[] args) {
        Agenda miAgenda = new Agenda();

        System.out.println("--- 📥 CARGANDO CONTACTOS DESDE ARCHIVO ---");
        miAgenda.cargarDesdeArchivo("contactos.txt");

        miAgenda.buscarYMostrar("mariana");
        miAgenda.buscarYMostrar("mariana");
        miAgenda.buscarYMostrar("maria");

        System.out.println("\n--- 🏆 MOSTRANDO PRIORIDAD POR FRECUENCIA (Prefijo 'mar') ---");
        miAgenda.buscarYMostrar("mar");}}