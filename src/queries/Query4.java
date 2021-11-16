package queries;

import java.sql.*;

public class Query4 extends AbstractQuery{

    @Override
    public void executeQuery() {
        Connection myConnection = null;
        CallableStatement myCall = null;
        ResultSet myResultSet = null;

        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adventureworks", "student", "student");
            myCall = myConnection.prepareCall("{call procedure_name(?)}"); // svaki parametar je jedan upitnik
            //myCall.setString(1, 'NC-17'), ovo je primer za prosledjivanje input parametra
            //myCall.registerOutParameter(1, Types.INTEGER), primer za dobivanje out parametra
            //int count = myCall.getInt(1), ovako onda dobijemo taj out parametar u promenljivu da mozemo da ga printamo
            myCall.execute();
            myResultSet = myCall.getResultSet();

            while(myResultSet.next()){
                //npr. System.out.println(myResultSet.getXXX(NESTO));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(myConnection != null) {
                try {
                    myConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(myCall != null) {
                try {
                    myCall.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(myResultSet != null) {
                try {
                    myResultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
