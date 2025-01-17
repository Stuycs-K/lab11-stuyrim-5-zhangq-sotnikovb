public class HIV extends Adventurer{
  int viralLoad, viralLoadMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  private HIV(String name, int hp){
    super(name,hp);
    viralLoadMax = 12;
    viralLoad = viralLoadMax/2;
  }

  public HIV(String name){
    this(name,30);
  }

  public HIV(){
    this("HarryIvanVance");
  }

  /*The next 8 methods are all required because they are abstract:*/

  public String getType(){
    return("HIV");
  }
  public String getSpecialName(){
    return "viralLoad";
  }

  public int getSpecial(){
    return viralLoad;
  }

  public void setSpecial(int n){
    if (n > this.getSpecialMax()){
      this.viralLoad = this.getSpecialMax();
    }
    this.viralLoad = n;
  }

  public int getSpecialMax(){
    return this.viralLoadMax;
  }

  public String restoreSpecial(int n){
    this.setSpecial(this.getSpecial() + n);
    return ("Restored self viralLoad by " + n + "sp.");
  }

  public void applyDamage(int n){
    this.setHP(this.getHP()-n);
  }

  /*Deal 1-3 damage to opponent*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*3)+1;
    damage = damage * immuneSystem;
    other.applyDamage(damage);
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
      return this.getName() +" puked out virus to "+other.getName()+", but nothing seems to happen now...";
    }else{
      return "Failed to use special attack. Instead "+attack(other);
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
    other.applyDamage(1);
    }

}
