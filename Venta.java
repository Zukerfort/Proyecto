import java.util.ArrayList;
import java.time.LocalDate;

public class Venta {
    private String idDocumento;
    private TipoDocumento tipo;
    private LocalDate fecha;
    private ArrayList<Pasaje> pasajesVendidos;
    private Cliente cliente;

    public Venta(String id, TipoDocumento tipo, LocalDate fecha,Cliente cli){
        idDocumento = id;
        this.tipo = tipo;
        this.fecha = fecha;

        //FALTA COLOCAR CLIENTE
        this.cliente = cli;
        cli.addVenta(this);
        pasajesVendidos = new ArrayList<>();
    }


    public String getIdDocumento(){
        return this.idDocumento;
    }
    public TipoDocumento getTipo(){
        return this.tipo;
    }
    public LocalDate getFecha(){
        return this.fecha;
    }
    //ARREGLE GETCLIENTE
    public Cliente getCliente(){
        return this.cliente;
    }

    //ARREGLO CREATEPASAJE
    public void createPasaje(int asiento, Viaje viaje , Pasajero pasajero){
        pasajesVendidos.add(new Pasaje(asiento,viaje,pasajero,this));
    }
    public Pasaje[] getPasajes(){
        return pasajesVendidos.toArray(new Pasaje[0]);
    }

    public int getMonto(){
        int monto = 0;
        for (Pasaje pasaje : pasajesVendidos) {
            //ARREGLE ESTA PARTE
            monto += pasaje.getViaje().getPrecio();
        }        return monto;
    }
}
