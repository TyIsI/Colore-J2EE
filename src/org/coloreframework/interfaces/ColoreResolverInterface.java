package org.coloreframework.interfaces;

import org.coloreframework.ColoreProcessor;

public interface ColoreResolverInterface {
	
	public boolean init( String configPath );

	public ColoreProcessor resolve(String processorName);

}
