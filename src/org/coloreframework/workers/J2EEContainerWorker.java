package org.coloreframework.workers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.coloreframework.ColoreEngine;

public class J2EEContainerWorker extends HttpServlet
{

	private ColoreEngine _colore = null;
	private static final Logger _logger = Logger.getLogger(J2EEContainerWorker.class);

	public J2EEContainerWorker () {
	   	BasicConfigurator.configure();
	}
	
	public void init()
	{
		_logger.debug( "Running init" );
		String bootstrapClass = this.getInitParameter( "bootstrapClass" );
		String bootstrapConfigPath = this.getInitParameter( "bootstrapConfigPath" );
		try {
			_logger.debug( "Trying to get ColoreEngine instance..." );
			_colore = ColoreEngine.getInstance();
			_logger.debug( "Trying to get ColoreEngine bootstrapped..." );
			_colore.bootstrap( bootstrapClass, bootstrapConfigPath );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		_logger.info( "Ready for service" );
	}
	
	public void service(HttpServletRequest req, HttpServletResponse resp)
	{
		_logger.info( "Handling request" );
		
		J2EERequestWorker worker = null;
		
		try {
			worker = new J2EERequestWorker( req, resp );
			worker.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
