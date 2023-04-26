import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DungeonGame {
	public double worrior_health = 175;
	public double worrior_Max_attack_stat = 30;
	public double worrior_Min_attack_stat = 20;
	public int worrior_speed_stat = 20;
	public double mage_health = 175;
	public double mage_Max_attack_stat = 30;
	public double mage_Min_attack_stat = 20;
	public double mage_Max_magic_stat = 50;
	public double mage_Min_magic_stat = 30;
	public int mage_speed_stat = 10;
	public double potion_count = 7;
	public double ether_count = 7;
	public double mana_count = 14;
	public double goblin_health = 100;
	public double goblin_Max_attack_stat = 15;
	public double goblin_Min_attack_stat = 10;
	public int goblin_speed_stat = 5;
	public double Bat_health = 120;
	public double Bat_Max_attack_stat = 20;
	public double Bat_Min_attack_stat = 15;
	public int Bat_speed_stat = 12;
	public double Mimic_health = 130;
	public double Mimic_Max_attack_stat = 25;
	public double Mimic_Min_attack_stat = 20;
	public int Mimic_speed_stat = 20;
	public double gargoyle_health = 140;
	public double gargoyle_Max_attack_stat = 30;
	public double gargoyle_Min_attack_stat = 25;
	public int gargoyle_speed_stat = 25;
	public double dragon_health = 160;
	public double dragon_Max_attack_stat = 35;
	public double dragon_Min_attack_stat = 30;
	public int dragon_speed_stat = 30;
	public int goblin_attack_choice;
	public int room_1_key = 0;
	public int room_2_key = 0;
	public int room_3_key = 0;
	public int room_4_key = 0;
	public int boss_room_key = 0;
	public int bomb_count = 0;
	private Scanner input;
	public DungeonGame(Scanner input) {
        this.input = input;
    }
	

	public void start() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String start;
		System.out.println("welcome to dungeon crawler demo");
		System.out.println("press any key to begin the game");
		start = (in.readLine());
		opening_text();
	}

	public void opening_text() throws IOException {
		System.out.println("You find your self in a cold and damp dungeon with only a sword to defend yourself");
		System.out.println("Your objective is to slay the dragon and claim the gold at the end of the dungon.");
		System.out.println("Good luck!!");
		first_room_initial_visit();
	}

	public void first_room_initial_visit() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String battle_choice;
		String room_choice;
		String door_choice;
		boolean doagain = true;
		System.out.println("A threatening goblin has walked into the room. Time to battle!!!");
		do {
			System.out.println("Please select the action that you will do to fight the goblin");
			System.out.println("Attack");
			System.out.println("Heal");
			System.out.println("Check goblin stats");
			battle_choice = (in.readLine());
			String heal_choice;
			switch (battle_choice) {
				case "Check goblin stats":
				case "check goblin stats":
					System.out.println("The goblin's health is " + goblin_health);
					System.out.println("The goblin's minimum attack stat is " + goblin_Min_attack_stat);
					System.out.println("The goblin's maximuim attack stat is " + goblin_Max_attack_stat);
					break;
				case "Attack":
				case "attack":
					// create a priority queue for the goblin and the worrior and the mage to see
					// who goes first
					PriorityQueue<Integer> goblin_queue = new PriorityQueue<Integer>(3, Collections.reverseOrder());
					// add the worrior and the mage and the goblin to the queue
					goblin_queue.add((int) worrior_speed_stat);
					goblin_queue.add((int) mage_speed_stat);
					goblin_queue.add((int) goblin_speed_stat);
					// create a while loop to see who goes first
					while (!goblin_queue.isEmpty()) {
						int attack_order = goblin_queue.peek();
						if (attack_order == worrior_speed_stat) {
							System.out.println("You attack the goblin with your sword");
							goblin_health = goblin_health - Math.floor(Math.random() * (worrior_Max_attack_stat - worrior_Min_attack_stat + 1) + worrior_Min_attack_stat);
							System.out.println("The goblin now has " + goblin_health + " health left");
							goblin_queue.remove(worrior_speed_stat);
						}
						if (attack_order == mage_speed_stat) {
							System.out.println("You attack the goblin with your magic");
							goblin_health = goblin_health - Math.floor(Math.random() * (mage_Max_magic_stat - mage_Min_magic_stat + 1) + mage_Min_magic_stat);
							System.out.println("The goblin now has " + goblin_health + " health left");
							mana_count = mana_count - 1;
							System.out.println("You now have " + mana_count + " mana left");
							mana_count = mana_count - 1;
							goblin_queue.remove(mage_speed_stat);
						}
						if (attack_order == goblin_speed_stat) {
							goblin_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (goblin_attack_choice == 1) {
								System.out.println("The goblin attacks you with its club and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (goblin_Max_attack_stat - goblin_Min_attack_stat + 1) + goblin_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");
							}
							if (goblin_attack_choice == 2) {
								System.out.println("The goblin attacks the mage with its club and knocks you to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (goblin_Max_attack_stat - goblin_Min_attack_stat + 1) + goblin_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");

							}
							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
							goblin_queue.remove(goblin_speed_stat);
						}
					}
					break;
				case "Heal":
				case "heal":
					System.out.println("Who do you want to heal?");
					System.out.println("Worrior");
					System.out.println("Mage");
					heal_choice = (in.readLine());
					switch (heal_choice) {
						case "Worrior":
						case "worrior":
							System.out.println("You heal the worrior");
							worrior_health = worrior_health + 50;
							System.out.println("The worrior now has " + worrior_health + " health left");
							potion_count = potion_count - 1;
							System.out.println("You now have " + potion_count + " potions left");
							goblin_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (goblin_attack_choice == 1) {
								System.out.println("The goblin attacks you with its club and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (goblin_Max_attack_stat - goblin_Min_attack_stat + 1) + goblin_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");

							}
							if (goblin_attack_choice == 2) {
								System.out.println("The goblin attacks the mage with its club and knocks you to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (goblin_Max_attack_stat - goblin_Min_attack_stat + 1) + goblin_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
							}
							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
							break;
						case "Mage":
						case "mage":
							System.out.println("You heal the mage");
							mage_health = mage_health + 50;
							System.out.println("The mage now has " + mage_health + " health left");
							potion_count = potion_count - 1;
							goblin_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (goblin_attack_choice == 1) {
								System.out.println("The goblin attacks you with its club and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (goblin_Max_attack_stat - goblin_Min_attack_stat + 1) + goblin_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");

							}
							if (goblin_attack_choice == 2) {
								System.out.println("The goblin attacks the mage with its club and knocks you to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (goblin_Max_attack_stat - goblin_Min_attack_stat + 1) + goblin_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
							}

							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
							break;
					}
					break;
			}
		} while (goblin_health > 0);
		if (goblin_health <= 0) {
			System.out.println("Congratulations!! You have defeated the goblin!! You are now able to look around the room.");
		}
		System.out.println("Would you like to use a potion to heal yourself before looking around the room? This is your last chance before the next battle");
		room_choice = (in.readLine());
		String heal_choice;
		switch (room_choice) {
			case "Yes":
			case "yes":
				System.out.println("who would you like to heal?");
				System.out.println("Worrior");
				System.out.println("Mage");
				heal_choice = (in.readLine());
				switch (heal_choice) {
					case "Worrior":
					case "worrior":
						if (potion_count > 0 && worrior_health < 120) {
							System.out.println("You use one of your potions and you health heals by 50 points");
							worrior_health = worrior_health + 50;
							System.out.println("The worrior now has " + worrior_health + " health left");
							potion_count = potion_count - 1;
							System.out.println("You now have " + potion_count + " potions left");
						}
						if (potion_count < 1 || worrior_health == 120) {
							System.out.println("You are not able to use a potion at this time");
						}
						break;
					case "Mage":
					case "mage":
						if (potion_count > 0 && mage_health < 120) {
							System.out.println("You use one of your potions and you health heals by 50 points");
							mage_health = mage_health + 50;
							System.out.println("The mage now has " + mage_health + " health left");
							potion_count = potion_count - 1;
							System.out.println("You now have " + potion_count + " potions left");
						}
						if (potion_count < 1 || mage_health == 120) {
							System.out.println("You are not able to use a potion at this time");
						}
						break;
					case "Both":
					case "both":
						if (potion_count > 0 && mage_health < 120 && worrior_health < 120) {
							System.out.println("You use one of your potions and you health heals by 50 points");
							mage_health = mage_health + 50;
							worrior_health = worrior_health + 50;
							System.out.println("The mage now has " + mage_health + " health left");
							System.out.println("The worrior now has " + worrior_health + " health left");
							potion_count = potion_count - 2;
							System.out.println("You now have " + potion_count + " potions left");
						}
						if (potion_count < 1 || mage_health == 120 || worrior_health == 120) {
							System.out.println("You are not able to use a potion at this time");
						}
						break;
				}

			case "No":
			case "no":
				System.out.println("You decide not to heal your party");
				break;
		}
		System.out.println("Would you like to use an ether");
		String response = (in.readLine());
		switch (response) {
			case "Yes":
			case "yes":
				if (ether_count > 0 && mage_health < 120) {
					System.out.println("You use one of your ethers and you manna replenishes by 50 points");
					mana_count = mana_count + 50;
					System.out.println("The mage now has " + mana_count + " manna left");
					ether_count = ether_count - 1;
					System.out.println("You now have " + ether_count + " ethers left");
				}
				if (ether_count < 1 || mana_count == 120) {
					System.out.println("You are not able to use an ether at this time");
				}
				break;
			case "No":
			case "no":
				System.out.println("You decide not to use an ether");
				break;
		}
		System.out.println("You look around the room see a door in front of you and a door to the left of you");
		System.out.println("You also notice a big crack in the wall to the right.");
		System.out.println("The final thing that you notice is a treasue chest in the corner of the room.");
		System.out.println("Would you like to use an ether to replenish your manna?");
		do {
			System.out.println("What action do you want to do next");
			System.out.println("type 'front' to check the door in front of you. Type 'left' for the door on the left. Type chest to look inside the chest in the corner. Type 'crack' to go look at the crack in the wall");
			door_choice = (in.readLine());
			switch (door_choice) {
				case "Crack":
				case "crack":
					System.out.println("You can see an item just beyond the crack of a double layered wall but cannot quie make out what it is");
					System.out.println("If only you had an item to blast away the wall to get to the item");
					break;
				case "Chest":
				case "chest":
					if (room_2_key == 1)
						System.out.println("You already got this item");
					if (room_2_key == 0)
						System.out.println("You walk over to the chest and open it to find a key to one of the doors");
					room_2_key = 1;
					break;
				case "Front":
				case "front":
					if (room_4_key == 1) {
						System.out.println("You try to put the key into the lock but the door does not seem to want to open");
					}
					if (room_4_key == 0) {
						System.out.println("you do not seem to have the correct key for this door");
					}
					break;
				case "Left":
				case "left":
					if (room_2_key == 1) {
						System.out.println("You take the key over to the door on the left and put it in the lock");
						second_room_initial_visit();
					}
					if (room_2_key == 0) {
						System.out.println("you do not seem to have the correct key for this door");
					}
			}
		} while (doagain == true);
	}

	public void second_room_initial_visit() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String battle_choice;
		String room_choice;
		String door_choice;
		boolean doagain = true;
		System.out.println("As you walk into the now room, the door slams behind you. you stry to re open it witht the key but it seems to magically sealed.");
		System.out.println("A big bat swoops down from the top of the room. Time to battle!!!");
		do {
			System.out.println("Please select the action that you will do to fight the Bat");
			System.out.println("Attack");
			System.out.println("Heal");
			System.out.println("Check Bat stats");
			battle_choice = (in.readLine());
			String heal_choice;
			switch (battle_choice) {
				case "Check bat stats":
				case "check bat stats":
					System.out.println("The Bat's health is " + Bat_health);
					System.out.println("The Bat's minimum attack stat is " + Bat_Min_attack_stat);
					System.out.println("The Bat's maximuim attack stat is " + Bat_Max_attack_stat);
					break;
				case "Attack":
				case "attack":
					PriorityQueue<Integer> Bat_queue = new PriorityQueue<Integer>(3, Collections.reverseOrder());
					Bat_queue.add((int) worrior_speed_stat);
					Bat_queue.add((int) mage_speed_stat);
					Bat_queue.add((int) goblin_speed_stat);
					while (!Bat_queue.isEmpty()) {
						int attack_order = Bat_queue.peek();
						if (attack_order == worrior_speed_stat) {
							System.out.println("You attack the Bat with your sword");
							Bat_health = Bat_health - Math.floor(Math.random() * (worrior_Max_attack_stat - worrior_Min_attack_stat + 1) + worrior_Min_attack_stat);
							System.out.println("The Bat now has " + Bat_health + " health left");
							Bat_queue.remove(worrior_speed_stat);
						}
						if (attack_order == mage_speed_stat) {
							System.out.println("You attack the Bat with your magic");
							Bat_health = Bat_health - Math.floor(Math.random() * (mage_Max_magic_stat - mage_Min_magic_stat + 1) + mage_Min_magic_stat);
							System.out.println("The Bat now has " + Bat_health + " health left");
							mana_count = mana_count - 1;
							System.out.println("You now have " + mana_count + " mana left");
							mana_count = mana_count - 1;
							Bat_queue.remove(mage_speed_stat);
						}
						if (attack_order == Bat_speed_stat) {
							int bat_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (bat_attack_choice == 1) {
								System.out.println("The bat attacks you with its club and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (Bat_Max_attack_stat - Bat_Min_attack_stat + 1) + Bat_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");
								Bat_queue.remove(Bat_speed_stat);
							}
							if (bat_attack_choice == 2) {
								System.out.println("The Bat attacks the mage with its club and knocks you to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (Bat_Max_attack_stat - Bat_Min_attack_stat + 1) + Bat_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
								Bat_queue.remove(Bat_speed_stat);
							}
							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
						}
					}
					break;
				case "Heal":
				case "heal":
					System.out.println("Who do you want to heal?");
					System.out.println("Worrior");
					System.out.println("Mage");
					heal_choice = (in.readLine());
					int Bat_attack_choice;
					switch (heal_choice) {
						case "Worrior":
						case "worrior":
							System.out.println("You heal the worrior");
							worrior_health = worrior_health + 50;
							System.out.println("The worrior now has " + worrior_health + " health left");
							potion_count = potion_count - 1;
							System.out.println("You now have " + potion_count + " potions left");
							Bat_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (Bat_attack_choice == 1) {
								System.out.println("The Bat attacks you with its club and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (Bat_Max_attack_stat - Bat_Min_attack_stat + 1) + Bat_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");
							}
							if (Bat_attack_choice == 2) {
								System.out.println("The Bat attacks the mage with its club and knocks you to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (Bat_Max_attack_stat - Bat_Min_attack_stat + 1) + Bat_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
							}
							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
							break;
						case "Mage":
						case "mage":
							System.out.println("You heal the mage");
							mage_health = mage_health + 50;
							System.out.println("The mage now has " + mage_health + " health left");
							potion_count = potion_count - 1;
							Bat_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (Bat_attack_choice == 1) {
								System.out.println("The Bat attacks you with its club and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (Bat_Max_attack_stat - Bat_Min_attack_stat + 1) + Bat_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");
							}
							if (Bat_attack_choice == 2) {
								System.out.println("The Bat attacks the mage with its club and knocks you to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (Bat_Max_attack_stat - Bat_Min_attack_stat + 1) + Bat_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
							}
							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
							break;
					}
					break;
			}
		} while (Bat_health > 0);
		if (Bat_health <= 0) {
			System.out
					.println("Congratulations!! You have defeated the Bat!! You are now able to look around the room.");
		}
		System.out.println(
				"Would you like to use a potion to heal yourself before looking around the room? This is your last chance before the next battle");
		room_choice = (in.readLine());
		String heal_choice;
		switch (room_choice) {
			case "Yes":
			case "yes":
				System.out.println("who would you like to heal?");
				System.out.println("Worrior");
				System.out.println("Mage");
				heal_choice = (in.readLine());
				switch (heal_choice) {
					case "Worrior":
					case "worrior":
						if (potion_count > 0 && worrior_health < 120) {
							System.out.println("You use one of your potions and you health heals by 50 points");
							worrior_health = worrior_health + 50;
							System.out.println("The worrior now has " + worrior_health + " health left");
							potion_count = potion_count - 1;
							System.out.println("You now have " + potion_count + " potions left");
						}
						if (potion_count < 1 || worrior_health == 120) {
							System.out.println("You are not able to use a potion at this time");
						}
						break;
					case "Mage":
					case "mage":
						if (potion_count > 0 && mage_health < 120) {
							System.out.println("You use one of your potions and you health heals by 50 points");
							mage_health = mage_health + 50;
							System.out.println("The mage now has " + mage_health + " health left");
							potion_count = potion_count - 1;
							System.out.println("You now have " + potion_count + " potions left");
						}
						if (potion_count < 1 || mage_health == 120) {
							System.out.println("You are not able to use a potion at this time");
						}
						break;
					case "Both":
					case "both":
						if (potion_count > 0 && mage_health < 120 && worrior_health < 120) {
							System.out.println("You use one of your potions and you health heals by 50 points");
							mage_health = mage_health + 50;
							worrior_health = worrior_health + 50;
							System.out.println("The mage now has " + mage_health + " health left");
							System.out.println("The worrior now has " + worrior_health + " health left");
							potion_count = potion_count - 2;
							System.out.println("You now have " + potion_count + " potions left");
						}
						if (potion_count < 1 || mage_health == 120 || worrior_health == 120) {
							System.out.println("You are not able to use a potion at this time");
						}
						break;
				}
			case "No":
			case "no":
				System.out.println("You decide not to use a potion");
				break;
		}
		System.out.println("Would you like to use an ether");
		String response = (in.readLine());
		switch (response) {
			case "Yes":
			case "yes":
				if (ether_count > 0 && mage_health < 120) {
					System.out.println("You use one of your ethers and you manna replenishes by 50 points");
					mana_count = mana_count + 50;
					System.out.println("The mage now has " + mana_count + " manna left");
					ether_count = ether_count - 1;
					System.out.println("You now have " + ether_count + " ethers left");
				}
				if (ether_count < 1 || mana_count == 120) {
					System.out.println("You are not able to use an ether at this time");
				}
				break;
			case "No":
			case "no":
				System.out.println("You decide not to use an ether");
				break;
		}
		System.out.println("You look around the room see a door in front of you and a door to the right of you");
		System.out.println("The final thing that you notice is a treasue chest in the corner of the room.");

		do {
			System.out.println("What action do you want to do next");
			System.out.println(
					"type 'behind' to check the door behind of you. Type 'right' for the door on the right. Type chest to look inside the chest in the corner");
			door_choice = (in.readLine());
			switch (door_choice) {
				case "Chest":
				case "chest":
					if (room_3_key == 1)
						System.out.println("You already got this item");
					if (room_3_key == 0)
						System.out.println("You walk over to the chest and open it to find a key to one of the doors");
					room_3_key = 1;
					break;
				case "Right":
				case "right":
					if (room_3_key == 1) {
						System.out.println("You use the key and the door opens up to the next room.");
						Room_3_initial_visit();
					}
					if (room_3_key == 0) {
						System.out.println("you do not seem to have the correct key for this door");
					}
					break;
				case "Behind":
				case "behind":
					System.out.println("You cannot go through that door. it is magically sealed");
					break;
			}
			System.out.println("you go back to the begining of the room");
		} while (doagain = true);
	}

	public void Room_3_initial_visit() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String battle_choice;
		String room_choice;
		String door_choice;
		boolean doagain = true;
		System.out.println("As you walk into the room, the door magically seals intself behind you yet again.");
		System.out.println("You walk further into the room and dont see any new monster in the room. All you see is a chest, a crack in the wall in front of you, and a door on the right.");
		do {
			System.out.println("What will you do next? Type 'check crack' to go inspect the crack in the wall. Type 'right' to check the door on the right. Type 'chest' to check the chest. Type 'behind' to check the door behind you");
			door_choice = (in.readLine());
			switch (door_choice) {
				case "Chest":
				case "chest":
					System.out.println("You walk over to the chest and all of a sudden sharp teeth, arms, and legs sprout out of the it");
					System.out.println("OH NO ITS A MIMIC");
					System.out.println("TIME TO BATTLE");
					do {
						System.out.println("Please select the action that you will do to fight the Mimic");
						System.out.println("Attack");
						System.out.println("Heal");
						System.out.println("Check mimic stats");
						battle_choice = (in.readLine());
						String heal_choice;
						switch (battle_choice) {
							case "Check mimic stats":
							case "check mimic stats":
								System.out.println("The Mimic's health is " + Mimic_health);
								System.out.println("The Mimic's minimum attack stat is " + Mimic_Min_attack_stat);
								System.out.println("The Mimic's maximuim attack stat is " + Mimic_Max_attack_stat);
								break;
							case "Attack":
							case "attack":
								PriorityQueue<Integer> Mimic_queue = new PriorityQueue<Integer>(3, Collections.reverseOrder());
								Mimic_queue.add((int) worrior_speed_stat);
								Mimic_queue.add((int) mage_speed_stat);
								Mimic_queue.add((int) Mimic_speed_stat);
								int attack_order = Mimic_queue.peek();
								while (!Mimic_queue.isEmpty()) {
									if (attack_order == worrior_speed_stat) {
										System.out.println("You attack the Bat with your sword");
										Mimic_health = Mimic_health - Math.floor( Math.random() * (worrior_Max_attack_stat - worrior_Min_attack_stat + 1) + worrior_Min_attack_stat);
										System.out.println("The Bat now has " + Mimic_health + " health left");
										Mimic_queue.remove(worrior_speed_stat);
									}
									if (attack_order == mage_speed_stat) {
										System.out.println("You attack the Bat with your magic");
										Mimic_health = Mimic_health - Math.floor(Math.random() * (mage_Max_magic_stat - mage_Min_magic_stat + 1) + mage_Min_magic_stat);
										System.out.println("The Bat now has " + Mimic_health + " health left");
										mana_count = mana_count - 1;
										System.out.println("You now have " + mana_count + " mana left");
										Mimic_queue.remove(mage_speed_stat);
									}
									if (attack_order == Mimic_speed_stat) {
										int Mimic_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
										if (Mimic_attack_choice == 1) {
											System.out.println("The bat attacks you with its club and knocks you to the floor");
											worrior_health = worrior_health - Math.floor(Math.random() * (Mimic_Max_attack_stat - Mimic_Min_attack_stat + 1) + Mimic_Min_attack_stat);
											System.out.println("You now have " + worrior_health + " health left");
											Mimic_queue.remove(Mimic_speed_stat);
										}
										if (Mimic_attack_choice == 2) {
											System.out.println("The Bat attacks the mage with its club and knocks you to the floor");
											mage_health = mage_health - Math.floor(Math.random() * (Bat_Max_attack_stat - Bat_Min_attack_stat + 1) + Bat_Min_attack_stat);
											System.out.println("You now have " + mage_health + " health left");
											Mimic_queue.remove(Mimic_speed_stat);
										}
										if (worrior_health <= 0 && mage_health <= 0) {
											game_over_screen();
										}
									}
								}
								break;
							case "Heal":
							case "heal":
								System.out.println("Who do you want to heal?");
								System.out.println("Worrior");
								System.out.println("Mage");
								heal_choice = (in.readLine());
								int Mimic_attack_choice;
								switch (heal_choice) {
									case "Worrior":
									case "worrior":
										System.out.println("You heal the worrior");
										worrior_health = worrior_health + 50;
										System.out.println("The worrior now has " + worrior_health + " health left");
										potion_count = potion_count - 1;
										System.out.println("You now have " + potion_count + " potions left");
										Mimic_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
										if (Mimic_attack_choice == 1) {
											System.out.println("The Mimic attacks you with its club and knocks you to the floor");
											worrior_health = worrior_health - Math.floor(Math.random() * (Mimic_Max_attack_stat - Mimic_Min_attack_stat + 1) + Mimic_Min_attack_stat);
											System.out.println("You now have " + worrior_health + " health left");

										}
										if (Mimic_attack_choice == 2) {
											System.out.println("The Mimic attacks the mage with its club and knocks you to the floor");
											mage_health = mage_health - Math.floor(Math.random() * (Mimic_Max_attack_stat - Mimic_Min_attack_stat + 1) + Mimic_Min_attack_stat);
											System.out.println("You now have " + mage_health + " health left");
										}
										if (worrior_health <= 0 && mage_health <= 0) {
											game_over_screen();
										}
										break;
									case "Mage":
									case "mage":
										System.out.println("You heal the mage");
										mage_health = mage_health + 50;
										System.out.println("The mage now has " + mage_health + " health left");
										potion_count = potion_count - 1;
										Mimic_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
										if (Mimic_attack_choice == 1) {
											System.out.println("The Mimic attacks you with its club and knocks you to the floor");
											worrior_health = worrior_health - Math.floor(Math.random() * (Mimic_Max_attack_stat - Mimic_Min_attack_stat + 1) + Mimic_Min_attack_stat);
											System.out.println("You now have " + worrior_health + " health left");
										}
										if (Mimic_attack_choice == 2) {
											System.out.println("The Mimic attacks the mage with its club and knocks you to the floor");
											mage_health = mage_health - Math.floor(Math.random() * (Mimic_Max_attack_stat - Mimic_Min_attack_stat + 1) + Mimic_Min_attack_stat);
											System.out.println("You now have " + mage_health + " health left");
										}
										if (worrior_health <= 0 && mage_health <= 0) {
											game_over_screen();
										}
										break;
								}
								break;
						}
					} while (Mimic_health > 0);
					if (Mimic_health <= 0) {
						System.out.println("Congratulations!! You have defeated the Mimic!! You are now able to look around the room.");
					}
					System.out.println("Would you like to use a potion to heal yourself before looking around the room? This is your last chance before the next battle");
					room_choice = (in.readLine());
					String heal_choice;
					switch (room_choice) {
						case "Yes":
						case "yes":
							System.out.println("who would you like to heal?");
							System.out.println("Worrior");
							System.out.println("Mage");
							heal_choice = (in.readLine());
							switch (heal_choice) {
								case "Worrior":
								case "worrior":
									if (potion_count > 0 && worrior_health < 120) {
										System.out.println("You use one of your potions and you health heals by 50 points");
										worrior_health = worrior_health + 50;
										System.out.println("The worrior now has " + worrior_health + " health left");
										potion_count = potion_count - 1;
										System.out.println("You now have " + potion_count + " potions left");
									}
									if (potion_count < 1 || worrior_health == 120) {
										System.out.println("You are not able to use a potion at this time");
									}
									break;
								case "Mage":
								case "mage":
									if (potion_count > 0 && mage_health < 120) {
										System.out.println("You use one of your potions and you health heals by 50 points");
										mage_health = mage_health + 50;
										System.out.println("The mage now has " + mage_health + " health left");
										potion_count = potion_count - 1;
										System.out.println("You now have " + potion_count + " potions left");
									}
									if (potion_count < 1 || mage_health == 120) {
										System.out.println("You are not able to use a potion at this time");
									}
									break;
								case "Both":
								case "both":
									if (potion_count > 0 && mage_health < 120 && worrior_health < 120) {
										System.out.println("You use one of your potions and you health heals by 50 points");
										mage_health = mage_health + 50;
										worrior_health = worrior_health + 50;
										System.out.println("The mage now has " + mage_health + " health left");
										System.out.println("The worrior now has " + worrior_health + " health left");
										potion_count = potion_count - 2;
										System.out.println("You now have " + potion_count + " potions left");
									}
									if (potion_count < 1 || mage_health == 120 || worrior_health == 120) {
										System.out.println("You are not able to use a potion at this time");
									}
									break;
							}
						case "No":
						case "no":
							System.out.println("you decide not to use a potion");
							break;
					}
					System.out.println("Would you like to use an ether");
					String response = (in.readLine());
					switch (response) {
						case "Yes":
						case "yes":
							if (ether_count > 0 && mage_health < 120) {
								System.out.println("You use one of your ethers and you manna replenishes by 50 points");
								mana_count = mana_count + 50;
								System.out.println("The mage now has " + mana_count + " manna left");
								ether_count = ether_count - 1;
								System.out.println("You now have " + ether_count + " ethers left");
							}
							if (ether_count < 1 || mana_count == 120) {
								System.out.println("You are not able to use an ether at this time");
							}
							break;
						case "No":
						case "no":
							System.out.println("You decide not to use an ether");
							break;
					}
					break;
				case "Check crack":
				case "check crack":
					System.out.println("You can see an item just beyond the crack in the wall but cannot quie make out what it is");
					System.out.println("If only you had an item to blast away the wall to get to the item");
					break;
				case "Behind":
				case "behind":
					System.out.println("The door behind you is magically sealed. You can not go back the way you came.");
					break;
				case "Right":
				case "right":
					if (room_4_key == 1) {
						System.out.println("You use the key and the door opens up to the next room.");
						Room_4_initial_visit();
					}
					if (room_4_key == 0) {
						System.out.println("You do not have the correct key for this door");
					}
					break;
			}
			System.out.println("you go back to the middle of the room");
		} while (doagain = true);
	}

	public void Room_4_initial_visit() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String battle_choice;
		String room_choice;
		String door_choice;
		boolean doagain = true;
		System.out.println("As you walk into the now room, the door slams behind you. You try to re open it witht the key but it seems to magically sealed.");
		System.out.println("A big gargoyle swoops down from the top of the room. Time to battle!!!");
		do {
			System.out.println("Please select the action that you will do to fight the gargoyle");
			System.out.println("Attack");
			System.out.println("Heal");
			System.out.println("Check gargoyle stats");
			battle_choice = (in.readLine());
			String heal_choice;
			switch (battle_choice) {
				case "Check gargoyle stats":
				case "check gargoyle stats":
					System.out.println("The gargoyle's health is " + gargoyle_health);
					System.out.println("The gargoyle's minimum attack stat is " + gargoyle_Min_attack_stat);
					System.out.println("The gargoyle's maximuim attack stat is " + gargoyle_Max_attack_stat);
					break;
				case "Attack":
				case "attack":
					PriorityQueue<Integer> gargoyle_queue = new PriorityQueue<Integer>(3, Collections.reverseOrder());
					gargoyle_queue.add((int) worrior_speed_stat);
					gargoyle_queue.add((int) mage_speed_stat);
					gargoyle_queue.add((int) Mimic_speed_stat);
					while (!gargoyle_queue.isEmpty()) {
						int attack_order = gargoyle_queue.peek();
						if (attack_order == worrior_speed_stat) {
							System.out.println("You attack the gargoyle with your sword");
							Mimic_health = Mimic_health - Math.floor(Math.random() * (worrior_Max_attack_stat - worrior_Min_attack_stat + 1) + worrior_Min_attack_stat);
							System.out.println("The gargoyle now has " + gargoyle_health + " health left");
							gargoyle_queue.remove(worrior_speed_stat);
						}
						if (attack_order == mage_speed_stat) {
							System.out.println("You attack the gargoyle with your magic");
							gargoyle_health = gargoyle_health - Math.floor(Math.random() * (mage_Max_magic_stat - mage_Min_magic_stat + 1) + mage_Min_magic_stat);
							System.out.println("The gargoyle now has " + gargoyle_health + " health left");
							mana_count = mana_count - 1;
							System.out.println("You now have " + mana_count + " mana left");
							gargoyle_queue.remove(mage_speed_stat);
						}
						if (attack_order == gargoyle_speed_stat) {
							int gargoyle_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (gargoyle_attack_choice == 1) {
								System.out.println("The gargoyle attacks you with its club and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (gargoyle_Max_attack_stat - gargoyle_Min_attack_stat + 1) + gargoyle_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");
								gargoyle_queue.remove(Mimic_speed_stat);
							}
							if (gargoyle_attack_choice == 2) {
								System.out.println("The gargoyle attacks the mage with its club and knocks you to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (gargoyle_Max_attack_stat - gargoyle_Min_attack_stat + 1) + gargoyle_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
								gargoyle_queue.remove(gargoyle_speed_stat);
							}
							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
						}
					}
					break;
				case "Heal":
				case "heal":
					System.out.println("Who do you want to heal?");
					System.out.println("Worrior");
					System.out.println("Mage");
					heal_choice = (in.readLine());
					int gargoyle_attack_choice;
					switch (heal_choice) {
						case "Worrior":
						case "worrior":
							System.out.println("You heal the worrior");
							worrior_health = worrior_health + 50;
							System.out.println("The worrior now has " + worrior_health + " health left");
							potion_count = potion_count - 1;
							System.out.println("You now have " + potion_count + " potions left");
							gargoyle_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (gargoyle_attack_choice == 1) {
								System.out.println("The gargoyle attacks and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (gargoyle_Max_attack_stat - gargoyle_Min_attack_stat + 1) + gargoyle_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");
							}
							if (gargoyle_attack_choice == 2) {
								System.out.println("The Mimic attacks the mage with its club and knocks you to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (gargoyle_Max_attack_stat - gargoyle_Min_attack_stat + 1) + gargoyle_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
							}
							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
							break;
						case "Mage":
						case "mage":
							System.out.println("You heal the mage");
							mage_health = mage_health + 50;
							System.out.println("The mage now has " + mage_health + " health left");
							potion_count = potion_count - 1;
							gargoyle_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (gargoyle_attack_choice == 1) {
								System.out.println("The Mimic attacks you and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (gargoyle_Max_attack_stat - gargoyle_Min_attack_stat + 1) + gargoyle_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");

							}
							if (gargoyle_attack_choice == 2) {
								System.out.println("The gargoyle attacks the mage and knocks them to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (gargoyle_Max_attack_stat - gargoyle_Min_attack_stat + 1) + gargoyle_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
							}

							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
					}
			}
		} while (gargoyle_health > 0);
		if (gargoyle_health <= 0) {
			System.out.println("Congratulations!! You have defeated the gargoyle!! You are now able to look around the room.");
		}
		System.out.println("Would you like to use a potion to heal yourself before looking around the room? This is your last chance before the next battle");
		room_choice = (in.readLine());
		String heal_choice;
		switch (room_choice) {
			case "Yes":
			case "yes":
				System.out.println("who would you like to heal?");
				System.out.println("Worrior");
				System.out.println("Mage");
				heal_choice = (in.readLine());
				switch (heal_choice) {
					case "Worrior":
					case "worrior":
						if (potion_count > 0 && worrior_health < 120) {
							System.out.println("You use one of your potions and you health heals by 50 points");
							worrior_health = worrior_health + 50;
							System.out.println("The worrior now has " + worrior_health + " health left");
							potion_count = potion_count - 1;
							System.out.println("You now have " + potion_count + " potions left");
						}
						if (potion_count < 1 || worrior_health == 120) {
							System.out.println("You are not able to use a potion at this time");
						}
						break;
					case "Mage":
					case "mage":
						if (potion_count > 0 && mage_health < 120) {
							System.out.println("You use one of your potions and you health heals by 50 points");
							mage_health = mage_health + 50;
							System.out.println("The mage now has " + mage_health + " health left");
							potion_count = potion_count - 1;
							System.out.println("You now have " + potion_count + " potions left");
						}
						if (potion_count < 1 || mage_health == 120) {
							System.out.println("You are not able to use a potion at this time");
						}
						break;
					case "Both":
					case "both":
						if (potion_count > 0 && mage_health < 120 && worrior_health < 120) {
							System.out.println("You use one of your potions and you health heals by 50 points");
							mage_health = mage_health + 50;
							worrior_health = worrior_health + 50;
							System.out.println("The mage now has " + mage_health + " health left");
							System.out.println("The worrior now has " + worrior_health + " health left");
							potion_count = potion_count - 2;
							System.out.println("You now have " + potion_count + " potions left");
						}
						if (potion_count < 1 || mage_health == 120 || worrior_health == 120) {
							System.out.println("You are not able to use a potion at this time");
						}
						break;
				}
			case "No":
			case "no":
				System.out.println("You decide not to use a potion");
				break;
		}
		System.out.println("Would you like to use an ether");
		String response = (in.readLine());
		switch (response) {
			case "Yes":
			case "yes":
				if (ether_count > 0 && mage_health < 120) {
					System.out.println("You use one of your ethers and you manna replenishes by 50 points");
					mana_count = mana_count + 50;
					System.out.println("The mage now has " + mana_count + " manna left");
					ether_count = ether_count - 1;
					System.out.println("You now have " + ether_count + " ethers left");
				}
				if (ether_count < 1 || mana_count == 120) {
					System.out.println("You are not able to use an ether at this time");
				}
				break;
			case "No":
			case "no":
				System.out.println("You decide not to use an ether");
				break;
		}
		System.out.println("You look around the room see a door in front of you and a door to the right of you");
		System.out.println("The final thing that you notice is a treasue chest in the corner of the room.");

		do {
			System.out.println("What action do you want to do next");
			System.out.println("Type 'behind' to check the door behind of you. Type 'right' for the door on the right. Type 'right chest' to look inside the chest in the right corner. Type 'left chest' to look inside the chest in the left corner. Type 'front' to go challenge the final boss of the game.");
			door_choice = (in.readLine());
			switch (door_choice) {
				case "Right chest":
				case "right chest":
					if (room_1_key == 1)
						System.out.println("You already got these items");
					if (room_1_key == 0)
						System.out.println("You walk over to the chest and open it to find a key to one of the doors");
					room_1_key = 1;
					break;
				case "Left chest":
				case "left chest":
					if (bomb_count == 3)
						System.out.println("You already got these items");
					if (bomb_count == 0)
						System.out.println("You walk over to the chest and open it to find two bombs that look like they can be used in two of the other rooms");
					bomb_count = 3;
					break;
				case "Right":
				case "right":
					if (room_1_key == 1) {
						System.out.println("You use the key and the door opens up to the next room.");
						System.out.println("As you leave the room the treasue chest become magically sealed and cannot be opened again");
						Room_1_second_visit();
					}
					if (room_1_key == 0) {
						System.out.println("You do not seem to have the correct key for this door");
					}
					break;
				case "Behind":
				case "behind":
					System.out.println("You cannot go through that door. it is magically sealed");
					break;
				case "Front":
				case "front":
					if (boss_room_key == 1) {
						System.out.println("You use the key and the door opens up to the next room.");
						System.out.println("As you leave the room the treasue chest become magically sealed and cannot be opened again");
						System.out.println("As you walk into the room you hear all the door locks throughout the dungeon begin to open");
						boss_room();
					}
					if (boss_room_key == 0) {
						System.out.println("you do not seem to have the correct key for this door");
					}
					break;
			}
			System.out.println("you now go back to the middle of the room");
		} while (doagain == true);
	}

	public void boss_room() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String battle_choice;
		System.out.println("As you walk into the now room, the door slams behind you. You try to re open it witht the key but it seems to magically sealed.");
		System.out.println("A big dragon swoops down from the top of the room. Time to battle!!!");
		do {
			System.out.println("Please select the action that you will do to fight the dragon");
			System.out.println("Attack");
			System.out.println("Heal");
			System.out.println("Check dragon stats");
			battle_choice = (in.readLine());
			String heal_choice;
			switch (battle_choice) {
				case "Check dragon stats":
				case "check dragon stats":
					System.out.println("The dragon's health is " + dragon_health);
					System.out.println("The dragon's minimum attack stat is " + dragon_Min_attack_stat);
					System.out.println("The dragon's maximuim attack stat is " + dragon_Max_attack_stat);
					break;
				case "Attack":
				case "attack":
					PriorityQueue<Integer> dragon_queue = new PriorityQueue<Integer>(3, Collections.reverseOrder());
					dragon_queue.add((int) worrior_speed_stat);
					dragon_queue.add((int) mage_speed_stat);
					dragon_queue.add((int) Mimic_speed_stat);
					while (!dragon_queue.isEmpty()) {
						int attack_order = dragon_queue.peek();
						if (attack_order == worrior_speed_stat) {
							System.out.println("You attack the dragon with your sword");
							dragon_health = dragon_health - Math.floor(Math.random() * (worrior_Max_attack_stat - worrior_Min_attack_stat + 1) + worrior_Min_attack_stat);
							System.out.println("The dragon now has " + dragon_health + " health left");
							dragon_queue.remove(worrior_speed_stat);
						}
						if (attack_order == mage_speed_stat) {
							System.out.println("You attack the dragon with your magic");
							dragon_health = dragon_health - Math.floor(Math.random() * (mage_Max_magic_stat - mage_Min_magic_stat + 1) + mage_Min_magic_stat);
							System.out.println("The dragon now has " + dragon_health + " health left");
							mana_count = mana_count - 1;
							System.out.println("You now have " + mana_count + " mana left");
							dragon_queue.remove(mage_speed_stat);
						}
						if (attack_order == dragon_speed_stat) {
							int dragon_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (dragon_attack_choice == 1) {
								System.out.println("Thedragon attacks you with its club and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (dragon_Max_attack_stat - dragon_Min_attack_stat + 1) + dragon_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");
								dragon_queue.remove(Mimic_speed_stat);
							}
							if (dragon_attack_choice == 2) {
								System.out.println("The dragon attacks the mage with its club and knocks you to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (dragon_Max_attack_stat - dragon_Min_attack_stat + 1) + dragon_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
								dragon_queue.remove(dragon_speed_stat);
							}
							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
						}
					}
					break;
				case "Heal":
				case "heal":
					System.out.println("Who do you want to heal?");
					System.out.println("Worrior");
					System.out.println("Mage");
					heal_choice = (in.readLine());
					int dragon_attack_choice;
					switch (heal_choice) {
						case "Worrior":
						case "worrior":
							System.out.println("You heal the worrior");
							worrior_health = worrior_health + 50;
							System.out.println("The worrior now has " + worrior_health + " health left");
							potion_count = potion_count - 1;
							System.out.println("You now have " + potion_count + " potions left");
							dragon_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (dragon_attack_choice == 1) {
								System.out.println("The dragon attacks and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (dragon_Max_attack_stat - dragon_Min_attack_stat + 1) + dragon_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");
							}
							if (dragon_attack_choice == 2) {
								System.out.println("The dragon attacks the mage with its club and knocks you to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (dragon_Max_attack_stat - dragon_Min_attack_stat + 1) + dragon_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
							}
							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
							break;
						case "Mage":
						case "mage":
							System.out.println("You heal the mage");
							mage_health = mage_health + 50;
							System.out.println("The mage now has " + mage_health + " health left");
							potion_count = potion_count - 1;
							dragon_attack_choice = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
							if (dragon_attack_choice == 1) {
								System.out.println("The dragon attacks you and knocks you to the floor");
								worrior_health = worrior_health - Math.floor(Math.random() * (dragon_Max_attack_stat - dragon_Min_attack_stat + 1) + dragon_Min_attack_stat);
								System.out.println("You now have " + worrior_health + " health left");
							}
							if (dragon_attack_choice == 2) {
								System.out.println("The dragon attacks the mage and knocks them to the floor");
								mage_health = mage_health - Math.floor(Math.random() * (dragon_Max_attack_stat - dragon_Min_attack_stat + 1)+ dragon_Min_attack_stat);
								System.out.println("You now have " + mage_health + " health left");
							}
							if (worrior_health <= 0 && mage_health <= 0) {
								game_over_screen();
							}
							break;
					}
					break;
			}
		} while (dragon_health > 0);
		while (dragon_health > 0);
		if (dragon_health <= 0) {
			System.out.println("Congratulations!! You have defeated the dragon!!");
			System.out.println("with the dragon fianlly defeated you go to claim your ritches");
			System.out.println("thank you so much for palying");
			System.exit(0);
		}
	}

	public void Room_1_second_visit() throws IOException {
		String door_choice;
		String back_to_middle_response;
		boolean doagain;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("As enter the room you started in you see the crack in the wall that is now on your left and a the door to the second room that is now on your right");
		do {
			System.out.println("What action do you want to do next");
			System.out.println("Type 'crack' to check the crack in the wall. Type chest to look inside the chest in the corner. Type 'right' to to the next room. Type 'behind to try to open the door behind you");
			door_choice = (in.readLine());
			switch (door_choice) {
				case "Chest":
				case "chest":
					System.out.println("The chest is magically sealed and cannot be opened.");
				case "Crack":
				case "crack":
					if (bomb_count == 1)
						System.out.println("You already got the item from this wall");
					if (bomb_count > 2)
						System.out.println("You use two of the bombs to break open the wall. You find a ring that boosts you minimum attck stat by 5 points");
					worrior_Min_attack_stat = worrior_Min_attack_stat + 5;
					bomb_count = bomb_count - 2;
					if (bomb_count == 0) {
						System.out.println("You can see an item just beyond the crack in the wall but cannot quie make out what it is");
						System.out.println("If only you had an item to blast away the wall to get to the item");
					}
					break;
				case "Right":
				case "right":
					if (room_2_key == 1) {
						System.out.println("You use the key and the door opens up to the next room.");
						Room_2_second_visit();
					}
					if (room_2_key == 0) {
						System.out.println("you do not seem to have the correct key for this door");
					}
					break;
				case "Beind":
				case "behind":
					System.out.println("It seems that the door has once again sealed itself with magic");
					break;
			}
			System.out.println(
					"Do you want to go back to the middle of the room and do a different action? Type yes or no");
			back_to_middle_response = (in.readLine());
			if (back_to_middle_response.charAt(0) == 'Y' || back_to_middle_response.charAt(0) == 'y')
				doagain = true;
			else {
				doagain = false;
				game_over_screen();
			}
		} while (doagain);
	}

	public void Room_2_second_visit() throws IOException {
		String door_choice;
		String back_to_middle_response;
		boolean doagain;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("You look around the room see a door in front of you and a door to the right of you");
		System.out.println("As you walk into the room, the door magically seals intself behind you yet again.");
		do {
			System.out.println("What action do you want to do next");
			System.out.println("type 'behind' to check the door behind of you. Type 'right' for the door on the right. Type chest to look inside the chest in the corner");
			door_choice = (in.readLine());
			switch (door_choice) {
				case "Chest":
				case "chest":
					System.out.println("The chest is magically sealed and cannot be opened");
					break;
				case "Right":
				case "right":
					if (room_3_key == 1) {
						System.out.println("You use the key and the door opens up to the next room.");
						Room_3_second_visit();
					}
					if (room_3_key == 0) {
						System.out.println("you do not seem to have the correct key for this door");
					}
					break;
				case "Behind":
				case "behind":
					System.out.println("You cannot go through that door. it is magically sealed");
					break;
			}
			System.out.println("Do you want to go back to the middle of the room and do a different action? Type yes or no");
			back_to_middle_response = (in.readLine());
			if (back_to_middle_response.charAt(0) == 'Y' || back_to_middle_response.charAt(0) == 'y')
				doagain = true;
			else {
				doagain = false;
				game_over_screen();
			}
		} while (doagain);
	}

	public void Room_3_second_visit() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String door_choice;
		String back_to_middle_response;
		boolean doagain;
		System.out.println("As you walk into the room, the door magically seals intself behind you yet again.");
		System.out.println("You see is a chest, a crack in the wall in front of you, a door on the right, and a chest in the coner.");
		do {
			System.out.println("What will you do next? Type 'check crack' to go inspect the crack in the wall. Type 'right' to check the door on the right. Type 'chest' to check the chest. Type 'behind' to check the door behind you");
			door_choice = (in.readLine());
			switch (door_choice) {
				case "Chest":
				case "chest":
					System.out.println("The chest is magically sealed and you cannot open it");
					break;
				case "Check crack":
				case "check crack":
					if (bomb_count < 1) {
						System.out.println("You do not have the correct item to get to this area or you have already retrieved the item");
					}
					if (bomb_count > 0) {
						System.out.println("You use one of the bombs to break open the wall. You find a sword that boosts you maximum attck stat by 5 points");
						worrior_Min_attack_stat = worrior_Max_attack_stat + 5;
						bomb_count = bomb_count - 1;
					}
					break;
				case "Behind":
				case "behind":
					System.out.println("The door behind you is magically sealed. You can not go back the way you came.");
					break;
				case "Right":
				case "right":
					if (room_4_key == 1) {
						System.out.println("You use the key and the door opens up to the next room.");
						Room_4_second_visit();
					}
					if (room_4_key == 0) {
						System.out.println("You do not have the correct key for this door");
					}
					break;
			}
			System.out.println("Do you want to go back to the middle of the room and do a different action? Type yes or no");
			back_to_middle_response = (in.readLine());
			if (back_to_middle_response.charAt(0) == 'Y' || back_to_middle_response.charAt(0) == 'y')
				doagain = true;
			else {
				doagain = false;
				game_over_screen();
			}
		} while (doagain);
	}

	public void Room_4_second_visit() throws IOException {
		String door_choice;
		String back_to_middle_response;
		boolean doagain;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("You look around the room see a door in front of you and a door to the right of you");
		System.out.println("The final thing that you notice is a treasue chest in the corner of the room.");
		do {
			System.out.println("What action do you want to do next");
			System.out.println("Type 'behind' to check the door behind of you. Type 'right' for the door on the right. Type 'right chest' to look inside the chest in the right corner. Type 'left chest' to look inside the chest in the left corner. Type 'front' to go challenge the final boss of the game.");
			door_choice = (in.readLine());
			switch (door_choice) {
				case "Right chest":
				case "right chest":
					System.out.println("The chest is magically sealed");
					break;
				case "Left chest":
				case "left chest":
					System.out.println("The cest is magically sealed.");
					break;
				case "Right":
				case "right":
					System.out.println("The door is magically sealed");
					break;
				case "Behind":
				case "behind":
					System.out.println("You cannot go through that door. it is magically sealed");
					break;
				case "Front":
				case "front":
					if (boss_room_key == 1) {
						System.out.println("As you walk into the room you hear all the door locks throughout the dungeon begin to open");
						boss_room();
					}
					if (boss_room_key == 0) {
						System.out.println("you do not seem to have the correct key for this door");
					}
					break;
			}
			System.out.println("Do you want to go back to the middle of the room and do a different action? Type yes or no");
			back_to_middle_response = (in.readLine());
			if (back_to_middle_response.charAt(0) == 'Y' || back_to_middle_response.charAt(0) == 'y')
				doagain = true;
			else {
				doagain = false;
				game_over_screen();
			}
		} while (doagain);
	}

	public void game_over_screen() {
		System.out.println("You have perished. Game Over. Please reload program and try again");
		System.exit(0);
	}
}
