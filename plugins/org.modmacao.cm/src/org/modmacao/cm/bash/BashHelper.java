package org.modmacao.cm.bash;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class BashHelper {
	Properties props;
	
	public BashHelper() {
		loadProperties();
	}
	
	public Properties getProperties() {
		if (props == null)
			loadProperties();
		
		return props;
	}
	
	private void loadProperties() {
		props = new Properties();
		InputStream input = null;
		
		try {
    		String filename = "bash.properties";
    		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
    		if (bundle != null) {
    			URL url = FrameworkUtil.getBundle(this.getClass()).getResource(filename);
    			input = url.openConnection().getInputStream();
    		}

    		if (input == null) {
    			// try to read properties without BundleLoader
    			input = this.getClass().getClassLoader().getResourceAsStream(filename);	
    		}
    		
    		props.load(input);
    		
    	} catch (IOException ex) {
    		ex.printStackTrace();
        } finally {
        	if(input!=null){
	        	try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
	}
	
	
}
