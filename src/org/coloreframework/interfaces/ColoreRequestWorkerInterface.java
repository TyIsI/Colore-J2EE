package org.coloreframework.interfaces;

import java.io.PrintWriter;
import java.util.HashMap;

public interface ColoreRequestWorkerInterface {
	
	// Core functionality
	public void run();
	
	// Session
	public Object getSessionParameter( String key );
	public boolean setSessionParameter( String key, Object val );
	
	// Input
	public Object getInputParameter( String key );
	public HashMap<String,Object> getInputArguments();
	public Object getInputData();
	public Object getInputDataStream();

	// Worker
	public Object getWorkerParameter( String key );
	public boolean setWorkerParameter( String key, Object val );
	
	// Output
	public Object getOutputParameter( String key );
	public void setOutputParameter( String key, Object val );
	public void setOutputData( Object val );
	public String getOutputData();
	public PrintWriter getOutputDataStream();
	
}
