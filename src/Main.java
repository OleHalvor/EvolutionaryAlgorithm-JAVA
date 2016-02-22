import java.util.ArrayList;
import java.util.Objects;

public class Main {

    static ArrayList<Genome> children_genomes = new ArrayList<>();
    static ArrayList<Genome> adult_genomes = new ArrayList<>();
    static ArrayList<Genome> parent_genomes = new ArrayList<>();
    static ArrayList<Genome> new_generation = new ArrayList<>();

    //Params:
    static int genome_length=20;
    static int num_children = 10;
    static int num_parents = 8;
    static int generations = 0;


    public static void main(String[] args) {
        children_genomes = generate_children(num_children);
        System.out.println("Children Genomes");
        print_fitness_stats(children_genomes);
        adult_genomes = select_adults(children_genomes);
        parent_genomes = select_parents(adult_genomes);
        System.out.println("Parent Genomes");
        print_fitness_stats(parent_genomes);
        new_generation = reproduction(parent_genomes);
        System.out.println("New generation");
        print_fitness_stats(new_generation);

        while(true){
            children_genomes = new_generation;
            System.out.println("Children Genomes");
            print_fitness_stats(children_genomes);
            adult_genomes = select_adults(children_genomes);
            parent_genomes = select_parents(adult_genomes);
            System.out.println("Parent Genomes");
            print_fitness_stats(parent_genomes);
            new_generation = reproduction(parent_genomes);
            System.out.println("New generation");
            print_fitness_stats(new_generation);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<Genome> reproduction(ArrayList<Genome> parents){
        ArrayList<Genome> offspring = new ArrayList<>();
        for (Genome p1:parents){
            for (Genome p2:parents){
                if (!p2.equals(p1)){
                    offspring.add(p1.crossover(p2));
                }
            }
        }
        return offspring;
    }

    private static void print_fitness_stats(ArrayList<Genome> genes){
        System.out.println("----------------------");

        int max = 0;
        for ( Genome g:genes)if(Fitness.eval_fitness(g)>max)max = Fitness.eval_fitness(g);
        System.out.println("Max Fitness: "+max);

        int tot =0;
        for ( Genome g:genes) tot+=Fitness.eval_fitness(g);
        int avg = tot/genes.size();
        System.out.println("Avg Fitness: "+avg);

        int min = Fitness.eval_fitness(genes.get(0));
        for ( Genome g:genes)if(Fitness.eval_fitness(g)<min)min = Fitness.eval_fitness(g);
        System.out.println("Min Fitness: "+min);

        System.out.println("----------------------");
    }

    private static ArrayList<Genome> sort_genomes(ArrayList<Genome> old_list){
        ArrayList<Genome> pop = new ArrayList<>(old_list);
        for (int i=0; i<pop.size(); i++){
            Genome x_gen = pop.get(i);
            int x = Fitness.eval_fitness(pop.get(i));
            int j = i-1;
            while( (j>=0)&&(Fitness.eval_fitness(pop.get(j))>x) ){
                pop.set(j+1,pop.get(j));
                j = j-1;
            }
            pop.set(j+1, x_gen);
        }
        return pop;


    }

    private static ArrayList<Genome> select_adults(ArrayList<Genome> children){
        return children;
    }

    private static ArrayList<Genome> select_parents(ArrayList<Genome> adults){
        ArrayList<Genome> sorted_adults = sort_genomes(adults);
        return new ArrayList<Genome>(sorted_adults.subList((sorted_adults.size()-num_parents),sorted_adults.size()));
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
