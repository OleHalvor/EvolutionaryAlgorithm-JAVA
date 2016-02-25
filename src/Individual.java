/**
 * Created by Olli on 24.02.2016.
 */
public class Individual {
    Genome genome;
    double fitness;
    double proportionate_fitness_start;
    double proportionate_fitness;

    public Individual(Genome genome, double fitness, double proportionate_fitness, double proportionate_fitness_start){
        this.genome = genome;
        this.proportionate_fitness = proportionate_fitness;
        this.fitness = fitness;
        this.proportionate_fitness_start = proportionate_fitness_start;
    }

    public Individual(Genome genome, double fitness){
        this.genome = genome;
        this.fitness = fitness;
    }

    public void setPropFit(double proportionate_fitness){
        this.proportionate_fitness = proportionate_fitness;
    }

    public void setPropFitStart(double proportionate_fitness_start){
        this.proportionate_fitness_start = proportionate_fitness_start;
    }

    public double getPropFitStart(){
        return proportionate_fitness_start;
    }

    public Genome getGenome(){
        return genome;
    }

    public double getPropFit(){
        return proportionate_fitness;
    }

    public double getFitness(){
        return fitness;
    }
}
