/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iw_last.utils;

/**
 *
 * @author Padovano
 */
public enum InputValidation {
    MAIL("\\w+@\\w+\\.\\w{2,}"),
    TEL("(\\+?\\(\\d{1,3}\\))?\\d{8}|{7}"),
    NUMBER("\\d*\\.?\\d*"),
    INTEGER("\\d*");

    private String regex;
    private InputValidation(String regex) {
        this.regex=regex;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
    
        
}
