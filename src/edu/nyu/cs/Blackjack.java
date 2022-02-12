package edu.nyu.cs;

import java.util.Scanner;

/**
 * A variation of the game of Blackjack.  
 * Complete this program according to the instructions in the README.md file as well as within the given comments below.
 */
public class Blackjack {

  /**
   * The main function is automatically called first in a Java program.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) throws Exception {

    // Scanner to read user input:
    Scanner scnr = new Scanner(System.in);

    //Print Welcome message
    System.out.println("Welcome to Blackjack!");

    // User cards 2-11:
    int card1 = (int) (Math.random() * 10) + 2;
    int card2 = (int) (Math.random() * 10) + 2;

    int user_sum = card1+card2;

    String u_cards = Integer.toString(card1) + ", " +  Integer.toString(card2);
    

    // Dealer cards 2-11:
    int card3 = (int) (Math.random() * 10) + 2;
    int card4 = (int) (Math.random() * 10) + 2;
    
    int dealer_sum = card3+card4;

    System.out.println("Your cards are: " + card1 + " and " + card2);

    String d_cards = Integer.toString(card3) + ", " +  Integer.toString(card4);
    
    while(true){
      if(user_sum <= 21){
        System.out.println("Would you like to hit or stand?");
        String instr = scnr.nextLine();

        if(instr.equals("hit")){
          int new_card_u = (int) (Math.random() * 10) + 2;

          user_sum += new_card_u;
          u_cards += ", " + Integer.toString(new_card_u);

        }
        
        else if(instr.equals("stand") || instr.equals("stop") || instr.equals("pass")){
          // first way of breaking out of loop: if user hasn't exceeded sum and decides to stand. Game proceeds to dealer.
          break;
        }
      
        else{
          System.out.println("Not valid command, try again");
        }
      }

      else{
        System.out.println("Your cards are: " + u_cards);
        System.out.println("You have bust!");
        System.out.println("Dealer wins!");
        // Second way of breaking out of loop: user has busted. Print their cards and winning message.
        // if user breaks out of loop this way, user_sum > 21 so dealer loop won't be activated and game will be over.
        break;
      }
      
    }
    
    // If user hasn't busted: now change roles to dealer
    if(user_sum <= 21){

      while(true){

        if(dealer_sum <= 21){
          // Generate random number 0 or 1.
          int dealer_instr = (int)(Math.random() * 2);

          if (dealer_instr == 0){
            int new_card_d = (int) (Math.random() * 10) + 2;
            dealer_sum += new_card_d;
            d_cards += ", " + Integer.toString(new_card_d);
            System.out.println("The dealer hits.");
          }

          else{
            System.out.println("The dealer stands.");
            // Break out of loop because dealer chose to stand
            break;
        }
      }

      else{
        System.out.println("Your cards are: " + u_cards);
        System.out.println("The dealer's cards are: " + d_cards);
        System.out.println("The dealer has bust!");
        System.out.println("You win!");
        //break out of loop because dealer has busted
        break;

      }

    }
  } 
    // Both players stand, neither had busted, so we now determine who is closer to 21:
    if(user_sum <= 21 && dealer_sum <= 21){
      int user_dif = 21 - user_sum;
      int dealer_dif = 21 - dealer_sum;

      System.out.println("Your cards are: " + u_cards);
      System.out.println("The dealer's cards are: " + d_cards);

      if(dealer_dif < user_dif){
        System.out.println("Dealer wins!");

      }

      else if(dealer_dif > user_dif){
        System.out.println("You win!");
      }

      else{
        System.out.println("Tie!");
      }    
    }
    scnr.close();
  } // main

}
