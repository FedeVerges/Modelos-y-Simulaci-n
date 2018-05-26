package eventos;

import fel.Selector;

public class Item {

    private int numero = 0;
    private float tiempoArribo = 0;
    private float tiempoDuracionServicio = 0;
    private static float tiempoEsperaColaLeve = 0;
    private static float tiempoEsperaColaMedio = 0;
    private static float tiempoEsperaColaGrave = 0;
    private static float tiempoTransito = 0;
    private static int cantidadLeves = 0;
    private static int cantidadMedios = 0;
    private static int cantidadGraves = 0;
    private static int cantidadItems = 0;
    private int medico;

    public void setMedico(int medico) {
        this.medico = medico;

    }

    public static void Init() {
        tiempoTransito = 0;
        cantidadLeves = 0;
        cantidadMedios = 0;
        cantidadGraves = 0;
        cantidadItems = -1;
        tiempoEsperaColaLeve = 0;
        tiempoEsperaColaMedio = 0 ;
        tiempoEsperaColaGrave = 0;

    }

    public int getMedico() {
        return medico;
    }

    public Item(int numero, float tiempoArribo, int transito) {
        this.numero = numero;
        this.tiempoArribo = tiempoArribo;
        this.tiempoDuracionServicio = 0;

    }

    public static int getCantidadLeves() {
        return cantidadLeves;
    }

    public static int getCantidadMedios() {
        return cantidadMedios;
    }

    public static int getCantidadGraves() {
        return cantidadGraves;
    }

    public static void setCantidadLeves(int cantidadLeves) {
        Item.cantidadLeves = cantidadLeves;
    }

    public static void setCantidadMedios(int cantidadMedios) {
        Item.cantidadMedios = cantidadMedios;
    }

    public static void setCantidadGraves(int cantidadGraves) {
        Item.cantidadGraves = cantidadGraves;
    }

    /**
     * @return Returns the cantidadItems.
     */
    public static int getCantidadItems() {
        return cantidadItems;
    }

    /**
     * @param cantidadItems The cantidadItems to set.
     */
    public static void setCantidadItems(int cantidadItems) {
        Item.cantidadItems = cantidadItems;
    }

    /**
     * @return Returns the tiempoEsperaCola.
     */
    public static float getTiempoEsperaColaLeve() {
        return tiempoEsperaColaLeve;
    }

    public static float getTiempoEsperaColaMedio() {
        return tiempoEsperaColaMedio;
    }

    public static float getTiempoEsperaColaGrave() {
        return tiempoEsperaColaGrave;
    }
    
    

    /**
     * @param tiempoEsperaCola The tiempoEsperaCola to set.
     */
    public static void setTiempoEsperaColaLeve(float tiempoActual, float tiempoDuracionServicio, float tiempoArribo) {
        Item.tiempoEsperaColaLeve += tiempoActual - (tiempoArribo + tiempoDuracionServicio);
    }

    public static void setTiempoEsperaColaMedio(float tiempoActual, float tiempoDuracionServicio, float tiempoArribo) {
        Item.tiempoEsperaColaMedio += tiempoActual - (tiempoArribo + tiempoDuracionServicio);
    }

    public static void setTiempoEsperaColaGrave(float tiempoActual, float tiempoDuracionServicio, float tiempoArribo) {
        Item.tiempoEsperaColaGrave += tiempoActual - (tiempoArribo + tiempoDuracionServicio);
    }
    
    

    /**
     * @return Returns the tiempoTransito.
     */
    public static float getTiempoTransito() {
        return tiempoTransito;
    }

    /**
     * @param tiempoTransito The tiempoTransito to set.
     */
    public static void setTiempoTransito(float tiempoActual, float tiempoArribo) {
        //calcular el tiempo de transito
        tiempoTransito += (tiempoActual - tiempoArribo);
    }

    /**
     * @return Returns the numero.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero The numero to set.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return Returns the tiempoArribo.
     */
    public float getTiempoArribo() {
        return tiempoArribo;
    }

    /**
     * @param tiempoArribo The tiempoArribo to set.
     */
    public void setTiempoArribo(float tiempoArribo) {
        this.tiempoArribo = tiempoArribo;

    }

    /**
     * @return Returns the tiempoDuracionServicio.
     */
    public float getTiempoDuracionServicio() {
        return tiempoDuracionServicio;
    }

    /**
     * @param tiempoDuracionServicio The tiempoDuracionServicio to set.
     */
    public void setTiempoDuracionServicio(float tiempoDuracionServicio) {
        this.tiempoDuracionServicio = tiempoDuracionServicio;
    }

}
