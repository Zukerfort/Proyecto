//Paula Suco
public class Rut implements IdPersona{
    private int numero;
    private char dv;

    public Rut(int numero, char dv){
        this.numero = numero;
        this.dv = dv;
    }

    public int getNumero() {
        return numero;
    }

    public char getDv() {
        return dv;
    }

    public static Rut of(String rutStr) { //VALIDA LA WEA CONCHETUMARE
        String rutConDv;
        rutConDv = rutStr.replaceAll("[.-]", "");

        char dv = rutConDv.charAt(rutConDv.length() - 1);

        int dvAux;

        if (dv == 'K' || dv == 'k') {
            dv = 10;
            dvAux = dv;
        } else {
            dvAux = dv - 48;
        }

        String numeros = rutConDv.substring(0, rutConDv.length() - 1);
        int[] nums = new int[numeros.length()];

        int cont = 2;
        int suma = 0;
        int resto;
        int verificador = 0;

        for (int i = 0; i < nums.length; i++) {
            nums[i] = numeros.charAt(numeros.length() - i - 1) - '0';

            suma += ((nums[i]) * cont);
            cont++;

            if (cont > 7) {
                cont = 2;
            }

            resto = suma % 11;
            verificador = 11 - resto;

        }
        if (verificador == 11 && dvAux == 0) {
            verificador = 0;
        }

        if (rutConDv.length() < 8) {
            return null;
        }

        if (dvAux == verificador) {
            char dvFinal = rutConDv.charAt(rutConDv.length() - 1);
            String numFinal = rutConDv.substring(0, rutConDv.length() - 1);
            int numeroFinal = Integer.parseInt(numFinal);
            return new Rut(numeroFinal, dvFinal);
        }
        return null;
    }

    @Override
    public String toString() {
        //NUMERO A CADENA
        String rutAux = Integer.toString(numero);
        StringBuilder rutFor =  new StringBuilder();
        int Rut = rutAux.length();

        int cont = 0;

        for(int i = Rut -1; i>= 0; i--){
            rutFor.insert(0, rutAux.charAt(i));
            cont++;

            if(cont == 3 && i > 0){
                rutFor.insert(0, ".");
            }
        }
        rutFor.append("-").append(dv);
        return rutFor.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (obj instanceof Rut r) {
            return this.numero== r.getNumero() && this.dv== r.getDv();
        }return false;
    }
}
