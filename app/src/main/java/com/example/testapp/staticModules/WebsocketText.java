package com.example.testapp.staticModules;

import java.util.Map;

public class WebsocketText {
    public Integer id;
    public String method;
    private Parameters params;
    public Integer contextId;
}

class Parameters {
    public String state;
    public String[] patterns;
    public String maxScriptsCacheSize;
    public Integer maxDepth;
    public String expression;
    public String sourceURL;
    public Integer executionContextId;
    public boolean persistScript;
    public String objectGroup;
    public boolean includeCommandLineAPI;
    public boolean silent;
    public boolean returnByValue;
    public boolean generatePreview;
    public boolean userGesture;
    public boolean awaitPromise;
    public boolean replMode;
    public boolean allowUnsafeEvalBlockedByCSP;
    public String uniqueContextId;
}