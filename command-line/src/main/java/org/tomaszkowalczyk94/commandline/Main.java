package org.tomaszkowalczyk94.commandline;

import org.tomaszkowalczyk94.core.pomodorotasksmanager.CoreEngine;

public class Main {
    public static void main(String[] args) {
        CoreEngine coreEngine = new CoreEngine();
        coreEngine.run();

        System.out.println("hello world");
    }
}
