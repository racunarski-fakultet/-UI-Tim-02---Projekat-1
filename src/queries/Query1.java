package queries;

import java.sql.*;

public class Query1 extends AbstractQuery{

    @Override
    public void executeQuery() throws SQLException {
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;

        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, username, password);
            myStatement = myConnection.createStatement();
            myResultSet = myStatement.executeQuery("select * from employees");

            while(myResultSet.next()){
                //npr. System.out.println(myResultSet.getString(indeks(krecu od 1) ili ime kolone));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myConnection!= null) myConnection.close();
            if (myStatement != null) myStatement.close();
            if (myResultSet != null) myResultSet.close();
        }
    }
}
