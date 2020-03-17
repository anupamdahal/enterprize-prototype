import java.util.*;

/*
This class models the Evolution over the years given the environmental conditions
@author Anupam Dahal, Torunima Taposhi
 */

public class Simulation
{
    public int numYears;
    public Environment env;

    private int numSnipes;
    private int numPredators;
    private int numSnoozeberries;


    private ArrayList <DeathReport> deaths;
    private ArrayList <Snipe> snipes;
    private ArrayList <Snipe> offSprings;

    Random random = new Random();

    /**
     * Constructor
     * @param years
     * @param environment
     */
    public Simulation(int years, Environment environment){

        this.numYears = years;
        this.env = environment;

        this.numSnipes = 300;
        this.numPredators = 200;
        this.numSnoozeberries = 200;

        this.deaths = new ArrayList<DeathReport>();
        this.snipes = new ArrayList<Snipe>();
        this.offSprings = new ArrayList<Snipe>();
    }

    /**
     * Runs a new simulation for a Simulation object
     */
    public void RunNewSimulation(){

        initSimulation();

        DeathReport deathReport;
        Snipe subject, previous;
        previous =  new Snipe(random.nextBoolean(),
                              random.nextBoolean(),
                              random.nextBoolean(),
                              random.nextBoolean());


        for (int i = 0; i<numYears; i++){

            for (int j = 0; j< snipes.size(); j++){

                random = new Random();
                subject = snipes.get(j);


                //find food
                if (random.nextDouble() < subject.GetFoodChance() && this.numSnoozeberries > 0){
                    this.numSnoozeberries -= 1;
                    subject.energy += 4;
                }

                //avoid predetor
                if ( random.nextBoolean() || random.nextBoolean()){

                    if (random.nextDouble() < subject.GetSurvivalChance() ){
                        subject.LoseEnergy(1);
                    }
                    else{
                        subject.isAlive = false;
                        deathReport = new DeathReport(i, subject.age, CauseOfDeath.Predation);

                        deaths.add(deathReport);
                        snipes.remove(j);

                    }
                }

                //offspring
                if (subject.isAlive && j%2 == 1){
                    int k = j;
                    while (k<0){
                        previous = snipes.get(k);
                        if (previous.isAlive){
                            break;
                        }
                        else{
                            k--;
                        }
                    }

                    //only if a living mate is found
                    if (k != 0){

                        if(random.nextBoolean()){
                            this.offSprings.add(previous.GenerateOffspring());
                        }
                        else{
                            this.offSprings.add(subject.GenerateOffspring());
                        }
                    }

                    //increament age
                    subject.age += 1;

                    //lose energy
                    subject.LoseEnergy(subject.energyRequired);

                    if (!subject.isAlive){

                        // System.out.println("boom");

                        deathReport = new DeathReport(i, subject.age, CauseOfDeath.Starvation);

                        deaths.add(deathReport);
                        snipes.remove(j);

                    }

                }

            }

            this.snipes.addAll(offSprings);
            Collections.shuffle(snipes);
            offSprings.clear();

            if (this.snipes.size() == 0){
                break;
            }

            replinish();
        }


        return;
    }

    /**
     * It summerizes the deathReport of the deaths of the simulation
     * @return string of details of the deaths
     */
    public String SummarizeDeaths(){

        int noStarvation = 0;
        int noPredation = 0;
        DeathReport death;
        String report;

        Iterator<DeathReport> itr = this.deaths.iterator();
        while(itr.hasNext()){
            death = itr.next();

            // System.out.println(death.cause);
            if(death.cause == CauseOfDeath.Starvation){
                noStarvation += 1;
            }else if (death.cause == CauseOfDeath.Predation){
                noPredation += 1;
            }
            else{
                System.out.println(death.cause);
            }
        }

        report = "No. of Deaths = "+ this.deaths.size();
        report += ". No of Deaths due to starvation = " + noStarvation;
        report += ". No of Death due to predation = " + noPredation + ".";

        System.out.println(report);

        return report;
    }

    /**
     * it sets up simulation parameters given the initial conditions
     */
    private void initSimulation(){


        if(this.env.isDangerous){
            this.numPredators = 400;
        }

        if (this.env.isPlentiful){
            this.numSnoozeberries = 400;
        }

        for(int i = 0; i<numSnipes; i++){
            Snipe snipe = new Snipe(random.nextBoolean(),
                                    random.nextBoolean(),
                                    random.nextBoolean(),
                                    random.nextBoolean());

            this.snipes.add(snipe);
        }

    }

    /**
     * replinishs the food and predator in the environment
     */
    private void replinish(){
        if(this.env.isPlentiful){
            this.numSnoozeberries += 400;
        }
        else{
            this.numSnoozeberries += 200;
        }

        if (this.numSnoozeberries > 600 ){
            this.numSnoozeberries = 600;
        }

        if(this.env.isDangerous){
            this.numPredators = 400;
        }
        else{
            this.numPredators = 200;
        }

    }
}
