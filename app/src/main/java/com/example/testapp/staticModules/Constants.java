package com.example.testapp.staticModules;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    private static Constants instance;
    public Map<String, String> methods = new HashMap<>();

    private Constants() {
        methods.put("enableRuntime", "Runtime.enable");
        methods.put("enableProfiler", "Profiler.enable");
        methods.put("enableDebugger", "Debugger.enable");
        methods.put("setPauseOnExceptions", "Debugger.setPauseOnExceptions");
        methods.put("setAsyncCallStackDepth", "Debugger.setAsyncCallStackDepth");
        methods.put("setBlackboxPatterns", "Debugger.setBlackboxPatterns");
        methods.put("runIfWaitingForDebugger", "Runtime.runIfWaitingForDebugger");
        methods.put("discardConsoleEntries", "Runtime.discardConsoleEntries");
        methods.put("compileScript", "Runtime.compileScript");
        methods.put("evaluate", "Runtime.evaluate");


    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

}
