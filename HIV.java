import java.util.*;
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

  public String restoreSpecial(int n){
    this.setSpecial(this.getSpecial() + n);
    return ("Restored self viralLoad by " + n + "sp.");
  }

  /*Deal 1-3 damage to opponent*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*3)+1;
    damage = (int)(damage * getImmuneSystem());
    other.applyDamage(damage);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Choose an enemy, and after 3 turns deal 1 points of damage for 1 turn and get a damage debuff for 5 turns to it.
  *If the enemy support another enemy in those 3 turns, it can infect the other enemy.
  *Reduces viralLoad by 6.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 6){
      setSpecial(getSpecial()-6);
      other.setInfected (7);
      return this +" puked out virus to "+other.getName()+", but nothing seems to happen now...";
    }else{
      return "Failed to use special attack. Instead "+attack(other);
    }

  }
  /*Increase ally damage by 20% of the original */
  public String support(Adventurer other){
	String statement = this+" transfer interstitial fluid to "+other+" and increases ally damage by 1.2x";
	if (this.getInfected()>0){
		other.setInfected(7);
		statement = this + " infected " + other.getName()+ "\n" +statement;
    }
    other.setImmuneSystem(other.getImmuneSystem()+0.2);
    return statement;
  }

  /*Restores 2 special and 4 hp to self.*/
  public String support(){
    int hp = 4;
    setHP(getHP()+hp);
    return this+" gained interstitial fliud and restores "+restoreSpecial(2)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }

  //Decrease enemy damage by 20% of max and deal 1 damage
  public static void spAttackEffect (Adventurer other){
    other.setImmuneSystem(other.getImmuneSystem()-0.2);
    other.applyDamage(1);
    }

    public String takeTurn(ArrayList<Adventurer> own, ArrayList<Adventurer> enemies)
    {
      if (getHP()<6)
        return support();
      else if (getSpecial()>9)
        return specialAttack(enemies.get((int)(Math.random()*3)));
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
