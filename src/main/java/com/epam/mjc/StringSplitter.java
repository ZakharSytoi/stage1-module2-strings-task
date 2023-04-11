package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */


    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();
        String tmpString = source;

        for (String delimiter : delimiters) {
            StringBuilder sb = new StringBuilder();
            for (String i : tmpString.split(delimiter)) {
                sb.append(i).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            tmpString = sb.toString();
        }
        StringBuilder sb = new StringBuilder(tmpString);
        while(sb.charAt(0) == ' ') sb.deleteCharAt(0);
        for (int i = 0; i < sb.length() - 2; i++) {
            while (sb.charAt(i) == ' ' && sb.charAt(i + 1) == ' ') {
                sb.deleteCharAt(i + 1);
            }
        }
        tmpString = sb.toString();
        Collections.addAll(result, tmpString.split(" "));
        return result;
    }
}
