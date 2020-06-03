import java.util.Scanner; 

public class castleDefense {
	
	public static void main(String[] args) { 
		System.out.println("Welcome to Castle Defense! In this game, it is your goal to try and destroy the castle of your opponent. In order to do this, \nyou must attack their castle with 16 soldiers and 8 knights. You will begin the game with 500 gold, 2 towns, 1 castle, and \ntwo units of troops. Each round, you have three turns. During these three turns, the player can either attack the buildings \nor troops of their opponent, use their turn to build structures, or build up their troops during their turn. In order to house \ntroops, barracks (for soldiers) must be used and stables (for knights) must be used. Barracks have a capacity of 2 and stables \nhave a capacity of 4. Below are costs. If costs ever get to 0 then the game will be lost!" 
				+ "\nBuildings (In order of hardest to destroy):\n"
					+ "• Town (100 G, no upkeep)\n"
					+"• Castle(2000G, 100 G upkeep)Stables(400G, 100 G upkeep)\n "
				+ "Troops:\n "
					+"• Soldiers - upkeep (25 G per unit) \n"
					+ "• Knights - Upkeep (50G per unit)\n");
		Scanner s = new Scanner(System.in); 
		System.out.println("Welcome player 1! Please enter your name"); 
		String name1 = s.nextLine(); 
		Player player1 = new Player(name1, 1); 
		System.out.println("Weclome player2! Please enter your name"); 
		String name2 = s.nextLine(); 
		Player player2 = new Player (name2, 2); 
		int roundNum = 1; 
		while (loss(player1) || loss(player2)) { // runs while no one has lost.
			if (roundNum != 1 ) { 
				updateGold(player1); 
				updateGold(player2); 
			}
			System.out.println("This is round number " + roundNum); 
			System.out.println(player1.getName() + " , it is your turn!"); 
			turn(player1, player2, s); 			
			if (!(loss(player1) || loss(player2))) { 
				determineLoss(player1, player2) ; 
			}
			System.out.println(player2.getName() + ", it is your turn!"); 
			turn(player2, player1, s); 
			roundNum++; 
		}
		determineLoss(player1, player2); 
		
	}
	
	public static void updateGold(Player p ) { 
		p.addProfit();
		p.maintain();
	}
	
	public static void determineLoss(Player p1, Player p2) { 
		if (!loss(p1)) { 
			System.out.println("Congratulations " + p2.getName()+", you won!"); 
		} else if (!loss(p2) ) { 
			System.out.println("Congratulations " + p1.getName()+", you won!"); 
		}
	}
	
	public static boolean loss(Player p1) { // returns false if player has lost 
		return p1.getNumCastles() != 0 && p1.getGold() >= 0 ; 
	}
	
	public static void turn(Player p1, Player p2, Scanner s) { 
		System.out.print(playerStats(p1)); 
		int index = 0 ; 
		while (index < 3  ) { // repeats until three of the players moves have been used
			System.out.println("This is turn " + (index + 1 ) + ". What would you like to do, "
					+ "would you like to: (1) Build, (2) destroy an enemny building, (3) "
					+ "attack enemy troops, (4) strengthen your army, or (5) skip? "); 
			int input1 = s.nextInt(); 
			if (input1 == 1) {
				System.out.println("Would you like to build (1) a town, (2) barracks, (3) a stable,"
						+ " or (4) a castle"); 
				int n = s.nextInt(); 
				int gold = p1.getGold() - p1.getRoundCost(); 
				if (n == 1) { 
					if(gold >= 100) { 
						p1.buyTown();
						index++; 
					} else { 
						failure("gold"); 
					}
					
				} else if (n==2) { 
					if (gold >= 250 ) { 
						p1.buyBarracks(); 
						index++;
					} else { 
						failure("gold"); 
					}
					
				} else if (n==3 ) { 
					if (gold >= 500) { 
						p1.buyStable(); 
						index++;
					} else { 
						failure("gold"); 
					}
					
				} else if (n==4) { 
					if (gold >= 2100) { 
						p1.buyCastle();
						index++;
					} else {
						failure("gold"); 
					}
				}
				
			} else if (input1 == 2 ) { 
				System.out.println("Would you like to destroy a (1) town, (2) barracks, (3) "
						+ "stable, or (4) a castle?"); 
				int n = s.nextInt(); 
				int so = p1.getNumSoldiers(); 
				int k = p1.getNumKnights(); 
				if (n==1) {
					if (so > 0 ) { 
						p2.destroyTown();
						index++; 
					} else { 
						failure("soldiers"); 
					}
					
				} else if (n==2) { 
					if (so >= 2) { 
						p2.destroyBarracks();
						index++; 
					} else { 
						failure("soldiers"); 
					}
					
				} else if (n==3 ) { 
					if (so >= 4 || k >= 2 ) { 
						p2.destroyStable();
						index++; 
					}else { 
						failure("soldiers or knights"); 
					}
					
				} else if (n==4) { 
					if (so >= 16 && k >= 8 ) { 
						p2.destroyCastle();
						index ++ ; 
					} else { 
						failure ("soldiers and knights"); 
					}
				}
				
			} else if (input1 == 3 ) { 
				System.out.println("Would you like to (1) attack soldiers or "
						+ "(2) attack knights? "); 
				int s1 = p1.getNumSoldiers(); 
				int k1 = p1.getNumKnights(); 
				int s2 = p2.getNumSoldiers(); 
				int k2 = p2.getNumSoldiers(); 
				int n = s.nextInt(); 				
				if (n==1) {
					System.out.println("How many soldiers are you going to attack?") ; 
					int r = s.nextInt(); 
					if (s1 >= r && s2 >= r) { 
						System.out.println("You have defeated " + r + "soldiers."); 
						for (int i = 0 ; i < r; i++ ) { 
							p1.killSoldiers();
							p2.killSoldiers();
							index++; 
						}
					} else { 
						failure("soldiers or your opponent does not have enought soliders"); 
					}
					
				} else if (n==2) { 
					System.out.println("How many knights are you going to attack?") ; 
					int r = s.nextInt(); 
					if (k1 >= r && k2 >= r) { 
						System.out.println("You have defeated " + r + "knights."); 
						for (int i = 0 ; i < r; i++ ) { 
							p1.killKnights();
							p2.killKnights();
							index++; 
						}
					} else { 
						failure("knights or your opponent does not have enought knights"); 
					}
				}
				
			} else if (input1 == 4 ) { 
				System.out.println("Would you like to (1) raise soldiers or (2) raise knights?"); 
				int n = s.nextInt(); 
				int gold = p1.getGold() - p1.getRoundCost(); 
				int so = p1.getSoldierCapacity(); 
				int k = p1.getKnightCapacity(); 
				if (n==1 ) { 
					System.out.println("How many soldier units would you like to raise? "); 
					int r = s.nextInt(); 
					if (r * 75 > gold) { 
						if (so >= r ) { 
							failure("soldier capacity");
						}
						for (int i = 0 ; i < r ; i++ ) { 
							p1.buySoldiers();
						}
						index++; 
					} else { 
						if (so < r) { 
							failure ("soldier capacity"); 
						}
						failure("gold"); 
					}
				}else { 
					System.out.println("How many soldier units would you like to raise? "); 
					int r = s.nextInt(); 
					if (r * 150 > gold) { 
						if (k >= r ) { 
							failure("knight capacity");
						}
						for (int i = 0 ; i < r ; i++ ) { 
							p1.buyKnights();
						}
						index++; 
					}else { 
						if (k < r) { 
							failure ("knight capacity"); 
						}
						failure("gold"); 
					}
					
				}
				
			} else if (input1 == 5) { 
				System.out.println("Your turn has been skipped."); 
				index++; 
			}
			System.out.println(playerStats(p1)); 
		}
	}
	public static String failure(String problem) { 
		return "You do not have enough " + problem + " to do this."; 
	}
	public static String playerStats(Player p ) { 
		String output = ""; 
		output += p.getName() + ", these are your stats.\n"; 
		output += "Gold: " + p.getGold() + "\n"; 
		output += "Number of Towns: " + p.getNumTowns() + "\n"; 
		output += "Number of Barracks: " + p.getNumBarracks() + "\n"; 
		output += "Number of Stables: " + p.getNumStables() + "\n"; 
		output += "Number of Soldier Units: " + p.getNumSoldiers() + "\n"; 
		output += "Number of Knight Units: " + p.getNumKnights() + "\n"; 
		return output; 
	}
}
