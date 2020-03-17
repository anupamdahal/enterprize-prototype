import java.util.*;
/*
This class models a fictious airal species snipes given various paramerter
These parameter determines its chances of finding food and survaival
@author Anupam Dahal, Torunima Taposhi
 */
public class Snipe
{
  public boolean beakIsLong;
  public boolean bodyIsFat;
  public boolean longDistanceFlier;
  public boolean conservativeGenes;
  public boolean isAlive;
  public int age;
  public int energy;
  public int energyRequired;

  private float predatorSurvivalChance;
  private float foodChance;
  private Snipe offSpring;

  Random random = new Random();
  /**
   * This is a class that constructs an arial species Snipes given following params
   * @param longbeak  boolean
   * @param fatbody   boolean
   * @param longflier boolean
   * @param cgenes    boolean
   */
  public Snipe(boolean longbeak, boolean fatbody, boolean longflier, boolean cgenes)
  {

    this.isAlive = true;
    this.energyRequired = 2;

    this.beakIsLong = longbeak;
    this.bodyIsFat = fatbody;
    this.longDistanceFlier = longflier;
    this.conservativeGenes = cgenes;
    this.energy = 2;
    this.age = 0;

    this.predatorSurvivalChance = (float) 0.5;
    this.foodChance = (float) 0.5;

    if (this.beakIsLong){
      this.predatorSurvivalChance -= 0.05;
      this.foodChance += 0.1;
    }
    else{
      predatorSurvivalChance += 0.05;
      foodChance -= 0.1;
    }

    if (this.bodyIsFat){
      this.predatorSurvivalChance -= 0.1;
      if (random.nextBoolean()){
        this.energyRequired = 1;
      }
    }
    else{
      this.predatorSurvivalChance += 0.1;
      if (random.nextBoolean() && random.nextBoolean()){
        this.energyRequired = 3;
      }

    }

    if (this.longDistanceFlier){
      this.foodChance += 0.2;
      this.predatorSurvivalChance -= 0.1;
    }
    else{
      this.predatorSurvivalChance += 0.2;
      this.foodChance -= 0.1;
    }

  }

  /**
   * Getter for chances of finding food
   * @return float
   */
  public float GetFoodChance ()
  {
    return this.foodChance;
  }

  /**
   * Getter of survaival Chances
   * @return float
   */
  public float GetSurvivalChance ()
  {
    return this.predatorSurvivalChance;
  }

  /**
   * Initializes a new snipes as the offSpring of a given snipe pairs.
   * @return Snipe
   */
  public Snipe GenerateOffspring ()
  {
    if (this.conservativeGenes){
      offSpring = new Snipe(this.beakIsLong, this.bodyIsFat, this.longDistanceFlier, this.conservativeGenes);
    }
    else{
      if(random.nextBoolean() || random.nextBoolean()){
        offSpring = new Snipe(this.beakIsLong, this.bodyIsFat, this.longDistanceFlier, this.conservativeGenes);
      }
      else{
        offSpring = new Snipe(!this.beakIsLong, !this.bodyIsFat, !this.longDistanceFlier, !this.conservativeGenes);
      }
    }

    return this.offSpring;
  }

 /**
 * Decreases a snipe's energy by the given amount
 * @param e int
 */
  public void LoseEnergy (int e)
  {
    this.energy -=e;

    if(this.energy <= 0){
      this.isAlive = false;
    }

    if(this.energyRequired > this.energy && this.age > 0){
      this.isAlive = false;
    }

  }

}
