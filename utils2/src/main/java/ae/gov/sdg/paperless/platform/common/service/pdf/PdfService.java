package ae.gov.sdg.paperless.platform.common.service.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspose.words.Document;
import com.aspose.words.FontSettings;
import com.aspose.words.FontSourceBase;
import com.aspose.words.PhysicalFontInfo;
import com.aspose.words.SaveFormat;

import ae.gov.sdg.paperless.platform.common.model.components.DataContainer;

@Service
public class PdfService
{
	private static final Logger log = LoggerFactory.getLogger(PdfService.class);
    private static final String PDF_GENERATE_ERROR = "Failed to generate PDF";
    public static final String FONT_FOLDER = "src/main/resources/fonts";
    
	public DataContainer generatePdf(final String base64Doc) throws Exception 
	{
		final byte[] decodedBytes = Base64.getDecoder().decode(base64Doc);
        log.debug("Request Params for generatePdf method - {}", base64Doc);
		final InputStream in  = new ByteArrayInputStream(decodedBytes);
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try
		{
			/**
			 * conversion using APACHE POI
			 */
			/**
			 * XWPFDocument document = new XWPFDocument(in);
			 * PdfConverter.getInstance().convert(document, out,
			 * PdfOptions.create().getFontProvider().);
			 */
			FontSettings.getDefaultInstance().setFontsFolder("/usr/local/journey-services/fonts", false);

			for(final FontSourceBase fsb : FontSettings.getDefaultInstance().getFontsSources())
			{ 
				log.info("FONT SOURCE: {}",fsb.getType());
				for(final PhysicalFontInfo fi : fsb.getAvailableFonts())
				{
					log.info("FONT: {}",fi.getFullFontName());
				} 
			}
			
			final Document doc = new Document(in);
			doc.save(out, SaveFormat.PDF);
			
	        final byte[] resp = out.toByteArray();
	        final DataContainer dc = new DataContainer();
	        dc.setData(Base64.getEncoder().encodeToString(resp));
	        
			return dc;
		}
		catch(final Exception ex)
		{
			log.error(PDF_GENERATE_ERROR, ex);
			throw ex;
		}
		finally
		{
			try
			{
				in.close();
			}
			catch(final IOException ioexp)
			{
				log.error("Error closing input stream.");
			}
			try {
				out.flush();
			} catch (final IOException e) {
				log.error("Error flusing output stream.");
			}
	        try {
				out.close();
			} catch (final IOException e) {
				log.error("Error closing output stream.");
			}
		}
	}
}