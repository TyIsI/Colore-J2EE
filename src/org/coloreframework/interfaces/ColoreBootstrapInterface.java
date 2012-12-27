package org.coloreframework.interfaces;

import java.util.ArrayList;
import java.util.HashMap;



public interface ColoreBootstrapInterface {

	public boolean init( String configStoreLocation );
	public HashMap<String,String> getHelpers();
	public HashMap<String,String> getResolvers();
	public HashMap<String,String> getSettings();
	
}
