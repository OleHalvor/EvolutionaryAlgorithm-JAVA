/**
 * Created by Olli on 22.02.2016.
 */
public class Fitness {


    // ONE-MAX




    static public double eval_fitness(Genome g){
        int[] target_string = Main.target_string;
        int problem = Main.problem;
        if (problem==0){
            double score = 0;
            int[] dna = g.getDna();
            for (int i=0; i<dna.length; i++){
                if (dna[i]==1) score+=1;
                //if (dna[i]==target_string[i]) score+=1;
            }
            return score;
        }
        else if (problem==1) {
            int zero_cap = 21;
            double score = 0;
            int[] dna = g.getDna();
            int last = dna[0];
            int target = dna[0];
            for (int i = 0; i < dna.length; i++) {
                if (dna[i] == last) {
                    if (dna[i]==1) score+=1;
                    else score += 1;
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
            int l = g.getDna().length;
            for (int i=0; i<l-1; i++){
                for (int k=i+1; k<l; k++ ){

                }
            }


        }
        return -1;
    }
}
