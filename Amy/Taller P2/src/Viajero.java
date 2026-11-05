public class Viajero extends Personaje {
    private String dimension;
    private int[] energia;// carga de la pistola de portales en 4 viajes
    int suma = 0;
    double resultado = 0;
    public Viajero(String nombre, String especie, String estado, int episodios, String dimension, int[] energia) {
        super(nombre, especie, estado, episodios);   // llama al constructor de Personaje
        this.dimension = dimension;
        this.energia = energia;
    }

    public String getDimension() { return dimension; }

    public int[] getEnergia()    { return energia; }
    
    public double promedio() {
        for (int i = 0; i < energia.length; i++) {
            suma = suma + energia[i];
        }
        resultado = (double) suma / energia.length;
        return resultado;
    }
    @Override
    public String getTipo() {
        return "Viajero";
    }
    @Override
    public String resumen() {
        return super.resumen() + " | Dimension " + dimension;
    }

}