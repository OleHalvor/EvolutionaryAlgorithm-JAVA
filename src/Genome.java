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

    public Genome(int[] dna){
        this.dna = dna;
    }

    public int[] getDna(){
        return dna;
    }

    public Genome crossover(Genome g){
        Random rnd = new Random();

        int[] dna0 = this.dna;
        int[] dna1 = g.getDna();
        int[] new_dna = new int[dna0.length];
        for (int i=0; i<dna0.length; i++){
            int rand = rnd.nextInt(2);
            if (rand==0)new_dna[i]=dna0[i];
            else new_dna[i]=dna1[i];
        }
        return new Genome(new_dna);
    }
}
