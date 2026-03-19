import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Agenda<String> diccionario = new Agenda<>();

        System.out.println("--- 📥 INSERTANDO PALABRAS ---");
        diccionario.insertar("Tom", "Gato de la caricatura");
        diccionario.insertar("Tomas", "Nombre propio de persona");
        diccionario.insertar("Toro", "Animal bovino");

        System.out.println("Palabras insertadas.\n");

        System.out.println("--- 🔍 ESTADO INICIAL ---");
        buscarEImprimir(diccionario, "Tom");
        buscarEImprimir(diccionario, "Tomas");
        buscarEImprimir(diccionario, "Toro");

        System.out.println("\n--- 🗑️ ELIMINANDO 'Tomas' ---");
        diccionario.eliminar("Tomas");
        System.out.println("Proceso de eliminación terminado.\n");

        System.out.println("--- 🔍 ESTADO DESPUÉS DE ELIMINAR ---");
        buscarEImprimir(diccionario, "Tom");   // Debería seguir existiendo
        buscarEImprimir(diccionario, "Tomas"); // Ya NO debería existir
        buscarEImprimir(diccionario, "Toro");  // Debería seguir existiendo (rama diferente)
    }

    private static void buscarEImprimir(Agenda<String> trie, String palabra) {
        LinkedList<String> resultado = trie.buscar(palabra);
        System.out.print("Buscando '" + palabra + "': ");
        if (resultado != null && !resultado.isEmpty()) {
            System.out.println("✅ Encontrado -> " + resultado);
        } else {
            System.out.println("❌ No encontrado");
        }
    }
}