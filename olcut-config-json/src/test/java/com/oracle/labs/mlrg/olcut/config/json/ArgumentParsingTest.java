package com.oracle.labs.mlrg.olcut.config.json;

import com.oracle.labs.mlrg.olcut.config.ConfigurationManager;
import com.oracle.labs.mlrg.olcut.config.Option;
import com.oracle.labs.mlrg.olcut.config.Options;
import com.oracle.labs.mlrg.olcut.config.StringListConfigurable;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ArgumentParsingTest {

    @Test
    public void testConfigLoading() throws IOException {
        String[] args = new String[]{"-c","stringListConfig.json","--other-arguments","that-get-in-the-way", "--config-file-formats", "com.oracle.labs.mlrg.olcut.config.json.JsonConfigFactory"};
        loadFromArgs("-c",args);
        args = new String[]{"--config-file-formats", "com.oracle.labs.mlrg.olcut.config.json.JsonConfigFactory", "--config-file","stringListConfig.json","--other-arguments","that-get-in-the-way"};
        loadFromArgs("--config-file",args);
        args = new String[]{"-o","--config-file","stringListConfig.json,componentListConfig.json", "--config-file-formats", "com.oracle.labs.mlrg.olcut.config.json.JsonConfigFactory","-s"};
        loadFromArgs("--config-file with multiple files",args);
        args = new String[]{"-o","--config-file","componentListConfig.json","-s", "--config-file-formats", "com.oracle.labs.mlrg.olcut.config.json.JsonConfigFactory","-c","stringListConfig.json"};
        loadFromArgs("overriding --config-file with -c",args);
    }

    public void loadFromArgs(String name, String[] args) throws IOException {
        ParsingOptions o = new ParsingOptions();
        ConfigurationManager cm = new ConfigurationManager(args,o);
        StringListConfigurable slc = (StringListConfigurable) cm.lookup("listTest");
        assertEquals("Loading from " + name + " failed.", "a", slc.strings.get(0));
        assertEquals("Loading from " + name + " failed.", "b", slc.strings.get(1));
        assertEquals("Loading from " + name + " failed.", "c", slc.strings.get(2));
    }

    public static class ParsingOptions implements Options {
        @Option(charName = 'o', longName="other", usage="test hard")
        public boolean other;

        @Option(charName = 's', longName="surrounding", usage="test hard 2: test harder")
        public boolean surrounding;

        @Option(longName="other-arguments", usage="test hard with a vengeance")
        public String otherArguments;
    }
}

