package fel;

import eventos.Evento;
import eventos.Item;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Fel {

    private static Fel fel;
    private LinkedList lista;

    private Fel() {
        lista = new LinkedList();
    }

    public static Fel getFel() {
        if (fel == null) {
            fel = new Fel();
        }
        return (fel);
    }

    public void insertarFel(Evento e) {
        // lista ordenada de mayor a menor con respecto al tiempo de ocurrencia del evento.
        lista.add(e);
        Collections.sort(lista, new Comparator<Evento>() {
            @Override
            public int compare(Evento o1, Evento o2) {
                if (o1.getTiempo() < o2.getTiempo()) {
                    return -1;
                } else {
                    return 1;
                }
            }

        });
        

    }

    public Evento suprimirFel() {
        return (Evento) lista.remove();

    }

    public void mostrarFel() {
        Iterator i = lista.iterator();
        Evento e;
       // System.out.println("\nTIEMPO ESPERA EN COLA "+Item.getTiempoEsperaCola());
        while (i.hasNext()) {
            e = (Evento) i.next();

            System.out.println(" Tipo de Evento:" + e.getTipo() + " Tiempo:" + e.getTiempo() + " Item numero: " + e.getItem().getNumero());

        }

    }
    public void LimpiarFel(){
        lista.clear();
    }

    /**
     * @return Returns the lista.
     */
    public LinkedList getLista() {
        return lista;
    }

}
