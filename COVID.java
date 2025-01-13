public class COVID extends Adventurer{
  int viralLoad, viralLoadMax;
  ArrayList<Adventurer> Ally, Enemy;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public COVID(String name, int hp){
    super(name,hp);
    viralLoadMax = 20;
    viralLoad = viralLoadMax/2;
  }

  public COVID(String name){
    this(name,18);
  }

  public COVID(){
    this("COdyVInnyDan");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "viralLoad";
  }

  public int getSpecial(){
    return viralLoad;
  }
  
 public String restoreSpecial(int n){
    this.setSpecial(this.getSpecial() + 2);
    return ("Restored self viralLoad by 2sp. Now viralLoad: "+ this.getSpecial() + "/" + this.getSpecialMax());
  }

  public void setSpecial(int n){
    if (n > this.getSpecialMax()){
      this.viralLoad = this.getSpecialMax()
    }
    this.viralLoad = n;
  }

  public int getSpecialMax(){
    return this.viralLoadMax;
  }

  /*Deal 4-7 damage to opponent, restores 2 viralLoad*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*4)+4;
    damage = damage * immuneSystem;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. They then regained 2 chunks of viralLoad.";
  }

  /*Choose an enemy, and after 3 turns deal 1 points of damage for 1 turn and get a damage debuff for 5 turns to it. 
  *If the enemy support another enemy in those 3 turns, it can infect the other enemy.  
  *Reduces viralLoad by 6.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 6){
      setSpecial(getSpecial()-6);
      other.setInfected (5);
      return this + " used their "+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough caffeine to use the ultimate code. Instead "+attack(other);
    }

  }
  /*Increase ally damage by 20% of the original */
  public String support(Adventurer other){
    other.setImmuneSystem(other.getImmuneSystem()+0.2);
    return "transfer interstitial fluid to "+other+" and increases ally damage by 1.2x";
  }
  
  /*Restores 2 special and 4 hp to self.*/
  public String support(){
    int hp = 4;
    setHP(getHP()+hp);
    return this+" gained interstitial fliud and restores "+restoreSpecial(2)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
  
  //Decrease enemy damage by 20% of max and deal 1 damage
  public void spAttackEffect (Adventurer other){
    other.setImmuneSystem(other.getImmuneSystem-0.2);
    }
  
}













