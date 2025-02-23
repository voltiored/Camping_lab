package prog2.model;

public abstract class Allotjament implements InAllotjament {
    private String nom;
    private String id;
    private static String ubicacio;
    private long estadaMinimaALTA;
    private long estadaMinimaBAIXA;

    public Allotjament(String nom, String id, long estadaMinimaALTA, long estadaMinimaBAIXA, String ubicacio) {
        this.nom = nom;
        this.id = id;
        this.ubicacio = ubicacio;
        this.estadaMinimaALTA = estadaMinimaALTA;
        this.estadaMinimaBAIXA = estadaMinimaBAIXA;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public long getEstadaMinima(Temp temp) {
        return temp == Temp.ALTA ? estadaMinimaALTA : estadaMinimaBAIXA;
    }

    public int getEstadaMinimaBaixa() {
        return (int) estadaMinimaBAIXA;
    }

    @Override
    public void setEstadaMinima(long estadaMinimaALTA_, long estadaMinimaBAIXA_) {
        this.estadaMinimaALTA = estadaMinimaALTA_;
        this.estadaMinimaBAIXA = estadaMinimaBAIXA_;
    }

    @Override
    public abstract boolean correcteFuncionament();

    @Override
    public String toString() {
        return "Nom=" + nom + ", Id=" + id + ", estada mínima en temp ALTA: " + estadaMinimaALTA +
                ", estada mínima en temp BAIXA: " + estadaMinimaBAIXA;
    }
    // Classe Parcela
    public static class Parcela extends Allotjament {
        private float metres;
        private boolean connexioElectrica;

        public Parcela(String nom, String id, float metres, boolean connexioElectrica) {
            super(nom, id, 4, 2, ubicacio); // Estada mínima: ALTA = 4 dies, BAIXA = 2 dies
            this.metres = metres;
            this.connexioElectrica = connexioElectrica;
        }

        @Override
        public boolean correcteFuncionament() {
            return connexioElectrica; // Suposem que està operativa si té connexió elèctrica.
        }

        @Override
        public String toString() {
            return super.toString() + ", Parcela{mida=" + metres + ", connexioElectrica=" + connexioElectrica + "}";
        }
        public float getMetres() {
            return metres;
        }
    }

    // Classe Bungalow
    public static class Bungalow extends Allotjament {
        private final String mida;
        private int habitacions;
        private int placesPersones;
        private int placesParquing;
        private boolean terrassa;
        private boolean tv;
        private boolean aireFred;

        public Bungalow(String nom, String id, String mida, int habitacions, int placesPersones,
                        int placesParquing, boolean terrassa, boolean tv, boolean aireFred) {
            super(nom, id, 7, 4, ubicacio); // Estada mínima: ALTA = 7 dies, BAIXA = 4 dies
            this.mida = mida;
            this.habitacions = habitacions;
            this.placesPersones = placesPersones;
            this.placesParquing = placesParquing;
            this.terrassa = terrassa;
            this.tv = tv;
            this.aireFred = aireFred;
            id = "AJsh";
        }

        @Override
        public boolean correcteFuncionament() {

            return aireFred; // Suposem que sempre està operatiu
        }

        @Override
        public String toString() {
            return super.toString() + ", Bungalow{mida=" + mida + ", habitacions=" + habitacions + ", placesPersones=" + placesPersones +
                    ", placesParquing=" + placesParquing + ", terrassa=" + terrassa + ", tv=" + tv + ", aireFred=" + aireFred + "}";
        }
    }

    // Classe BungalowPremium
    public static class BungalowPremium extends Bungalow {
        private boolean serveisExtra;
        private String codiWifi;

        public BungalowPremium(String nom, String id, String mida, int habitacions, int placesPersones,
                               int placesParquing, boolean terrassa, boolean tv, boolean aireFred,
                               boolean serveisExtra, String codiWifi) {
            super(nom, id, mida, habitacions, placesPersones, placesParquing, terrassa, tv, aireFred);
            this.serveisExtra = serveisExtra;
            this.codiWifi = codiWifi;
        }

        @Override
        public String toString() {
            return super.toString() + ", BungalowPremium{serveisExtra=" + serveisExtra + ", codiWifi='" + codiWifi + "'}";
        }
        @Override
        public boolean correcteFuncionament() {
            return codiWifi != null && codiWifi.length() >= 8 && codiWifi.length() <= 16;
        }

    }

    // Classe Glamping
    public static class Glamping extends Allotjament {
        private String material;
        private boolean casaMascota;

        public Glamping(String nom, String id, String mida, int habitacions, int placesPersones,
                        String material, boolean casaMascota) {
            super(nom, id, 5, 3, ubicacio); // Estada mínima: ALTA = 6 dies, BAIXA = 4 dies
            this.material = material;
            this.casaMascota = casaMascota;
        }

        @Override
        public boolean correcteFuncionament() {
            return casaMascota;
        }

        @Override
        public String toString() {
            return super.toString() + ", Glamping{material='" + material + "', casaMascota=" + casaMascota + "}";
        }
    }

    // Classe MobilHome
    public static class MobilHome extends Allotjament {
        private boolean terrassaBarbacoa;

        public MobilHome(String nom, String id, String mida, int habitacions, int placesPersones,
                         boolean terrassaBarbacoa) {
            super(nom, id, 5, 3, ubicacio); // Estada mínima: ALTA = 4 dies, BAIXA = 3 dies
            this.terrassaBarbacoa = terrassaBarbacoa;
        }

        @Override
        public boolean correcteFuncionament() {
            return terrassaBarbacoa;
        }

        @Override
        public String toString() {
            return super.toString() + ", MobilHome{terrassaBarbacoa=" + terrassaBarbacoa + "}";
        }
    }

}
