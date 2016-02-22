import java.util.ArrayList;
import java.util.Objects;

public class Main {

    static ArrayList<Genome> children_genomes = new ArrayList<>();
    static ArrayList<Phenome> children_phenomes = new ArrayList<>();
    static ArrayList<Genome> adult_genomes = new ArrayList<>();
    static ArrayList<> adults = new ArrayList<>();

    //Params:
    static int genome_length=20;
    static int num_children = 10;
    static int num_adults = 8;
    static int generations = 0;


    public static void main(String[] args) {
        children_genomes = generate_children(num_children);
        adult_genomes = select_adults(children_genomes);


        //children_phenomes = convert_genomes(children_genomes);
    }

    private static ArrayList<Genome> select_parents((ArrayList<Genome> adults){
        ArrayList<Genome> best_parents = new ArrayList<>();
        for (Genome adult:adults){
            for (Genome best:best_parents){

            }
        }

    }

    private static ArrayList<Genome> select_adults(ArrayList<Genome> children){
        return children;
    }

    private static ArrayList<Genome> generate_children(int size){
        ArrayList<Genome> genomes = new ArrayList<>();
        for (int i=0; i<size; i++){
            genomes.add(new Genome(genome_length));
        }
        return genomes;

    }

    private static ArrayList<Phenome> convert_genomes(ArrayList<Genome> genomes){
        ArrayList<Phenome> phenomes = new ArrayList<Phenome>();
        for (Genome g:genomes){
            phenomes.add(new Phenome(Fitness.eval_fitness(g)));
        }
        return phenomes;
    }


}
