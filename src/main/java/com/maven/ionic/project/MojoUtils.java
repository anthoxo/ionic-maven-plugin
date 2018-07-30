package com.maven.ionic.project;

import java.util.ArrayList;

public class MojoUtils {

    public static String[] formattingCommand(String[] command, String args) {

        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < command.length; i++) {
            result.add(command[i]);
        }

        if (args != null) {
            String[] arguments = args.split(" ");
            for (int i = 0; i < arguments.length; i++) {
                result.add(arguments[i]);
            }
        }

        return result.toArray(new String[0]);
    }
}
