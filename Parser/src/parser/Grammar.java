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


public class Grammar {
    String Tokens[];
    String lookahead;
    int pointer = 0 ;
    boolean success = false ;
    public Grammar(String[] tokens) {
        this.Tokens = tokens;
    }

    public void start(){
        lookahead = Tokens[0];
        stmt();
    }

    void GetNextToken(){
        this.pointer += 1;
        if(pointer == Tokens.length){
            this.printMessage();
        } else {
            lookahead = Tokens[pointer];
        }
    }

    void stmt(){
        if (lookahead.equals("type")){
            match("type");
            factor();
            exprDash();
            match(";");
            return;
        }
        term();
        exprDash();
        match(";");
    }

    void exprDash() {
        switch (lookahead){
            case "+": {
                match("+");
                term();
                exprDash();
                break;
            }
            case "-": {
                match("-");
                term();
                exprDash();
                break;
            }
            case "=": {
                match("=");
                term();
                exprDash();
                break;
            }
            case ",": {
                match(",");
                factor();
                break;
            }
            case "(": {
                match("(");
                term();
                match(")");
                break;
            }
            default:{
                success = false;
            }
        }
    }

    void term() {
        factor();
        termDash();
    }

    void termDash() {
        switch (lookahead){
            case "*": {
                match("*");
                factor();
                termDash();
                break;
            }
            case "/" :{
                match("/");
                factor();
                termDash();
                break;
            }
            case "+" :{
                match("+");
                factor();
                termDash();
                break;
            }
            case "-" :{
                match("-");
                factor();
                termDash();
                break;
            }
            default:{
                success = false;
            }
        }
    }

    void factor() {
        switch (lookahead){
            case "id": {
                match("id");
                break;
            }
            case "num": {
                match("num");
                break;
            }
            default:{
                success = false;
            }
        }
    }


    void match(String terminal){
        if(lookahead.equals(terminal)){
            System.out.println(" \"" + lookahead + "\" " + " matching terminal => " + terminal);
            success = true;
            GetNextToken();
        } else {
            System.out.println("Error => " + " \"" + lookahead + "\" " + " doesn't much terminal" + terminal);
            success = false;
        }
    }


    void printMessage() {
        if (success) {
            System.out.println("Your syntax is correct...");
        } else {
            System.out.println("Syntax error, parser couldn't match your code");
        }
    }

}

