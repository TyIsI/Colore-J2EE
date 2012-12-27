package org.coloreframework.interfaces;

import org.coloreframework.ColoreProcessor;

public interface ColoreCoreInterface {
	
	public void bootstrap( String bootstrapClass, String bootstrapConfigPath ) throws Exception; 
	public ColoreProcessor resolve( String linkName );

}
