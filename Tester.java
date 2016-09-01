package com.liferay.test.configuration;

import com.germinus.easyconf.ComponentConfiguration;
import com.germinus.easyconf.ComponentProperties;
import com.germinus.easyconf.EasyConf;

/**
 * @author Test
 */
public class Tester {

	public static void main(String[] args) {
        ComponentConfiguration conf = EasyConf.getConfiguration("tester");
        ComponentProperties props = conf.getProperties();
        
        String name = props.getString("tester.name");
        
        System.out.println("Tester name: " + name);
	}

}