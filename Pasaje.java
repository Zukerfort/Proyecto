import java.time.LocalDateTime;
import java.time.ZoneOffset;
// juliana
public class Pasaje {
    // tiene que ser de solo 1 hasta muchos, minimo 1
    private long numero;
    private int asiento;
    private Viaje viaje;
    private Pasajero pasajero;
    private Venta venta;

    public Pasaje (int asiento, Viaje viaje, Pasajero pasajero, Venta venta){
        this.asiento = asiento;
        this.viaje = viaje;
        viaje.addPasaje(this);
        this.pasajero = pasajero;
        this.venta = venta;
        LocalDateTime now= LocalDateTime.now();
        numero = now.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public long getNumero(){
        return numero;
    }

    public int getAsiento(){
        return asiento;
    }

    public Viaje getViaje(){
        return viaje;
    }

    public Pasajero getPasajero(){
        return pasajero;
    }

    public Venta getVenta(){// es uno si dice get venta
        return venta;
    }

}
