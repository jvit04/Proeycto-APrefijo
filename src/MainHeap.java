import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MainHeap {
    public static void main(String[] args) {

        // 1. Creamos una lista de números totalmente desordenada
        ArrayList<Integer> numerosDesordenados = new ArrayList<>(Arrays.asList(4, 10, 8, 5, 7, 1, 9, 2));

        System.out.println("🔧 Arreglo Original: " + numerosDesordenados);

        // 2. Creamos un Comparator para Integers (obligatorio para tu constructor)
        Comparator<Integer> comparadorEnteros = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b); // Compara de forma natural los números
            }
        };

        // =========================================================
        // PRUEBA 1: MAX-HEAP (ascendente = false)
        // El número más grande debe quedar en la raíz (índice 0)
        // =========================================================
        System.out.println("\n--- 🟢 PRUEBA MAX-HEAP ---");
        Heap<Integer> maxHeap = new Heap<>(20, false, comparadorEnteros);

        // Ejecutamos tu función mágica
        maxHeap.construirHeap(numerosDesordenados);

        System.out.println("Arreglo del Max-Heap: " + maxHeap.datos);

        /* Si funciona bien, el 10 debería estar al principio.
        Ejemplo de salida esperada: [10, 7, 9, 5, 4, 1, 8, 2]
        (El orden exacto de los hijos puede variar ligeramente, pero el 10 DEBE ser el primero
        y cada padre debe ser mayor que sus hijos).
        */

        // =========================================================
        // PRUEBA 2: MIN-HEAP (ascendente = true)
        // El número más pequeño debe quedar en la raíz (índice 0)
        // =========================================================
        System.out.println("\n--- 🔵 PRUEBA MIN-HEAP ---");
        Heap<Integer> minHeap = new Heap<>(20, true, comparadorEnteros);

        // Volvemos a construir usando la misma lista desordenada
        // Ojo: pasamos una copia fresca para que no use la que ya modificó el Max-Heap
        minHeap.construirHeap(new ArrayList<>(Arrays.asList(4, 10, 8, 5, 7, 1, 9, 2)));

        System.out.println("Arreglo del Min-Heap: " + minHeap.datos);

        /* Si funciona bien, el 1 debería estar al principio.
        Ejemplo de salida esperada: [1, 2, 4, 5, 7, 8, 9, 10]
        (El 1 DEBE ser el primero y cada padre debe ser menor que sus hijos).
        */
    }
}