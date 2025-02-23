
package prog2.model;

import java.time.LocalDate;
import java.util.ArrayList;

import prog2.vista.ExcepcioReserva;

/**
 * Interfície per definir la forma del Càmping
 * @author lauraigual
 */
public interface InCamping {

    /**
     * Retorna el nom del càmping.
     * @return el nom del càmping.
     */
    String getNom();

    /**
     * Retorna la llista de reserves del camping.
     * @return
     */
    LlistaReserves getLlistaReserves();

    /**
     * Retorna la llista d'allotjaments del camping.
     */
    ArrayList<Allotjament> getLlistaAllotjaments();

    /**
     * Retorna la llista de clients del camping.
     * @return
     */
    ArrayList<Client> getLlistaClients();

    /**
     * Retorna el número total d'allotjaments del càmping.
     * @return el número total d'allotjaments.
     */
    int getNumAllotjaments();

    /**
     * Retorna el número total de reserves del càmping.
     * @return el número total de reserves.
     */
    int getNumReserves();

    /**
     * Retorna el número total de clients del càmping.
     * @return el número total de clients.
     */
    int getNumClients();



    /**
     * Crea un nou objecte de tipus Client i l'afegeix a la llista de clients.
     * @param nom_ el nom del nou client.
     * @param dni_ el DNI del nou client.
     * @throws ExcepcioReserva si el dni no és de 9 xifres (es comprova en la classe Client)
     */
    void afegirClient(String nom_, String dni_) throws ExcepcioReserva;


    /**
     * Afegeix una nova parcel·la a la llista d'allotjaments.
     * @param nom_ el nom de la parcela.
     * @param idAllotjament_ l'identificador únic de l'allotjament.
     * @param metres la mida de la parcela.
     * @param connexioElectrica true si disposa de connexió elèctrica, false altrament.
     */
    void afegirParcela(String nom_, String idAllotjament_, float metres, boolean connexioElectrica);

    /**
     * Afegeix un nou bungalow a la llista d'allotjaments.
     * @param nom_ el nom del bungalow.
     * @param idAllotjament_ l'identificador únic de l'allotjament.
     * @param mida la mida del bungalow.
     * @param habitacions el nombre d'habitacions del bungalow.
     * @param placesPersones el nombre màxim de places per a persones.
     * @param placesParquing el nombre de places de pàrquing disponibles.
     * @param terrassa true si disposa de terrassa, false altrament.
     * @param tv true si disposa de televisió, false altrament.
     * @param aireFred true si disposa d'aire condicionat, false altrament.
     */
    void afegirBungalow(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones,
                        int placesParquing, boolean terrassa, boolean tv, boolean aireFred);

    /**
     * Afegeix un bungalow premium a la llista d'allotjaments.
     * @param serveisExtra true si ofereix serveis extra.
     * @param codiWifi el codi de la xarxa Wi-Fi.
     * (Altres paràmetres igual que `afegirBungalow`)
     */
    void afegirBungalowPremium(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones,
                               int placesParquing, boolean terrassa, boolean tv, boolean aireFred,
                               boolean serveisExtra, String codiWifi);

    /**
     * Afegeix una casa glamping a la llista d'allotjaments.
     * @param material el material del que està fet.
     * @param casaMascota true si accepta mascotes.
     * (Altres paràmetres igual que `afegirBungalow`)
     */
    void afegirGlamping(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones,
                        String material, boolean casaMascota);


    /**
     *  Afegeix una mobil-home a la llista d'allotjaments.
     * @param terrassaBarbacoa true si disposa de terrassa amb barbacoa.
     * (Altres paràmetres igual que `afegirBungalow`)
     */
    void afegirMobilHome(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones,
                         boolean terrassaBarbacoa);


    /**
     * Afegeix una nova reserva al càmping. Per fer-ho fa el següent: cerca el soci que vol fer la reserva i el servei que es vol reservar amb la informació necessària rebuda com a paràmetres i invoca al mètode afegirReserva de la classe LlistaReserves que crearà la reserva, si es pot, i la afegirà a la llista de reserves.
     * @param id_ l'identificador de l'allotjament.
     * @param dni_ el DNI del client que fa la reserva.
     * @param dataEntrada la data d'entrada.
     * @param dataSortida la data de sortida.
     * @throws ExcepcioReserva si no es pot realitzar la reserva.
     */
    void afegirReserva(String id_, String dni_, LocalDate dataEntrada, LocalDate dataSortida) throws ExcepcioReserva;


    /**
     * Calcula la mida total de totes les parceles del càmping.
     * @return  la suma de les mides de totes les parceles.
     */
    float calculMidaTotalParceles();

    
    /**
     * Recorre la llista de serveis comprovant el correcte funcionament de cadascun d'ells per contar el número de serveis que estan operatius.
     * @return el nombre de serveis operatius.
     */
    int calculAllotjamentsOperatius();

    /**
     * Cerca i retorna l'allotjament amb estada mínima de la temporada baixa més curta.
     * @return l'allotjament amb estada mínima de la temporada baixa més curta.
     */
    Allotjament getAllotjamentEstadaMesCurta();



}

