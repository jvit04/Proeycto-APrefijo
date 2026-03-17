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
        this.cmp = cmp;
    }


    public int indiceHijoIzq(int r){
        int valor = r*2+1;
        if (valor >= n) return -1;
        return valor;
    }

    public int indiceHijoDer(int r){
        int valor= 2*r+2;
        if(valor>=n) return -1;
        return valor;
    }
    public boolean esHoja(int i){
        return (indiceHijoIzq(i)==-1&& indiceHijoDer(i)==-1);
    }

    private int getMayor(int raiz, int izq, int der){
        int mayor = raiz;
        // Solo comparamos si el izquierdo existe (!= -1)
        if(izq != -1 && cmp.compare(datos.get(mayor), datos.get(izq)) < 0){
            mayor = izq;
        }
        // Solo comparamos si el derecho existe (!= -1)
        if(der != -1 && cmp.compare(datos.get(mayor), datos.get(der)) < 0){
            mayor = der;
        }
        return mayor;
    }

    private int getMenor(int raiz, int izq, int der){
        int menor = raiz;
        // Solo comparamos si el izquierdo existe (!= -1)
        if(izq != -1 && cmp.compare(datos.get(menor), datos.get(izq)) > 0){
            menor = izq;
        }
        // Solo comparamos si el derecho existe (!= -1)
        if(der != -1 && cmp.compare(datos.get(menor), datos.get(der)) > 0){
            menor = der;
        }
        return menor;
    }

    private int revisar(int raiz, int izq, int der){
        if(asc){
            // Ascendente = Queremos el más pequeño en la raíz (Min-Heap)
            return getMenor(raiz, izq, der);
        }
        else {
            // Descendente = Queremos el más grande en la raíz (Max-Heap)
            return getMayor(raiz, izq, der);
        }
    }

    //función ajuste
    private void autoAjuste(int iDanno){
        //revisar raiz, izq y der quien tiene el mayor o el menor
        //dependiendo el criterio
        //¿Qué es iMayorOMenor?
        //Variable que consigue quién deberia ser el mayor o menor del heap
        //recordemos que la raiz debería tener esta característica
        //de ser el mayor o menor.
        int iMayorOMenor = revisar(iDanno, this.indiceHijoIzq(iDanno), this.indiceHijoDer(iDanno));
        //Comparo mis sospechas, si iDanno es igual a quién debe iMayorOMenor
        //no hay problema
        if(iDanno == iMayorOMenor) return;
        //si no, intercambio, hasta que todo este en orden
        intercambio(iDanno, iMayorOMenor);
        //repito todo otra vez pero ahora por el lado del intercambio
        autoAjuste(iMayorOMenor);
    }

    public void intercambio(int x, int y){
        E tmp;
        tmp =datos.get(x);
        datos.set(x,datos.get(y));
        datos.set(y,tmp);
    }

    public void construirHeap(ArrayList<E> Amalo){
        this.datos = new ArrayList<>(Amalo);
        this.n = datos.size();
        for (int i = n/2 - 1; i >= 0; i--) {
            this.autoAjuste(i);
        }
    }
    
    public E desencolar(){
        //caso base: si este vacío el heap
        if(n==0) return null;
        E eliminado = datos.getFirst();
        //caso base 2: solo hay un elemento, no hay que ajustar
        if (n == 1) {
            datos.removeLast();
            n--;
            return eliminado;
        }
        //eliminado normal
        datos.set(0,datos.getLast());
        datos.removeLast();
        n--;
        this.autoAjuste(0);
        return eliminado;
    }



    public void inOrden(){
            System.out.println(this.inOrdenLogica(0));
    }
    private ArrayList<E> inOrdenLogica(int i){
        //caso base 1:  si i supera a n o es -1
        if(i>=n || i==-1) return new ArrayList<>();
        ArrayList<E> inOrder = new ArrayList<>();
        inOrder.addAll(this.inOrdenLogica(this.indiceHijoIzq(i)));
        inOrder.add(datos.get(i));
        inOrder.addAll(this.inOrdenLogica(this.indiceHijoDer(i)));
        return inOrder;
    }

    public ArrayList<E> nodosHoja(){
        ArrayList<E> hojas = new ArrayList<>();
        //Matematicamente los nodos hojas estarán desde la mitad del árbol
        for (int i = n/2; i < n; i++) {
            hojas.add(datos.get(i));
        }
        return hojas;
    }

}
