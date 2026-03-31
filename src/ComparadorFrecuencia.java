import java.util.Comparator;

/**
 * Clase comparador hecha en clase para comparar los contactos de acuerdo a su frecuencia
 */
public class ComparadorFrecuencia implements Comparator<Contacto>{

    /**
     * Sobrescritura del metodo compare
     * @param a contacto 1
     * @param b contacto 2
     * @return 0 si es igual, 1 si es mayor, -1 si es menor
     */
    public int compare(Contacto a, Contacto b){
        if (a.getFrecuencia()==b.getFrecuencia()) return 0;
        if (a.getFrecuencia()>b.getFrecuencia()) return 1;
        return -1;
    }
}