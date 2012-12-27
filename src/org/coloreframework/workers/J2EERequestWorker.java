package org.coloreframework.workers;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.coloreframework.ColoreEngine;
import org.coloreframework.ColoreRequestWorker;

public class J2EERequestWorker extends ColoreRequestWorker
{
	
	private static final Logger _logger = Logger.getLogger(J2EERequestWorker.class);
	private static HttpSession _session;
	private HttpServletRequest _request;
	private HttpServletResponse _response;
	
	public J2EERequestWorker(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		super();
	   	_loadServletRequestObject( req );
	   	_loadServletResponseObject( resp );
	   	_getServletSession();
	}

	private void _loadServletRequestObject(HttpServletRequest req) {
		_request = req;
	}
	
	private void _loadServletResponseObject(HttpServletResponse resp) {
		_response = resp;
	}
	
	private void _getServletSession() {
		// TODO Auto-generated method stub
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
