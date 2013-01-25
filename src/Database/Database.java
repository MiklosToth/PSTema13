/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Logic.*;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;
import java.io.File;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import com.almworks.sqlite4java.SQLiteConnection;
import com.sun.org.apache.xpath.internal.operations.Lt;

/**
 *
 * @author Remus
 */
public class Database {
    //Baza de date
    public static SQLiteConnection database;
    
    /**
     * Initializeaza accesul la baza de date (conexiunea acesteia)
     */
    public static void Init()
    {
        //Open the database
        database = new SQLiteConnection(new File("C:\\Users\\Remus\\Documents\\NetBeansProjects\\ProiectSincretic13\\src\\Database\\PS13.s3db"));
	try
        {
            database.open(false);
	}
        catch (SQLiteException ex)
        {
            JOptionPane.showMessageDialog(null, ex,"Eroare",JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
	}
    }
    
    public static ArrayList<String[]> ToateSpectacolele()
    {
        //Citeste spectacolele din tabelul Spectacole al bazei de date
        
        ArrayList<String[]> data = new ArrayList<>();
        SQLiteStatement queryResult = null;
        
        try
        {
            queryResult = database.prepare("SELECT * FROM Spectacole");
            while(queryResult.step())
            {
                String[] row = new String[9];
                for(int column = 0; column < 9; column++)
                {
                    row[column] = queryResult.columnString(column);
                }
                data.add(row);
            }
        }
        catch (SQLiteException ex)
        {
            JOptionPane.showMessageDialog(null, ex,"Eroare",JOptionPane.ERROR_MESSAGE);
        }
        if(data.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Nu exista spectacole", "Eroare",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return data;
    }
    
    public static DetaliiBilet GenereazaDetaliiBilet(String idSpectacol, String titlu,double pret_balcon,double pret_loja, double pret_parter){
        
        SQLiteStatement queryResult = null;
        DetaliiBilet detBilet=null;
        int bt,lt,pt,bv,lv,pv; //baocon_total, loja_total, parter_total, balcon_vandut etc...
        double incasari;
        try
        {
            queryResult = database.prepare("SELECT * FROM Bilete where id_spectacol='"+ idSpectacol +"'");
            
            queryResult.step();
            
            bt=Integer.parseInt(queryResult.columnString(1));
            lt=Integer.parseInt(queryResult.columnString(2));
            pt=Integer.parseInt(queryResult.columnString(3));
            bv=Integer.parseInt(queryResult.columnString(4));
            lv=Integer.parseInt(queryResult.columnString(5));
            pv=Integer.parseInt(queryResult.columnString(6));
            
            incasari=pret_balcon*bv+pret_loja*lv+pret_parter*pv;
            
            detBilet=new DetaliiBilet(idSpectacol,titlu,incasari, bt, lt, pt, bv, lv, pv);
        }
        catch (SQLiteException ex)
        {
            JOptionPane.showMessageDialog(null, ex,"Eroare",JOptionPane.ERROR_MESSAGE);
        }
        return detBilet;
        
    }
    
    
    
}
