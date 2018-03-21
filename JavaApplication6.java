/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author user
 */
public class JavaApplication6 {
    static Connection con=null;
public static Connection connectDB(){
        //Code for connecting to db
       try{
           Class.forName("org.sqlite.JDBC");
           con = DriverManager.getConnection("jdbc:sqlite:prepareTest6.db");
           //con=DriverManger.getConnection("jdbc.mysql://localhost/cms","root","");
           if(con!=null)
               System.out.println("CONNECCTED SUCCESFULLY!!!!");
           else
               System.out.println("ERROR IN CONNECTING");
       }catch(ClassNotFoundException | SQLException e){
           e.printStackTrace();
       } 
         return con;
    }
    public void create(){
        try{
          String create="CREATE TABLE IF NOT EXISTS client(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                           "client_first_name VARCHAR,"+
                                           "client_last_name VARCHAR,"+
                                           "client_mob VARCHAR,"+
                                           "client_address VARCHAR,"+
                                           "client_dob DATE,"+
                                           "client_bg VARCHAR,"+
                                           "client_height VARCHAR,"+
                                           "client_weight INT,"+
                                           "client_medical_problem VARCHAR,"+
                                           "client_package_name VARCHAR,"+
                                           "package_start_date DATE,"+
                                           "package_end_date DATE,"+
                                           "package_duration INT,"+
                                           "client_package_amount INT"+
                                           "client_balance_fees INT"+
                                           "trainer_accquired INT)"+"";
        Statement st=this.con.createStatement();
        st.executeUpdate(create);
        String login="CREATE TABLE IF NOT EXISTS login(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                      "name VARCHAR"+"surname VARCHAR"+"username VARCHAR"+"password VARCHAR)";
        st.executeUpdate(login);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void insert(){
        try{
 /*client_first_name,client_last_name,client_mob,client_address,client_dob,"+"client_bg,client_height,client_weight,client_medical_problem,client_package_name,package_start_date,package_end_date,package_duration,client_package_amount*/
           String insert="insert into client(client_first_name,client_last_name,client_mob,client_address,client_dob,"+"client_bg,client_height,client_weight) "+
                           "VALUES('ashnil','vaziraní',8669166039,'ulhasnagar','1998-08-26','b+','143',70,'nil','Strength','2018-03-02','2018-06-02',3000)";
             
//            PreparedStatement st=(PreparedStatement) con.createStatement();
//            st.executeUpdate(sql);
//            st.setString(1,"ashnil");
//            st.setString(2, "vazirani");
//            st.setInt(3,966534221);
//            st.setInt(8,456789012);
           String insertQuery="insert into client(client_first_name, client_last_name, client_mob, client_address, client_dob, client_bg, client_height, client_weight, client_medical_problem, client_package_name, package_start_date, package_end_date, package_duration, client_package_amount) "+
                   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           PreparedStatement ps=this.con.prepareStatement(insertQuery);
           ps.setString(1,"dhiren");
           ps.setString(2, "choti");
           ps.setString(3,"9096159000");//int cannot handle it so typecast it later!!
           ps.setString(4,"ULHASNAGAR");
           ps.setString(5,"1994-12-12");
           ps.setString(6,"b+");
           ps.setString(7,"103");
           ps.setInt(8,70);
           ps.setString(9,"WEIGHT!");
           ps.setString(10,"cardio");
           ps.setString(11,"2018-01-16");
           ps.setString(12,"2018-03-16");
           ps.setInt(13,3);
           ps.setInt(14,3000);
           ps.executeUpdate();
           System.out.println("RECORD INSERTED!!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String select(){
        try{
            String name="";
            int i=0;
            String sql="SELECT * FROM client";// WHERE (package_start_date)>=package_end_date";
            Statement st=con.createStatement();
           ResultSet rs=st.executeQuery(sql);
           while(rs.next()){
                    System.out.println("ID:" +rs.getInt("ID"));
                    System.out.println("FIRST NAME:" +rs.getString("client_first_name"));
                    System.out.println("LAST NAME:" +rs.getString("client_last_name"));
                    long mobno=Long.parseLong(rs.getString("client_mob"));//fetch mobno in long
                    System.out.println("mobile:" +mobno);
                    System.out.println("address:" +rs.getString("client_address"));
                    System.out.println("dob:" +rs.getString("client_dob"));
                    System.out.println("BG::" +rs.getString("client_bg"));
                    System.out.println("height:" +rs.getString("client_height"));
                    System.out.println("weight:" +rs.getInt("client_weight"));
                    System.out.println("medical prblm:" +rs.getString("client_medical_problem"));
                    System.out.println("pack name:" +rs.getString("client_package_name"));
                    System.out.println("start dtae:" +rs.getString("package_start_date"));
                    System.out.println("end date:" +rs.getString("package_end_date"));
                    System.out.println("duration in months:" +rs.getInt("package_duration"));
                    System.out.println("amt:" +rs.getInt("client_package_amount"));
                    name=rs.getString("client_first_name");
                    return name;
           }
          
            //rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
         return null;
    }
    /**
     * @param args the command line arguments 
     */
    //code for connecting DB
    public static void main(String[] args) throws AWTException, MalformedURLException, InterruptedException, InterruptedException, SQLException{
        // TODO code application logic here
//       TestFrame tf=new TestFrame();
//       tf.setVisible(true);
        JavaApplication6 j=new JavaApplication6();
         j.connectDB();
        j.create();
   //   j.insert();
//          String name;
//         name = j.select();
      
        PersonalInfo p=new PersonalInfo();
        p.setVisible(true);
        p.fetchData();
//        TrayIconDemo t=new TrayIconDemo();
//        t.getNotification();
    }
    
}
