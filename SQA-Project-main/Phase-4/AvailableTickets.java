import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AvailableTickets {
     // IIIIIIII_UUUUUUUUUU_CCCCCCCCCCCCCCC_B_PPPPPP_F_NN
     String rentalUnitID = null;
     String username = null;
     String city = null;
     int bedrooms = 0;
     String rentalPrice = null;
     Boolean rentalFlag = null;
     int nightRemaining = 0;

     // empty constructor
     public AvailableTickets() { /* empty constructor */ }

     public AvailableTickets(String rentalunitid, String username, String city, int bedroom, String rentalprice, String rentalflag, int nightsremaining) {
          this.rentalUnitID = rentalunitid;
          this.username = username;
          this.city = city;
          this.bedrooms = bedroom;
          this.rentalPrice = rentalprice;
          if (rentalflag.equals("T")){
              this.rentalFlag = true;
          } else {
              this.rentalFlag = false;
          }
          this.nightRemaining = nightsremaining;
     }

     public String generateRentalID(int n) {
          String AlphaNumericString = "0123456789" + "abcdefghijklmnopqrstuvxyz";
          StringBuilder sb = new StringBuilder(n);

          for (int i = 0; i < n; i++) {
               int index = (int)(AlphaNumericString.length() * Math.random());
               sb.append(AlphaNumericString.charAt(index));
          }
          return sb.toString();
     }

     /**
      * sets the rental unit id
      * @param rentalUnitId
      */
     public void setRentalUnitID(String rentalUnitId) { this.rentalUnitID = rentalUnitId; }

     /**
      * sets the username of the client
      * @param username
      */
     public void setUsername(String username) { this.username = username; }

     /**
      * sets the name of the city of the rental
      * @param city
      */
     public void setCity(String city) { this.city = city; }

     /**
      * sets the bedroom for the rental unit
      * @param bedrooms
      */
     public void setBedrooms(int bedrooms) { this.bedrooms = bedrooms; }

     /**
      * sets the rental price for rental unit
      * @param rentalPrice
      */
     public void setRentalPrice(String rentalPrice) { this.rentalPrice = rentalPrice; }

     /**
      * sets the rental flag for rental unit
      * @param rentalFlag
      */
     public void setRentalFlag(Boolean rentalFlag) { this.rentalFlag = rentalFlag; }

     /**
      * sets the nights remaining for the rental unit
      * @param nightsRemaining
      */
     public void setNightsRemaining(int nightsRemaining) { this.nightRemaining = nightsRemaining; }

     /**
      * Writes to the available rental units file when user posts accounts
      * @param availableRentalUnitString
      * @param outputFile
      * @throws IOException
      */
     public void writeAvailableRentalUnits(String availableRentalUnitString, String outputFile) throws IOException {
          File output = new File(outputFile);
          FileWriter fileWriter = new FileWriter(output, true);
          try {
               fileWriter.write(availableRentalUnitString);
          } catch (Exception e) {
               e.printStackTrace();
          } finally {
               fileWriter.close();
          }
     }
}