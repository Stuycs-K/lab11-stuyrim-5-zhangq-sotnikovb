import java.util.*;
public class CodeWarrior extends Adventurer{
  int caffeine, caffeineMax;
  String preferredLanguage;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public CodeWarrior(String name, int hp, String language){
    super(name,hp);
    caffeineMax = 12;
    caffeine = caffeineMax/2;
    preferredLanguage = language;
  }

  public CodeWarrior(String name, int hp){
    this(name,hp,"c++");
  }

  public CodeWarrior(String name){
    this(name,24);
  }

  public CodeWarrior(){
    this("Carmack");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "caffeine";
  }

  public int getSpecial(){
    return caffeine;
  }

  public void setSpecial(int n){
    caffeine = n;
  }

  public int getSpecialMax(){
    return caffeineMax;
  }

  public String restoreSpecial(int n){
    this.setSpecial(this.getSpecial() + n);
    return ("Restored self viralLoad by " + n + "sp.");
  }

  public String getType(){
    return "CodeWarrior";
  }
  /*Deal 2-7 damage to opponent, restores 2 caffeine*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*6)+2;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. They then take a sip of their coffee.";
  }

  /*Deal 3-12 damage to opponent, only if caffeine is high enough.
  *Reduces caffeine by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(Math.random()*5+Math.random()*5)+3;
      other.applyDamage(damage);
      return this + " used their "+preferredLanguage+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough caffeine to use the ultimate code. Instead "+attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return "Gives a coffee to "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" drinks a coffee to restores "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }

  public String takeTurn(ArrayList<Adventurer> own, ArrayList<Adventurer> enemies)
    {
      if (getHP()<6)
        return support();
      else if (getSpecial()>10)
        return specialAttack(enemies.get((int)(Math.random()*3)));
      else
        if(Math.random()*2 == 0)
          return support(own.get((int)(Math.random()*3)));
        else
          return attack(enemies.get((int)(Math.random()*3)));
    }
}
