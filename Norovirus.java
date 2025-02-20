import java.util.*;
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

  /*Deal 5 damage to opponent*/
  public String attack(Adventurer other){
    int damage = 5;
    damage = (int)(damage * getImmuneSystem());
    other.applyDamage(damage);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Choose an enemy, and it skips a turn.
  *Reduces viralLoad by 6.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 6){
      setSpecial(getSpecial()-6);
      other.setTurn(0);
      return this +" vomits toward "+other.getName()+", causing it to skip a round";
    }else{
      return "Failed to use special attack. Instead "+attack(other);
    }

  }
  /*restore 4sp to an ally*/
  public String support(Adventurer other){
	String statement = this+" transfer interstitial fluid to "+other+" and restored 4 chunks of viral load";
	if (this.getInfected()>0){
		other.setInfected(7);
		statement = this + " infected " + other.getName()+ "\n" +statement;
    }
    other.setSpecial(other.getSpecial()+4);
    return statement;
  }

  /*Restores 2sp and 3 hp to self.*/
  public String support(){
    setSpecial(getSpecial()+2);
    setHP(getHP()+3);
    return this+" gained interstitial fliud and restores 3 chunks of viral load";
  }

  public String takeTurn(ArrayList<Adventurer> own, ArrayList<Adventurer> enemies)
    {
      if (getHP()<6){
        return support();
	  }
      else
      {
		int ownIndex = (int)(Math.random()*3);
		int enemiesIndex = (int)(Math.random()*3);
		while(own.get(ownIndex).getHP() == 0){
			ownIndex = (int)(Math.random()*3);
		}
		while(enemies.get(enemiesIndex).getHP() == 0){
			enemiesIndex = (int)(Math.random()*3);
		}
        if((int)(Math.random()*2) == 0){
			return support(own.get(ownIndex));
		}
        else
          return attack(enemies.get(enemiesIndex));
      }
    }
}
