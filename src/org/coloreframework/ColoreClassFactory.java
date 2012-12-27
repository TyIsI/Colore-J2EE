package org.coloreframework;

import org.coloreframework.exceptions.ColoreConfigStoreInterfaceException;
import org.coloreframework.exceptions.ColoreResolverInterfaceException;
import org.coloreframework.interfaces.ColoreBootstrapInterface;
import org.coloreframework.interfaces.ColoreResolverInterface;

public class ColoreClassFactory {
	
	public static Object make( String className ) throws Exception
	{
		Object classObj = Class.forName( className ).newInstance();
		
		if( classObj.getClass().getName().equals( className ) )
			return classObj;
		
		return null;
	}
	
	public static Object makeBootstrap( String className ) throws Exception
	{
		Object classObj = make( className );
		
		if( ! ColoreBootstrapInterface.class.isAssignableFrom( classObj.getClass() ) )
			throw new ColoreConfigStoreInterfaceException();
		
		return classObj;
	}

	public static Object makeResolver( String className ) throws Exception {
		Object classObj = make( className );
		
		if( ! ColoreResolverInterface.class.isAssignableFrom( classObj.getClass() ) )
			throw new ColoreResolverInterfaceException();
		
		return classObj;
	}

}
