/**
 * Created by Olli on 22.02.2016.
 */
public class Fitness {

    static public int eval_fitness(Genome g){
        int score = 0;
        int[] dna = g.getDna();
        for (int i=0; i<dna.length; i++){
            if (dna[i]==1) score+=1;
        }
        return score;
    }

    static public int eval_fitness(Phenome p){
        int dna = p.getDna();
        return dna;
    }


}
