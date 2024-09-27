import java.time.LocalTime;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static SistemaVentaPasajes sistemaVentaPasajes;

    public static void main(String[] args) {
        sc.useDelimiter("[\r\n]");
        sistemaVentaPasajes = new SistemaVentaPasajes();

        int opcionMenu;

        do {
            menu();
            opcionMenu = sc.nextInt();

            switch (opcionMenu) {
                case 1 -> createCliente();
                case 2 -> createBus();
                case 3 -> createViaje();
                case 4 -> vendePasajes();
                case 5 -> listPasajerosViaje();
                case 6 -> listVentas();
                case 7 -> listViajes();
                case 8 -> System.out.println("Finalizando el programa........");
                default -> System.out.println("Opcion no valida");

            }
        } while (opcionMenu != 8);

    }

    private static void menu() {
        System.out.println("============================");
        System.out.println("...::: Menú principal :::...\n");
        System.out.println("""
                1) Crear cliente
                2) Crear bus
                3) Crear viaje
                4) Vender pasajes
                5) Lista de pasajeros
                6) Lista de ventas
                7) Lista de viajes
                8) Salir""");
        System.out.println("----------------------------");
        System.out.println("..:: Ingrese numero de opción: ");
    }

    private static void createCliente() {
        System.out.println("...:::: Crear nuevo cliente ::::...\n");
        int opcionIdentificador, opcionTratamiento;
        String nombres, apellidoPaterno, apellidoMaterno, email, telefonoMovil, rut, pas;

        IdPersona idPersona = null;
        Tratamiento tratamiento = null;
        Rut rutAux;
        Pasaporte pasAux;
        String parte1;
        String parte2;

        do {
            opcionIdentificador = leeInt("Rut [1] o Pasaporte [2] : ");

            if (opcionIdentificador == 1) {
                System.out.println("Ingrese rut con puntos y guion: 11.111.111-1");
                do {
                    System.out.print("R.U.T : ");
                    rut = sc.next();
                    rutAux = Rut.of(rut);

                    if (rutAux == null) {
                        System.out.println("R.U.T inválido. Inténtelo de nuevo.");
                    }

                } while (rutAux == null);
                idPersona = rutAux;

            } else if (opcionIdentificador == 2) {

                do {
                    System.out.println("Ingrese número y nacionalidad separados por un espacio: XXXXXXXXX XXXXXXXX");
                    System.out.print("Pasaporte: ");
                    pas = sc.next();

                    String[] partesPasaporte = pas.split(" ");
                    int num = partesPasaporte.length;

                    if (num >= 2) {
                        parte1 = partesPasaporte[0];
                        parte2 = partesPasaporte[1];
                        pasAux = Pasaporte.of(parte1, parte2);

                        if (pasAux != null) {
                            idPersona = pasAux;
                        }

                    } else {
                        System.out.println("Formato incorrecto. Intente nuevamente.");
                    }

                } while (idPersona == null);

            } else {
                System.out.println("Opcion no valida");
            }
        } while (opcionIdentificador != 1 && opcionIdentificador != 2);

        do {
            opcionTratamiento = leeInt("Sr.[1] o Sra.[2] : ");

            if (opcionTratamiento == 1) {
                tratamiento = Tratamiento.SR;
            } else if (opcionTratamiento == 2) {
                tratamiento = Tratamiento.SRA;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcionTratamiento != 1 && opcionTratamiento != 2);

        System.out.print("Nombres: ");
        nombres = sc.next();

        System.out.print("Apellido Paterno: ");
        apellidoPaterno = sc.next();

        System.out.print("Apellido Materno: ");
        apellidoMaterno = sc.next();

        System.out.print("Teléfono móvil: ");
        telefonoMovil = sc.next();

        System.out.print("Email: ");
        email = sc.next();

        Nombre nombreAux = new Nombre(nombres, apellidoPaterno, apellidoMaterno, tratamiento);

        if (sistemaVentaPasajes.createCliente(idPersona, nombreAux, telefonoMovil, email)) {
            System.out.println("...:::: Cliente guardado exitosamente ::::...");
        } else {
            System.out.println("No ha sido posible crear el cliente, ya existe.");
        }
    }

    private static void createBus() {
        String patente, marca, modelo;
        int nroAsientos;
        System.out.println("...:::: Creacion de un nuevo bus ::::...\n");

        System.out.print("Patente : ");
        patente = sc.next();

        System.out.print("Marca : ");
        marca = sc.next();

        System.out.print("Modelo : ");
        modelo = sc.next();

        System.out.print("Numero de asientos : ");
        nroAsientos = sc.nextInt();

        if (sistemaVentaPasajes.createBus(patente, marca, modelo, nroAsientos)) {
            System.out.println("...:::: Bus guardado exitosamente ::::...");
        } else System.out.println("No ha sido posible crear el bus, ya existe");
    }

    private static void createViaje() {
        String fecha, hora, patente;
        int precio;
        LocalDate fechaLocalDate;
        LocalTime horaLocalTime;

        System.out.println("...:::: Creacion de un nuevo viaje ::::...\n");

        System.out.print("Fecha : ");
        fecha = sc.next();
        fechaLocalDate = constructorFecha(fecha);

        System.out.print("Hora : ");
        hora = sc.next();
        horaLocalTime = constructorHora(hora);

        System.out.print("Patente : ");
        patente = sc.next();

        System.out.print("Precio : ");
        precio = sc.nextInt();

        if (sistemaVentaPasajes.createViaje(fechaLocalDate, horaLocalTime, precio, patente)) {
            System.out.println("...:::: Viaje guardado exitosamente ::::...");

        }else{
            System.out.println("No ha sido posible crear el viaje, ya existe o cometió un error al ingresar los datos solicitados");
        }
    }

    private static void vendePasajes() {

        System.out.println("\t...:::: Venta de pasajes ::::...");

        System.out.println(":::: Datos de la venta");

        System.out.print("ID Documento : ");
        String idDocumento = sc.next();

        TipoDocumento tipoDocumento = null;
        int tipoDocumentoAux;

        do {
            tipoDocumentoAux = leeInt("Tipo documento : [1] Boleta [2] Factura : ");
            if (tipoDocumentoAux == 1) {
                tipoDocumento = TipoDocumento.BOLETA;
            } else if (tipoDocumentoAux == 2) {
                tipoDocumento = TipoDocumento.FACTURA;
            } else {
                System.out.println("Opcion no valida");
            }

        } while (tipoDocumentoAux != 1 && tipoDocumentoAux != 2);

        System.out.print("Fecha de venta [dd/mm/yyyy] : ");
        LocalDate fechaVenta = constructorFecha(sc.next());


        System.out.print(":::: Datos del cliente");

        int opcionIdentificador;
        String rut, pas;

        IdPersona identificador = null;
        Rut rutAux;
        Pasaporte pasAux;
        String parte1;
        String parte2;

        do {
            System.out.print(" Rut [1] o Pasaporte [2] : ");
            opcionIdentificador = sc.nextInt();

            if (opcionIdentificador == 1) {
                System.out.println("Ingrese rut con puntos y guion: 11.111.111-1");
                do {
                    System.out.print("R.U.T : ");
                    rut = sc.next();
                    rutAux = Rut.of(rut);

                    if (rutAux == null) {
                        System.out.println("R.U.T inválido. Inténtelo de nuevo.");
                    }

                } while (rutAux == null);
                identificador = rutAux;

            } else if (opcionIdentificador == 2) {

                do {
                    System.out.println("Ingrese número y nacionalidad separados por un espacio: XXXXXXXXX XXXXXXXX");
                    System.out.print("Pasaporte: ");
                    pas = sc.next();

                    String[] partesPasaporte = pas.split(" ");
                    int num = partesPasaporte.length;

                    if (num >= 2) {
                        parte1 = partesPasaporte[0];
                        parte2 = partesPasaporte[1];
                        pasAux = Pasaporte.of(parte1, parte2);

                        if (pasAux != null) {
                            identificador = pasAux;
                        }

                    } else {
                        System.out.println("Formato incorrecto. Intente nuevamente.");
                    }

                } while (identificador== null);

            } else {
                System.out.println("Opcion no valida");
            }
        } while (opcionIdentificador != 1 && opcionIdentificador != 2);

        System.out.print("Nombre Cliente: ");
        String nombrePasajero = sistemaVentaPasajes.getNombreCliente(identificador);
        System.out.println(nombrePasajero);

        //TENGO QUE VALIDAR QUE NO EXISTA UNA VENTA CON LOS MISMOS DATOS, DE NO EXISTIR, PROCEDO CON LA VENTA
        //ADEMAS, DEBO VALIDAR QUE EXISTA UN CLIENTE CON LOS DATOS INGRESADOS
        if (sistemaVentaPasajes.iniciaVenta(idDocumento, tipoDocumento, fechaVenta, identificador)) {

            System.out.println(":::: Pasajes a vender");
            int cantidad = leeInt("\tCantidad de pasajes : ");

            if (cantidad <= 0) {
                System.out.println("La cantidad de pasajes debe ser mayor a 0.");
                return;
            }

            System.out.print("Fecha de viaje[dd/mm/yyyy] : ");
            String fechaViaje = sc.next();
            LocalDate fechaDeViaje = constructorFecha(fechaViaje);

            System.out.println(" 	 *---------------*---------------*---------------*--------------*");
            System.out.println("  	|  BUS          | SALIDA        | VALOR         | ASIENTOS      |");
            System.out.println(" 	|---------------+---------------+---------------+---------------|");

            String[][] horariosDisponibles = sistemaVentaPasajes.getHorariosDisponibles(fechaDeViaje);
            int cont = 1;
            for (int i = 0; i < horariosDisponibles.length; i++) {
                System.out.printf(cont + "  | %-13s | %-13s | %-13s | %-13s |%n",
                        horariosDisponibles[i][0], horariosDisponibles[i][1],
                        horariosDisponibles[i][2], horariosDisponibles[i][3]);

                if (cont < horariosDisponibles.length) {
                    System.out.println("  	|---------------+---------------+---------------+---------------|");
                }
                cont++;
            }
            System.out.println("  	*---------------*---------------*---------------*---------------*");

            // cantidad sirve para saber que use bien el tema de los asientos

            System.out.println("Seleccione Viaje en [1...." + (cont - 1) + "] : ");
            int eleccionBus = sc.nextInt();

            // Asientos disponibles para el viaje seleccionado
            System.out.println("Asientos disponibles para el viaje seleccionado : ");
            String patenteAux = horariosDisponibles[eleccionBus - 1][0];
            LocalTime horaAux = constructorHora(horariosDisponibles[eleccionBus - 1][1]);
            String[][] listaAsientos = sistemaVentaPasajes.listAsientosDeViaje(fechaDeViaje, horaAux, patenteAux);

            System.out.println("*----*----*----*----*----*");
            for (int i = 0; i < listaAsientos.length; i++) {
                System.out.printf("| %-2s | %-2s |    | %-2s | %-2s |\n", listaAsientos[i][0], listaAsientos[i][1], listaAsientos[i][2], listaAsientos[i][3]);
                if (i < listaAsientos.length - 1) {
                    System.out.println("|----+----+----+----+----|");
                }
            }
            System.out.println("*------*------*----*------*");
            System.out.println("Seleccione sus asientos [separe por , ] : ");
            String asientos = sc.next();
            String[] cantAsientos = asientos.split(",");

            for(int i = 0; i < cantAsientos.length; i++){
                cantAsientos[i]= cantAsientos[i].trim();

            }
            if (cantAsientos.length != cantidad) {
                System.out.println("La cantidad de asientos seleccionados no coincide con la cantidad de pasajes.");
                return;
            }
            //funciona
            //PIDO DATOS DE PASAJERO POR CADA ASIENTO
            for (int i = 0; i < cantAsientos.length; i++) {

                int asientoElegido = Integer.parseInt(cantAsientos[i]);
                System.out.println("::::Datos pasajeros " + (i + 1));

                // ponerle la cosa para que compare bien
                System.out.print("Rut[1] Pasaporte[2] : ");
                String identificadorAux2 = sc.next();

                Rut rutAux2;
                Pasaporte pasaporteAux2;
                IdPersona identificador2 = null;

                if (identificadorAux2.equals("1")) {
                    System.out.print("R.U.T : ");
                    identificadorAux2 = sc.next();
                    rutAux2 = Rut.of(identificadorAux2);
                    identificador2 = rutAux2;
                } else if (identificadorAux2.equals("2")) {
                    identificadorAux2 = leeString("PASAPORTE : ");
                    String[] partesPasaporte2 = identificadorAux2.split(" ");
                    pasaporteAux2 = Pasaporte.of(partesPasaporte2[0], partesPasaporte2[1]);
                    identificador2 = pasaporteAux2;
                }else {
                    System.out.println("opcion no valida");
                }

                if (sistemaVentaPasajes.vendePasaje(idDocumento, fechaDeViaje, horaAux, patenteAux, asientoElegido, identificador2)) {
                    System.out.println(":::: Pasaje agregado exitosamente::::");
                } else {
                    Tratamiento tratamiento = null;

                    String nombre, telefono, contacto, telefonoContac;
                    int tratContacto;
                    System.out.println("No existe un pasajero con este identificador, procederemos a crearlo");
                    nombre = leeString("ingrese nombre completo : ");
                    int opcionTrat = leeInt("Sr.[1] o Sra.[2] : ");

                    do {
                        if (opcionTrat == 1) {
                            tratamiento = Tratamiento.SR;
                        } else if (opcionTrat == 2) {
                            tratamiento = Tratamiento.SRA;
                        } else {
                            System.out.println("Opción no válida");
                            opcionTrat = leeInt("Sr.[1] o Sra.[2] : ");
                        }
                    } while (opcionTrat != 1 && opcionTrat != 2);


                    String[] partesNombre = nombre.split(" ");
                    String nombres = partesNombre[0] + " " + partesNombre[1];
                    Nombre nombreNom = new Nombre(nombres, partesNombre[2], partesNombre[3], tratamiento);
                    telefono = leeString("Ingrese numero de telefono (+569) 1111 1111 : ");
                    contacto = leeString("Ingrese nombre completo de su contacto de emergencia : ");
                    tratContacto = leeInt("Sr.[1] o Sra.[2] : ");

                    Tratamiento tratamientoContacto = null;
                    do {
                        if (tratContacto == 1) {
                            tratamientoContacto = Tratamiento.SR;
                        } else if (tratContacto == 2) {
                            tratamientoContacto = Tratamiento.SRA;
                        }else{
                            System.out.println("Opcion no valida");
                        }

                    } while (tratContacto != 1 && tratContacto != 2);

                    String[] partesNombreContac = contacto.split(" ");
                    String nombresContac = partesNombreContac[0] + " " + partesNombreContac[1];
                    Nombre nombreNomContac = new Nombre(nombresContac, partesNombreContac[2], partesNombreContac[3], tratamientoContacto);
                    telefonoContac = leeString("Ingrese numero de telefono de su contacto de emergencia (+569) 1111 1111 : ");

                    //CREO EL PASAJERO
                    if (sistemaVentaPasajes.createPasajero(identificador2, nombreNom, telefono, nombreNomContac, telefonoContac)){
                        System.out.println("Pasajero creado correctamente");
                        if (sistemaVentaPasajes.vendePasaje(idDocumento, fechaDeViaje, horaAux, patenteAux, asientoElegido, identificador2)) {
                            System.out.println(":::: Pasaje agregado exitosamente::::");
                        } else {
                            System.out.println("No se ha podido guardar el pasaje");
                        }
                    } else {
                        System.out.println("No se ha podido crear el pasajero");
                    }
                }
            }
            System.out.println("Monto total de la venta: " + sistemaVentaPasajes.getMontoVenta(idDocumento, tipoDocumento));
            System.out.println("Imprimiendo los pasajes : ");
            String[][] list = sistemaVentaPasajes.pasajesAlImprimir(idDocumento, tipoDocumento);
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    System.out.println("------------------ PASAJE " + (i + 1) + " ------------------");
                    System.out.println("NUMERO DE PASAJE : " + list[i][0]);
                    System.out.println("FECHA DE VENTA : " + list[i][1]);
                    System.out.println("HORA DE VIAJE : " + list[i][2]);
                    System.out.println("PATENTE BUS : " + list[i][3]);
                    System.out.println("ASIENTO : " + list[i][4]);
                    System.out.println("RUT/PASAPORTE : " + list[i][5]);
                    System.out.println("NOMBRE PASAJERO : " + list[i][6]);
                    System.out.println("--------------------------------------------");
                }
            } else {
                System.out.println("No se encontraron pasajes para el documento proporcionado.");
            }

        }else{
            System.out.println("VENTA FALLIDA......");
        }
    }

    private static void listPasajerosViaje() {
        System.out.println("...:::: Listado de pasajeros de un viaje ::::...");

        System.out.print("Fecha del viaje [dd/mm/yyyy]: ");
        String fecha = sc.next();
        LocalDate fechaLocalDate = constructorFecha(fecha);

        System.out.print("Hora del viaje [hh:mm]: ");
        String hora = sc.next();
        LocalTime horaLocalTime = constructorHora(hora);

        System.out.print("Patente bus: ");
        String patente = sc.next();

        String[][] listaPasajeros = sistemaVentaPasajes.listPasajeros(fechaLocalDate, horaLocalTime, patente);

        if (listaPasajeros.length > 0) {
            System.out.println("\n...:::: Listado de pasajeros del viaje ::::...");
            System.out.println("*---------*-----------------------*-----------------------------------------------*-----------------------------------------------*-------------------*");
            System.out.printf("| %-7s | %-21s | %-45s | %-45s | %-17s |%n",
                    "ASIENTO", "RUT/PASS", "PASAJERO", "CONTACTO", "TELEFONO CONTACTO");
            System.out.println("|---------+-----------------------+-----------------------------------------------+-----------------------------------------------+-------------------|");

            for (String[] pasajero : listaPasajeros) {
                System.out.printf("| %-7s | %-21s | %-45s | %-45s | %-17s |%n",
                        pasajero[0], pasajero[1], pasajero[2], pasajero[3], pasajero[4]);
                System.out.println("|---------+-----------------------+-----------------------------------------------+-----------------------------------------------+-------------------|");

            }

        } else {
            System.out.println("No existen pasajeros para listar");
        }
    }

    private static void listVentas() {
        String[][] listaVentas = sistemaVentaPasajes.listVentas();

        if (listaVentas.length > 0) {
            System.out.println("           ...:::: Listado de ventas ::::...");

            System.out.println("*----------------------*----------------------*----------------------*-------------------------------*---------------------------------------------------------*------------------*--------------*");
            System.out.printf("| %-22s | %-22s | %-22s | %-31s | %-57s | %-18s | %-14s |%n",
                    "ID DOCUMENT", "TIPO DOC", "FECHA", "RUT/PASAPORTE", "CLIENTE", "CANT BOLETOS", "TOTAL VENTA");
            System.out.println("|----------------------|----------------------|----------------------|-------------------------------|---------------------------------------------------------|------------------|--------------|");

            for (int i = 0; i < listaVentas.length; i++) {
                String[] venta = listaVentas[i];
                System.out.printf("| %-22s | %-22s | %-22s | %-31s | %-57s | %-18s | %-14s |%n",
                        venta[0], venta[1], venta[2], venta[3], venta[4], venta[5], venta[6]);

                if (i < listaVentas.length - 1) {
                    System.out.println("|----------------------+----------------------+----------------------+-------------------------------+---------------------------------------------------------+------------------+--------------|");
                }
            }

            System.out.println("*----------------------*----------------------*----------------------*-------------------------------*---------------------------------------------------------*------------------*--------------*");
        } else {
            System.out.println("No existen ventas para listar");
        }
    }

    private static void listViajes() {
        String[][] listaViajes = sistemaVentaPasajes.listViajes();

        if (listaViajes.length > 0) {
            System.out.println("           ...:::: Listado de viajes ::::...");
            System.out.println("*-------------*-------*--------*-------------*---------*");
            System.out.printf("| %-11s | %-5s | %-6s | %-11s | %-7s |%n", "FECHA", "HORA", "PRECIO", "DISPONIBLES", "PATENTE");
            System.out.println("|-------------+-------+--------+-------------+---------|");

            for (int i = 0; i < listaViajes.length; i++) {
                String[] viaje = listaViajes[i];
                System.out.printf("| %-11s | %-5s | %-6s | %-11s | %-7s |%n",
                        viaje[0], viaje[1], viaje[2], viaje[3], viaje[4]);


                if (i < listaViajes.length - 1) {
                    System.out.println("|-------------+-------+--------+-------------+---------|");
                }
            }

            System.out.println("*-------------*-------*--------*-------------*---------*");
        } else {
            System.out.println("No existen viajes para listar");
        }
    }



    // METODOS PRIVADOS PARA OPTIMIZAR

    private static LocalDate constructorFecha(String str) {
        LocalDate date;
        String[] fechaArray = str.split("/");

        date = LocalDate.of(Integer.parseInt(fechaArray[2]),
                Integer.parseInt(fechaArray[1]),
                Integer.parseInt(fechaArray[0]));

        return date;
    }

    private static LocalTime constructorHora(String str) {
        LocalTime time;
        String[] horaArray = str.split(":");
        time = LocalTime.of(Integer.parseInt(horaArray[0]),
                Integer.parseInt(horaArray[1]));
        return time;
    }

    private static String leeString(String msg) {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("[\r\n\u2028\u2029\u0085\t]+");
        System.out.printf("%1s ", msg);
        return scan.next();
    }

    private static int leeInt(String msg) {
        sc.useDelimiter("[\r\n\u2028\u2029\u0085\t]+");
        System.out.printf("%1s ", msg);
        int num = 0;
        do {
            num = sc.nextInt();
        } while (num < 0);

        return num;
    }
}


