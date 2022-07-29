/*
 * Copyright (c) 2021-2022. caoccao.com Sam Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.testapp;

import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.V8Runtime;
import com.example.testapp.staticModules.JSModules;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.server.NativeWebSocketServletContainerInitializer;
import org.eclipse.jetty.websocket.server.WebSocketUpgradeFilter;

import java.io.InputStreamReader;


public class CDTShell {
    private static CDTShell instance;
    private String jsModule;

    public static CDTShell getInstance() {
        if (instance == null) {
            instance = new CDTShell();
        }
        return instance;
    }

    public void setJsModule(InputStreamReader is) {
        JSModules modules = new JSModules(is);
        this.jsModule = modules.module1;
    }

    public void run() {
        Server jsonServer = new Server(CDTConfig.getPort());
        ServletContextHandler jsonServletContextHandler = new ServletContextHandler(
                ServletContextHandler.SESSIONS | ServletContextHandler.NO_SECURITY);
        jsonServletContextHandler.setContextPath(CDTConfig.PATH_ROOT);
        jsonServer.setHandler(jsonServletContextHandler);
        try {
            jsonServletContextHandler.addServlet(CDTHttpServlet.class, CDTConfig.PATH_JSON);
            jsonServletContextHandler.addServlet(CDTHttpServlet.class, CDTConfig.PATH_JSON_VERSION);
            NativeWebSocketServletContainerInitializer.configure(jsonServletContextHandler,
                    (servletContext, nativeWebSocketConfiguration) -> {
                        nativeWebSocketConfiguration.getPolicy().setMaxTextMessageBufferSize(0xFFFFFF);
                        nativeWebSocketConfiguration.addMapping(CDTConfig.PATH_JAVET, CDTWebSocketAdapter.class);
                    });
            WebSocketUpgradeFilter.configure(jsonServletContextHandler);
            V8Runtime v8Runtime = V8Host.getV8Instance().createV8Runtime();
            v8Runtime.getExecutor(this.jsModule).setModule(true).setResourceName("./x.js").compileV8Module();
            jsonServer.start();
            MainActivity.Log("Server is started. Please press any key to stop the server.");
            MainActivity.Log("Welcome to CDT Shell!");
            MainActivity.Log("Input the script or '" + CDTConfig.COMMAND_EXIT + "' to exit.");
            CDTConfig.setV8Runtime(v8Runtime);
        } catch (Throwable t) {
            MainActivity.Log(t.getMessage());
        }
    }
}
