import java.util.ArrayList;
import java.util.Comparator;

public class Heap<E> {
    ArrayList<E> datos;
    int max;
    int n;
    boolean asc;
    Comparator<E> cmp;

    public Heap(int max, boolean asc, Comparator<E> cmp) {
        this.max = max;
        this.asc = asc;
        this.n = 0;
        datos = new ArrayList<>(max);
        this.cmp = cmp;}

    public int indiceHijoIzq(int r) {
        int valor = r * 2 + 1;
        if (valor >= n) return -1;
        return valor;}

    public int indiceHijoDer(int r) {
        int valor = 2 * r + 2;
        if (valor >= n) return -1;
        return valor;}

    public boolean esHoja(int i) {
        return (indiceHijoIzq(i) == -1 && indiceHijoDer(i) == -1);}

    private void intercambiar(int x, int y) {
        E tmp = datos.get(x);
        datos.set(x, datos.get(y));
        datos.set(y, tmp);}

    public void construirHeap(ArrayList<E> lista) {
        this.datos = new ArrayList<>(lista);
        this.n = datos.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            this.autoAjuste(i);}}

    private void autoAjuste(int i) {
        int izq = indiceHijoIzq(i);
        int der = indiceHijoDer(i);
        int objetivo = i;

        if (izq != -1) {
            if (!asc) { // Max-Heap
                if (cmp.compare(datos.get(izq), datos.get(objetivo)) > 0) objetivo = izq;
            } else { // Min-Heap
                if (cmp.compare(datos.get(izq), datos.get(objetivo)) < 0) objetivo = izq;}}

        if (der != -1) {
            if (!asc) { // Max-Heap
                if (cmp.compare(datos.get(der), datos.get(objetivo)) > 0) objetivo = der;
            } else { // Min-Heap
                if (cmp.compare(datos.get(der), datos.get(objetivo)) < 0) objetivo = der;}}

        if (objetivo != i) {
            intercambiar(i, objetivo);
            autoAjuste(objetivo);}}

    public E desencolar() {
        if (n == 0) return null;
        E eliminado = datos.get(0);
        if (n == 1) {
            datos.remove(0);
            n--;
            return eliminado;}
        datos.set(0, datos.get(n - 1));
        datos.remove(n - 1);
        n--;
        this.autoAjuste(0);
        return eliminado;}

    public void nodosHoja() {
        ArrayList<E> hojas = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (esHoja(i)) {
                hojas.add(datos.get(i));}}
        System.out.println("Nodos Hoja del Heap: " + hojas);}

    public void inOrden() {
        System.out.println("Recorrido InOrden: " + this.inOrdenLogica(0));}

    private ArrayList<E> inOrdenLogica(int i) {
        if (i >= n || i == -1) return new ArrayList<>();
        ArrayList<E> resultado = new ArrayList<>();
        resultado.addAll(this.inOrdenLogica(this.indiceHijoIzq(i)));
        resultado.add(datos.get(i));
        resultado.addAll(this.inOrdenLogica(this.indiceHijoDer(i)));
        return resultado;}}