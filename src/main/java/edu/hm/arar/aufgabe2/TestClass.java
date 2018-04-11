package edu.hm.arar.aufgabe2;

import java.io.IOException;

/**
 * Created by Sümeyye on 08.04.2018.
 */
public class TestClass {

    public TestClass (int i){

    }

    public TestClass(){}


    public void SwMs(){
        char x = 'n';
        switch (x) {
            case 'x':
                break;
            case 'f':
                break;
            default:
                break;
        }
    }

    public void ifMethod() {
        String x = "";
        if (x.equals("")) {
            x = "z";
        }else if (x.equals("n")) {
    x = "f";
    return;
        }else {
x = "r";
        }
    }

    public String orMethod() {
        if ("a".equals("b") && "a".equals("d")) {
            return "z";
        }

        if ("a".equals("b") || "a".equals("d")) {
            return "r";
        }

        return "b";
    }

    public void tryMethod() throws Exception {
        throw new Exception();
    }

    public void catchMethod() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void catchMethodZwei() {
        try {
            if("b".equals("x")) {
                throw new Exception();
            } else {
                if ("b".equals("x")) {
                    throw new IOException();
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch(IOException | IllegalArgumentException io) {
            io.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void schleifen() {
        String x = "";
        while(x.equals("b")) {
            System.out.print("");
            break;
        }

        for(int i = 0; i < 1; i++) {
            System.out.print("");
        }
    }

    public void schleifenFor() {

        for(int i = 0; i < 1; i++) {
            System.out.print("");
        }
    }

    public void schleifenWhile() {
        String x = "";
        while(x.equals("b")) {
            System.out.print("");
        }
    }


}
