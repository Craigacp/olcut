/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.labs.mlrg.olcut.config.remote;

import com.oracle.labs.mlrg.olcut.config.PropertyException;
import com.oracle.labs.mlrg.olcut.util.SimpleLabsLogFormatter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stgreen
 */
public class RegistryTest {

    private JiniConfigurationManager cm1;

    private JiniConfigurationManager cm2;

    public RegistryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SimpleLabsLogFormatter.setAllLogFormatters();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        cm1 = null;
        cm2 = null;
    }

    @After
    public void tearDown() throws Exception {
        if(cm1 != null) {
            cm1.shutdown();
        }
        if(cm2 != null) {
            cm2.shutdown();
        }
    }

    @Test
    public void testRegister() throws IOException {
        cm1 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/serverConfig.xml");
        RegistryConfigurable rc = (RegistryConfigurable) cm1.lookup("servercomp");
        assertNotNull(rc);
    }

    @Test
    public void testRegisterWithEntries() throws IOException {
        cm1 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/serverConfig.xml");
        RegistryConfigurable rc = (RegistryConfigurable) cm1.lookup("servercompWithEntries");
        assertNotNull(rc);
    }

    @Test
    public void testSpecificRegister() throws IOException {
        cm1 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/specificRegConfig.xml");
        RegistryConfigurable rc = (RegistryConfigurable) cm1.lookup("servercomp");
        cm1.shutdown();
        assertNotNull(rc);
    }

    @Test
    public void testRegisterAndLookup() throws IOException {

        //
        // Register in one manager.
        cm1 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/serverConfig.xml");
        RegistryConfigurable rc1 = (RegistryConfigurable) cm1.lookup("servercomp");
        assertNotNull(rc1);

        //
        // Lookup in another.
        cm2 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/clientConfig.xml");
        RegistryConfigurable rc2 = (RegistryConfigurable) cm2.lookup("servercomp");

        assertEquals(rc2.stringOp("test"), "Received: test");

        //
        // Make sure that the method ran in the first object!
        assertTrue(((RegistryConfigurableImpl) rc1).recs.size() == 1);
        assertEquals(((RegistryConfigurableImpl) rc1).recs.get(0), "test");
    }

    @Test
    public void testRegisterAndLookupWithEntries() throws IOException {

        //
        // Register in one manager.
        cm1 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/serverConfig.xml");
        RegistryConfigurable rc1 = (RegistryConfigurable) cm1.lookup("servercompWithEntries");
        assertNotNull(rc1);

        //
        // Lookup in another.
        cm2 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/clientConfig.xml");
        RegistryConfigurable rc2 = (RegistryConfigurable) cm2.lookup("servercompWithEntries");

        assertEquals(rc2.stringOp("test"), "Received: test");

        //
        // Make sure that the method ran in the first object!
        assertEquals(1, ((RegistryConfigurableImpl) rc1).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc1).recs.get(0), "test");
    }

    @Test
    public void testRegisterAndLookupAllWithEntries() throws IOException {

        //
        // Register in one manager.
        cm1 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/serverConfig.xml");
        RegistryConfigurable rc1 = (RegistryConfigurable) cm1.lookup("servercompWithEntries");
        RegistryConfigurable rc2 = (RegistryConfigurable) cm1.lookup("servercompWithEntriesA");
        RegistryConfigurable rc3 = (RegistryConfigurable) cm1.lookup("servercompWithEntriesB");
        RegistryConfigurable rc4 = (RegistryConfigurable) cm1.lookup("servercompWithEntriesC");
        assertNotNull(rc1);
        assertNotNull(rc2);
        assertNotNull(rc3);
        assertNotNull(rc4);

        //
        // LookupAll in another.
        cm2 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/clientConfig.xml");
        ConfigurationEntries ce = (ConfigurationEntries) cm2.lookup("serverEntries");
        List<RegistryConfigurable> rrc2 = cm2.lookupAll(RegistryConfigurable.class,null,ce.getEntries());

        //
        // Check we found all the elements
        assertEquals(4,rrc2.size());

        for (RegistryConfigurable r : rrc2) {
            r.stringOp("test");
        }

        //
        // Make sure that the methods ran in the server's objects
        assertEquals(1, ((RegistryConfigurableImpl) rc1).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc1).recs.get(0), "test");
        assertEquals(1, ((RegistryConfigurableImpl) rc2).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc2).recs.get(0), "test");
        assertEquals(1, ((RegistryConfigurableImpl) rc3).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc3).recs.get(0), "test");
        assertEquals(1, ((RegistryConfigurableImpl) rc4).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc4).recs.get(0), "test");
    }
    @Test
    public void testRegisterAndLookupAllWithStringEntries() throws IOException {

        //
        // Register in one manager.
        cm1 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/serverConfig.xml");
        RegistryConfigurable rc1 = (RegistryConfigurable) cm1.lookup("servercompWithEntries");
        RegistryConfigurable rc2 = (RegistryConfigurable) cm1.lookup("servercompWithEntriesA");
        RegistryConfigurable rc3 = (RegistryConfigurable) cm1.lookup("servercompWithEntriesB");
        RegistryConfigurable rc4 = (RegistryConfigurable) cm1.lookup("servercompWithEntriesC");
        assertNotNull(rc1);
        assertNotNull(rc2);
        assertNotNull(rc3);
        assertNotNull(rc4);

        //
        // LookupAll in another.
        cm2 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/clientConfig.xml");
        List<RegistryConfigurable> rrc2 = cm2.lookupAll(RegistryConfigurable.class,null,new String[]{"data1","data2"});

        //
        // Check we found all the elements
        assertEquals(4,rrc2.size());

        for (RegistryConfigurable r : rrc2) {
            r.stringOp("test");
        }

        //
        // Make sure that the methods ran in the server's objects
        assertEquals(1, ((RegistryConfigurableImpl) rc1).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc1).recs.get(0), "test");
        assertEquals(1, ((RegistryConfigurableImpl) rc2).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc2).recs.get(0), "test");
        assertEquals(1, ((RegistryConfigurableImpl) rc3).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc3).recs.get(0), "test");
        assertEquals(1, ((RegistryConfigurableImpl) rc4).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc4).recs.get(0), "test");
    }

    @Test
    public void testRegisterAndLookupAllWithPartialMatchingEntries() throws IOException {

        //
        // Register in one manager.
        cm1 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/serverConfig.xml");
        RegistryConfigurable rc1 = (RegistryConfigurable) cm1.lookup("servercompWithEntries");
        RegistryConfigurable rc2 = (RegistryConfigurable) cm1.lookup("servercompWithEntriesA");
        RegistryConfigurable rc3 = (RegistryConfigurable) cm1.lookup("servercompWithEntriesB");
        RegistryConfigurable rc4 = (RegistryConfigurable) cm1.lookup("servercompWithEntriesC");
        assertNotNull(rc1);
        assertNotNull(rc2);
        assertNotNull(rc3);
        assertNotNull(rc4);

        //
        // LookupAll in another.
        cm2 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/clientConfig.xml");
        ConfigurationEntries ce = (ConfigurationEntries) cm2.lookup("serverPartialMatchingEntries");
        List<RegistryConfigurable> rrc2 = cm2.lookupAll(RegistryConfigurable.class,null,ce.getEntries());

        //
        // Check we found all the elements
        assertEquals(4,rrc2.size());

        for (RegistryConfigurable r : rrc2) {
            r.stringOp("test");
        }

        //
        // Make sure that the methods ran in the server's objects
        assertEquals(1, ((RegistryConfigurableImpl) rc1).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc1).recs.get(0), "test");
        assertEquals(1, ((RegistryConfigurableImpl) rc2).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc2).recs.get(0), "test");
        assertEquals(1, ((RegistryConfigurableImpl) rc3).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc3).recs.get(0), "test");
        assertEquals(1, ((RegistryConfigurableImpl) rc4).recs.size());
        assertEquals(((RegistryConfigurableImpl) rc4).recs.get(0), "test");
    }

    @Test
    public void testRegisterAndLookupWithPartialMatchingEntries() throws IOException {

        //
        // Register in one manager.
        cm1 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/serverConfig.xml");
        RegistryConfigurable rc1 = (RegistryConfigurable) cm1.lookup("servercompWithEntries");
        assertNotNull(rc1);

        //
        // Lookup in another.
        cm2 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/clientConfig.xml");
        RegistryConfigurable rc2 = (RegistryConfigurable) cm2.lookup("servercompWithPartialMatchingEntries");
        assertNotNull(rc2);
        assertEquals(rc2.stringOp("test"), "Received: test");

        //
        // Make sure that the method ran in the first object!
        assertTrue(((RegistryConfigurableImpl) rc1).recs.size() == 1);
        assertEquals(((RegistryConfigurableImpl) rc1).recs.get(0), "test");
    }

    @Test(expected= PropertyException.class)
    public void testRegisterAndLookupWithNonMatchingEntries() throws IOException {

        //
        // Register in one manager.
        cm1 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/serverConfig.xml");
        RegistryConfigurable rc1 = (RegistryConfigurable) cm1.lookup("servercompWithEntries");
        assertNotNull(rc1);

        //
        // Lookup in another.
        cm2 = new JiniConfigurationManager("/com/oracle/labs/mlrg/olcut/config/remote/clientConfig.xml");
        RegistryConfigurable rc2 = (RegistryConfigurable) cm2.lookup("servercompWithNonMatchingEntries");

        fail("Found a component which shouldn't have matched.");
   }

}