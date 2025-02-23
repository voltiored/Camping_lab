package prog2.model;
import prog2.vista.ExcepcioReserva;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Client client;
    private Allotjament allotjament;
    private LocalDate dataEntrada;
    private LocalDate dataSortida;

    public Reserva(Client client, Allotjament allotjament, LocalDate dataEntrada, LocalDate dataSortida) {
        this.client = client;
        this.allotjament = allotjament;
        this.dataEntrada = dataEntrada;
        this.dataSortida = dataSortida;

        // Calcular la duració de l'estada
        long diesEstada = ChronoUnit.DAYS.between(dataEntrada, dataSortida);

        // Comprovar si l'estada compleix el mínim requerit
        int estadaMinima = getEstadaMinima(allotjament, dataEntrada);
        if (diesEstada < estadaMinima) {
            throw new IllegalArgumentException("L'estada és inferior a la mínima per a aquest allotjament.");
        }
    }

    private int getEstadaMinima(Allotjament allotjament, LocalDate dataEntrada) {
        MonthDay entradaMD = MonthDay.from(dataEntrada);
        MonthDay iniciBaixa = MonthDay.of(9, 21);
        MonthDay fiBaixa = MonthDay.of(3, 20);

        boolean temporadaBaixa = entradaMD.isAfter(iniciBaixa) || entradaMD.isBefore(fiBaixa);
        InAllotjament.Temp temp = temporadaBaixa ? InAllotjament.Temp.BAIXA : InAllotjament.Temp.ALTA;
        return (int) allotjament.getEstadaMinima(temp);
    }

    public Allotjament getAllotjament() {
        return allotjament;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public LocalDate getDataSortida() {
        return dataSortida;
    }
}