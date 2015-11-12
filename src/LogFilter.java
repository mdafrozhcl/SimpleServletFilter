import java.io.IOException;
import java.util.HashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LogFilter implements Filter {
	HashMap<String, Integer> register;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String IpAddr = request.getRemoteAddr();
		int count = 1;
		if (register.containsKey(IpAddr)) {
			count += register.get(IpAddr);
			register.replace(IpAddr, count);
		} else {
			register.put(IpAddr, count);
		}
		System.out.println("IP: " + IpAddr + " count: " + count + " Type: "
				+ request.getDispatcherType().name() + " Date/time: "
				+ new java.util.Date());
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		register = new HashMap<String, Integer>();
	}
}