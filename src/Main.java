import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Instanciamos el Trie indicando que el dato asociado (E) será un String
        Agenda<String> diccionario = new Agenda<>();

        System.out.println("--- 📥 INSERTANDO PALABRAS ---");
        diccionario.insertar("mar", "Masa de agua salada");
        diccionario.insertar("mar", "Nombre propio corto"); // Segunda definición para "mar"
        diccionario.insertar("marino", "Perteneciente al mar");
        diccionario.insertar("marea", "Movimiento de ascenso y descenso del mar");
        diccionario.insertar("sol", "Estrella luminosa");
        diccionario.insertar("solo", "Sin compañía");
        System.out.println("Palabras insertadas correctamente.\n");

        System.out.println("--- 🔍 BUSCANDO PALABRAS ---");

        // 1. Buscar una palabra con un solo dato
        buscarEImprimir(diccionario, "marea");

        // 2. Buscar una palabra con múltiples datos (LinkedList)
        buscarEImprimir(diccionario, "mar");

        // 3. Buscar una palabra que comparte raíz pero es diferente
        buscarEImprimir(diccionario, "solo");

        System.out.println("\n--- ⚠️ BUSCANDO CASOS ESPECIALES ---");

        // 4. Buscar un prefijo que NO es una palabra (no tiene finNombre = true)
        buscarEImprimir(diccionario, "ma");

        // 5. Buscar una palabra que no existe en absoluto
        buscarEImprimir(diccionario, "luna");

        // 6. Buscar una palabra más larga que las que están en el árbol
        buscarEImprimir(diccionario, "mariposa");
    }

    // Método auxiliar para no repetir código en los System.out.println
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