public class COVID extends Adventurer{
  int viralLoad, viralLoadMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  private COVID(String name, int hp){
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
  
  public String getType(){
    return("COVID");
  }
  
  public String getSpecialName(){
    return "viralLoad";
  }

  public int getSpecial(){
    return viralLoad;
  }
  
  public String restoreSpecial(int n){
    this.setSpecial(this.getSpecial() + n);
    return ("Restored self viralLoad by " + n + "sp. Now viralLoad: "+ this.getSpecial() + "/" + this.getSpecialMax());
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

  /*Deal 4-7 damage to opponent, restores 2 viralLoad*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*4)+4;
    damage = damage * immuneSystem;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. They then regained 2 chunks of viralLoad";
  }

  /*Choose an enemy, and deal 12 points of damage. 
  *Reduces viralLoad by 10.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 10){
      setSpecial(getSpecial()-10);
      other.applyDamage
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













