import java.util.ArrayList;

public class Bus {
    private String patente;
    private String marca;
    private String modelo;
    private int nroAsientos;
    private ArrayList<Viaje> viajes = new ArrayList<>();



    public Bus(String patente, int nroAsientos){
        this.patente = patente;
        this.nroAsientos = nroAsientos;
    }

    public String getPatente(){
        return patente;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public int getNroAsientos(){
        return nroAsientos;
    }

    public void addViaje(Viaje viaje){
        //ARREGLO EL NOMBRE REALIZA
        viajes.add(viaje);
    }

}
