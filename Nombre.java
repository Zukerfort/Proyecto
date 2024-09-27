public class Nombre {
    private Tratamiento tratamiento;
    private String nombres, apellidoPaterno, apellidoMaterno;

    public Nombre(String nombres, String apellidoPaterno, String apellidoMaterno, Tratamiento tratamiento){
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.tratamiento = tratamiento;
    }

    //GETTERS

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    //SETTERS

    public void setTratamiento(Tratamiento trat) {
        this.tratamiento = trat;
    }

    public void setNombres(String nomb) {
        this.nombres = nomb;
    }

    public void setApellidoPaterno(String apePaterno) {
        this.apellidoPaterno = apePaterno;
    }

    public void setApellidoMaterno(String apeMaterno) {
        this.apellidoMaterno = apeMaterno;
    }

    //TO STRING

    public String toString() {
        return tratamiento+" "+nombres+" "+apellidoPaterno+" "+apellidoMaterno;
    }

    //EQUALS
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Nombre) {
            return this.nombres.equals(((Nombre)obj).nombres) && this.apellidoMaterno.equals(((Nombre)obj).apellidoMaterno) && this.apellidoPaterno.equals(((Nombre)obj).apellidoPaterno);
        }
        return false;
    }
}

