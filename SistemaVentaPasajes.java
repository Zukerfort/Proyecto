import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//Benjamín Matamala
public class SistemaVentaPasajes {
    private ArrayList<Bus> buses = new ArrayList<>();
    private ArrayList<Pasajero> pasajeros = new ArrayList<>();
    private ArrayList<Viaje> viajes = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public boolean createCliente(IdPersona id, Nombre nom, String fono, String email){
        if (findCliente(id) == null){
            Cliente a = new Cliente(id, nom, email);
            clientes.add(a);
            a.setTelefono(fono);
            return true;
        }
        return false;
    }

    public boolean createPasajero(IdPersona id, Nombre nom, String fono, Nombre nomContacto, String fonoContacto){
        if (findPasajero(id) == null){
            Pasajero b = new Pasajero(id, nom,nomContacto, fonoContacto);
            pasajeros.add(b);
            b.setTelefono(fono);
            return true;
        }
        return false;
    }

    public boolean createBus(String patente, String marca, String modelo, int nroAsientos){
        if (findBus(patente) == null){
            Bus c = new Bus(patente, nroAsientos);
            c.setMarca(marca);
            c.setModelo(modelo);

            buses.add(c);
            return true;
        }else{
            return false;
        }
    }

    public boolean createViaje(LocalDate fecha, LocalTime hora, int precio, String patBus){
        if(findViaje(fecha, String.valueOf(hora), patBus) == null){
            Bus bus = findBus(patBus);
            if (bus != null){
                Viaje d = new Viaje(fecha, hora, precio, bus);
                viajes.add(d);
                return true;
            }
        }
        return false;
    }

    public boolean iniciaVenta(String idDoc, TipoDocumento tipo, LocalDate fechaVenta, IdPersona idCliente){
        Cliente client = findCliente(idCliente);
        if(client != null && findVenta(idDoc,tipo) == null){
            Venta e = new Venta(idDoc, tipo, fechaVenta, client);
            ventas.add(e);
            return true;
        }else {
            return false;
        }
    }

    public String[][] getHorariosDisponibles(LocalDate fechaViaje){

        int viajesDisponibles = 0;

        for (Viaje v : viajes) {
            if(v.getFecha().isEqual(fechaViaje)){
                viajesDisponibles++;
            }
        }
        String [][] horariosDisponibles = new String[viajesDisponibles][4];

        if(viajesDisponibles > 0){
            for(int i =0 ; i < viajes.size(); i++){
                Viaje viajeAux = viajes.get(i);
                if(viajeAux.getFecha().isEqual(fechaViaje)){
                    horariosDisponibles [i][0] = viajeAux.getBus().getPatente();
                    horariosDisponibles [i][1] = String.valueOf(viajeAux.getHora());
                    horariosDisponibles [i][2] = String.valueOf(viajeAux.getPrecio());
                    horariosDisponibles [i][3] = String.valueOf(viajeAux.getNroAsientosDisponibles());
                }
            }
            return horariosDisponibles;
        }return new String[0][0];
    }

//    public String[][] listAsientosDeViaje(LocalDate fecha, LocalTime hora, String patBus){
//        if(findViaje(fecha, String.valueOf(hora), patBus) != null){
//            Viaje viajeAux = findViaje(fecha, String.valueOf(hora), patBus);
//            return Objects.requireNonNull(viajeAux).getAsientos();
//            //return viajeAux.getAsientos();
//        }else{
//            return new String[0][0];
//        }
//    }

    public String[][] listAsientosDeViaje(LocalDate fecha, LocalTime hora, String patBus){
        Viaje viajeAux = findViaje(fecha, String.valueOf(hora), patBus);
        if (viajeAux != null) {
            return viajeAux.getAsientos();
        } else {
            return new String[0][0];
        }
    }

    public int getMontoVenta(String idDocumento, TipoDocumento tipo) {
        int montoVenta = 0;
        if (findVenta(idDocumento, tipo) != null) {
            Venta ventaAux = findVenta(idDocumento, tipo);
            montoVenta += ventaAux.getMonto();
            return montoVenta;
        }
        return montoVenta;
    }

    public String getNombrePasajero(IdPersona idPasajero) {
        Pasajero pasajero = findPasajero(idPasajero);
        if (pasajero != null) {
            return pasajero.getNombreCompleto().toString();
        }
        return null;
    }

    public String getNombreCliente(IdPersona idCliente) {
        Cliente cliente = findCliente(idCliente);
        if (cliente != null) return cliente.getNombreCompleto().toString();

        return null;
    }

    public boolean vendePasaje(String idDoc, LocalDate fecha, LocalTime hora, String patBus, int asiento, IdPersona idPasajero){
        if (findPasajero(idPasajero) != null && findBus(patBus) != null) {
            Viaje viajeAux = findViaje(fecha, String.valueOf(hora), patBus);
            if (viajeAux != null) {
                for (Venta v : ventas) {
                    if (v.getIdDocumento().equals(idDoc)) {
                        Pasajero pasajeroAux = findPasajero(idPasajero);
                        v.createPasaje(asiento, viajeAux, pasajeroAux);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String[][] listVentas(){
        String[][]ventasAux = new String[ventas.size()][7];
        for(int i=0; i< ventasAux.length; i++) {
            ventasAux[i][0] = ventas.get(i).getIdDocumento();
            ventasAux[i][1] = String.valueOf(ventas.get(i).getTipo());
            ventasAux[i][2] = String.valueOf(ventas.get(i).getFecha());
            ventasAux[i][3] = String.valueOf(ventas.get(i).getCliente().getIdPersona());
            ventasAux[i][4] = String.valueOf(ventas.get(i).getCliente().getNombreCompleto());
            ventasAux[i][5] = String.valueOf(ventas.get(i).getCliente().getVentas().length); //revisar
            ventasAux[i][6] = String.valueOf(ventas.get(i).getMonto()); //revisar
        }
        return ventasAux;
    }

    public String[][] listViajes(){
        String[][]viajesAux = new String[viajes.size()][5];
        for(int i=0; i<viajesAux.length; i++){
            viajesAux[i][0] = String.valueOf(viajes.get(i).getFecha());
            viajesAux[i][1] = String.valueOf(viajes.get(i).getHora());
            viajesAux[i][2] = String.valueOf(viajes.get(i).getPrecio());
            viajesAux[i][3] = String.valueOf(viajes.get(i).getNroAsientosDisponibles());
            viajesAux[i][4] = viajes.get(i).getBus().getPatente();
        }
        return viajesAux;
    }

    public String[][] listPasajeros(LocalDate fecha, LocalTime hora, String patBus){
        Viaje viajeAux = findViaje(fecha, hora.toString(), patBus);
        if(viajeAux!=null) {
            String[][] pasajeros = viajeAux.getListaPasajeros();
            return pasajeros;
        }
        return new String[0][0];
    }

    public String[][] pasajesAlImprimir(String idDocumento, TipoDocumento tipo) {
        Venta ventaAux = findVenta(idDocumento, tipo);
        if (ventaAux != null) {
            Pasaje[] pasajesAux = ventaAux.getPasajes();
            String[][] listPasajes = new String[pasajesAux.length][7];

            for (int i = 0; i < pasajesAux.length; i++) {
                Pasaje pasajeAux = pasajesAux[i];
                listPasajes[i][0] = String.valueOf(pasajeAux.getNumero());
                listPasajes[i][1] = pasajeAux.getViaje().getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                listPasajes[i][2] = pasajeAux.getViaje().getHora().format(DateTimeFormatter.ofPattern("HH:mm"));
                listPasajes[i][3] = pasajeAux.getViaje().getBus().getPatente();
                listPasajes[i][4] = String.valueOf(pasajeAux.getAsiento());
                listPasajes[i][5] = pasajeAux.getPasajero().getIdPersona().toString();
                listPasajes[i][6] = String.valueOf(pasajeAux.getPasajero().getNombreCompleto());
            }

            return listPasajes;
        }
        return null;
    }

    private Cliente findCliente(IdPersona id){
        for(Cliente client : clientes){
            if(client.getIdPersona().equals(id)){
                return client;
            }
        }
        return null;
    }

    private Venta findVenta(String idDocumento, TipoDocumento tipoDocumento){
        for(Venta venta : ventas){
            if(venta.getIdDocumento().equals(idDocumento) && venta.getTipo().equals(tipoDocumento)){
                return venta;
            }
        }
        return null;
    }

    private Bus findBus(String patente){
        for(Bus bus : buses){
            if(bus.getPatente().equals(patente)){
                return bus;
            }
        }
        return null;
    }

//Le meti .toString a la getHora

    private Viaje findViaje(LocalDate fecha, String hora, String patenteBus){
        for (Viaje viaje : viajes){
            if(viaje.getFecha().equals(fecha) && viaje.getHora().toString().equals(hora) && viaje.getBus().getPatente().equals(patenteBus)){
                return viaje;
            }
        }
        return null;
    }
    private Pasajero findPasajero(IdPersona idPersona){
        for(Pasajero pasajero : pasajeros){
            if(pasajero.getIdPersona().equals(idPersona)){
                return pasajero;
            }
        }
        return null;
    }

}