import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TransactionTests {
     DailyTransaction dailyTransaction = new DailyTransaction();

     // login test
     /**
      * Test to check if the logged in user is an admin
      * @throws IOException
      */
     @Test
     public void testLogin() throws IOException {
          Transaction loginTransaction = new Transaction();
          loginTransaction.parseUserInput("userAccount.txt");
          String username = "DevalPan";
          Boolean LoggedIn = false;

          if (LoggedIn) {
               loginTransaction.parseRentalUnit("availableTickets.txt");
          }

          System.out.println("Testing if user is properly logged in.");
          LoggedIn = loginTransaction.LoginUser(username, dailyTransaction);

          Assertions.assertEquals(true, LoggedIn, "User was not set to be properly logged in after loggin in.");
     }

     /**
      * Test to check if username is not null
      * @throws IOException
      */
     @Test
     public void testLogin2() throws IOException {
          Transaction loginTransaction = new Transaction();

          loginTransaction.parseUserInput("userAccount.txt");
          String username = "DevalPan";
          Boolean LoggedIn = false;

          if (LoggedIn) {
               loginTransaction.parseRentalUnit("availableTickets.txt");
          }

          LoggedIn = loginTransaction.LoginUser(username, dailyTransaction);

          String userName = dailyTransaction.getUserName();

          Assertions.assertNotNull(userName);
     }

     /**
     * Check whether the username length matches the predetermined user input from
     * the user account file
     *
     * @throws IOException
     */
     @Test
     public void testLogin3() throws IOException {
          Transaction loginTransaction = new Transaction();
          loginTransaction.parseUserInput("userAccount.txt");
          String username = "DevalPan";
          Boolean LoggedIn = false;

          if (LoggedIn) {
               loginTransaction.parseRentalUnit("availableTickets.txt");
          }
          System.out.println("Testing if user is properly logged in.");
          LoggedIn = loginTransaction.LoginUser(username, dailyTransaction);

          String userName = dailyTransaction.getUserName();

          // Input DevalPan
          Assertions.assertEquals(8, userName.length(),
                    "User name length does not match expected result for username = DevalPan");
     }

     /**
     * Checks whether correct privilege is given to the user with the correct
     * account type
     *
     * @throws IOException
     */
     @Test
     public void testLogin4() throws IOException {
          Transaction loginTransaction = new Transaction();
          loginTransaction.parseUserInput("userAccount.txt");
          String username = "DevalPan";
          Boolean LoggedIn = false;

          if (LoggedIn) {
               loginTransaction.parseRentalUnit("availableTickets.txt");
          }

          LoggedIn = loginTransaction.LoginUser(username, dailyTransaction);

          String userType = dailyTransaction.getUserType();

          System.out.println("Usertype: " + userType);
          // Input DanielEar
          Assertions.assertEquals("RS", userType,
                    "User type does not match the priviledge type for username = DanielEar");
     }

     /**
     * Check to see if inner for loop executes once
     *
     * @throws IOException
     */
     @Test
     public void testLogin5() throws IOException {
          Transaction loginTransaction = new Transaction();
          loginTransaction.parseUserInput("userAccount.txt");
          String username = "DevalPan";
          Boolean LoggedIn = false;

          if (LoggedIn) {
               loginTransaction.parseRentalUnit("availableTickets.txt");
          }

          LoggedIn = loginTransaction.LoginUser(username, dailyTransaction);

          int loopIterations = dailyTransaction.getLoopIterations();

          // Input DevalPan
          Assertions.assertEquals(1, loopIterations,
                    "DevalPan is the first user in the system, therefore only 1 iteration is needed.");
     }

     /**
     * Check to see if inner for loop executes twice
     *
     * @throws IOException
     */
     @Test
     public void testLogin6() throws IOException {
          Transaction loginTransaction = new Transaction();
          loginTransaction.parseUserInput("userAccount.txt");
          String username = "DanielEar";
          Boolean LoggedIn = false;

          if (LoggedIn) {
               loginTransaction.parseRentalUnit("availableTickets.txt");
          }

          LoggedIn = loginTransaction.LoginUser(username, dailyTransaction);

          int loopIterations = dailyTransaction.getLoopIterations();

          // Input DanielEar
          Assertions.assertEquals(2, loopIterations,
                    "DevalPan is the first user in the system, therefore only 1 iteration is needed.");
     }

     /**
      * Check Logged in user cannot login again
      * @throws IOException
      */
     @Test
     public void testLogin7() throws IOException {
          Transaction loginTransaction = new Transaction();
          loginTransaction.parseUserInput("userAccount.txt");
          String username = "DanielEar";
          Boolean LoggedIn = true;

          if (LoggedIn) {
               loginTransaction.parseRentalUnit("availableTickets.txt");
          } else {
               LoggedIn = loginTransaction.LoginUser(username, dailyTransaction);
          }

          Assertions.assertEquals(true, LoggedIn, "User cannot log in after already being logged in.");
     }

     /**
      * Checks if current user and the username are the same
      * @throws IOException
      */
     @Test
     public void testLogin8() throws IOException {
          Transaction loginTransaction = new Transaction();
          loginTransaction.parseUserInput("userAccount.txt");
          String username = "DevalPan";
          Boolean LoggedIn = false;

          if (LoggedIn) {
               loginTransaction.parseRentalUnit("availableTickets.txt");
          }

          LoggedIn = loginTransaction.LoginUser(username, dailyTransaction);

          String currentUserName = dailyTransaction.getCurrentUser();

          Assertions.assertEquals(username, currentUserName, "Current User does not match actual user.");
     }

     /**
      * Checks if the logged in user is an admin
      * 
      * @throws IOException
      */
     @Test
     public void testDelete1() throws IOException {
          String user = "DanielEar";
          String input = "testUserA";
          Transaction transaction = new Transaction();
          HashMap<String, Boolean> retVals = new HashMap<String, Boolean>();

          System.out.println("\nTesting Delete1: Check if user is an Admin");

          transaction.parseUserInput("userAccount.txt");
          transaction.parseRentalUnit("availableTickets.txt");
          transaction.LoginUser(user, dailyTransaction);

          retVals = transaction.DeleteUserAccount(input, dailyTransaction);

          Assertions.assertEquals(true, retVals.get("isAdmin"));
     }

     /**
      * Checks if the loop iterates when the selected user doesn't exist
      * 
      * @throws IOException
      */
     @Test
     public void testDelete2() throws IOException {
          String user = "DanielEar";
          String input = "Anonymous testUserB";
          Transaction transaction = new Transaction();
          HashMap<String, Boolean> retVals = new HashMap<String, Boolean>();

          System.out.println("\nTesting Delete2: Check if the loop iterates when given a bad username");

          transaction.parseUserInput("userAccount.txt");
          transaction.parseRentalUnit("availableTickets.txt");
          transaction.LoginUser(user, dailyTransaction);

          retVals = transaction.DeleteUserAccount(input, dailyTransaction);

          Assertions.assertEquals(true, retVals.get("loop"));
     }

     /**
      * Checks if the deleted user is in the user accounts file
      * 
      * @throws IOException
      */
     @Test
     public void testDelete3() throws IOException {
          String user = "DanielEar";
          String delUser = "testUserC";
          Boolean isDeleted = false;
          Transaction transaction = new Transaction();
          ArrayList<User> users = new ArrayList<User>();

          System.out.println("\nTesting Delete3: Check if the deleted user exists in the user accounts file");

          transaction.parseUserInput("userAccount.txt");
          transaction.parseRentalUnit("availableTickets.txt");
          transaction.LoginUser(user, dailyTransaction);

          transaction.DeleteUserAccount(delUser, dailyTransaction);
          users = transaction.parseUserInput("userAccount.txt");

          for (int i = 0; i < users.size(); i++) {
               String temp = users.get(i).userName.replaceAll("[^A-Za-z]+", "");
               System.out.printf("Temp = %s\n", temp);
               if (!temp.equals(delUser)) {
                    isDeleted = false;
               } else {
                    isDeleted = true;
                    break;
               }
          }
          Assertions.assertEquals(false, isDeleted);
     }

     /**
      * Checks if the deleted user still has rentals in the available tickets file
      * 
      * @throws IOException
      */
     @Test
     public void testDelete4() throws IOException {
          String user = "DanielEar";
          String delUser = "testUserD";
          Boolean isDeleted = false;
          Transaction transaction = new Transaction();
          ArrayList<AvailableTickets> rentals = new ArrayList<AvailableTickets>();

          System.out.println("\nTesting Delete4: Check if the deleted user still has available rentals");

          transaction.parseUserInput("userAccount.txt");
          transaction.parseRentalUnit("availableTickets.txt");
          transaction.LoginUser(user, dailyTransaction);

          transaction.DeleteUserAccount(delUser, dailyTransaction);
          rentals = transaction.parseRentalUnit("availableTickets.txt");

          for (int i = 0; i < rentals.size(); i++) {
               String temp = rentals.get(i).username.replaceAll("[^A-Za-z]+", "");
               System.out.printf("Temp = %s\n", temp);
               if (!temp.equals(delUser)) {
                    isDeleted = false;
               } else {
                    isDeleted = true;
                    break;
               }
          }

          Assertions.assertEquals(false, isDeleted);
     }
}