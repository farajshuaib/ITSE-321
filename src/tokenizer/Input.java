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
public class Input {
    // def RegEx
    public final String  letter = "[A-Za-z]";
    public final String  digit = "[0-9]";

    String input;
    int CHAR_POINTER;
    int STATE;
    char CurrentChar;
    String word = "";
    Token token = new Token() ;

    // constructor ...
    public Input(String IN) {
        this.input = IN;
        this.CHAR_POINTER = 0;
        this.STATE = 1;
        this.setCurrentChar();
    }
    
    public int getCurrentState(){
        return this.STATE;
    }
    
    public void setCurrentState(int state){
        this.STATE = state;
    }


    public void setCHAR_POINTER() {
        if(this.CHAR_POINTER == input.length() - 1 ){
            return;
        }
        this.CHAR_POINTER = this.CHAR_POINTER + 1;    
    }

    public char getCurrentChar() {
        return this.CurrentChar;
    }

    // set the current char at everytime loop on switching the state ...
    public void setCurrentChar() {
        this.CurrentChar = this.input.charAt(CHAR_POINTER);
    }
    
    public void setCurrentWord(char c) {
        if(c == ' '){
            return;
        }
        this.word = this.word + c;
    }

    
    public void fail(){
        System.out.println(" \n fail to find char index of \" " + getCurrentChar() + " \" in the automata... \n start from state one again >>>");
        this.setCurrentState(1);
    }

    // change current char to String to make it easy to check is matches a regex
    public String CurrentCharToString(){
        return Character.toString(getCurrentChar());
    }

    public void getNextChar(char c, int nextState){
        if(this.CHAR_POINTER == input.length() - 1){
           this.setCurrentWord(c);
            if(word.length() > 0){
                token.newToken(this.word);
            }
            token.printTokens();
            this.word = "";
            System.exit(0);
        }
            this.setCurrentState(nextState);
            this.setCurrentWord(c);
            this.setCHAR_POINTER();
            this.setCurrentChar();
            this.execute();
    }
    
    public void toToken(){
        if(word.length() > 0){
            token.newToken(this.word);
            this.word = "";
        }
        this.setCurrentState(1);
        this.execute();
    }

    public void execute() {

        switch (this.getCurrentState()) {
            case 1:
                switch(getCurrentChar()){
                    case '=':
                        getNextChar(getCurrentChar(), 7);
                        break;
                    case '+':
                        getNextChar(getCurrentChar(), 8);
                        break;
                    case '-':
                        getNextChar(getCurrentChar(), 9);
                        break;
                    case '*':
                        getNextChar(getCurrentChar(), 10);
                        break;
                    case '/':
                        getNextChar(getCurrentChar(), 11);
                        break;
                    case 'i':
                        getNextChar(getCurrentChar(), 18);
                        break;
                    case 'f':
                        getNextChar(getCurrentChar(), 12);
                        break;
                    default:
                        if(CurrentCharToString().matches(letter)){
                            getNextChar(getCurrentChar(), 22);
                        } else if (CurrentCharToString().matches(digit)){
                            getNextChar(getCurrentChar(), 2);
                        }  else {
                            fail();
                        }
                        break;
                }
            case 2:
                if (getCurrentChar() == '.') {
                    getNextChar(getCurrentChar(), 4);
                } else if (CurrentCharToString().matches(digit)) {
                    getNextChar(getCurrentChar(), 2);
                } else  {
                    getNextChar(getCurrentChar(), 3);
                }
                break;
            case 4:
                if (CurrentCharToString().matches(digit)) {
                    getNextChar(getCurrentChar(), 5);
                }
                break;
            case 5:
                if (CurrentCharToString().matches(digit)) getNextChar(getCurrentChar(), 5);
                else {
                    getNextChar(getCurrentChar(), 6);
                }
                break;
            case 12:
                if (getCurrentChar() == 'l') {
                    getNextChar(getCurrentChar(), 13);
                }
                break;
            case 13:
                if (getCurrentChar() == 'o') {
                    getNextChar(getCurrentChar(), 14);
                }
                break;
            case 14:
                if (getCurrentChar() == 'a') {
                    getNextChar(getCurrentChar(), 15);
                }
                break;
            case 15:
                if (getCurrentChar() == 't') {
                    getNextChar(getCurrentChar(), 16);
                }
                break;
            case 16:
                if (!CurrentCharToString().matches(letter) && !CurrentCharToString().matches(digit)) {
                    getNextChar(getCurrentChar(), 17);
                }
                break;
            case 18:
                if (getCurrentChar() == 'n') {
                    getNextChar(getCurrentChar(), 19);
                }
                break;
            case 19:
                if (getCurrentChar() == 't') {
                    getNextChar(getCurrentChar(), 20);
                }
                break;
            case 20:
                if (!CurrentCharToString().matches(letter) && !CurrentCharToString().matches(digit)) {
                    getNextChar(getCurrentChar(), 21);
                }
                break;
            case 22:
                if (CurrentCharToString().matches(letter) || CurrentCharToString().matches(digit)) {
                    getNextChar(getCurrentChar(), 22);
                } else {
                    getNextChar(getCurrentChar(), 23);
                }
                break;
            case 3:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 21:
            case 17:
            case 23:
                this.toToken();
                break;
            default:
                System.err.println("char not matches");
                break;
        }
    }

}
