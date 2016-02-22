/**
 * Created by Olli on 22.02.2016.
 */
public class Genome_convertion {

    public Phenome gen_to_phen(Genome g){
        return new Phenome(Fitness.eval_fitness(g));
    }
}
