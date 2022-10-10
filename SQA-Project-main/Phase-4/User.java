import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class User {
     String userName = null;
     String userType = null;

     public User() { /** empty constructor */ }

     public User(String username, String userType) {
          this.userName = username;
          this.userType = userType;
     }

     /**
      * sets the username of a client
      * @param username of length 15
      */
     public void setUserName(String username) { this.userName = username; }

     /**
      * sets the usertype of a client
      * @param usertype -> (AA=admin, FS=full-standard, RS=rent-standard, PS=post-standard)
      */
     public void setUserType(String usertype) { this.userType = usertype; }

     /**
      * Writes to the user accounts file of a client when they end the session
      * @param userAccountString
      * @param outputFile
      * @throws IOException
      */
     public void writeUserAccounts(String userAccountString, String outputFile) throws IOException {
          File output = new File(outputFile);
          FileWriter fileWriter = new FileWriter(output, true);
          try {
               fileWriter.write(userAccountString);
          } catch (Exception e) {
               e.printStackTrace();
          } finally {
               fileWriter.close();
          }
     }
}