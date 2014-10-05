package com.itemhunter.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    //TODO - Change these to JSON methods before release

    /*
     * Convenience method to extract an ArrayList out of the database provided string.
     */
    public static ArrayList<String> deTokifier(String list){
           return new ArrayList<String>(Arrays.asList(list.split("\\|")));
    }

    /*
     * Convenience method to create a string out of an ArrayList to store in a database.
     */
    public static String reTokifier(ArrayList<String> lists){
        String list = "";
        for(String item : lists){
            list += item+"|";
        }
        return list;
    }
	
}
