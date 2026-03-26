import java.util.Comparator;

public class ComparadorFrecuencia implements Comparator<Contacto>{

    public int compare(Contacto a, Contacto b){
        if (a.getFrecuencia()==b.getFrecuencia()) return 0;//Esta funcion la utilizo para comparar alfabeticamente los nombres
        if (a.getFrecuencia()>b.getFrecuencia()) return 1;
        return -1;
    }
}