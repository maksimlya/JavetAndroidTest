package com.example.testapp.staticModules;

import com.example.testapp.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSModules {
    private static JSModules instance;
    public String module1;

    public JSModules(InputStreamReader is) {
        StringBuilder jsContent = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(is);
            String line;
            while((line = reader.readLine()) != null) {
                jsContent.append(line).append("\r\n");
            }
            module1 = jsContent.toString();
        } catch (IOException e) {
            MainActivity.Log( e.getMessage());
        }
    }
}
