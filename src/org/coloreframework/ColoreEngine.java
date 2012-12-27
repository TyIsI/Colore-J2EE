package org.coloreframework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.coloreframework.exceptions.ColoreBootstrapInitializationException;
import org.coloreframework.exceptions.ColoreBootstrapInvalidHelpersException;
import org.coloreframework.exceptions.ColoreEngineBootstrapEmptyHelperSettingsException;
import org.coloreframework.exceptions.ColoreEngineBootstrapLoadNewResolverException;
import org.coloreframework.exceptions.ColoreEngineBootstrapLogLevelSettingException;
import org.coloreframework.exceptions.ColoreEngineBootstrapLogNameSettingException;
import org.coloreframework.exceptions.ColoreEngineBootstrapMissingInputHelperSettingException;
import org.coloreframework.exceptions.ColoreEngineBootstrapNullHelperSettingsException;
import org.coloreframework.exceptions.ColoreEngineBootstrapResolverZoneSettingException;
import org.coloreframework.exceptions.ColoreEngineGetInstanceException;
import org.coloreframework.interfaces.ColoreBootstrapInterface;
import org.coloreframework.interfaces.ColoreCoreInterface;
import org.coloreframework.interfaces.ColoreResolverInterface;


public class ColoreEngine implements ColoreCoreInterface {

	private static ColoreEngine _instance;
	private static boolean _operational = false;
	private HashMap<String, String> _applicationSettings = null;
	private HashMap<String, String> _applicationHelpers = null;
	private HashMap<String,ColoreResolverInterface> _resolvers = null;
	private static final Logger _logger = Logger.getLogger(ColoreEngine.class);

	private ColoreEngine() throws Exception
	{
		_resolvers = new HashMap<String,ColoreResolverInterface>();
	}

	public void bootstrap( String bootstrapClass, String bootstrapConfigPath ) throws Exception
	{
		_logger.info( "Running Bootstrap" );
		
		_logger.info( "Bootstrap Loading" );
		Object factoryObj = ColoreClassFactory.makeBootstrap( bootstrapClass );
		
		ColoreBootstrapInterface bootstrapObj = (ColoreBootstrapInterface) factoryObj;
		
		_logger.info( "Bootstrap Initialization" );
		if( ! bootstrapObj.init( bootstrapConfigPath ) )
			throw new ColoreBootstrapInitializationException();
		
		_logger.info( "Loading Application Settings" );
		_loadApplicationSettings( bootstrapObj );
		_logger.info( "Loading Helper Settings" );
		_loadHelperSettings( bootstrapObj );
		_logger.info( "Loading Resolvers" );
		_loadResolvers( bootstrapObj );
		
		_setOperational();
	}

	private void _loadApplicationSettings( ColoreBootstrapInterface bootstrapObj) throws ColoreEngineBootstrapLogNameSettingException, ColoreEngineBootstrapResolverZoneSettingException, ColoreEngineBootstrapLogLevelSettingException {
		_applicationSettings =  bootstrapObj.getSettings();
		
		if( ! _applicationSettings.containsKey( "logname" ) )
			throw new ColoreEngineBootstrapLogNameSettingException();
		
		if( ! _applicationSettings.containsKey( "loglevel" ) )
			throw new ColoreEngineBootstrapLogLevelSettingException();
		
		if( ! _applicationSettings.containsKey( "resolverzone" ) )
			throw new ColoreEngineBootstrapResolverZoneSettingException();
	}
	
	private void _loadHelperSettings(ColoreBootstrapInterface bootstrapObj) throws ColoreBootstrapInvalidHelpersException, ColoreEngineBootstrapEmptyHelperSettingsException, ColoreEngineBootstrapNullHelperSettingsException, ColoreEngineBootstrapMissingInputHelperSettingException {
		_applicationHelpers = bootstrapObj.getHelpers();
		
		if( _applicationHelpers == null )
			throw new ColoreEngineBootstrapNullHelperSettingsException();
			
		if( _applicationHelpers.isEmpty() )
			throw new ColoreEngineBootstrapEmptyHelperSettingsException();

		if( ! _applicationHelpers.containsKey( "input" ) )
			throw new ColoreEngineBootstrapMissingInputHelperSettingException();

	}
	
	private void _loadResolvers(ColoreBootstrapInterface bootstrapObj) throws Exception {
		HashMap<String, String> applicationResolvers = bootstrapObj.getResolvers();
		
		Iterator<Entry<String, String>> resolverIterator = applicationResolvers.entrySet().iterator();
		
		Entry<String, String> resolverEntry = null;
		
		while( resolverIterator.hasNext() )
		{
			resolverEntry = resolverIterator.next();
			String resolverClassPath = resolverEntry.getKey();
			String resolverStorePath = resolverEntry.getValue();
			_logger.info( "resolverIterator.next := " + resolverClassPath );
			ColoreResolverInterface resolverObj = (ColoreResolverInterface) ColoreClassFactory.makeResolver( resolverClassPath );
			
			if( ! resolverObj.init( resolverStorePath ) )
				throw new ColoreEngineBootstrapLoadNewResolverException();
				
			_resolvers.put( resolverClassPath, resolverObj );
		}
		
		_logger.info( "_loadResolvers loaded: " + _resolvers.size() + " resolver(s)" );
	}

	public static synchronized ColoreEngine getInstance() throws Exception
	{
		if( ColoreEngine.class.isInstance( _instance ) )
			return _instance;

		if( _instance == null )
			_instance = new ColoreEngine();
		
		if( ! ColoreEngine.class.isInstance( _instance ) )
			throw new ColoreEngineGetInstanceException();

		_logger.info( "getInstance() returning instance..." );

		return _instance;
	}

	public ColoreProcessor resolve( String processorName )
	{
		Iterator<Entry<String, ColoreResolverInterface>> resolverIterator = _resolvers.entrySet().iterator();
		
		while( resolverIterator.hasNext() )
		{
			Entry<String, ColoreResolverInterface> resolverEntry = resolverIterator.next();
			
			_logger.debug( "resolving: " + processorName );
			ColoreProcessor resolverResult = resolverEntry.getValue().resolve( processorName );
			
			if( resolverResult != null )
				return resolverResult;
		}
		
		return null;
	}

	public String getInputHelper() {
		if( _applicationHelpers.containsKey( "input" ) )
			return _applicationHelpers.get( "input" );
		
		return null;
	}

	public String getExceptionHelper() {
		if( _applicationHelpers.containsKey( "exception" ) )
			return _applicationHelpers.get( "exception" );
		
		return null;
	}

	public String getOutputHelper() {
		if( _applicationHelpers.containsKey( "output" ) )
			return _applicationHelpers.get( "output" );
		
		return null;
	}
	
	public static boolean isOperational() {
		return _operational;
	}
	
	private void _setOperational() {
		_operational = true;
	}
	
	private void _unsetOperational() {
		_operational = false;
	}

}
