package org.coloreframework;

import java.util.HashMap;

import org.coloreframework.exceptions.ColoreRequestWorkerColoreEngineNotReadyException;
import org.coloreframework.interfaces.ColoreRequestWorkerInterface;

public abstract class ColoreRequestWorker implements ColoreRequestWorkerInterface
{

	private ColoreEngine _colore = null;
	private HashMap<String, Object> _arguments;
	private HashMap<String, Object> _parameters;
	private HashMap<String, Object> _settings;
	
	public ColoreRequestWorker() throws Exception
	{
		if( ! ColoreEngine.isOperational() )
			throw new ColoreRequestWorkerColoreEngineNotReadyException();
		
		_colore = ColoreEngine.getInstance();
		_arguments = new HashMap<String,Object>();
		_parameters = new HashMap<String,Object>();
		_settings = new HashMap<String,Object>();
	}
	
}
