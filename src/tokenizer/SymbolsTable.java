/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer;

/**
 *
 * @author faraj
 */
import java.util.Enumeration;
import java.util.Hashtable;

public class SymbolsTable {

    public Hashtable Table;
    int id;

    public SymbolsTable() {
        // define the symbols table
        Table = new Hashtable();
        this.id = 0;
    }


    public void AddToken(String token) {
        // put the token name as key to search on later to catch if it's already exist on symbolsTables or not ...
        if(!this.SearchToken(token)) {
            Table.put(token, ++id);
        }
    }

    public boolean SearchToken(String token) {
        // return true if the variable already exist on the symbols table
        return  Table.containsKey(token);
    }

    public String get(String token){
        // get the token key and value ...
        return "<id" + Table.get(token) + " , " + token + ">";
    }

    public  void printTable(){
        // get keys() from Hashtable and iterate
        Enumeration<String> enumeration = Table.keys();

        // iterate using enumeration object
        System.out.println("______ID_____________Value___________");
        while(enumeration.hasMoreElements()) {
            String token = enumeration.nextElement();
            System.out.println("| \t"  + Table.get(token) + "\t|\t" + token );
        }
        System.out.println("__________________________________");
    }

}
