public class HIV extends Adventurer{
  int viralLoad, viralLoadMax, ImmuneSystem, infected;
  ArrayList<Adventurer> Ally, Enemy;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public HIV(String name, int hp){
    super(name,hp);
    viralLoadMax = 12;
    viralLoad = viralLoadMax/2;
    ImmuneSystem = 1;
    infected = 0;
  }

  public HIV(String name){
    this(name,30);
  }

  public HIV(){
    this("HarryIvanVance");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "viralLoad";
  }
  
  public int setInfected(int infected){
    this.infected = infected;
  }
  
  public int getInfected(){
    return this.infected;
  }
  

  public int getSpecial(){
    return viralLoad;
  }

  public void setSpecial(int n){
    this.viralLoad = n;
  }

  public int getSpecialMax(){
    return this.viralLoadMax;
  }
  
  public int getImmuneSystem(){
    return this.ImmuneSystem
  }
  
  public void setImmuneSystem(int n){
    this.ImmuneSystem += n;
  }

  /*Deal 1-3 damage to opponent, restores 2 viralLoad*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*3)+1;
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
  /*Increase ally damage to 1.2x */
  public String support(Adventurer other){
    // call function to add damage
    return "transfer interstitial fluid to "+other+" and increases ally damage by 1.2x";
  }
  
  /*Restores 2 special and 4 hp to self.*/
  public String support(){
    int hp = 4;
    setHP(getHP()+hp);
    return this+" gained interstitial fliud and restores "+restoreSpecial(2)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
  
  //Decrease enemy damage to 0.8x and deal 1 damage
  public String spAttackEffect (Adventurer other){
    
  }
  
}













