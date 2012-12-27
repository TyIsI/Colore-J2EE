package com.example;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.coloreframework.interfaces.ColoreBootstrapInterface;

public class MyExampleBootstrap implements ColoreBootstrapInterface
{

	private static final Logger _logger = Logger.getLogger(MyExampleBootstrap.class);
	
	public boolean init(String bootstrapConfigPath) {
		_logger.info( "Bootstrap init." );

		if( bootstrapConfigPath.equalsIgnoreCase( "empty://" ) )
			return true;
		
		_logger.error( "Bootstrap fail." );
		
		return false;
	}
	
	public HashMap<String, String> getHelpers() {
		HashMap<String, String> helperList = new HashMap<String, String>();
		
		helperList.put( "input", "contextHelper" );
		helperList.put( "output", "renderingHelper" );
		
		return helperList;
	}

	public HashMap<String, String> getResolvers() {
		HashMap<String, String> resolverList = new HashMap<String,String>();
		
		resolverList.put( "com.example.MyExampleResolver", "empty://" );
		
		return resolverList;
	}

	public HashMap<String, String> getSettings() {
		HashMap<String, String> settingsList = new HashMap<String,String>();
		
		settingsList.put( "logname", "com.example.colore" );
		settingsList.put( "loglevel", "0" );
		settingsList.put( "resolverzone", "example.com" );
		
		return settingsList;
	}

}
