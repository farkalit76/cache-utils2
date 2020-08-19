package ae.gov.sdg.paperless.platform.common.filters;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/***
 * This wrapper is written to create a copy of request to read the body
 * 
 * @author c_swetabh.raj
 *
 */
public class MyRequestWrapper extends HttpServletRequestWrapper {
	
	private static final Log logger = LogFactory.getLog(MyRequestWrapper.class);
	
    private final String body;
    
    public MyRequestWrapper(final HttpServletRequest request) throws IOException {

        super(request);
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            final InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
            	readFile(stringBuilder, inputStream);
            } else {
                stringBuilder.append("");
            }
        } catch (final IOException ex) {
        	logger.error(ex.getMessage());
            throw ex;
        } 
        body = stringBuilder.toString();
    }



	/**
	 * @param stringBuilder
	 * @param inputStream
	 * @throws Exception
	 */
	private void readFile(final StringBuilder stringBuilder, final InputStream inputStream) throws IOException {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
		    final char[] charBuffer = new char[128];
		    int bytesRead = -1;
		    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		        stringBuilder.append(charBuffer, 0, bytesRead);
		    }
		}catch (Exception e) {
			logger.error("Error while reading the file: "+e.getMessage());
			throw e;
		}
	}



    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public void setReadListener(final ReadListener listener) {
            	//need to implement as required
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }
}