import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;

public class Transaction {
     public static String input = null;
     public static FileInputStream inputFile = null;
     public static String username = null;
     // daily transaction, user accounts, avaiable tickets
     public static DailyTransaction dailyTransaction = new DailyTransaction();
     public static User userAccounts = new User();
     public static AvailableTickets availableTickets = new AvailableTickets();

     public static final Scanner scan = new Scanner(System.in);
     public static Boolean LoggedIn = false;

     BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

     // arraylist to store all users
     ArrayList<User> allUsers = new ArrayList<User>();
     // arraylist to store all available rental units
     ArrayList<AvailableTickets> allAvailableTickets = new ArrayList<AvailableTickets>();

     List<String> parseFileList = new ArrayList<String>();

     // TODO: Make list of all daily transactions
     // TODO: Change padding of the rental unit price
     // TODO: Pad out all the parameters in the files

     public void setUser(String un) {
          un = Transaction.username;
     }

     public String getUser() {
          return Transaction.username;
     }

     /**
      * parses the user account file and stores that in its own array
      * 
      * @param file
      */
     public ArrayList<User> parseUserInput(String file) {
          try {
               File inpFile = new File(file);
               Scanner myReader = new Scanner(inpFile);
               while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] split = data.split("\\s+");
                    if (data.length() > 0) {
                         allUsers.add(new User(split[0], split[1]));
                    }
               }
               myReader.close();
          } catch (Exception e) {
               System.out.println("An error has occured, please try again later.");
               e.printStackTrace();
               System.exit(1);
          }
          return allUsers;
     }

     public void overWriteUserAccounts(String file) {
          try {
               FileWriter outFile = new FileWriter(file);
               for (int i = 0; i < allUsers.size(); i++) {
                    outFile.write(String.format("%s %s\n", allUsers.get(i).userName, allUsers.get(i).userType));
               }
               outFile.close();
               System.out.printf("%s has been updated\n", file);
          } catch (Exception e) {
               System.out.println("An error has occured, please try again later.");
               e.printStackTrace();
               System.exit(1);
          }
     }

     /**
      * parses the available rental units and stores that in its own array
      * 
      * @param file
      */
     public ArrayList<AvailableTickets> parseRentalUnit(String file) {
          try {
               File rentalFile = new File(file);
               Scanner myReader = new Scanner(rentalFile);
               while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] split = data.split("\\s+");
                    if (data.length() > 0) {
                         allAvailableTickets
                                   .add(new AvailableTickets(split[0], split[1], split[2], Integer.parseInt(split[3]),
                                             split[4], split[5], Integer.parseInt(split[6])));
                    }
               }
               myReader.close();
          } catch (Exception e) {
               System.out.println("An error has occured, please try again later.");
               e.printStackTrace();
               System.exit(1);
          }
          return allAvailableTickets;
     }

     public void overWriteAvailableTickets(String file) {
          String nRemaining;
          String rentalflag;
          try {
               FileWriter outFile = new FileWriter(file);
               for (int i = 0; i < allAvailableTickets.size(); i++) {
                    if (allAvailableTickets.get(i).rentalFlag) {
                         rentalflag = "T";
                    } else {
                         rentalflag = "F";
                    }

                    if (allAvailableTickets.get(i).nightRemaining < 10) {
                         nRemaining = "0" + Integer.toString(allAvailableTickets.get(i).nightRemaining);
                    } else {
                         nRemaining = Integer.toString(allAvailableTickets.get(i).nightRemaining);
                    }

                    outFile.write(String.format("%s %s %s %d %s %s %s\n",
                              allAvailableTickets.get(i).rentalUnitID,
                              allAvailableTickets.get(i).username,
                              allAvailableTickets.get(i).city,
                              allAvailableTickets.get(i).bedrooms,
                              allAvailableTickets.get(i).rentalPrice,
                              rentalflag,
                              nRemaining));
               }
               outFile.close();
               System.out.printf("%s has been updated\n", file);
          } catch (Exception e) {
               System.out.println("An error has occured, please try again later.");
               e.printStackTrace();
               System.exit(1);
          }
     }

     /**
      * Creates transaction of form type UUUUUUUUUU_TT and saves it to the daily
      * transaction file
      * 
      * @param userName -> contains username of length 15 UUUUUUUUUUUUUUU
      * @param userType -> contains user account type of length 2 TT, AA=Admin,
      *                 FS=full-standard, BS=buy-standard, SS=sell-standard
      * @throws IOException
      */
     public void CreateUser(String userLoginUserName, String userLoginType) throws IOException {
          System.out.println("Welcome to OT-BnB");
          // ask until username length is less than 15
          do {
               // prompt username
               System.out.println("Enter username (username length cannot exceed 15): ");
               userLoginUserName = scan.nextLine();

               System.out.println(
                         "Enter account type of length 2 (AA=Admin, FS=full-standard, RS=buy-standard, PS=sell-standard): ");
               userLoginType = scan.nextLine();
          } while (userLoginUserName.length() > 15);

          LoggedIn = true;
          userLoginUserName = String.format("%" + (-10) + "s", userLoginUserName).replace(' ', '_');
          // save information for user accounts
          userAccounts.setUserName(userLoginUserName);
          userAccounts.setUserType(userLoginType);

          // save information for daily transaction
          dailyTransaction.setUserName(userLoginUserName);
          dailyTransaction.setUserType(userLoginType);
          dailyTransaction.setTransactionCode("01");

          // user accounts string
          StringBuilder userAccountString = new StringBuilder();
          userAccountString.append("\n" + userAccounts.userName);
          userAccountString.append(" " + userAccounts.userType);
          userAccounts.writeUserAccounts(userAccountString.toString(), "userAccount.txt");
          // dailyTransaction.userName = userLoginUserName;
          // dailyTransaction.userType = userLoginType;
     }

     /**
      * Ask for username on the login screen as a text-line
      * 
      * @throws IOException
      */
     public Boolean LoginUser(String userName, DailyTransaction DT) throws IOException {
          // Scanner scann = new Scanner(System.in);
          System.out.println("\n-----Login-----");
          System.out.print("Enter your username: ");
          do {
               // read line
               if (userName == null || userName.length() < 1) {
                    // userName = console.readLine();
                    userName = scan.nextLine();
               }
               // loop through
               for (int i = 0; i < allUsers.size(); i++) {
                    String user = allUsers.get(i).userName.replaceAll("[^A-Za-z]+", "");
                    if (user.equals(userName)) {
                         DT.setCurrentUserName(user);
                         System.out.println("Logged In! " + userName);
                         LoggedIn = true;
                         DT.setUserName(userName);
                         DT.setUserType(allUsers.get(i).userType);

                         DT.setLoopIterations(i + 1);
                         // scann.close();
                    }
                    // if (allUsers.get(i).userType.equals("RS")) {
                    // availableTickets.setUsername(userName);
                    // DT.setUserName(userName);
                    // DT.setUserType(allUsers.get(i).userType);
                    // }
               }
          } while (LoggedIn == false);

          return LoggedIn;
          // scann.close();
     }

     /**
      * Deletes all users rental posts and deletes user account from system database
      * 
      * @param userName -> current users username
      * @throws IOException
      */
     public HashMap<String, Boolean> DeleteUserAccount(String userInput, DailyTransaction DT) throws IOException {
          // check if user is logged in and their account type if of type of admin
          HashMap<String, Boolean> retVals = new HashMap<String, Boolean>();
          System.out.println(LoggedIn);
          System.out.println("userType = " + dailyTransaction.userType);
          String username = "";
          int count = 0;
          boolean userExists = true;
          if (LoggedIn == true && DT.userType.equals("AA")) {
               System.out.println("\n-----Delete Account-----");

               retVals.put("isAdmin", true);
               do {
                    System.out.println("Enter the username of the user you want to delete: ");
                    if (userInput == null || userInput.length() < 1){
                        // username = console.readLine();
                        username = scan.nextLine();
                    } else {
                        String[] tempString = userInput.split(" ");
                        if (count == 0){
                            username = tempString[0];
                        } else {
                            username = tempString[1];
                        }
                        
                    }
                    
                    // Check if the loop has iterated
                    if (count > 0) {
                         retVals.put("loop", true);
                         break;
                    } else {
                         retVals.put("loop", false);
                    }

                    // Delete user
                    for (int i = 0; i < allUsers.size(); i++) {
                         String temp = allUsers.get(i).userName.replaceAll("[^A-Za-z]+", "");
                         if (temp.equals(username)) {
                              System.out.println("Deleting " + username);
                              userExists = true;
                              allUsers.remove(i);
                              break;
                         } else {
                              userExists = false;
                         }
                    }

                    // Delete available tickets
                    for (int i = 0; i < allAvailableTickets.size(); i++) {
                         String temp = allAvailableTickets.get(i).username.replaceAll("[^A-Za-z]+", "");
                         if (temp.equals(username)) {
                              System.out.printf("Deleting %s's tickets\n", username);
                              allAvailableTickets.remove(i);
                              break;
                         }
                    }

                    if (!userExists) {
                         System.out.printf("user %s does not exist\n", username);
                         count++;
                    }
               } while (userExists == false);
               // Update userAccounts file
               overWriteUserAccounts("../backend/userAccount.txt");
               overWriteAvailableTickets("../backend/availableTickets.txt");

               // save information for daily transaction
               DT.setUserName(username);
               DT.setTransactionCode("02");

               return retVals;
          } else {
               System.out.println(
                         "You are either not logged in or have not created an admin account, to delete an account please log in as an admin.");
               retVals.put("isAdmin", false);
               return retVals;
          }
     }

     /**
      * Posts a new rental unit in the system and saves it to the daily transaction
      * file
      * 
      * @param city             -> the city at which the rental unit is (String)
      * @param rentalPrice      -> the rental price for the unit (Double)
      * @param numberOfBedrooms -> number of bedrooms in unit (int)
      * @param rentedFlag       -> if unit is rented (Boolean)
      * @throws IOException
      */
     public void PostRentalUnit(String city, String rentalPrice, int numberOfBedrooms, Boolean rentedFlag)
               throws IOException {
          // check if user is logged in and account type if rent standard
          System.out.println(LoggedIn + " " + dailyTransaction.userType);
          if (LoggedIn == true && dailyTransaction.userType.equals("RS")) {
               System.out.println("\n-----Post Rental Unit-----");
               System.out.println("Enter a rental unit information you would like to post to our system.");
               do {
                    System.out.println("Enter city name of the rental unit: ");
                    city = scan.nextLine();

                    System.out.println("Enter rental price of the unit: ");
                    rentalPrice = scan.nextLine();

                    System.out.println("Enter number of bedrooms in unit: ");
                    // numberOfBedrooms = Integer.parseInt(console.readLine());
                    numberOfBedrooms = Integer.parseInt(scan.nextLine());

                    // System.out.print("Enter if unit is rented: ");
                    // rentedFlag = Boolean.parseBoolean(console.readLine());
                    // TODO: unsafe check, parse out flags in string to figure out length to
                    // maintain daily transaction file integrity (fix phase 3)
               } while (city.length() > 15);

               if (!rentalPrice.contains(".")) {
                    rentalPrice = rentalPrice.concat(".");
               }
               rentalPrice = String.format("%" + (-7) + "s", rentalPrice).replace(' ', '0');
               // save information to available rental units
               String rentalID = availableTickets.generateRentalID(8);
               availableTickets.setRentalUnitID(rentalID);
               availableTickets.setCity(city);
               availableTickets.setRentalPrice(rentalPrice);
               availableTickets.setBedrooms(numberOfBedrooms);
               availableTickets.setRentalFlag(false);
               availableTickets.setNightsRemaining(9);

               // save information for daily transaction
               dailyTransaction.setTransactionCode("03");
               dailyTransaction.setRentalId(rentalID);
               dailyTransaction.setCity(city);
               dailyTransaction.setRentalPrice(rentalPrice);
               dailyTransaction.setBedrooms(numberOfBedrooms);
               dailyTransaction.setNumNights(9);
               // dailyTransaction.setRentalFlag(false);
          } else {
               System.out.println(
                         "You are either not logged in or have not created a rent-standard account, to post a rental unit please either login or create a rent-standard account.");
               return;
          }
     }

     /**
      * Search for all available rental units in a given city with a maximum rental
      * price and minimum number of bedrooms
      * 
      * @param city             -> ask for city name (String)
      * @param rentalPrice      -> ask for rental price (Double)
      * @param numberOfBedrooms -> ask for # of bedrooms (int)
      * @throws IOException
      */
     public void SearchRentalUnit(String city, String rentalPrice, int numberOfBedrooms) throws IOException {
          do {
               

               System.out.println("Enter city name of the rental unit: ");
               city = scan.nextLine();

               System.out.println("Enter rental price of the unit: ");
               rentalPrice = scan.nextLine();

               System.out.println("Enter number of bedrooms in unit: ");
               
               try {
                    numberOfBedrooms = Integer.parseInt(scan.nextLine());
               } catch (NumberFormatException e) {
                    numberOfBedrooms = 1;
               }


               System.out.println("----- All rental Units Available -----");
               // read the available tickets file
               for (int i = 0; i < allAvailableTickets.size(); i++) {
                    AvailableTickets currentItem = allAvailableTickets.get(i);
                    if (city.equals(currentItem.city) && Double.parseDouble(currentItem.rentalPrice) < Double.parseDouble(rentalPrice) && currentItem.bedrooms > numberOfBedrooms && currentItem.rentalFlag == false) {
                         System.out.println("Rental Unit: " + i + " " + currentItem.rentalUnitID + " " + currentItem.username + " " + currentItem.city + " " + currentItem.bedrooms + " " + currentItem.rentalPrice + " " + currentItem.rentalFlag + " " + currentItem.nightRemaining);
                    }
               }
          } while (city.length() > 15 || rentalPrice == "");

          // save information for the daily transaction
          if (!rentalPrice.contains(".")) {
               rentalPrice = rentalPrice.concat(".");
          }

          rentalPrice = String.format("%" + (-7) + "s", rentalPrice).replace(' ', '0');
          // System.out.println("FORMAT STRING: " + rentalPrice);
          dailyTransaction.setCity(city);
          dailyTransaction.setRentalPrice(rentalPrice);
          dailyTransaction.setBedrooms(numberOfBedrooms);
          dailyTransaction.setTransactionCode("04");
     }

     /**
      * Rent an available rental unit
      * 
      * @param rentalId       -> rental id of the unit (String)
      * @param numberOfNights -> number of nights available on unit (int)
      * @throws IOException
      */
     public void RentAvailableUnit(String rentalId, int numberOfNights) throws IOException {
          // check if user is loggedin and account type is post standard
          if (LoggedIn == true && dailyTransaction.userType.equals("PS")) {
               System.out.println("OT-BnB");
               System.out.println("Rent an available unit");

               do {
                    System.out.println("Enter the rental ID: ");
                    rentalId = scan.nextLine();

                    System.out.println("Enter number of nights you would like to rent out: ");
                    numberOfNights = Integer.parseInt(scan.nextLine());
                    // TODO: for next phase of development integrate more robust check before
                    // accepting user input (assuming user will always give bad input)
               } while (numberOfNights > 9);

               // save information for daily transaction
               dailyTransaction.setRentalId(rentalId);
               dailyTransaction.setNumNights(numberOfNights);
               dailyTransaction.setTransactionCode("05");
          } else {
               System.out.println(
                         "You are either not logged in or have not created a post-standard account, to post a rental unit please either login or create a post-standard account.");
               return;
          }
     }

     /**
      * Writes out to the daily Transaction file
      * 
      * @throws IOException
      */
     public void logout() throws IOException {
          // TODO: Update end of session that all fixed length requirements are fulfilled
          // TODO: Pad all empty spaces of fixed length requirements with trailing _
          // "underscores"
          if (LoggedIn == false) {
               System.out.println("You are not logged In!");
               return;
          }

          // daily transaction string
          StringBuilder dailyTransactionString = new StringBuilder();

          if (dailyTransaction.transactionCode == null) {
               dailyTransaction.setTransactionCode("00");
          }
          dailyTransactionString.append("\n" + dailyTransaction.transactionCode);
          dailyTransactionString.append(" " + dailyTransaction.userName);
          dailyTransactionString.append(" " + dailyTransaction.userType);
          if (dailyTransaction.rentalUnitID == null) {
               dailyTransaction.setRentalId("________");
          }
          dailyTransactionString.append(" " + dailyTransaction.rentalUnitID);
          if (dailyTransaction.city == null) {
               dailyTransaction.setCity("_______________");
          }
          dailyTransactionString.append(" " + dailyTransaction.city);
          if (dailyTransaction.bedrooms == -1) {
               dailyTransaction.setBedrooms(0);
          }
          dailyTransactionString.append(" " + dailyTransaction.bedrooms);
          if (dailyTransaction.rentalPrice == null) {
               dailyTransaction.setRentalPrice("10");
          }
          dailyTransactionString.append(" " + dailyTransaction.rentalPrice);
          if (dailyTransaction.numNights == -1) {
               dailyTransaction.setNumNights(0);
          }
          dailyTransactionString.append(" " + dailyTransaction.numNights);

          // available rental units
          StringBuilder availableRentalUnitsString = new StringBuilder();
          if (availableTickets.rentalUnitID == null) {
               availableTickets.setRentalUnitID("0000000a");
          }
          availableRentalUnitsString.append("\n" + availableTickets.rentalUnitID);
          if (availableTickets.username == null) {
               availableTickets.setUsername(dailyTransaction.userName);
          }
          availableRentalUnitsString.append(" " + availableTickets.username);
          if (availableTickets.city == null) {
               availableTickets.setCity("_______________");
          }
          availableRentalUnitsString.append(" " + availableTickets.city);
          availableRentalUnitsString.append(" " + availableTickets.bedrooms);
          if (availableTickets.rentalPrice == null) {
               availableTickets.setRentalPrice("10");
          }
          availableRentalUnitsString.append(" " + availableTickets.rentalPrice);
          if (availableTickets.rentalFlag == null) {
          availableTickets.setRentalFlag(false);
          }
          if (availableTickets.rentalFlag == true) {
          availableRentalUnitsString.append(" T");
          } else if (availableTickets.rentalFlag == false) {
          availableRentalUnitsString.append(" F");
          }
          availableRentalUnitsString.append(" " + availableTickets.nightRemaining);

          // System.out.println(dailyTransactionString);
          // System.out.println(userAccountString);
          // System.out.println(availableRentalUnitsString);

          // write to daily transaction file
          dailyTransaction.writeDailyTransaction(dailyTransactionString.toString(), "../backend/dailytransaction.output");
          availableTickets.writeAvailableRentalUnits(availableRentalUnitsString.toString(), "../backend/availableTickets.txt");
          // availableTickets.writeAvailableRentalUnits(availableRentalUnitsString.toString(),
          // "availableTickets.txt");
          LoggedIn = false;
          System.out.println(dailyTransaction.userName + " you have been logged out.");
     }

     /**
      * Display the front end that the user will interact with
      * 
      * @throws IOException
      */
     public void displayFrontEnd() throws IOException {
          int selection = 0;
          do {
               displaySelectionMenu();
               System.out.print("Enter you choice: ");
               selection = Integer.parseInt(scan.nextLine());
          } while (selection < 1);

          System.out.println("\nOT-BnB");

          switch (selection) {
               // create user account
               case 1:
                    LoggedIn = LoginUser(username, dailyTransaction);
                    // parseRentalUnit("availableTickets.txt");
                    break;
               // user login
               case 2:
                    CreateUser(dailyTransaction.userName, dailyTransaction.userType);
                    break;
               // search for available rental units
               case 3:
                    SearchRentalUnit(dailyTransaction.city, dailyTransaction.rentalPrice, dailyTransaction.bedrooms);
                    break;
               // post a new rental units
               case 4:
                    PostRentalUnit(dailyTransaction.city, dailyTransaction.rentalPrice, dailyTransaction.bedrooms,
                              dailyTransaction.rentalFlag);
                    break;
               // rent an available rental units
               case 5:
                    RentAvailableUnit(dailyTransaction.rentalUnitID, dailyTransaction.numNights);
                    break;
               // delete user account
               case 6:
                    DeleteUserAccount("", dailyTransaction);
                    break;
               case 7:
                    logout();
                    break;
               case 8:
                    if (LoggedIn == false){
                        System.out.println("Successfully exited the program");
                        return;
                    } else {
                        logout();
                        System.out.println("Successfully logged out and exited the program");
                        return;
                    }
               default:
                    break;
          }
          // if user isn't logged in then return
        //   if (LoggedIn == false)
        //        return;
          // display menu after choices have been selected
          displayFrontEnd();
     }

     /**
      * display selection menu options
      */
     public void displaySelectionMenu() {
          System.out.println("\nWelcome to OT-BnB");
          System.out.println("Select option 1 to login to your account");
          System.out.println("Select option 2 to create your account");
          System.out.println("Select option 3 to search for available rental units");
          System.out.println("Select option 4 to post a new rental unit");
          System.out.println("Select option 5 to rent an available unit");
          System.out.println("Select option 6 to delete an account");
          System.out.println("Select option 7 to logout");
          System.out.println("Select option 8 to exit");
     }

     public static void main(String[] args) {
          Transaction transaction = new Transaction();

          // transaction.parseUserInput(args[0]);
          transaction.parseUserInput("../backend/userAccount.txt");
          transaction.parseRentalUnit("../backend/availableTickets.txt");
          // if (LoggedIn) {
          // // transaction.parseRentalUnit(args[1]);
          // transaction.parseRentalUnit("availableTickets.txt");
          // }
          // display front end
          try {
               transaction.displayFrontEnd();
          } catch (IOException e) {
               e.printStackTrace();
          }

          scan.close();
     }
}