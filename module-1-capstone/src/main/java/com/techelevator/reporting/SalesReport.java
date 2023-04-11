package com.techelevator.reporting;

import com.techelevator.core.Product;
import com.techelevator.core.Purchase;
import com.techelevator.util.TELogException;

import java.io.*;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
public class SalesReport {
    private static Map<Product,Integer> sales ;
    private static BufferedWriter bw = null;
    private static PrintWriter pw = null;
    private static FileWriter fw=null;
    private static DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    private static StringBuilder string;
    private static File sourceFile = new File("reports/sales.prt");

    public SalesReport() {
        sales = new HashMap<>();
    }

    public void addSales(Product product,Integer quantity){
        sales.put(product,sales.get(product)==null?quantity:sales.get(product)+quantity);
    }
    public void generateReport(Product[] products){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String sDateTime = dateTime.format(LocalDateTime.now());
        string = new StringBuilder();
        try {
            if(Objects.isNull(fw)) fw= new FileWriter(sourceFile, true);
            string.append("Report datetime: ").append(sDateTime).append(" ");
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(     ); // Blank line
            pw.println(string);
            double TotalSales = 0.00;
            for(Product product : products){
                int quantity = sales.get(product)==null? 0 :sales.get(product);
                pw.println(product.getName()+" | "+ quantity);
                TotalSales+=quantity*product.getPrice();
            }
            pw.println(    ); // Blank line
            pw.println("***TOTAL SALES*** "+currency.format(TotalSales));

        }catch (FileNotFoundException | DateTimeException e ){
            System.err.println("Something wrong happened : "+e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try  {
                pw.flush();
                fw.flush();
                bw.flush();
            } catch (TELogException | IOException e) {
                System.err.println("Error :" + e.getMessage());
            }
        }
    }

}
