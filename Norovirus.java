public class Norovirus extends Adventurer{
  int viralLoad, viralLoadMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  private Norovirus(String name, int hp){
    super(name,hp);
    viralLoadMax = 12;
    viralLoad = viralLoadMax/2;
  }

  public Norovirus(String name){
    this(name,26);
  }

  public Norovirus(){
    this("Noroboy");
  }

  /*The next 8 methods are all required because they are abstract:*/
  
  public String getType(){
    return("Norovirus");
  }
  
  public String getSpecialName(){
    return "viralLoad";
  }

  public int getSpecial(){
    return viralLoad;
  }
  
 public String restoreSpecial(int n){
    this.setSpecial(this.getSpecial() + n);
    return ("Restored self viralLoad by " + n + "sp."); 
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

  public void applyDamage(int n){
    this.setHP(this.getHP()-n);
  }
  
  /*Deal 5 damage to opponent*/
  public String attack(Adventurer other){
    int damage = 5;
    damage = damage * immuneSystem;
    other.applyDamage(damage);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. They then regained 2 chunks of viralLoad";
  }

  /*Choose an enemy, and it skips a turn. 
  *Reduces viralLoad by 6.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 6){
      setSpecial(getSpecial()-6);
      other.setHP(other.getHP()-12);
      return this.getName() +" struck toward "+other.getName()+", dealing 12 points of damage";
    }else{
      return "Failed to use special attack. Instead "+attack(other);
    }

  }
  /*restore 4sp to an ally*/
  public String support(Adventurer other){
    other.setSpecial(other.getSpecial()+4);
    return "transfer interstitial fluid to "+other+" and restored 4 chunks of viral load";
  }
  
  /*Restores 3 hp to self.*/
  public String support(){
    int hp = 3;
    setHP(getHP()+hp);
    return this+" gained interstitial fliud and restores 3 chunks of viral load";
  }
}













