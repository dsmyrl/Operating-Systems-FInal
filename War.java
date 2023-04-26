import java.io.*;
import java.util.*;

//make a game of war with 1 player and 1 computer
public class War {
    private Scanner input;

    public War(Scanner input) {
        this.input = input;
    }

    
    public void start() {
        //create a deck of cards
       //make a queue of cards
       Queue<Integer> deck = new LinkedList<Integer>();
       Stack<Integer> playerdiscardpile = new Stack<Integer>();
       Stack<Integer> computerdiscardpile = new Stack<Integer>();
       //create ints for all 52 cards in the deck and titile them with their name

        int twoofhearts = 2; int threeofhearts = 3; int fourofhearts = 4; int fiveofhearts = 5; 
        int sixofhearts = 6; int sevenofhearts = 7; int eightofhearts = 8; int nineofhearts = 9;
        int tenofhearts = 10; int jackofhearts = 11; int queenofhearts = 12; int kingofhearts = 13;
        int aceofhearts = 14;

        int twoofdiamonds = 2; int threeofdiamonds = 3; int fourofdiamonds = 4; int fiveofdiamonds = 5;
        int sixofdiamonds = 6; int sevenofdiamonds = 7; int eightofdiamonds = 8; int nineofdiamonds = 9;
        int tenofdiamonds = 10; int jackofdiamonds = 11; int queenofdiamonds = 12; int kingofdiamonds = 13;
        int aceofdiamonds = 14;

        int twoofclubs = 2; int threeofclubs = 3; int fourofclubs = 4; int fiveofclubs = 5;
        int sixofclubs = 6; int sevenofclubs = 7; int eightofclubs = 8; int nineofclubs = 9;
        int tenofclubs = 10; int jackofclubs = 11; int queenofclubs = 12; int kingofclubs = 13;
        int aceofclubs = 14;

        int twoofspades = 2; int threeofspades = 3; int fourofspades = 4; int fiveofspades = 5;
        int sixofspades = 6; int sevenofspades = 7; int eightofspades = 8; int nineofspades = 9;
        int tenofspades = 10; int jackofspades = 11; int queenofspades = 12; int kingofspades = 13;
        int aceofspades = 14;

//add all the cards into a main deck
        deck.add(twoofhearts); deck.add(threeofhearts); deck.add(fourofhearts); deck.add(fiveofhearts);
        deck.add(sixofhearts); deck.add(sevenofhearts); deck.add(eightofhearts); deck.add(nineofhearts);
        deck.add(tenofhearts); deck.add(jackofhearts); deck.add(queenofhearts); deck.add(kingofhearts);
        deck.add(aceofhearts);

        deck.add(twoofdiamonds); deck.add(threeofdiamonds); deck.add(fourofdiamonds); deck.add(fiveofdiamonds);
        deck.add(sixofdiamonds); deck.add(sevenofdiamonds); deck.add(eightofdiamonds); deck.add(nineofdiamonds);
        deck.add(tenofdiamonds); deck.add(jackofdiamonds); deck.add(queenofdiamonds); deck.add(kingofdiamonds);
        deck.add(aceofdiamonds);

        deck.add(twoofclubs); deck.add(threeofclubs); deck.add(fourofclubs); deck.add(fiveofclubs);
        deck.add(sixofclubs); deck.add(sevenofclubs); deck.add(eightofclubs); deck.add(nineofclubs);
        deck.add(tenofclubs); deck.add(jackofclubs); deck.add(queenofclubs); deck.add(kingofclubs);
        deck.add(aceofclubs);

        deck.add(twoofspades); deck.add(threeofspades); deck.add(fourofspades); deck.add(fiveofspades);
        deck.add(sixofspades); deck.add(sevenofspades); deck.add(eightofspades); deck.add(nineofspades);
        deck.add(tenofspades); deck.add(jackofspades); deck.add(queenofspades); deck.add(kingofspades);
        deck.add(aceofspades);

        //shuffle the deck
        Collections.shuffle((List<?>) deck);
        //make a queue for the player
        Queue<Integer> player = new LinkedList<Integer>();

        Queue<Integer> computer = new LinkedList<Integer>();

            //deal the cards to the player and computer
            for (int j = 0; j < 26; j++) 
            {
                player.add(deck.remove());
                computer.add(deck.remove());
            }
            
            System.out.println("Welcome to War!");
            System.out.println("the cards have already been shuffled and dealt to you and the computer");
            System.out.println("the player has " + player.size() + " cards");
            System.out.println("the computer has " + computer.size() + " cards");
            System.out.println("are you ready to play? (y/n)");

            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            if (answer.equals("y")) 
            {
                System.out.println("let's play!");
            }
            else if (answer.equals("n")) 
            {
                System.out.println("ok, goodbye");
                System.exit(0);
            }
            else 
            {
                System.out.println("that is not a valid answer");
                System.exit(0);
            }
            do{
            
            System.out.println("press enter to flip a card");
            input.nextLine();
            //flip a card
            System.out.println("your card is " + player.peek());
            System.out.println("the computer's card is " + computer.peek());
            if(player.peek() < computer.peek())
            {
                System.out.println("the computer wins this round");
                //add the removed cards to the discard pile
                computerdiscardpile.add(computer.remove());
                computerdiscardpile.add(player.remove());
            }
            else if(player.peek() > computer.peek())
            {
                System.out.println("you win this round");
                //add the removed to the bottom of the deck of the player
                playerdiscardpile.add(player.remove());
                playerdiscardpile.add(computer.remove());
            }
            else if(player.peek() == computer.peek())
            {
                boolean tie = true;

                do{
                
                System.out.println("it's time for war!");
                //place two cards in the deck face down and the third card face up
                //if the player wins, they get all the cards
                //if the computer wins, they get all the cards
                //if it's a tie again, repeat the process

                //make a queue for the war cards
                Queue<Integer> warcardsforcomputer = new LinkedList<Integer>();
                Queue<Integer> warcardsforplayer = new LinkedList<Integer>();
                //add the cards to the queue
                //if there are not enough cards for war then shuffle the discard pile and add it to the deck
                if(player.size() < 3)
                {
                    Collections.shuffle((List<?>) playerdiscardpile);
                    player.addAll(playerdiscardpile);
                    playerdiscardpile.clear();
                }
                if(computer.size() < 3)
                {
                    Collections.shuffle((List<?>) computerdiscardpile);
                    computer.addAll(computerdiscardpile);
                    computerdiscardpile.clear();
                }
                warcardsforcomputer.add(computer.remove());
                warcardsforcomputer.add(computer.remove());
                warcardsforcomputer.add(computer.remove());
                warcardsforplayer.add(player.remove());
                warcardsforplayer.add(player.remove());
                warcardsforplayer.add(player.remove());
                //flip the cards
                System.out.println("your card is " + warcardsforplayer.peek());
                System.out.println("the computer's card is " + warcardsforcomputer.peek());
                
                if(warcardsforplayer.peek() > warcardsforcomputer.peek())
                {
                    System.out.println("you win this round");
                    //add the removed cards to the discard pile
                    playerdiscardpile.add(player.remove());
                    tie = false;
                }
                else if(warcardsforplayer.peek() < warcardsforcomputer.peek())
                {
                    System.out.println("the computer wins this round");
                 
                    //add the removed cards to the discard pile
                    computerdiscardpile.add(computer.remove());
                    tie = false;
                }
                else if(warcardsforplayer.peek() == warcardsforcomputer.peek())
                {
                    tie = true;
                }
                }while(tie == true);
            }
                if (player.size() == 0 && playerdiscardpile.size() > 0)
                {
                    //if the player has no cards, but has cards in the discard pile, shuffle the discard pile and add it to the player's deck
                    System.out.println("the player has no cards, but has cards in the discard pile, shuffling the discard pile and adding it to the player's deck");
                    Collections.shuffle((List<?>) playerdiscardpile);
                    player.addAll(playerdiscardpile);
                }
                if(computer.size() == 0 && computerdiscardpile.size() > 0)
                {
                    //if the computer has no cards, but has cards in the discard pile, shuffle the discard pile and add it to the computer's deck
                    System.out.println("the computer has no cards, but has cards in the discard pile, shuffling the discard pile and adding it to the computer's deck");
                    Collections.shuffle((List<?>) computerdiscardpile);
                    computer.addAll(computerdiscardpile);
                }
            
                }while(player.size() > 0 && computer.size() > 0);
                if(player.size() == 0)
                {
                    System.out.println("the computer wins!");
                }
                else if(computer.size() == 0)
                {
                    System.out.println("you win!");
            }
        }

        public static void main(String[] args) throws Exception 
        {
            War game = new War(new Scanner(System.in));
        game.start();
        }
    }
