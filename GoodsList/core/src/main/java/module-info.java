module app.core {
    exports core;
    exports json;
    requires com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;

}
