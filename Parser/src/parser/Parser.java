/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author faraj
 */

import java.util.* ;


public class Parser {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        Scanner IN = new Scanner(System.in);

        System.out.println("enter your tokens here...");
        String input = IN.nextLine();

        if (input.length() <= 0) {
            System.err.println("please enter your stream of tokens to pass it into parser... \n ");
            return; // there should be a tokens before continue ...
        }

        String Tokens[] = input.split(" ");


        Grammar parser = new Grammar(Tokens);

        parser.start();
    }
    
}
