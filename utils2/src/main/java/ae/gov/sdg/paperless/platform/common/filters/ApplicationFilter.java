package ae.gov.sdg.paperless.platform.common.filters;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.JOURNEY;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import ae.gov.sdg.paperless.platform.util.JsonUtil;

/***
 * This is the application filter through which all the requests will be passed through.
 * 
 * @author c_swetabh.raj
 *
 */
public class ApplicationFilter implements Filter {

	@Override
    public void init(final FilterConfig filterConfig) throws ServletException {
		//need to implement as required
    }

    @Override
    public void doFilter(final ServletRequest servletReq, final ServletResponse servletRes, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletReq;
        final HttpServletResponse response = (HttpServletResponse) servletRes;
        final MyRequestWrapper myRequestWrapper = new MyRequestWrapper(request);
        if (!isEmpty(IOUtils.toString(myRequestWrapper.getReader()))) {
            final Map<String, Object> map = JsonUtil
                    .fromJson(IOUtils.toString(myRequestWrapper.getReader()));
            request.setAttribute(JOURNEY, map.get(JOURNEY));
        }
        chain.doFilter(myRequestWrapper, response);
    }

    @Override
    public void destroy() {
    	//need to implement as required
    }

}