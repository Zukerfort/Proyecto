import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Viaje {
    private LocalDate fecha;
    private LocalTime hora;
    private int precio;
    private Bus bus;
    private ArrayList<Pasaje> pasajes;


    public Viaje(LocalDate fecha, LocalTime hora, int precio, Bus bus) {
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.bus = bus;
        bus.addViaje(this);
        this.pasajes = new ArrayList<>();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {

        return hora;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Bus getBus() {
        return bus;
    }
    //SET BUS NO DEBE EXISTIR

    //ARREGLO GETASIENTOS
    public String[][] getAsientos() {

        int cantAsientos = bus.getNroAsientos();
        int filas = (int) Math.ceil((double) cantAsientos / 4);
        String[][] asientos = new String[filas][4];

        int contador = 0;
        for (int j = 0; j < filas; j++) {
            for (int i = 0; i < 4; i++) {
                contador++;
                if (contador <= cantAsientos) {
                    asientos[j][i] = String.valueOf(contador);
                    if (pasajes != null) {
                        for (Pasaje pasaje : pasajes) {
                            if (pasaje != null && pasaje.getAsiento() == contador) {
                                asientos[j][i] = "*";
                                break;
                            }
                        }
                    }
                } else {
                    asientos[j][i] = "";
                }
            }
        }
        return asientos;
    }

    //SET ASIENTOS NO DEBE EXISTIR

    //GET PASAJE NO DEBE EXISTIR

    //SET PASAJE NO DEBE EXISTIR

    //CREO ADDPASAJE

    public void addPasaje(Pasaje pasaje) {

        this.pasajes.add(pasaje);
    }

    //CREO GETLISTAPASAJEROS

//    public String[][] getListaPasajeros() {
//        String [][] pasajeros = new String[pasajes.size()][5];
//        int fila = 0;
//        for(Pasaje pasaje : pasajes){
//            pasajeros[fila][0] = String.valueOf(pasaje.getAsiento());
//            pasajeros[fila][1] = String.valueOf(pasaje.getPasajero().getIdPersona());
//            pasajeros[fila][2] = String.valueOf(pasaje.getPasajero().getNombreCompleto());
//            pasajeros[fila][3] = String.valueOf(pasaje.getPasajero().getNomContacto());
//            pasajeros[fila][4] = pasaje.getPasajero().getFonoContacto();
//            fila++;
//        }
//        return pasajeros;
//    }

        public String[][] getListaPasajeros() {
            if (pasajes == null || pasajes.isEmpty()) {
                return new String[0][0];
            }
            String [][] pasajeros = new String[pasajes.size()][5];
            int fila = 0;
            for(Pasaje pasaje : pasajes) {
                Pasajero pasajero = pasaje.getPasajero();

                pasajeros[fila][0] = String.valueOf(pasaje.getAsiento());
                pasajeros[fila][1] = String.valueOf(pasajero.getIdPersona());
                pasajeros[fila][2] = String.valueOf(pasaje.getPasajero().getNombreCompleto());
                pasajeros[fila][3] = String.valueOf(pasaje.getPasajero().getNomContacto());
                pasajeros[fila][4] = pasajero.getFonoContacto();
                fila++;
            }
            return pasajeros;
        }


    public boolean existeDisponibilidad() {
        return getNroAsientosDisponibles() > 0;
    }

    //ELIMINE SET EXISTEDISPONIBILIDAD

    public int getNroAsientosDisponibles() {
        int cantAsientos = bus.getNroAsientos();
        if(pasajes != null){
            return (cantAsientos - pasajes.size());
        }
        return cantAsientos;

    }

    //NO EXISTE SET NUMERO ASIENTOS
}
