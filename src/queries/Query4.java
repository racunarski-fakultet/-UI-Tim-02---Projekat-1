package queries;

import java.sql.*;

public class Query4 extends AbstractQuery{

    @Override
    public void executeQuery() throws SQLException {
        Connection myConnection = null;
        CallableStatement myCall = null;
        ResultSet myResultSet = null;

        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, username, database);
            myCall = myConnection.prepareCall("{call procedure_name(?,?,?)}"); // svaki parametar je jedan upitnik
            //myCall.setString(1, 'NC-17'), ovo je primer za prosledjivanje input parametra
            //myCall.registerOutParameter(2, Types.INTEGER), primer za dobivanje out parametra
            //int count = myCall.getInt(2), ovako onda dobijemo taj out parametar u promenljivu da mozemo da ga printamo
            myCall.execute();
            myResultSet = myCall.getResultSet();

            while(myResultSet.next()){//todo mozda nije potrebno, proveri
                //npr. System.out.println(count aslkjdfhasdkljh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myConnection!= null) myConnection.close();
            if (myCall      != null) myCall.close();
            if (myResultSet != null) myResultSet.close();
        }
    }
}
