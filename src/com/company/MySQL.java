package com.company;

import com.company.Bank.Account;
import com.company.Bank.Borrowing;
import com.company.Bank.Cheque;
import com.company.Bank.DepositAccount;

import java.security.PublicKey;
import java.sql.*;
import java.util.concurrent.BrokenBarrierException;

public class MySQL {
    static String URL = "jdbc:mysql://localhost:3306/ap";
    static String Username = "root";
    static String Password = "6006";

    //total
    boolean executeSQL (String sqlcmd) throws SQLException, ClassNotFoundException {
//        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,Username,Password);

            Statement s = con.prepareStatement(sqlcmd) ;
            s.execute(sqlcmd) ;
            con.close();
            return true;
//        }catch (Exception ex){
//            return false;
//        }
    }
    static int getMaxId() throws SQLException {
        String sqlCmd = "SELECT MAX(id) FROM estate " ;
        MySQL sql = new MySQL();
        ResultSet result = sql.executeQuery(sqlCmd);
        if (result.next())
            return result.getInt(1);
        else
            return 0 ;
    }
    ResultSet executeQuery (String sqlcmd){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,Username,Password);

            Statement s = con.prepareStatement(sqlcmd) ;
            ResultSet rs = s.executeQuery(sqlcmd) ;

            return rs;
        }catch (Exception ex){
            return null;
        }
    }

    //Person
    static boolean addPerson(Person per)  {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,Username,Password);

            String sqlCmd = String.format("INSERT INTO persons (name , id , age , gender ,walet) values ('%s','%s','%s','%s','%s')" , per.getName(),per.getId(),per.getAge(),per.getGender(),per.getWallet());

            Statement s = con.prepareStatement(sqlCmd) ;
            s.execute(sqlCmd) ;
            con.close();
            return true;
        } catch (Exception ex){
            return false;
        }

    }
    static boolean loadPerson(String id ) throws SQLException {
        String sqlcmd = String.format("SELECT * from persons WHERE id = '%s'" , id);
        MySQL sql = new MySQL();
        ResultSet rs = sql.executeQuery(sqlcmd);
        if (rs.next()){
            System.out.println("Name : \t\t\t" + rs.getString("name"));
            System.out.println("Personal id : \t" + rs.getString("id"));
            System.out.println("Age : \t\t\t" + rs.getString("age"));
            System.out.println("Gender : \t\t" + rs.getString("gender"));
            System.out.println("Money : \t\t" + rs.getString("walet"));
            System.out.println();
            return true;
        }
        return false;

    }
    public static boolean updatePerson(Person person) {
        try {
            String sqlcmd = String.format("UPDATE persons SET name = '%s',age ='%s' , gender = '%s' , walet = '%s' WHERE id = '%s'", person.getName(), person.getAge(), person.getGender(), person.getWallet(), person.getId());

            MySQL sql = new MySQL();
            boolean res = sql.executeSQL(sqlcmd);
            return res;
        }
        catch(Exception ex){
            ex.getStackTrace();
            return false;
        }
    }
    static boolean deletePerson(String id) throws SQLException, ClassNotFoundException {
        String sqlcmd = String.format("DELETE FROM persons WHERE id = '%s'" , id);

        MySQL sql = new MySQL();
        boolean res = sql.executeSQL(sqlcmd);
        return res;
    }
    static void loadFromSqlPerson(){
        try {
            String sqlcmd = "SELECT * from persons";
            MySQL sql = new MySQL();
            ResultSet rs = sql.executeQuery(sqlcmd);
            while (rs.next()) {
                System.out.println("Name : \t\t\t" + rs.getString("name"));
                System.out.println("Personal id : \t" + rs.getString("id"));
                System.out.println("Age : \t\t\t" + rs.getString("age"));
                System.out.println("Gender : \t\t" + rs.getString("gender"));
                System.out.println("Money : \t\t" + rs.getString("walet"));
                System.out.println();
            }
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }

    //Estate
    static boolean addEstate(Estate estate) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,Username,Password);

            int id = getMaxId() +1;

            String sqlCmd = String.format("INSERT INTO estate (id , personId , address , dateBuy ,value) values ('%s','%s','%s','%s','%s')" , id,estate.getPersonId(),estate.getAddress(),estate.getDateBuy(),estate.getValue());

            Statement s = con.prepareStatement(sqlCmd) ;
            s.execute(sqlCmd) ;
            con.close();
            return true;
        } catch (Exception ex){
            return false;
        }

    }
    static boolean updateEstate(Estate estate , int id) throws SQLException, ClassNotFoundException {

        String sqlcmd = String.format("UPDATE estate SET personId = '%s',address ='%s' , dateBuy = '%s' , value = '%s' WHERE id = '%d'" ,estate.getPersonId(),estate.getAddress(),estate.getDateBuy(),estate.getValue() , id  );

        MySQL sql = new MySQL();
        boolean res = sql.executeSQL(sqlcmd);
        return res ;


    }
    static boolean deleteEstate(int id)  {
        try {
            String sqlcmd = String.format("DELETE FROM estate WHERE id = '%d'", id);

            MySQL sql = new MySQL();
            boolean res = sql.executeSQL(sqlcmd);
            return res;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return  false ;
        }
    }
    static boolean loadEstate(String personalId ) throws SQLException {
        String sqlcmd = String.format("SELECT * from estate WHERE personId = '%s'", personalId);
        MySQL sql = new MySQL();
        ResultSet rs = sql.executeQuery(sqlcmd);
        if (rs.next()) {
            System.out.println("Estate id : \t" + rs.getString("id"));
            System.out.println("Address: \t\t" + rs.getString("address"));
            System.out.println("Date buy : \t\t" + rs.getString("dateBuy"));
            System.out.println("Value : \t\t" + rs.getString("value"));
            System.out.println();
            return true;
        }
        return false;
    }
    static void loadFromSqlEstate(){
        try {
            String sqlcmd = "SELECT * from estate";
            MySQL sql = new MySQL();
            ResultSet rs = sql.executeQuery(sqlcmd);
            while (rs.next()) {
                System.out.println("Id : \t\t\t" + rs.getString("id"));
                System.out.println("Address: \t\t" + rs.getString("address"));
                System.out.println("Date buy : \t\t" + rs.getString("dateBuy"));
                System.out.println("Value : \t\t" + rs.getString("value"));
                System.out.println();
            }
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }

    //Bank
    static String getMaxAccountId() throws SQLException {
        String sqlCmd = "SELECT MAX(accountId) FROM bankaccount " ;
        MySQL sql = new MySQL();
        ResultSet result = sql.executeQuery(sqlCmd);
        if (result.next())
            return result.getString(1);
        else
            return null;
    }
    static String getMaxNumber() throws SQLException {
        String sqlCmd = "SELECT Max(number) FROM creditcard ٌWHERE " ;
        MySQL sql = new MySQL();
        ResultSet result = sql.executeQuery(sqlCmd);
        if (result.next())
            return result.getString(1);
        else
            return null ;

    }
    static int getMaxIdCheque() throws SQLException {
        String sqlCmd = "SELECT MAX(BatchofchecksId) FROM batchofchecks " ;
        MySQL sql = new MySQL();
        ResultSet result = sql.executeQuery(sqlCmd);
        if (result.next())
            return result.getInt(1);
        else
            return 0 ;
    }
    static String getMaxAccountDepId() throws SQLException {
        String sqlCmd = "SELECT MAX(accountId) FROM bankaccount " ;
        MySQL sql = new MySQL();
        ResultSet result = sql.executeQuery(sqlCmd);
        if (result.next())
            return result.getString(1);
        else
            return null;
    }
    public static boolean addAccount(Account account) throws ClassNotFoundException, SQLException {
//        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,Username,Password);

        String id = getMaxAccountId();
        assert id != null;
        long idd = Long.parseLong(id);
        idd = idd + 698 ;
        id = String.valueOf(idd);

        String sqlCmd = String.format("INSERT INTO bankaccount (accountId , personId, bankroll , date ,demerit) values ('%s','%s','%d','%s','%d')" , id,account.getPersonId(),account.getBankroll(),account.getDate(),account.getDemerit());

        Statement s = con.prepareStatement(sqlCmd) ;
        s.execute(sqlCmd) ;
        con.close();
        return true;
//    } catch (Exception ex){
//        return false;
//    }
    }
    public static boolean addCreditCard(Account account , String name) throws ClassNotFoundException, SQLException {
//        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,Username,Password);

        String number = getMaxNumber() ;
        assert number != null;
        long number1 = Long.parseLong(number);
        number1 = number1 + 258 ;
        number = String.valueOf(Long.valueOf(number1));

        String sqlCmd = String.format("INSERT INTO creditcard (personId , name, number , cvv2 ,dateExpiration) values ('%s','%s','%s','%s','%s')",account.getPersonId(),name,number,4587,"05-06"   );
                                                                                                                      //,account.getPersonId(),,number,4525,"05-06"
        Statement s = con.prepareStatement(sqlCmd) ;
        s.execute(sqlCmd) ;
        con.close();
        return true;
//    } catch (Exception ex){
//        return false;
//    }
    }
    public static boolean addBatchOfCheck(String personalId) throws ClassNotFoundException, SQLException {
//        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,Username,Password);

        int id = getMaxIdCheque();
        id++;

        String sqlCmd = String.format("INSERT INTO batchofchecks (personalid ,BatchofchecksId) values ('%s','%d')", personalId,id );

        Statement s = con.prepareStatement(sqlCmd) ;
        s.execute(sqlCmd) ;
        con.close();
        return true;
//    } catch (Exception ex){
//        return false;
//    }




    }
    public static boolean addAccountDep(DepositAccount account) throws ClassNotFoundException, SQLException {
//        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,Username,Password);

        String id = getMaxAccountDepId();
        assert id != null;
        long idd = Long.parseLong(id);
        idd = idd + 698 ;
        id = String.valueOf(idd);


        String sqlCmd = String.format("INSERT INTO depositaccount (accountId , personId, bankroll , date ,interestRates,time,demerit) values ('%s','%s','%d','%s','%f','%d','%d')" , id,account.getPersonId(),account.getBankroll(),account.getDate(),account.getInterestRates(),account.getTime(),account.getDemerit());

        //String sqlCmd = String.format("INSERT INTO bankaccount (accountId , personId, bankroll , date ,demerit) values ('%s','%s','%d','%s','%d')" , id,account.getPersonId(),account.getBankroll(),account.getDate(),account.getDemerit());

        Statement s = con.prepareStatement(sqlCmd) ;
        s.execute(sqlCmd) ;
        con.close();
        return true;
//    } catch (Exception ex){
//        return false;
//    }
    }
    public static Account checkAccountId(String accountId)  {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,Username,Password);

        String sqlcmd = String.format("SELECT * from bankaccount WHERE personId = '%s'" , accountId);

        MySQL sql = new MySQL();

        ResultSet rs = sql.executeQuery(sqlcmd);
        if (rs.next()) {
            Account account = new Account(rs.getString("accountId"), rs.getString("personId"), rs.getInt("bankroll"), rs.getString("date") ,rs.getInt("demerit"));
            return account ;
        }
        con.close();
        return null;
    } catch (Exception ex){
        return null;
    }
    }
    public static Person checkPersonId(String accountId)  {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,Username,Password);

        String sqlcmd = String.format("SELECT * from persons WHERE id = '%s'" , accountId);

        MySQL sql = new MySQL();
        ResultSet rs = sql.executeQuery(sqlcmd);
        if (rs.next()){
            Person person = new Person(rs.getString("name"), rs.getString("id"), rs.getString("age"),rs.getString("gender"),rs.getString("walet") );
            return person;
        }
        return null;
    } catch (Exception ex){
        return null;
        }
    }
    public static boolean updateAccount(Account account) throws SQLException, ClassNotFoundException {

        String sqlcmd = String.format("UPDATE bankaccount SET bankroll = '%d' , demerit = '%d'  WHERE accountId = '%s'" , account.getBankroll(),account.getDemerit() , account.getAccountId()  );

        MySQL sql = new MySQL();
        boolean res = sql.executeSQL(sqlcmd);
        return res ;


    }
    public static boolean addNewCheque(Cheque cheque) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,Username,Password);

            String sqlCmd = String.format("INSERT INTO cheques (idReceiver,price,date,accountId,idSender) values ('%s','%s','%s','%s','%s')" ,cheque.getIdReceiver(),cheque.getPrice(),cheque.getDate(),cheque.getAccountId(),cheque.getIdSender());

            Statement s = con.prepareStatement(sqlCmd) ;
            s.execute(sqlCmd) ;
            con.close();
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
    public static Cheque checkCheque(String personalId) throws ClassNotFoundException, SQLException {
        //try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,Username,Password);

            String sqlcmd = String.format("SELECT * from cheques WHERE idReceiver= '%s'" , personalId);

            MySQL sql = new MySQL();

            ResultSet rs = sql.executeQuery(sqlcmd);
            if (rs.next()) {
                Cheque cheque = new Cheque(rs.getString("idReceiver"),rs.getString("price"),rs.getString("date"),rs.getString("accountId"),rs.getString("idSender"));

                //Account account = new Account(rs.getString("accountId"), rs.getString("personId"), rs.getInt("bankroll"), rs.getString("date"));
                return  cheque;
            }
            con.close();
            return null;
//    } catch (Exception ex){
//        return false;
//    }



    }
    public static boolean deleteCheque (Cheque cheque) throws SQLException, ClassNotFoundException {
        String sqlcmd = String.format("DELETE FROM cheques WHERE idReceiver = '%s' AND idSender = '%s' ",cheque.getIdReceiver() , cheque.getIdSender());

        //String sqlcmd = String.format("DELETE FROM cheques WHERE idReceiver = '%s' ",cheque.getIdReceiver() );

        MySQL sql = new MySQL();
        boolean res = sql.executeSQL(sqlcmd);
        return res;
    }
    static void loadFromBankAccount()  {
        try {
            String sqlcmd = "SELECT * from bankaccount";
            MySQL sql = new MySQL();
            ResultSet rs = sql.executeQuery(sqlcmd);
            while (rs.next()) {
                System.out.println("Bank account id : \t\t\t" + rs.getString("accountId"));
                System.out.println("Personal id : \t\t\t\t" + rs.getString("personId"));
                System.out.println("Bankroll : \t\t\t\t\t" + rs.getString("bankroll"));
                System.out.println("Account opening date : \t\t" + rs.getString("date"));
                System.out.println("demerit : \t\t\t \t\t" + rs.getString("demerit"));
                System.out.println();
            }
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }
    static int sumOfBankroll() throws SQLException {
        String sqlcmd = "SELECT SUM(bankroll) FROM bankaccount";
        MySQL sql = new MySQL();
        ResultSet result = sql.executeQuery(sqlcmd);
        if (result.next())
            return result.getInt(1);
        else
            return 0 ;
    }
    static void loadFromBankAccountMoneyHeyfes() throws SQLException, ClassNotFoundException {
        String sqlcmd = "SELECT * from bankaccount";
        MySQL sql = new MySQL();
        ResultSet rs = sql.executeQuery(sqlcmd);
        while (rs.next()){
            System.out.println("Bank account id : \t\t\t" + rs.getString("accountId"));
            System.out.println("Personal id : \t\t\t\t" + rs.getString("personId"));
            String personalId = rs.getString("personId");
            System.out.println("Bankroll : \t\t\t\t\t" + rs.getString("bankroll"));
            String bankroll = rs.getString("bankroll");

            String sqlCmd = String.format("SELECT * from persons WHERE id = '%s'" , personalId);
            ResultSet rs1 = sql.executeQuery(sqlCmd);
            if (rs1.next()){
                System.out.println("Name :    \t\t\t\t\t" + rs1.getString("name"));
                System.out.println("Age :    \t\t\t\t\t" + rs1.getString("age"));
                System.out.println("Gender :    \t\t\t\t" + rs1.getString("gender"));
                robbery(personalId);
                takeMoney(personalId, Integer.parseInt(bankroll));
            }
            System.out.println();
        }
    }
    static void robbery(String personalId) throws SQLException, ClassNotFoundException {
        String sqlcmd = String.format("UPDATE bankaccount SET bankroll = '%d' WHERE personId = '%s'", 0 ,personalId );
        MySQL sql = new MySQL();
        sql.executeSQL(sqlcmd);

    }
    static void takeMoney(String personalID , int bankroll){
        Person person = MySQL.checkPersonId(personalID);
        int wallet = Integer.parseInt(person.getWallet());
        wallet = wallet + bankroll ;
        person.setWallet(wallet+"");
        MySQL.updatePerson(person);
    }
    public static boolean checkAcc(String personalId){
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,Username,Password);

        String sqlcmd = String.format("SELECT * from persons WHERE  id = '%s'" , personalId);

        MySQL sql = new MySQL();

        ResultSet rs = sql.executeQuery(sqlcmd);
        if (rs.next()) {
            Person person = new Person(rs.getString("name"),rs.getString("id"),rs.getString("age"),rs.getString("gender"),rs.getString("walet"));
            return  true ;
        }
        con.close();
        return false;
        } catch (Exception ex){
            ex.getStackTrace();
            return false;
        }
    }
    public static Estate checkEstate(String personalID)  {
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,Username,Password);


        String sqlcmd = String.format("SELECT * FROM estate WHERE personId  = '%s'" , personalID);

        MySQL sql = new MySQL();
        ResultSet rs = sql.executeQuery(sqlcmd);
        if (rs.next()) {
            //Account account = new Account(rs.getString("accountId"), rs.getString("personId"), rs.getInt("bankroll"), rs.getString("date"));
            Estate estate = new Estate(rs.getString("personId"),rs.getString("address"),rs.getString("dateBuy"),rs.getString("value"));
            return estate ;
        }
//        con.close();
        return null;
    } catch (Exception ex){
        ex.getStackTrace();
        return null;
    }
    }
    public static Date loadDay(){
        try {
            String sqlCmd = "SELECT MAX(date) FROM date ";
            MySQL sql = new MySQL();
            ResultSet result = sql.executeQuery(sqlCmd);
            if (result.next())
                return result.getDate(1);
            else
                return null;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return null;
        }


    }
    static boolean updateDate(Date date) throws ClassNotFoundException, SQLException {
        //try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, Username, Password);


        String sqlCmd = String.format("INSERT INTO date (date) values ('%s')", date);

        Statement s = con.prepareStatement(sqlCmd);
        s.execute(sqlCmd);
        con.close();
        return true;

//        catch (Exception ex){
//            ex.getStackTrace();
//            return false;
//        }
    }
    public static DepositAccount detailsDep (String personalId) {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,Username,Password);

        String sqlcmd = String.format("SELECT * from depositaccount WHERE personId = '%s'" , personalId);

        MySQL sql = new MySQL();
        ResultSet rs = sql.executeQuery(sqlcmd);
        if (rs.next()) {
            DepositAccount dep= new DepositAccount(rs.getString("accountId"),rs.getString("personId"), rs.getInt("bankroll "),rs.getString("date"),rs.getInt("demerit"),rs.getDouble("interestRates"),rs.getInt("time") );
            //DepositAccount dep = new DepositAccount(rs.getString("accountId"),personalId,rs.getInt("bankroll"),rs.getString("date"),rs.getInt("demerit"),rs.getDouble("interestRates"), rs.getInt("time"));


            return dep;
        }
        con.close();
        return null;
         }
        catch (Exception ex){
            ex.getStackTrace();
            return null;
        }
    }
    public static boolean deleteDepAcc(String personalID)  {
        try {
            String sqlcmd = String.format("DELETE FROM depositaccount WHERE personId = '%S'", personalID);

            MySQL sql = new MySQL();
            boolean res = sql.executeSQL(sqlcmd);
            return res;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return  false ;
        }
    }
    public static int getMaxIdBorrowing() throws SQLException {
        String sqlCmd = "SELECT MAX(id) FROM borrowing " ;
        MySQL sql = new MySQL();
        ResultSet result = sql.executeQuery(sqlCmd);
        if (result.next())
            return result.getInt(1);
        else
            return 0 ;
    }
    public static boolean addBorrowing(Borrowing bor)  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,Username,Password);

            String sqlCmd = String.format("INSERT INTO borrowing (id , personalId , price  , numberOfPayment ,payment , percent) values ('%d','%s','%d','%d','%d' , '%d')"
                    ,bor.getId(),bor.getPersonalId(),bor.getPrice() , bor.getNumberOfPayment(),bor.getPayment(),bor.getPercent());

            Statement s = con.prepareStatement(sqlCmd) ;
            s.execute(sqlCmd) ;
            con.close();
            return true;
        } catch (Exception ex){
            return false;
        }

    }
    public static Borrowing checkBorrowing(String accountId)  {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,Username,Password);

            String sqlcmd = String.format("SELECT * from borrowing WHERE personalId = '%s'" , accountId);

            MySQL sql = new MySQL();
            ResultSet rs = sql.executeQuery(sqlcmd);
            if (rs.next()){
                Borrowing bor = new Borrowing(rs.getInt("id"),rs.getString("personalId"), rs.getInt("price"),rs.getInt("numberOfPayment"),rs.getInt("payment"),rs.getInt("percent") );
                return bor ;
            }
            return null;
        } catch (Exception ex){
            ex.getStackTrace();
            return null;
        }
    }
    public static boolean updateBorrowing(Borrowing bor) {
        try {
            String sqlcmd = String.format("UPDATE borrowing SET personalId  ='%s' , price  = '%d' , numberOfPayment = '%d', payment = '%d' , percent ='%d' WHERE id = '%d'",
                    bor.getPersonalId(),bor.getPrice(),bor.getNumberOfPayment(),bor.getPayment(),bor.getPercent(),bor.getId());
            MySQL sql = new MySQL();
            boolean res = sql.executeSQL(sqlcmd);
            return res;
        }
        catch(Exception ex){
            ex.getStackTrace();
            return false;
        }
    }

}


