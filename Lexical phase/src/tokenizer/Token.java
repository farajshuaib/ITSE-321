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
import java.util.ArrayList;

public class Token {

    ArrayList<String> tokens ;
    SymbolsTable symbolsTable;
    final String spChar = "[\"[$&+,:;=?@#|'<>.-^*()%!]\"]";

    public Token() {
        this.tokens = new ArrayList<String>();
        this.symbolsTable = new SymbolsTable();
    }

    // adding a new token to array list >>>
    public void newToken(String token){

        if(token.matches("[0-9]*")){ // check if it matches an int number
            tokens.add("<num,int>");
        }
        else if(token.matches("([0-9]*[.])?[0-9]+")){ // check if it matches an float number
            tokens.add("<num,float>");
        }
        // check if it matches an 'int' 'float' string or any special char
        else if (token.equals("int") || token.equals("float") || token.matches(spChar)) {
            tokens.add("<"+token+">");
        }
        else {
            symbolsTable.AddToken(token);
            tokens.add(symbolsTable.get(token));
        }
    }

    public void printTokens(){
        // printing all tokens ...
        System.out.println("\n tokens....");
        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i));
        }
        
        System.out.println("symbols table....");
        symbolsTable.printTable();
        
    }

 

}

