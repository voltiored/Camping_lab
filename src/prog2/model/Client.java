package prog2.model;

import prog2.vista.ExcepcioReserva;

public class Client {
    private String nom;
    private String dni;

    public Client(String nom, String dni) throws ExcepcioReserva {
        if (dni == null || dni.length() != 9) {
            throw new ExcepcioReserva("El DNI ha de tenir 9 caràcters.");
        }
        this.nom = nom;
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDNI() {
        return dni;
    }

    public void setDni(String dni) throws ExcepcioReserva {
        if (dni == null || dni.length() != 9) {
            throw new ExcepcioReserva("El DNI ha de tenir 9 caràcters.");
        }
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Client{nom='" + nom + "', dni='" + dni + "'}";
    }
}
