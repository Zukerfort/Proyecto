
import java.util.Objects;
public class Pasaporte implements IdPersona {
    private String numero, nacionalidad;

    public Pasaporte(String numero, String nacionalidad) {
        this.numero = numero;
        this.nacionalidad = nacionalidad;
    }

    public String getNumero() {
        return numero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public static Pasaporte of(String numero, String nacionalidad) {

        if (Objects.requireNonNull(numero).matches("\\d+") && Objects.requireNonNull(nacionalidad).matches("[a-zA-Z]+")) {
            return new Pasaporte(numero, nacionalidad);
        }
        System.out.println("Pasaporte no válido. Número o nacionalidad incorrectos.");
        return null;
    }
    @Override
    public String toString() {
        return numero + " " + nacionalidad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Pasaporte p) {
            return numero.equals(p.getNumero()) && nacionalidad.equals(p.getNacionalidad());

        }
        return false;
    }

}