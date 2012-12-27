package com.example;

import org.coloreframework.ColoreLogicLink;
import org.coloreframework.interfaces.ColoreResolverInterface;

public class MyExampleResolver implements ColoreResolverInterface {

	@Override
	public boolean init(String configPath) {
		
		if( configPath.equalsIgnoreCase( "empty://" ) )
			return true;
		
		return false;
	}

	@Override
	public ColoreLogicLink resolve(String linkName) {
		// TODO Auto-generated method stub
		return null;
	}

}
