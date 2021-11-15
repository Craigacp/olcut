module com.oracle.labs.mlrg.olcut.core.test {
    requires java.base;
    requires java.logging;
    requires java.management;
    requires java.xml;

    requires org.jline.builtins;
    requires org.jline.reader;
    requires org.jline.style;
    requires org.jline.terminal;
    requires org.jline.terminal.jansi;

    requires com.oracle.labs.mlrg.olcut.core;

    exports com.oracle.labs.mlrg.olcut.test.config;
    exports com.oracle.labs.mlrg.olcut.test.config.test;
    exports com.oracle.labs.mlrg.olcut.test.config.property;
    exports com.oracle.labs.mlrg.olcut.test.command;
    exports com.oracle.labs.mlrg.olcut.test.provenance;
    exports com.oracle.labs.mlrg.olcut.test.util;

    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires org.junit.platform.commons;

    opens com.oracle.labs.mlrg.olcut.test.config to com.oracle.labs.mlrg.olcut.core;
    opens com.oracle.labs.mlrg.olcut.test.provenance to com.oracle.labs.mlrg.olcut.core;
    opens com.oracle.labs.mlrg.olcut.test.util to com.oracle.labs.mlrg.olcut.core;
}