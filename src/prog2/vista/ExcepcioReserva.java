package prog2.vista;

/**
 * Excepció personalitzada per a errors en la gestió de reserves.
 *
 * @author lauraigual
 */
public class ExcepcioReserva extends Exception {

    public ExcepcioReserva(String message) {
        super(message);
    }

    public ExcepcioReserva(String message, Throwable cause) {
        super(message, cause);
    }
}

