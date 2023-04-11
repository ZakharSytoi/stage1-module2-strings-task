package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */

    public MethodSignature parseFunction(String signatureString) {
        String methodName;
        MethodSignature result;

        ArrayList<String> delimiters = new ArrayList<String>();
        delimiters.add("\\(");
        delimiters.add("\\)");
        delimiters.add(",");

        ArrayList<String> arrayForBigParts = new ArrayList<String>();
        if(signatureString.split(" ")[0].equals("private") ||
                signatureString.split(" ")[0].equals("public")||
                signatureString.split(" ")[0].equals("protected")){
            Collections.addAll(arrayForBigParts, signatureString.split(" ", 3));

            ArrayList<String> arrayForMethodParts = new ArrayList<String>(new StringSplitter().splitByDelimiters(arrayForBigParts.get(2), delimiters));
            methodName = arrayForMethodParts.get(0);
            if(arrayForMethodParts.get(arrayForMethodParts.size() - 1).charAt(arrayForMethodParts.get(arrayForMethodParts.size() - 1).length() -1) =='('){
                result = new MethodSignature(methodName, null);
                result.setAccessModifier(arrayForBigParts.get(0));
                result.setReturnType(arrayForBigParts.get(1));
                return result;
            }
            List<MethodSignature.Argument> argList = new ArrayList< MethodSignature.Argument >();
            for(int i = 1; i < arrayForMethodParts.size() - 1; i+= 2){
                    argList.add(new MethodSignature.Argument(arrayForMethodParts.get(i), arrayForMethodParts.get(i+1)));
            }
            result = new MethodSignature(methodName, argList);
            result.setAccessModifier(arrayForBigParts.get(0));
            result.setReturnType(arrayForBigParts.get(1));
        }
        else {
            Collections.addAll(arrayForBigParts, signatureString.split(" ", 2));;

            ArrayList<String> arrayForMethodParts = new ArrayList<String>(new StringSplitter().splitByDelimiters(arrayForBigParts.get(1), delimiters));
            methodName = arrayForMethodParts.get(0);
            if(arrayForMethodParts.get(arrayForMethodParts.size() - 1).charAt(arrayForMethodParts.get(arrayForMethodParts.size() - 1).length() -1) =='('){
                result = new MethodSignature(methodName, null);
                result.setAccessModifier(null);
                result.setReturnType(arrayForBigParts.get(0));
                return result;
            }
            List<MethodSignature.Argument> argList = new ArrayList< MethodSignature.Argument >();
            for(int i = 1; i < arrayForMethodParts.size() - 1; i+= 2){
                argList.add(new MethodSignature.Argument(arrayForMethodParts.get(i), arrayForMethodParts.get(i+1)));
            }
            result = new MethodSignature(methodName, argList);
            result.setAccessModifier(null);
            result.setReturnType(arrayForBigParts.get(0));
        }
        return result;
    }
}
