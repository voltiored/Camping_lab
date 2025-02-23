
package prog2.model;

import java.time.LocalDate;
import prog2.vista.ExcepcioReserva;

/**
 * Interfície per definir la forma de la llista de reserves
 * @author lauraigual
 */
public interface InLlistaReserves {
    
    /**
     * Comprova que l'allotjament estigui disponible pel dia i hora indicat.
     * En cas afirmatiu, crea la reserva i l’afegeix a la llista de reserves del càmping.
     * En cas negatiu, llança una excepció de tipus ExceptionReserva amb el missatge d'error.
     * 
     * @param allotjament
     * @param client
     * @param dataEntrada
     * @param dataSortida
     * @throws ExcepcioReserva 
     */
     void afegirReserva(Allotjament allotjament, Client client, LocalDate dataEntrada, LocalDate dataSortida) throws ExcepcioReserva;

    /**
     * Retorna el número de reserves de la llista.
     * @return el número de reserves.
     */
     int getNumReserves();

}
