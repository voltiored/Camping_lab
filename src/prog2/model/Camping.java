package prog2.model;

import prog2.vista.ExcepcioReserva;

import java.time.LocalDate;
import java.util.ArrayList;

public class Camping implements InCamping {
    private String nom;
    private ArrayList<Allotjament> allotjaments;
    private ArrayList<Client> clients;
    private LlistaReserves reserves;

    public Camping(String nom) {
        this.nom = nom;
        this.allotjaments = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.reserves = new LlistaReserves();
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public ArrayList<Allotjament> getLlistaAllotjaments() {
        return allotjaments;
    }

    @Override
    public ArrayList<Client> getLlistaClients() {
        return clients;
    }

    @Override
    public LlistaReserves getLlistaReserves() {
        return reserves;
    }

    @Override
    public int getNumAllotjaments() {
        return allotjaments.size();
    }

    @Override
    public int getNumReserves() {
        return reserves.getNumReserves();
    }

    @Override
    public int getNumClients() {
        return clients.size();
    }

    @Override
    public void afegirClient(String nom_, String dni_) throws ExcepcioReserva {
        clients.add(new Client(nom_, dni_));
    }

    @Override
    public void afegirParcela(String nom_, String idAllotjament_, float metres, boolean connexioElectrica) {
        allotjaments.add(new Allotjament.Parcela(nom_, idAllotjament_, metres, connexioElectrica));
    }

    @Override
    public void afegirBungalow(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones,
                               int placesParquing, boolean terrassa, boolean tv, boolean aireFred) {
        allotjaments.add(new Allotjament.Bungalow(nom_, idAllotjament_, mida, habitacions, placesPersones, placesParquing, terrassa, tv, aireFred));
    }

    @Override
    public void afegirBungalowPremium(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones,
                                      int placesParquing, boolean terrassa, boolean tv, boolean aireFred,
                                      boolean serveisExtra, String codiWifi) {
        allotjaments.add(new Allotjament.BungalowPremium(nom_, idAllotjament_, mida, habitacions, placesPersones, placesParquing, terrassa, tv, aireFred, serveisExtra, codiWifi));
    }

    @Override
    public void afegirGlamping(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones,
                               String material, boolean casaMascota) {
        allotjaments.add(new Allotjament.Glamping(nom_, idAllotjament_, mida, habitacions, placesPersones, material, casaMascota));
    }

    @Override
    public void afegirMobilHome(String nom_, String idAllotjament_, String mida, int habitacions, int placesPersones,
                                boolean terrassaBarbacoa) {
        allotjaments.add(new Allotjament.MobilHome(nom_, idAllotjament_, mida, habitacions, placesPersones, terrassaBarbacoa));
    }

    @Override
    public void afegirReserva(String id_, String dni_, LocalDate dataEntrada, LocalDate dataSortida) throws ExcepcioReserva {

        Allotjament allotjament = buscarAllotjament(id_);
        Client client = buscarClient(dni_);
        reserves.afegirReserva(allotjament, client, dataEntrada, dataSortida);
    }

    private Allotjament buscarAllotjament(String id) {

        for (Allotjament a : allotjaments) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    private Client buscarClient(String dni) {
        for (Client c : clients) {
            if (c.getDNI().equals(dni)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public float calculMidaTotalParceles() {
        float total = 0;
        for (Allotjament a : allotjaments) {
            if (a instanceof Allotjament.Parcela) {  // Comprova si és una Parcela
                total += ((Allotjament.Parcela) a).getMetres();
            }
        }
        return total;
    }

    @Override
    public int calculAllotjamentsOperatius() {
        int operatius = 0;
        for (Allotjament a : allotjaments) {
            if (a.correcteFuncionament()) {
                operatius++;
            }
        }
        return operatius;
    }

    @Override
    public Allotjament getAllotjamentEstadaMesCurta() {
        Allotjament minEstada = null;
        int minDies = Integer.MAX_VALUE;
        for (Allotjament a : allotjaments) {
            int diesMinims = a.getEstadaMinimaBaixa();
            if (diesMinims < minDies) {
                minDies = diesMinims;
                minEstada = a;
            }
        }
        return minEstada;
    }

    public static InAllotjament.Temp getTemporada(LocalDate data) {
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();
        if ((mes > 3 && mes < 9) || (mes == 3 && dia >= 21) || (mes == 9 && dia < 21)) {
            return InAllotjament.Temp.ALTA;
        } else {
            return InAllotjament.Temp.BAIXA;
        }
    }
}
