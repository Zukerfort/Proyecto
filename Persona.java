//Paula Suco
public class Persona {
    private IdPersona idPersona;
    private Nombre nombreCompleto;
    private String telefono;

    public Persona(IdPersona idPersona, Nombre nombreCompleto) {
        this.idPersona = idPersona;
        this.nombreCompleto = nombreCompleto;
    }

    //GETTERS
    public IdPersona getIdPersona() {
        return idPersona;
    }

    public Nombre getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    //SETTERS
    public void setIdPersona(IdPersona idPersona) {
        this.idPersona = idPersona;
    }

    public void setNombreCompleto(Nombre nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return idPersona+","+nombreCompleto+","+telefono+"\n";
    }

    public boolean equals(Object otro){
        return this.idPersona.equals(((Persona) otro).getIdPersona());
    }
}