import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class DailyTransaction {
     Scanner userInput = new Scanner(System.in);

     String transactionCode = null;
     String userName = null;
     String userType = null;
     String rentalUnitID = null;
     int bedrooms = -1;
     String rentalPrice = null;
     int numNights = -1;
     String city = null;
     Boolean rentalFlag = null;

     // empty constructor
     public DailyTransaction() { /** empty constructor */  }

     /**
      * sets the transaction code that the client performs
      * @param code -> the transaction code 01-create, 02-delete, 03-post, 04-search, 05-rent, 00-end of session
      */
     public void setTransactionCode(String code) { this.transactionCode = code; }

     /**
      * sets the username of a client
      * @param username
      */
     public void setUserName(String username) { this.userName = username; }

     /**
      * sets the usertype of a client
      * @param usertype
      */
     public void setUserType(String usertype) { this.userType = usertype; }


     /**
      * sets the city of a client
      * @param city
      */
     public void setCity(String city) { this.city = city; }


     /**
      * sets the rentalID of a client
      * @param rentalID
      */
     public void setRentalId(String rentalID) { this.rentalUnitID = rentalID; }


     /**
      * sets the rentalPrice of a client
      * @param rentalPrice
      */
     public void setRentalPrice(String rentalPrice) { this.rentalPrice = rentalPrice; }


     /**
      * sets the number of bedrooms of a client rental unit
      * @param bedrooms  
      */
     public void setBedrooms(int bedrooms) { this.bedrooms = bedrooms; }


     /**
      * sets the number of nights of a clients rental unit
      * @param numNights
      */
     public void setNumNights(int numNights) { this.numNights = numNights; }


     /**
      * sets the rental flag of a clients rental unit
      * @param rentFlag
      */
     public void setRentalFlag(Boolean rentFlag) { this.rentalFlag = rentFlag; }
     

     /**
      * Writes to the daily transaction file of a client when they end the session
      * @param dailyTransactionString   -> the string that writes to the file
      * @param outputFile               -> the output file name
      * @throws IOException
      */
     public void writeDailyTransaction(String dailyTransactionString, String outputFile) throws IOException {
          File output = new File(outputFile);
          FileWriter fileWriter = new FileWriter(output, true);
          try {
               fileWriter.write(dailyTransactionString);
          } catch (Exception e) {
               e.printStackTrace();
          } finally {
               fileWriter.close();
          }
     }
}
