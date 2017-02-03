/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author work
 */
public class GetUserInputConsole {

    public static final int STRING = 1;
    public static final int DATE = 2;
    public static final int NUMBER = 3;
    public static final int EMAIL = 4;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Checks if date is in a valid format
     *
     * @param date
     * @return
     */
    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            System.out.print("Invalid Date: Use Only The Format (YYYY-MM-DD)");
            return false;
        }
    }

    public static boolean isNumberValid(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.print("Invalid Number: Use Only Numbers");
            return false;
        } catch (NullPointerException e) {
            System.out.print("Invalid Number: Use Only Numbers");
            return false;
        }
        /* only got here if we didn't return false*/
        return true;
    }

    public static boolean isEmailValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            System.out.print("Invalid Email: Use the format (example@example.com)");
            return false;
        }
    }

    /**
     * gets user input as a string and hands it back to method caller
     *
     * @param minlength the minimum length an input must be to be considered
     * valid
     * @param maxlength the maximum length an input must be to be considered
     * valid
     * @return
     */
    public static String get(int minlength, int maxlength, int type) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean validInput = false;
        String line = null;
        boolean flagSize = false;
        /*check for a valid input. If none was given, then continue until one is found*/
        while (!validInput) {
            try {
                System.out.print("Enter Input: ");
                line = br.readLine();
                if (line.length() <= maxlength) {
                    /*if the length was under max length then continue*/
                    if (line.length() >= minlength) {
                        /*if the length was over min length then consider it a valid input*/
                        switch (type) {
                            case STRING:
                                validInput = true;
                                break;
                            case DATE:
                                if (minlength == 0 && line.length() == 0) {
                                    validInput = true;
                                } else {
                                    validInput = isDateValid(line);
                                }
                                break;
                            case NUMBER:
                                if (minlength == 0 && line.length() == 0) {
                                    validInput = true;
                                } else {
                                    validInput = isNumberValid(line);
                                }
                                break;
                            case EMAIL:
                                if (minlength == 0 && line.length() == 0) {
                                    validInput = true;
                                } else {
                                    validInput = isEmailValid(line);
                                }
                                break;
                            default:
                                System.out.println("0x0001 Not Valid Input, try again");
                                break;
                        }
                    } else {
                        flagSize = true;
                    }
                } else {
                    flagSize = true;
                }
                if (flagSize) {
                    /*gives and error message telling the user what went wrong with the size of their input*/
                    if (minlength > 0 && maxlength > 0) {
                        System.out.println("Input needs to be at least " + minlength + " characters and at most " + maxlength + " characters, try again");
                    } else if (minlength > 0) {
                        System.out.println("Too Small, needs to be at least " + minlength + " characters, try again");
                    } else {
                        System.out.println("Too Long, needs to be at most " + maxlength + " characters, try again");
                    }
                }
            } catch (Exception e) {
                System.out.println("0x0002 Not Valid Input, try again");
            }
        }
        return line;
    }
}
