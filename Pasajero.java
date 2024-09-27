import java.util.ArrayList;

// Juliana Defaur
public class Pasajero extends Persona{
    private Nombre nomContacto;
    private String fonoContacto;
    // private ArrayList<Pasaje> pasajes; Declaración del Arraylist PASAJE

    //AGREGO NOMBRE "NOMBRE DEL PASAJERO NO DEL CONTACTO"
    public Pasajero(IdPersona idPersona,Nombre nombre, Nombre nomContacto, String fonoContacto) {
        super(idPersona,nombre);
        this.nomContacto = nomContacto;
        this.fonoContacto = fonoContacto;
        //  pasajes = new ArrayList<Pasaje>();
    }

    public Nombre getNomContacto() {
        return nomContacto;
    }

    public void setNomContacto(Nombre nomContacto) {
        this.nomContacto = nomContacto;
    }

    public String getFonoContacto() {
        return fonoContacto;
    }

    public void setFonoContacto(String fonoContacto) {
        this.fonoContacto = fonoContacto;
    }

}
