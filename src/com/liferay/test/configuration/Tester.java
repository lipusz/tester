package com.liferay.test.configuration;

import com.germinus.easyconf.ComponentConfiguration;
import com.germinus.easyconf.ComponentProperties;
import com.germinus.easyconf.EasyConf;

/**
 * @author Test
 *<p>
 *This is a simple class to play with EasyConf.
 */
public class Tester {

	public static void main(String[] args) {
		ComponentProperties props = _conf.getProperties();

		printName(props.getString(_TESTER_NAME));

	}

	public static void printName(String name) {
		System.out.println("Tester name: " + name);
	}

	private static final String _CONFIGURATION_NAME = "tester";

	private static final String _TESTER_NAME = "tester.name";

	private static ComponentConfiguration _conf;

	static {
		_conf = EasyConf.getConfiguration(
			_CONFIGURATION_NAME);
	}

}