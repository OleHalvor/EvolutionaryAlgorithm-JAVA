import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Main {

    static ArrayList<Genome> children_genomes = new ArrayList<>();
    static ArrayList<Genome> adult_genomes = new ArrayList<>();
    static ArrayList<Genome> parent_genomes = new ArrayList<>();
    static ArrayList<Genome> new_generation = new ArrayList<>();
    static Genome best_genome;

    //Params:
    static int genome_length   = 40;
    static int num_children    = 100;
    static int num_parents     = 100;
    static int percentage_best = 50;
    static int generations     = 10000;
    static int mutation_rate   = 1;
    static int last_fitness    = 0;

    //Problem specific
    static int adult_selection = 1; //0:full, 1:over-production, 2:mixing
    static int problem = 0; //0:OneMax, 1:LOLZ, 2:Surprising sequences
    static int parent_selection = 0; //0: fitness-proportionate, 1:sigma-scaling, 2:turnament-selection, 3:Min

    public static int get_mutation_rate(){
        return mutation_rate;
    }

    public static void main(String[] args) {
        new_generation = generate_children(num_children);
        children_genomes = generate_children(num_children);
        System.out.println("First generation");
        print_fitness_stats(children_genomes);
        adult_genomes = select_adults(children_genomes);
        parent_genomes = select_parents(adult_genomes);
        new_generation = reproduction(parent_genomes);
        System.out.println("New generation");
        print_fitness_stats(new_generation);

        int c = 0;
        int target_fitness = genome_length;
        best_genome = parent_genomes.get(0);
        mutation_rate = 1;
        last_fitness = 0;
        while(target_fitness > Fitness.eval_fitness(best_genome) &&(c<generations) ) {

            if (last_fitness >= Fitness.eval_fitness(get_best_genome(new_generation))){
                mutation_rate++;
                System.out.println("max, Fitn"+ last_fitness +" ,"+Fitness.eval_fitness(get_best_genome(new_generation)));
                last_fitness = Fitness.eval_fitness(get_best_genome(new_generation));
            }
            else{
                mutation_rate = 1;
                last_fitness = Fitness.eval_fitness(get_best_genome(new_generation));
            }

            System.out.println("mutation rate: "+mutation_rate);

            c++;
            System.out.println(" New children pool size: "+new_generation.size());
            children_genomes = new_generation;
            adult_genomes = select_adults(children_genomes);
            System.out.println(" New adult size: "+adult_genomes.size());
            parent_genomes = select_parents(adult_genomes);
            System.out.println(" New parent size: "+parent_genomes.size());
            new_generation = reproduction(parent_genomes);
            System.out.println("New generation");
            print_fitness_stats(new_generation);
            if (c<10)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            else if (c<100)
                try {
                    Thread.sleep(50);
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

    private static Genome get_best_genome(ArrayList<Genome> genes){
        Genome max_genome = genes.get(0);
        int max = 0;
        for ( Genome g:genes)if(Fitness.eval_fitness(g)>max){
            max = Fitness.eval_fitness(g);
            max_genome = g;
        }
        best_genome = max_genome;
        return best_genome;
    }

    private static void print_fitness_stats(ArrayList<Genome> genes){

        // THIS ALSO SETS BEST GENOME
        System.out.println("----------------------");

        Genome max_genome = genes.get(0);
        int max = 0;
        for ( Genome g:genes)if(Fitness.eval_fitness(g)>max){
            max = Fitness.eval_fitness(g);
            max_genome = g;
        }
        best_genome = max_genome;
        System.out.println("Best genome: "+Genome.printDna(max_genome));
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
        Random rnd = new Random();
        if (adult_selection==0)
            return children;
        if (adult_selection==1){
            ArrayList<Genome> sorted_adults = sort_genomes(children);
            int best_count = (num_parents * percentage_best) / 100;
            int worst_count = num_children - best_count;
            ArrayList<Genome> best_adults = new ArrayList<>(sorted_adults.subList((sorted_adults.size() - best_count), sorted_adults.size()));
            ArrayList<Genome> worst_adults = new ArrayList<>(sorted_adults.subList(0, (sorted_adults.size() - best_count)));
            ArrayList<Genome> selected_adults = new ArrayList<>(best_adults);
            for (int i = 0; i < (worst_count); i++) {
                selected_adults.add(worst_adults.get(rnd.nextInt(worst_adults.size())));
            }
            if (parent_genomes.size() >= 2) {
                ArrayList<Genome> sorted_parents = sort_genomes(parent_genomes);
                selected_adults.add(sorted_parents.get(sorted_parents.size() - 1));
                selected_adults.add(sorted_parents.get(sorted_parents.size() - 2));
                selected_adults.add(sorted_parents.get(0));
                selected_adults.add(sorted_parents.get(1));
            }
            return selected_adults;
        }
        if (adult_selection==2){
            ArrayList<Genome> temp = new ArrayList<>(children);
            for (Genome g:adult_genomes)temp.add(g);
            return temp;
        }
        else return children;
    }

    private static ArrayList<Genome> select_parents(ArrayList<Genome> adults){
        ArrayList<Genome> sorted_adults = sort_genomes(adults);
        System.out.println(adults.size()+" Kom inn");
        Random rnd = new Random();
        if (parent_selection==0){
            ArrayList<Individual> individuals = new ArrayList<>();
            double total_fitness = 0;
            for (Genome g:adults){
                int fit = Fitness.eval_fitness(g);
                individuals.add(new Individual(g,fit ));
                total_fitness += fit;
            }
            double last_value = 0;
            for (Individual i:individuals){
                i.setPropFitStart(last_value);
                i.setPropFit((i.getFitness()/total_fitness)+last_value);
                last_value = (i.getFitness()/total_fitness)+last_value;

            }
            ArrayList<Genome> winners = new ArrayList<>();

            for (int i=0; i<num_parents; i++){
                double rnd_num = rnd.nextDouble();
                for (Individual k:individuals){
                    if ((rnd_num>k.getPropFitStart())&&(rnd_num<k.getPropFit())){
                        if (!winners.contains(k.genome))winners.add(k.getGenome());
                    }
                }
            }
            if (parent_genomes.size() >= 2) {
                ArrayList<Genome> sorted_parents = sort_genomes(parent_genomes);
                winners.add(sorted_parents.get(sorted_parents.size() - 1));
                winners.add(sorted_parents.get(sorted_parents.size() - 2));
                winners.add(sorted_parents.get(0));
                winners.add(sorted_parents.get(1));
            }
            System.out.println(winners.size()+" gikk ut");
            return winners;

        }
        if (parent_selection==1){

        }
        if (parent_selection==2){

        }


        if(parent_selection==3) {
            int best_count = (num_parents * percentage_best) / 100;
            int worst_count = num_children - best_count;
            ArrayList<Genome> best_adults = new ArrayList<>(sorted_adults.subList((sorted_adults.size() - best_count), sorted_adults.size()));
            ArrayList<Genome> worst_adults = new ArrayList<>(sorted_adults.subList(0, (sorted_adults.size() - best_count)));
            ArrayList<Genome> selected_adults = new ArrayList<>(best_adults);
            for (int i = 0; i < (worst_count); i++) {
                selected_adults.add(worst_adults.get(rnd.nextInt(worst_adults.size())));
            }
            if (parent_genomes.size() >= 2) {
                ArrayList<Genome> sorted_parents = sort_genomes(parent_genomes);
                selected_adults.add(sorted_parents.get(sorted_parents.size() - 1));
                selected_adults.add(sorted_parents.get(sorted_parents.size() - 2));
                selected_adults.add(sorted_parents.get(0));
                selected_adults.add(sorted_parents.get(1));
            }
            return selected_adults;
        }
        return adults;
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
