package study;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletHandler;

public class MyServer {

	public void start() throws Exception {
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setHost("127.0.0.1");
		connector.setPort(8080);
		server.addConnector(connector);
		
		ServletHandler servletHandler = new ServletHandler();
		servletHandler.addServletWithMapping(MyServlet2.class, "/*");
		server.setHandler(servletHandler);
		
		server.join();
		server.start();
	}
}
