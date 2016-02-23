/**
 * Created by Olli on 22.02.2016.
 */
public class Fitness {


    // ONE-MAX


    static int problem =1;

    static public int eval_fitness(Genome g){
        if (problem==0){
            int score = 0;
            int[] dna = g.getDna();
            for (int i=0; i<dna.length; i++){
                if (dna[i]==1) score+=1;
            }
            return score;
        }
        else if (problem==1) {
            int zero_cap = 21;
            int score = 0;
            int[] dna = g.getDna();
            int last = dna[0];
            int target = dna[0];
            for (int i = 0; i < dna.length; i++) {
                if (dna[i] == last) {
                    score += 1;
                    last = dna[i];
                } else {
                    break;
                }
            }
            if (target == 0) {
                if (score >= zero_cap) return zero_cap;
                else return score;
            }
            return score;
        }
        else if (problem==2){

        }
        return -1;
    }
}
