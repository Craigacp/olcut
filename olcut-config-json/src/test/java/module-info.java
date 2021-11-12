module com.oracle.labs.mlrg.olcut.config.json {
    requires java.base;
    requires java.logging;

    requires com.oracle.labs.mlrg.olcut.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports com.oracle.labs.mlrg.olcut.config.json;

    opens com.oracle.labs.mlrg.olcut.config.json to com.oracle.labs.mlrg.olcut.core;

    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires org.junit.platform.commons;
}