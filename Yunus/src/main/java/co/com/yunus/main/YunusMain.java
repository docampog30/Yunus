package co.com.yunus.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;


public class YunusMain {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);         

	    WebAppContext webContext = new WebAppContext();
	    webContext.setDescriptor("src/main/webapp/WEB-INF/web.xml");
	    webContext.setResourceBase("src/main/webapp");    
	    webContext.setContextPath("/yunus");
	    webContext.setServer(server);
	    webContext.setParentLoaderPriority(true);
	    server.setHandler(webContext);
	    server.start();
	    server.join();
	}
}
