
public class Player {
	private int gold; 
	private int num ; 
	private String name; 
	private int numCastles; 
	private int numBarracks; 
	private int numStables; 
	private int numTowns; 
	private int numSoldiers; 
	private int numKnights; 
	private int soldierCapacity; 
	private int roundCost; 
	private int profit; 
	private int knightCapacity; 
	
	
	public Player( String name , int num) {  
		gold = 500; 
		this.num = num; 
		this.name = name; 
		numCastles = 1; 
		numBarracks = 0 ; 
		numStables = 0; 
		numTowns = 2; 
		numSoldiers = 2; 
		numKnights = 0 ; 
		soldierCapacity = 2; 
		roundCost = 200; 
		profit = 200; 
		knightCapacity = 0 ; 
	}
	
	public void deductPurchase(int n) { 
		setGold(getGold() - n) ; 
	}
	
	public void addProfit() { 
		setGold(getGold() + profit) ; 
	}
	
	public void maintain() { 
		setGold(getGold() - roundCost); 
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public int getNumCastles() {
		return numCastles;
	}
	
	public void buyCastle() { 
		numCastles++ ; 
		gold -= 2000; 
		roundCost += 100; 
	}
	
	public void destroyCastle() { 
		numCastles --; 
		roundCost -= 100; 
	}


	public int getNumBarracks() {
		return numBarracks;
	}
	
	public void buyBarracks() { 
		numBarracks++; 
		gold -= 200;  
		roundCost += 50; 
		soldierCapacity += 2; 
	}
	
	public void destroyBarracks() { 
		numBarracks--; 
		roundCost -= 50 ; 
		soldierCapacity -= 2 ; 
	}

	public int getNumStables() {
		return numStables;
	}
	
	public void buyStable() { 
		numStables++; 
		gold -= 400 ; 
		roundCost += 100; 
		knightCapacity += 2; 
	}
	
	public void destroyStable() { 
		numStables --; 
		roundCost -= 100; 
		knightCapacity -= 2; 
	}

	public int getNumTowns() {
		return numTowns;
	}
	
	public void buyTown() { 
		gold -= 100; 
		profit += 100; 
		numTowns ++; 
	}
	
	public void destroyTown() { 
		numTowns --; 
		profit -= 100; 
	}

	public int getNumSoldiers() {
		return numSoldiers;
	}
	
	public void buySoldiers() { 
		gold -= 50; 
		roundCost += 25; 
	}
	
	public void killSoldiers() { 
		roundCost -= 25; 
	}

	public int getNumKnights() {
		return numKnights;
	}
	
	public void buyKnights() { 
		gold -= 100; 
		roundCost += 50; 
	}
	
	public void killKnights() { 
		roundCost -= 50; 
	}

	public int getSoldierCapacity() {
		return soldierCapacity;
	}
	
	public int getKnightCapacity() { 
		return knightCapacity; 
	}
	public int getRoundCost() {
		return roundCost; 
	}
	
	public int getProfit() { 
		return profit; 
	}

}
