module com.oracle.labs.mlrg.olcut.config.protobuf {
    requires java.base;
    requires java.logging;

    requires com.oracle.labs.mlrg.olcut.core;
    requires com.google.protobuf;

    exports com.oracle.labs.mlrg.olcut.config.protobuf;
    exports com.oracle.labs.mlrg.olcut.config.protobuf.protos;
}