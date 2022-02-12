// DO NOT TOUCH THIS FILE!
package edu.nyu.cs;

// import junit 4 testing framework
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import org.junit.contrib.java.lang.system.SystemOutRule; // system rules lib - useful for capturing system output
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
// import static org.mockito.Mockito.*;

import java.util.ArrayList;


public class BlackjackTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule();

    @Test
    public void testWelcome() {
        // checks whether program behaves as expected
        for (int i=0; i<10; i++) {
            double rand = Math.random();
            if (rand < 0.25) systemInMock.provideLines("stand");
            else if (rand < 0.5) systemInMock.provideLines("hit", "stand");
            else if (rand < 0.75) systemInMock.provideLines("hit", "hit", "stand");
            else systemInMock.provideLines("hit", "hit", "hit", "stand");
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(systemOutRule);
                if (!actual.isWelcome) {
                    // program did not print welcome message as first line
                    assertEquals("Expected to see a welcome message in the first line of output", "Your program did not output a welcome message as the first line... tsk tsk.");
                }
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", "Program crashed when testing for welcome message: " + e); // fail the test if any exception occurs
            }
        }
    }

    @Test
    public void testUserCardsDisplayed() {
        // checks whether program behaves as expected
        for (int i=0; i<10; i++) {
            double rand = Math.random();
            if (rand < 0.25) systemInMock.provideLines("stand");
            else if (rand < 0.5) systemInMock.provideLines("hit", "stand");
            else if (rand < 0.75) systemInMock.provideLines("hit", "hit", "stand");
            else systemInMock.provideLines("hit", "hit", "hit", "stand");
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(systemOutRule);
                if (actual.usersCards.size() == 0) {
                    // user's cards were not output correctly
                    assertEquals("Expected to see user's cards displayed", "User's cards not displayed");
                }
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", "Program crashed when testing for display of user's cards: " + e); // fail the test if any exception occurs
            }
        }
    }

    @Test
    public void testDealersCardsDisplayed() {
        // checks whether program behaves as expected
        for (int i=0; i<10; i++) {
            double rand = Math.random();
            if (rand < 0.25) systemInMock.provideLines("stand");
            else if (rand < 0.5) systemInMock.provideLines("hit", "stand");
            else if (rand < 0.75) systemInMock.provideLines("hit", "hit", "stand");
            else systemInMock.provideLines("hit", "hit", "hit", "stand");
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(systemOutRule);
                // test for false negative
                if (actual.dealersCards.size() == 0 && !actual.isUserBust) {
                    // dealer's cards were not output correctly
                    assertEquals("Expected to see dealer's cards displayed as long as the user is not bust", "Dealer's cards not displayed");
                }
                // test for false positive
                if (actual.isUserBust && actual.dealersCards.size() > 0) {
                    // dealer's cards should not have been output
                    assertEquals("Expected not to see dealer's cards displayed when the user is bust", "Dealer's cards were displayed");
                }
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", "Program crashed when testing for display of dealer's cards: " + e); // fail the test if any exception occurs
            }
        }
    }


    @Test
    public void testForAnyOutcome() {
        // checks whether program behaves as expected
        for (int i=0; i<10; i++) {
            double rand = Math.random();
            if (rand < 0.25) systemInMock.provideLines("stand");
            else if (rand < 0.5) systemInMock.provideLines("hit", "stand");
            else if (rand < 0.75) systemInMock.provideLines("hit", "hit", "stand");
            else systemInMock.provideLines("hit", "hit", "hit", "stand");
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(systemOutRule);
                if (actual.isUserWon && actual.isDealerWon || actual.isUserWon && actual.isTie || actual.isDealerWon && actual.isTie) {
                    assertEquals("In any run of the program, either the user wins, the dealer wins, or it's a tie", "Your program shows two winners, or shows a winner and a tie at the same time.");
                }
                // test for no outcome
                if (!actual.isUserWon && !actual.isDealerWon && !actual.isTie) {
                    assertEquals("In any run of the program, someone should win or the players should tie.", "In your program, we did not detect any winner or any tie, based on your output.");
                }
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", "Program crashed when testing for either a win of any kind or a tie: " + e); // fail the test if any exception occurs
            }
        }
    }

    @Test
    public void testUserWinScenarios() {
        // checks whether program behaves as expected
        for (int i=0; i<10; i++) {
            double rand = Math.random();
            if (rand < 0.25) systemInMock.provideLines("stand");
            else if (rand < 0.5) systemInMock.provideLines("hit", "stand");
            else if (rand < 0.75) systemInMock.provideLines("hit", "hit", "stand");
            else systemInMock.provideLines("hit", "hit", "hit", "stand");
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(systemOutRule);
                // test for false positive
                if (actual.isUserWon && !((actual.userTotal > actual.dealerTotal) || actual.isDealerBust)) {
                    assertEquals("Expected user to win only when their card total is greater than the dealer's or the dealer is bust", "Your program shows the user winning despite having " + actual.userTotal + " while the dealer has " + actual.dealerTotal + " and the dealer busts=" + actual.isDealerBust + ".");
                }
                // test for false negative
                if (!(actual.userTotal > 21) && !actual.isUserWon && ((actual.userTotal > actual.dealerTotal) || actual.isDealerBust)) {
                    assertEquals("Expected user to win when their card total is greater than the dealer's or the dealer is bust", "Your program does not show the user winning despite having " + actual.userTotal + " while the dealer has " + actual.dealerTotal + " and the dealer busts=" + actual.isDealerBust + ".");
                }
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", "Program crashed when testing various user win scenarios: " + e); // fail the test if any exception occurs
            }
        }
    }

    @Test
    public void testDealerWinScenarios() {
        // checks whether program behaves as expected
        for (int i=0; i<10; i++) {
            double rand = Math.random();
            if (rand < 0.25) systemInMock.provideLines("stand");
            else if (rand < 0.5) systemInMock.provideLines("hit", "stand");
            else if (rand < 0.75) systemInMock.provideLines("hit", "hit", "stand");
            else systemInMock.provideLines("hit", "hit", "hit", "stand");
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(systemOutRule);
                // test for false positive
                if (actual.isDealerWon && !((actual.dealerTotal > actual.userTotal) || actual.isUserBust)) {
                    assertEquals("Expected dealer to win only when their card total is greater than the user's or the user busts", "Your program shows the dealer winning despite having " + actual.dealerTotal + " while the user has " + actual.userTotal + " and the user busts=" + actual.isUserBust + ".");
                }
                // test for false negative
                if (!(actual.dealerTotal > 21) && !actual.isDealerWon && ((actual.dealerTotal > actual.userTotal) || actual.isUserBust)) {
                    assertEquals("Expected dealer to win when their card total is greater than the user's's or the user is bust", "Your program does not show the dealer winning despite having " + actual.dealerTotal + " while the user has " + actual.userTotal + " and the dealer busts=" + actual.isUserBust + ".");
                }
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", "Program crashed when testing various dealer win scenarios: " + e); // fail the test if any exception occurs
            }
        }
    } 

    @Test
    public void testTieScenarios() {
        // checks whether program behaves as expected
        for (int i=0; i<10; i++) {
            double rand = Math.random();
            if (rand < 0.25) systemInMock.provideLines("stand");
            else if (rand < 0.5) systemInMock.provideLines("hit", "stand");
            else if (rand < 0.75) systemInMock.provideLines("hit", "hit", "stand");
            else systemInMock.provideLines("hit", "hit", "hit", "stand");
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(systemOutRule);
                // false positive tie
                if (actual.isTie && !(actual.dealerTotal == actual.userTotal)) {
                    assertEquals("Expected a tie announce only when the two players' card totals are equal.", "Your program shows a tie even though it shows the dealer scored " + actual.dealerTotal + " while the user scored " + actual.userTotal);
                }
                // false negative tie
                if (!actual.isTie && actual.userTotal == actual.dealerTotal) {
                    assertEquals("Expected a tie when the two players' card totals are equal.", "Your program does not announce a tie, even when the dealer's total is " + actual.dealerTotal + " and the user's is " + actual.userTotal);
                }
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", "Program crashed when testing various tie scenarios: " + e); // fail the test if any exception occurs
            }
        }
    }    

    @Test
    public void testDealerBustScenarios() {
        // checks whether program behaves as expected
        for (int i=0; i<10; i++) {
            double rand = Math.random();
            if (rand < 0.25) systemInMock.provideLines("stand");
            else if (rand < 0.5) systemInMock.provideLines("hit", "stand");
            else if (rand < 0.75) systemInMock.provideLines("hit", "hit", "stand");
            else systemInMock.provideLines("hit", "hit", "hit", "stand");
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(systemOutRule);
                // test for false negative
                if (actual.dealerTotal > 21 && !actual.isDealerBust) {
                    assertEquals("Expected dealer to bust when their card total is over 21.", "Your program does not show the dealer is bust even when their card total is " + actual.dealerTotal);
                }
                // test for false positive
                if (actual.dealerTotal > 21 && !actual.isDealerBust) {
                    assertEquals("Expected dealer to only bust when their card total is over 21.", "Your program shows the dealer is bust even when their card total is " + actual.dealerTotal);
                }
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", "Program crashed when testing various dealer bust scenarios: " + e); // fail the test if any exception occurs
            }
        }
    }    

    @Test
    public void testUserBustScenarios() {
        // checks whether program behaves as expected
        for (int i=0; i<10; i++) {
            double rand = Math.random();
            if (rand < 0.25) systemInMock.provideLines("stand");
            else if (rand < 0.5) systemInMock.provideLines("hit", "stand");
            else if (rand < 0.75) systemInMock.provideLines("hit", "hit", "hit", "stand");
            else systemInMock.provideLines("hit", "hit", "hit", "hit", "hit", "stand");
            try {
                // run the program and get the results
                TestOutput actual = TestOutput.getOne(systemOutRule);
                // test false negatives
                if (TestOutput.getTotal(actual.usersCards) > 21 && !actual.isUserBust) {
                    assertEquals("Expected user to bust when their card total is over 21.", "Your program does not show the user is bust even when their card total is " + actual.userTotal);
                }
                // test for false positive
                if (actual.dealerTotal > 21 && !actual.isDealerBust) {
                    assertEquals("Expected user to only bust when their card total is over 21.", "Your program shows the user is bust even when their card total is " + actual.userTotal);
                }
            }
            catch (Exception e) {
                assertEquals("Program should not crash!", "Program crashed when testing various user bust scenarios: " + e); // fail the test if any exception occurs
            }
        }
    }    

} // Test class

// convenience class
class TestOutput {
    public TestOutput(String output) {
            output = output.trim(); // remove any leading/trailing whitespace
            this.lines = output.split("\n"); // split program output by line break
            this.usersCards = new ArrayList<Integer>();
            this.dealersCards = new ArrayList<Integer>();
            this.isWelcome = false;
            this.isUserBust = false;
            this.isDealerBust = false;
            this.isUserWon = false;
            this.isDealerWon = false;
            this.isTie = false;
            // check that first line is welcome message
            if (lines.length > 0 && lines[0].startsWith("Welcome")) {
                this.isWelcome = true;
            }
            // check for presence/absence of specific messages
            for (String line : lines) {
                line = line.trim();
                // look for line with user's cards
                if (line.startsWith("Your cards are: ")) {
                    // get the numbers out of the line
                    this.usersCards = TestOutput.getNumbers(line);
                }
                else if (line.startsWith("The dealer's cards are: ")) {
                    // get the numbers out of the line
                    this.dealersCards = TestOutput.getNumbers(line);
                }
                else if (line.startsWith("Dealer wins")) {
                    this.isDealerWon = true;
                }
                else if (line.startsWith("You win")) {
                    isUserWon = true;
                }
                else if (line.startsWith("Tie")) {
                    this.isTie = true;
                }
                else if (line.startsWith("You have bust")) {
                    this.isUserBust = true;
                }
                else if (line.startsWith("The dealer has bust")) {
                    this.isDealerBust = true;
                }
            } // for each line

            this.dealerTotal = TestOutput.getTotal(dealersCards);
            this.userTotal = TestOutput.getTotal(usersCards);
            // System.out.println(dealerTotal + ":" + userTotal);
    }

    public static ArrayList<Integer> getNumbers(String line) {
        String[] words = line.split(" "); // split by space
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i=0; i<words.length; i++) {
            words[i] = words[i].replaceAll("[^0-9]", ""); // extract just the number from string
            if (words[i].length() > 0) {
                nums.add(Integer.parseInt(words[i])); // add to array list
            }
        }
        return nums;
    }

    public static int getTotal(ArrayList<Integer> nums) {
        int total = 0;
        for (int i=0; i<nums.size(); i++) {
            total += nums.get(i);
        }        
        return total;
    }

    public static TestOutput getOne(SystemOutRule systemOutRule) throws Exception {
        String[] args = {};
        try {
            systemOutRule.clearLog();
            systemOutRule.enableLog(); // start capturing System.out
            Blackjack.main(args);
            String output = systemOutRule.getLogWithNormalizedLineSeparator().trim();
            TestOutput actual = new TestOutput(output);
            return actual;
        }
        catch (Exception e) {
            throw e;
        }

    }

    public String[] lines;
    public ArrayList<Integer> usersCards = new ArrayList<Integer>();
    public ArrayList<Integer> dealersCards = new ArrayList<Integer>();
    public boolean isWelcome = false;
    public boolean isUserBust = false;
    public boolean isDealerBust = false;
    public boolean isUserWon = false;
    public boolean isDealerWon = false;
    public boolean isTie = false;
    public int dealerTotal = 0;
    public int userTotal = 0;
}
