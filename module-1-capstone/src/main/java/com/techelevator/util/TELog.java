package com.techelevator.util;

import com.fasterxml.jackson.databind.DatabindException;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TELog {
    private static BufferedWriter bw = null;
    private static PrintWriter pw = null;
    private static FileWriter fw=null;
    private static DateTimeFormatter dateTime;
    private static StringBuilder string;
   private static File sourceFile = new File("logs/log.log");

    public static void log(Object message) throws TELogException, IOException {
        dateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
         string = new StringBuilder();
             try {
                 if(Objects.isNull(fw)) fw= new FileWriter(sourceFile, true);
                 string.append(dateTime.format(LocalDateTime.now())).append(" ");
                 bw = new BufferedWriter(fw);
                 pw = new PrintWriter(bw);
                 string.append(message);
                 pw.println(string);
             }catch (FileNotFoundException | DateTimeException e ){
                 System.err.println("something wrong happened : "+e.getMessage());
             }finally {
                 try  {
                     pw.flush();
                     fw.flush();
                     bw.flush();
                 } catch (TELogException e) {
                     System.err.println("Error :" + e.getMessage());
                 }
             }
    }

}
