module com.oracle.labs.mlrg.olcut.config.json {
    requires java.base;
    requires java.logging;

    requires transitive com.oracle.labs.mlrg.olcut.core;
    requires com.oracle.labs.mlrg.olcut.core.test;
    requires transitive com.fasterxml.jackson.annotation;
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;

    exports com.oracle.labs.mlrg.olcut.config.json;

    opens com.oracle.labs.mlrg.olcut.config.json to com.oracle.labs.mlrg.olcut.core;

    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires org.junit.platform.commons;
}