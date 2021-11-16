package queries;

import java.sql.*;

public class Query6 extends AbstractQuery{

    @Override
    public void executeQuery() throws SQLException {
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;

        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, username, password);
            myStatement = myConnection.createStatement();
            myResultSet = myStatement.executeQuery("SELECT * FROM adventureworks.getCurrencyUsage");

            while(myResultSet.next()){
                System.out.println(myResultSet.getString(1) + " -- " +  myResultSet.getString("Currency code") + " -- " +
                        myResultSet.getInt("Currency usage when buying"));
            }
            System.out.println("COLUMNS: Currency name -- Currency code -- Currency usage when buying\n");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myConnection!= null) myConnection.close();
            if (myStatement != null) myStatement.close();
            if (myResultSet != null) myResultSet.close();
        }
    }
}
