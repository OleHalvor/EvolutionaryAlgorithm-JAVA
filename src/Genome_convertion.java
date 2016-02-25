import java.util.ArrayList;

/**
 * Created by Olli on 22.02.2016.
 */
public class Genome_convertion {

    public Phenome gen_to_phen(Genome g){
        return new Phenome(Fitness.eval_fitness(g));
    }

    public static ArrayList<Genome> translate(ArrayList<Genome> g){
        System.out.println("placeholder");
        return new ArrayList<Genome>();
    }
}
