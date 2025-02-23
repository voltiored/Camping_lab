package prog2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import prog2.vista.ExcepcioReserva;

public class LlistaReserves implements InLlistaReserves {
    private List<Reserva> reserves;

    public LlistaReserves() {
        this.reserves = new ArrayList<>();
    }

    private boolean allotjamentDisponible(Allotjament allotjament, LocalDate dataEntrada, LocalDate dataSortida) {

        for (Reserva reserva : reserves) {
            if (reserva.getAllotjament().equals(allotjament)) {
                if (!(dataSortida.isBefore(reserva.getDataEntrada()) || dataEntrada.isAfter(reserva.getDataSortida()))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isEstadaMinima(Allotjament allotjament, LocalDate dataEntrada, LocalDate dataSortida) {
        if (allotjament == null) {
            throw new IllegalArgumentException("L'allotjament no pot ser null.");
        }
        int dies = (int) java.time.temporal.ChronoUnit.DAYS.between(dataEntrada, dataSortida);
        InAllotjament.Temp temporada = Camping.getTemporada(dataEntrada);
        long estadaMinima = allotjament.getEstadaMinima(temporada);
        return dies >= estadaMinima;
    }

    @Override
    public void afegirReserva(Allotjament allotjament, Client client, LocalDate dataEntrada, LocalDate dataSortida) throws ExcepcioReserva {
        if (allotjament == null) {
            throw new ExcepcioReserva("L'allotjament no es troba registrat.");
        }
        if (!allotjamentDisponible(allotjament, dataEntrada, dataSortida)) {
            throw new ExcepcioReserva("L’allotjament amb identificador " + allotjament.getId() + " no està disponible en la data demanada "
                    + dataEntrada + " pel client " + client.getNom() + " amb DNI: " + client.getDNI() + ".");
        }
        if (!isEstadaMinima(allotjament, dataEntrada, dataSortida)) {
            throw new ExcepcioReserva("Les dates sol·licitades pel client " + client.getNom() + " amb DNI: " + client.getDNI()
                    + " no compleixen l'estada mínima per l'allotjament amb identificador " + allotjament.getId() + ".");
        }
        reserves.add(new Reserva(client, allotjament, dataEntrada, dataSortida));
    }

    @Override
    public int getNumReserves() {
        return reserves.size();
    }
    public List<Reserva> getReserves() {
        return new ArrayList<>(reserves);
    }

    public void cancelReserva(Reserva reserva) throws ExcepcioReserva {
        if (!reserves.contains(reserva)) {
            throw new ExcepcioReserva("La reserva no existeix i no es pot cancel·lar.");
        }
        reserves.remove(reserva);
    }
}
