package Accounts.Expenses;


import Utilities.OracleConnection;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpensesModel {
    protected ObservableList<Expenses> getExpensesTableRecords() throws SQLException {
        String sql = "select expenses_id, type, title, description, amount,expense_date from expenses where (extract (month from expense_date))= (extract (month from sysdate)) order by expenses_id";
        ObservableList<Expenses> expenses = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String type = rs.getString(2);
                String title = rs.getString(3);
                String description = rs.getString(4);
                int amount = rs.getInt(5);
                Date expDate = rs.getDate(6);


                Expenses expense = new Expenses(id, type, title, description, amount, expDate);

                expenses.add(expense);
            }
            return expenses;
        } catch (Exception e) {
            System.out.println("getTableRecords : expModel");
            e.printStackTrace();
            throw e;
        }
    }

    protected boolean isExpensesAddedSuccessful(String expType, String expTitle, String expDescription, int amount, Date expDate) {
        try {
            String sql = "insert into expenses(type,title,description,amount,expense_date) values(?,?,?,?,?)";

            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            java.sql.Date exp_Date = new java.sql.Date(expDate.getTime());
            ps.setString(1, expType);
            ps.setString(2, expTitle);
            ps.setString(3, expDescription);
            ps.setInt(4, amount);
            ps.setDate(5, exp_Date);

            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("isExpenseAddSuccessfull\n\n");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    protected boolean isExpenseUpdateSuccessful(String expId, String expType, String expTitle, String expDescription, int amount, String expDate) {
        try {
            String sql = "update expenses set type=?,title=?,description=?,amount=?,expense_date=? where expenses_id=?";
            Date eDate = new SimpleDateFormat("MM/dd/yyyy").parse(expDate);


            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            java.sql.Date exp_Date = new java.sql.Date(eDate.getTime());
            ps.setString(1, expType);
            ps.setString(2, expTitle);
            ps.setString(3, expDescription);
            ps.setInt(4, amount);
            ps.setDate(5, exp_Date);
            ps.setString(6, expId);

            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("isExpenseAddedSuccessful\n\n");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    protected String[] showExpenseDetails(String[] info, String expid) {
        try {
            List<String> list = new ArrayList<>();
            String sql = "select * from expenses where expenses_id=?";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, expid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
                list.add(rs.getString(2));
                list.add(rs.getString(3));
                list.add(rs.getString(4));
                list.add(String.valueOf(rs.getInt(5)));
                list.add(String.valueOf(rs.getDate(6)));

            }
            info = list.toArray(info);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    protected int getSalary(){
        try{
            String sql="select sum(num_of_employee(designation_name,id)*amount) from designation";
            OracleConnection oc=new OracleConnection();
            PreparedStatement ps=oc.conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}


