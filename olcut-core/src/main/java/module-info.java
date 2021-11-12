module com.oracle.labs.mlrg.olcut.core {
    requires java.base;
    requires java.logging;
    requires java.management;
    requires java.xml;

    requires org.jline.builtins;
    requires org.jline.reader;
    requires org.jline.style;
    requires org.jline.terminal;
    requires org.jline.terminal.jansi;

    exports com.oracle.labs.mlrg.olcut.command;
    exports com.oracle.labs.mlrg.olcut.config;
    exports com.oracle.labs.mlrg.olcut.config.io;
    exports com.oracle.labs.mlrg.olcut.config.property;
    exports com.oracle.labs.mlrg.olcut.config.xml;
    exports com.oracle.labs.mlrg.olcut.provenance;
    exports com.oracle.labs.mlrg.olcut.provenance.io;
    exports com.oracle.labs.mlrg.olcut.provenance.impl;
    exports com.oracle.labs.mlrg.olcut.provenance.primitives;
    exports com.oracle.labs.mlrg.olcut.util;

    exports com.oracle.labs.mlrg.olcut.config.test;
}