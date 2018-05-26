package fel;

import eventos.Item;
import java.util.LinkedList;

public class Queue {

    private int cantidadItems;
    private LinkedList cola;
    private int id = 0;

    public void setId(int id) {
        this.id = id;
    }

    public Queue() {
        cola = new LinkedList();
        this.id++;
        cantidadItems = 0;
    }

    public void insertarCola(Item item) {
        cola.addLast(item);
        cantidadItems++;
        
    }

    public Item suprimirCola() {
        cantidadItems--;
        return (Item) cola.removeFirst();
    }

    public boolean HayCola() {
        return this.cantidadItems > 0;

    }

    public int getCantidadItems() {
        return cantidadItems;
    }

    public LinkedList getCola() {
        return cola;
    }

    public int getId() {
        return id;
    }
    
    
}
