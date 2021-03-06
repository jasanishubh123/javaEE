/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import misc.TempIO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/jaas/*"})
public class SecurityTokenFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public SecurityTokenFilter() {
    } 

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {
	if (debug) log("SecurityTokenFilter:DoBeforeProcessing");
        HttpServletRequest objHttpServletRequest = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse)response;

        HttpSession session = objHttpServletRequest.getSession();
    if(session.getAttribute("username").equals("") && objHttpServletRequest.getParameter("username").equals("") )
                {
                    //session.invalidate();
                      request.setAttribute("msg", "You cannot by pass the security. Login");

           res.sendRedirect(objHttpServletRequest.getContextPath()+"/index.jsp?msg=You cannot by pass the security. Login");
    
                }
     else {
   
        try {
      String username=  objHttpServletRequest.getParameter("username");
      String password = objHttpServletRequest.getParameter("password");
      
objHttpServletRequest.login(username, password);
System.out.println("user="+username+"  "+ objHttpServletRequest.isUserInRole("admin"));
//HttpSession session1 = objHttpServletRequest.getSession();
session.setAttribute("username", username);
session.setAttribute("password", password);

if(objHttpServletRequest.isUserInRole("admin"))
   res.sendRedirect(objHttpServletRequest.getContextPath()+"/admin.jsp");
else if(objHttpServletRequest.isUserInRole("supervisor"))
   res.sendRedirect(objHttpServletRequest.getContextPath()+"/users.jsp");
else
    res.sendRedirect(objHttpServletRequest.getContextPath()+"/index.jsp");

    } catch (Exception e) {
         request.setAttribute("msg", "Either login or password is wrong !!. Pl. login again");

       res.sendRedirect(objHttpServletRequest.getContextPath()+"/index.jsp?msg=Either login or password is wrong !!. Pl. login again");
      
  }
    }
     
             
           //  }
    } 

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {
	
    }
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {

	if (debug) log("SecurityTokenFilter:doFilter()");

	doBeforeProcessing(request, response);
	
	Throwable problem = null;
	try {
	    chain.doFilter(request, response);
	}
	catch(Throwable t) {
	    // If an exception is thrown somewhere down the filter chain,
	    // we still want to execute our after processing, and then
	    // rethrow the problem after that.
	    problem = t;
	    t.printStackTrace();
	}

	doAfterProcessing(request, response);

	// If there was a problem, we want to rethrow it if it is
	// a known type, otherwise log it.
	if (problem != null) {
	    if (problem instanceof ServletException) throw (ServletException)problem;
	    if (problem instanceof IOException) throw (IOException)problem;
	    sendProcessingError(problem, response);
	}
    }
    
    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
	return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
	this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter 
     */
    public void destroy() { 
    }

    /**
     * Init method for this filter 
     */
    public void init(FilterConfig filterConfig) { 
	this.filterConfig = filterConfig;
	if (filterConfig != null) {
	    if (debug) { 
		log("SecurityTokenFilter:Initializing filter");
	    }
	}
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
	if (filterConfig == null) return ("SecurityTokenFilter()");
	StringBuffer sb = new StringBuffer("SecurityTokenFilter(");
	sb.append(filterConfig);
	sb.append(")");
	return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
	String stackTrace = getStackTrace(t); 

	if(stackTrace != null && !stackTrace.equals("")) {
	    try {
		response.setContentType("text/html");
		PrintStream ps = new PrintStream(response.getOutputStream());
		PrintWriter pw = new PrintWriter(ps); 
		pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
		    
		// PENDING! Localize this for next official release
		pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n"); 
		pw.print(stackTrace); 
		pw.print("</pre></body>\n</html>"); //NOI18N
		pw.close();
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
	else {
	    try {
		PrintStream ps = new PrintStream(response.getOutputStream());
		t.printStackTrace(ps);
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
    }

    public static String getStackTrace(Throwable t) {
	String stackTrace = null;
	try {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    t.printStackTrace(pw);
	    pw.close();
	    sw.close();
	    stackTrace = sw.getBuffer().toString();
	}
	catch(Exception ex) {}
	return stackTrace;
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }

}
