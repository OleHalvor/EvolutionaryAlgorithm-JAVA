import java.util.Random;

/**
 * Created by Olli on 22.02.2016.
 */



public class Genome {
    private int[] dna;

    public Genome(int length){
        Random rnd = new Random();

        this.dna = new int[length];
        for (int i =0; i<length; i++){
            dna[i]=rnd.nextInt(2);
        }
    }

    public int[] getDna(){
        return dna;
    }

    public Genome crossover(Genome){
        //Crossover genes with another
        return this;
    }
}
