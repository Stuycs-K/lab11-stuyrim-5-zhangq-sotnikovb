import java.util.*;
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
    return ("Restored self viralLoad by " + n + "sp.");
  }

  public void setSpecial(int n){
    if (n > this.getSpecialMax()){
      this.viralLoad = this.getSpecialMax();
    }
	else if (n < 0){
		this.viralLoad = 0;
	}
	else{
		this.viralLoad = n;
	}
  }

  public int getSpecialMax(){
    return this.viralLoadMax;
  }

  /*Deal 4-7 damage to opponent,*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*4)+4;
    damage = (int)(damage * getImmuneSystem());
    other.applyDamage(damage);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Choose an enemy, and deal 12 points of damage.
  *Reduces viralLoad by 10.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 10){
      setSpecial(getSpecial()-10);
      other.applyDamage((int)(12*getImmuneSystem()));
      return this +" struck toward "+other.getName()+", dealing 12 points of damage";
    }else{
      return "Failed to use special attack. Instead "+attack(other);
    }

  }
  /*restore 4Hp to an ally*/
  public String support(Adventurer other){
	  String statement = this + " transfer interstitial fluid to "+other+" and restored 4Hp";
	  if (this.getInfected()>0){
		  other.setInfected(7);
		  statement = this + " infected " + other.getName()+ "\n" +statement;
    }
    other.setHP(other.getHP()+4);
    return statement;
  }

  /*Restores 3 hp to self.*/
  public String support(){
    int hp = 3;
    setHP(getHP()+hp);
    return this+" gained interstitial fliud and restores 3hp";
  }

  public String takeTurn(ArrayList<Adventurer> own, ArrayList<Adventurer> enemies)
  {
    if (getHP()<6)
      return support();
    else if (getSpecial()>14)
      return specialAttack(enemies.get((int)(Math.random()*3)));
    for (Adventurer ally : own) {
      if (ally!=this && ally.getHP()<6 && ally.getHP()>0)
        return support(ally);
    }
	int enemiesIndex = (int)(Math.random()*3);
	while(enemies.get(enemiesIndex).getHP() == 0){
		enemiesIndex = (int)(Math.random()*3);
	}
    return attack(enemies.get(enemiesIndex));
  }
}
