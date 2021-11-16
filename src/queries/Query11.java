package queries;

import java.sql.*;
import java.util.Scanner;

public class Query11 extends AbstractQuery{

    @Override
    public void executeQuery() throws SQLException {
        System.out.println("Unesite ime jezika:"); //TODO Ispisati korisniku par imena jezika sa kojima se moze ovo testirati.
        scanner = new Scanner(System.in);
        String languageName = scanner.nextLine();

        Connection myConnection = null;
        CallableStatement myCall = null;
        ResultSet myResultSet = null;

        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, username, password);
            myCall = myConnection.prepareCall("{call getProductInfoByLanguage(?,?)}");

            myCall.setString(1, languageName);
            myCall.registerOutParameter(2, Types.INTEGER);



            int flag = myCall.getInt(2);
            myCall.execute();
            myResultSet = myCall.getResultSet();

            while(myResultSet.next()){
                if(flag == 1){
                    System.out.println("Na datom jeziku nije napisano nijedno uputstvo.");
                }
                else{
                    System.out.println(myResultSet.getInt(1) + " -- " +  myResultSet.getString(2) + " -- " +
                            myResultSet.getBigDecimal(3));
                }
            }
            if(flag == 0)
                System.out.println("COLUMNS: Product ID -- Product name -- Product price\n");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myConnection!= null) myConnection.close();
            if (myCall      != null) myCall.close();
            if (myResultSet != null) myResultSet.close();
        }
    }
}
