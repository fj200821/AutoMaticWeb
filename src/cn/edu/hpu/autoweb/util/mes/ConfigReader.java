package cn.edu.hpu.autoweb.util.mes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ConfigReader {
    public String getProjectType() {
        return getAttr("projecttype");
    }

    public String getVersion() {
        return getAttr("version");
    }

    public String getFlag() {
        return getAttr("flag");
    }

    public String getSmsUsing() {;
        return getAttr("smsusing");
    }

    static public String getAttr(String attr) {
        if (prop == null) 
        {
            logger.info("load system config information.");
            try {
                prop = new Properties();
                InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream(
                        "/sysConfig.properties");
                prop.load(in);
            } catch (IOException e) {
            	logger.error("ConfigReader::getAttr catch exception:", e);
            }
        }
        
        if (prop.containsKey(attr)) 
        {
            return prop.getProperty(attr).trim();
        }
        else {
            return null;
        }
    }

    static Properties prop = null;
    
    private static Logger logger = LoggerFactory.getLogger(ConfigReader.class);
}
