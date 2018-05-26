package eventos;

import fel.Fel;
import fel.GeneradorTiempos;
import fel.Queue;
import fel.Selector;
import hospital.Servidor;
import java.util.ArrayList;

public class EventoSalida extends Evento {

    public EventoSalida(float tiempo, Item item, int transito) {
        super(1, tiempo, item, transito);
    }

    @Override
    public void planificarEvento(ArrayList <Selector> selector) {
        Fel fel = Fel.getFel();
        float TiempoArribo = 0;
        Selector medico;
        medico = selector.get(this.getItem().getMedico());
        
        if (medico.getColaAsociada().HayCola() == true) { // Hay cola.
            // planificar proxima salida // 
            
            float nuevoTiempoS;
            nuevoTiempoS = (float) GeneradorTiempos.getTiempoDuracionServicio(this.getTransito());
           // System.out.println(" 0 "+ selector.get(0).getColaAsociada().getCantidadItems());
           //System.out.println(" 1 "+ selector.get(1).getColaAsociada().getCantidadItems());
            
            EventoSalida NuevaSalida = new EventoSalida(nuevoTiempoS + super.getTiempo(), medico.getColaAsociada().suprimirCola(),this.getTransito());

           // System.out.println("\t Fuera de cola!! ");
           // System.out.println(" 0 "+ selector.get(0).getColaAsociada().getCantidadItems());
            fel.insertarFel(NuevaSalida);

        } else {
           // System.out.println("no hay cola ");
            medico = selector.get(this.getItem().getMedico());
            medico.getServidor().setEstado(false);
            medico.getServidor().setTiempoInicioOcio(this.getTiempo());

        }

        // Coleccion de Estadisticas //
        
        switch(this.getTransito()){
            case 0:
                Item.setTiempoEsperaColaLeve(this.getTiempo(), this.getItem().getTiempoDuracionServicio(), this.getItem().getTiempoArribo());
                break;
            case 1:
                Item.setTiempoEsperaColaMedio(this.getTiempo(), this.getItem().getTiempoDuracionServicio(), this.getItem().getTiempoArribo());
                break;
            case 2:
                Item.setTiempoEsperaColaGrave(this.getTiempo(), this.getItem().getTiempoDuracionServicio(), this.getItem().getTiempoArribo());
                break;
        }
                Item.setTiempoTransito(this.getTiempo(), this.getItem().getTiempoArribo());
        

    }
}
