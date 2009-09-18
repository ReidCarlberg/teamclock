package com.fivesticks.time.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.fivesticks.time.common.Settings;
import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class DatabaseInitializer {

    public static void main(String[] args) {
        try {
            new DatabaseInitializer().initializeTables(1);
        } catch (DatabaseInitializeFailedException e) {
            throw new RuntimeException("failed");
        }
    }

    public void initializeTables() throws DatabaseInitializeFailedException {
        initializeTables(1);
    }

    /**
     * uses the hibernate SchemaExport to build a base database.
     *  
     */
    public void initializeTables(int quantityOwners)
            throws DatabaseInitializeFailedException {
        try {
            handleTables();
            //			new SetupBasicData();
            new DatabaseContentInitializer().initialize(quantityOwners);

        } catch (Exception e) {
            log.info(e);
            throw new DatabaseInitializeFailedException(e.toString());
        }

    }

    /**
     *  
     */
    private void handleTables() throws Exception {

        //		Configuration cfg = new DatabaseUtility().getTables();
        //		boolean script = true;
        //		boolean drop = false;
        //		boolean export = true;
        //		String outFile = null;
        //		String propFile = null;
        //		boolean formatSQL = false;
        //		String delim = null;
        //
        //		SchemaExport ex = new SchemaExport(cfg);
        //		/**
        //		 * for future reference, the SchemaExport returns itself when it calls
        // each
        //		 * method. So it looks like a VB "with" block.
        //		 */
        //
        //		new SchemaExport(cfg).setOutputFile(outFile).setDelimiter(
        //			delim).create(
        //			script,
        //			export);

        LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean) SpringBeanBroker
                .getBeanFactory().getBean("&mySessionFactory");

        lsfb.dropDatabaseSchema();

        
        lsfb.createDatabaseSchema();

    }

    //	public void initializeTables() throws DatabaseInitializerFailedException
    // {
    //		try {
    //			
    //			Configuration cfg = this.getConfiguration();
    //			handleTablesCreate(cfg);
    //
    //		} catch (Exception e) {
    //			throw new DatabaseInitializerFailedException(e.toString());
    //		}
    //
    //	}

    //	public void initializeTablesFromPath()
    //		throws DatabaseInitializerFailedException {
    //		try {
    //
    //			Configuration cfg = this.getPrefixedConfiguration();
    //			handleTablesCreate(cfg);
    //
    //			new SetupBasicData();
    //
    //		} catch (Exception e) {
    //				throw new DatabaseInitializerFailedException(e.toString());
    //			}
    //
    //		}
    //
    //		public void initializeTablesFromPath(String path)
    //			throws DatabaseInitializerFailedException {
    //			try {
    //
    //				Configuration cfg = this.getPrefixedConfiguration(path);
    //				handleTablesCreate(cfg);
    //
    //			} catch (Exception e) {
    //				throw new DatabaseInitializerFailedException(e.toString());
    //			}
    //
    //		}
    //
    //		public void updateTables() throws DatabaseInitializerFailedException {
    //			try {
    //				Configuration cfg = this.getConfiguration();
    //				handleTablesUpdate(cfg);
    //
    //			} catch (Exception e) {
    //				throw new DatabaseInitializerFailedException(e.toString());
    //			}
    //		}
    //
    //		public void updateTablesFromPath()
    //			throws DatabaseInitializerFailedException {
    //			try {
    //				Configuration cfg = this.getPrefixedConfiguration();
    //				handleTablesUpdate(cfg);
    //
    //			} catch (Exception e) {
    //				throw new DatabaseInitializerFailedException(e.toString());
    //			}
    //		}
    //
    //		public void updateTablesFromPath(String path)
    //			throws DatabaseInitializerFailedException {
    //			try {
    //				Configuration cfg = this.getPrefixedConfiguration(path);
    //				handleTablesUpdate(cfg);
    //
    //			} catch (Exception e) {
    //				throw new DatabaseInitializerFailedException(e.toString());
    //			}
    //		}
    /**
     * Crweates new tables in an existing, empty database.
     */
//    private void handleTablesCreate(Configuration cfg) throws Exception {
//
//        boolean script = true;
//        boolean drop = false;
//        boolean export = true;
//        String outFile = null;
//        String propFile = null;
//        boolean formatSQL = false;
//        String delim = null;
//
//        SchemaExport ex = new SchemaExport(cfg);
//        /**
//         * for future reference, the SchemaExport returns itself when it calls
//         * each method. So it looks like a VB "with" block.
//         */
//        new SchemaExport(cfg).setOutputFile(outFile).setDelimiter(delim)
//                .create(script, export);
//    }
//
//    /**
//     * Updates tables in an existing, NON-empty database.
//     */
//    private void handleTablesUpdate(Configuration cfg) throws Exception {
//
//        LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean) SpringBeanBroker
//                .getBeanFactory().getBean("&mySessionFactory");
//
//        lsfb.updateDatabaseSchema();
//
//        //			boolean script = true;
//        //			boolean drop = false;
//        //			boolean export = true;
//        //			String outFile = null;
//        //			String propFile = null;
//        //			boolean formatSQL = false;
//        //			String delim = null;
//        //
//        //			SchemaExport ex = new SchemaExport(cfg);
//        //			/**
//        //			 * for future reference, the SchemaExport returns itself when it calls
//        // each
//        //			 * method. So it looks like a VB "with" block.
//        //			 */
//        //			new SchemaUpdate(cfg).execute(true, true);
//    }

    //		private Configuration getPrefixedConfiguration()
    //			throws VersionConfigurationException {
    //			String prefix =
    //				System.getProperty("fstxtime.path.properties")
    //					+ "/WEB-INF/classes/";
    //			return getPrefixedConfiguration(prefix);
    //		}
    //
    //		private Configuration getPrefixedConfiguration(String prefix)
    //			throws VersionConfigurationException {
    //
    //// return DatabaseUtility.singleton.getConfiguration(prefix);
    //
    //			return VersionConfiguration
    //				.factory
    //				.build(VersionConfiguration.CURRENT_VERSION)
    //				.getPrefixedConfiguration(prefix);
    //
    //		}
    //
    //		private Configuration getConfiguration()
    //			throws VersionConfigurationException {
    //
    //// return DatabaseUtility.singleton.getConfiguration("");
    //
    //			return VersionConfiguration
    //				.factory
    //				.build(VersionConfiguration.CURRENT_VERSION)
    //				.getConfiguration();
    //
    //		}

    static Log log = LogFactory.getLog(DatabaseInitializer.class.getName());

    /**
     * @param dumpFileName
     * @throws DatabaseInitializerFailedException
     */
    public void initializeFromDumpFile(String dropDumpFileName,
            String addDumpFileName) throws DatabaseInitializerFailedException {

        DataSource ds = (DataSource) SpringBeanBroker.getBeanFactory().getBean(
                "myDataSource");

        handleSQLExecute(ds, dropDumpFileName);

        handleSQLExecute(ds, addDumpFileName);

    }

    private void handleSQLExecute(DataSource ds, String fileName)
            throws DatabaseInitializerFailedException {

        //get text file
        String init;
        try {
            init = getInitializationString(fileName);
        } catch (IOException e2) {
            e2.printStackTrace();
            throw new DatabaseInitializerFailedException(e2);
        }

        //get the connection
        Connection conn;
        try {
            conn = ds.getConnection();
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new DatabaseInitializerFailedException(e1);
        }

        //break into parts...
        String[] lines = init.split(";\n");

        //execute against
        try {
            for (int i = 0; i < lines.length; i++) {
                //				    System.out.println("line: " + lines[i]);

                if (!lines[i].trim().equals("")
                        && !lines[i].trim().startsWith("--")) {
                    //    				    System.out.println("line: " + lines[i]);
                    conn.createStatement().execute(lines[i] + ";");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseInitializerFailedException("when executing...", e);
        }

        try {
            conn.close();
        } catch (SQLException e3) {

            e3.printStackTrace();
        }

    }

    /**
     * @return
     */
    private String getInitializationString(String fileName) throws IOException {
        String prefix = System.getProperty(Settings.KEY_PATH);
        //String prefix = "C:/java/eclipseData/eSavvyRes/hotel";
        String inputFile = prefix + "/" + fileName;
        StringBuffer ret = new StringBuffer();

        boolean moreLines = true;

        BufferedReader newInput = new BufferedReader(new FileReader(inputFile));
        while (moreLines == true) {
            String newLine = newInput.readLine();
            if (newLine == null) {
                moreLines = false;
            } else {
                ret.append(newLine + "\n");
            }
        }
        newInput.close();

        return ret.toString();

    }

}