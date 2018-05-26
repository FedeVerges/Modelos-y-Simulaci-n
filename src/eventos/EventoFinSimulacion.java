package eventos;

import fel.Queue;
import fel.Selector;
import hospital.Servidor;
import java.util.ArrayList;

public class EventoFinSimulacion extends Evento {

    public EventoFinSimulacion(float tiempo) {
        super(2, tiempo, new Item(-1, tiempo,-1),-1);
    }

    @Override
    public void planificarEvento(ArrayList <Selector> selector) {
        
        //System.out.println("\n\n ¡¡¡TERMINAMOS LA SIMULACION !!! "); 
        
        
        
    }

}
