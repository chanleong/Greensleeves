package itext;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import com.google.gson.Gson;

public class ImageHandler {
	
	/**
	 * 
	 * @param concept Essay's most suitable concept string
	 */
	
	private String queryStr = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
	private String concept = "";
	private String charset = "UTF-8";
	
	private int height;
	private int width;
	
	public ImageHandler(String concept){
		this.concept = concept;
	}
	
	public byte[] getImgByteArray(){
		String imgUrl = getImgStr();
		
		ByteArrayOutputStream bais = new ByteArrayOutputStream();
		InputStream is = null;
		
		try {
			URL url = new URL(imgUrl);
			is = url.openStream();
			byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
			int n;

			while ( (n = is.read(byteChunk)) > 0 ) {
				bais.write(byteChunk, 0, n);
			}
			
			if(height > 200||width > 200){
				byte[] tmp = resize(bais.toByteArray());
				return tmp;
			}
		}
		catch (IOException e) {
			//System.err.printf ("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
			e.printStackTrace ();
			// Perform any other exception handling that's appropriate.
		}
		finally {
			if (is != null) { 
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}

		
		return bais.toByteArray();
	}
	
	private byte[] resize(byte[] data){
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		
		try{
			BufferedImage img = ImageIO.read(in);
			img = Scalr.resize(img, 200);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ImageIO.write(img, "png", buffer);
			
			return buffer.toByteArray();
			
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	private String getImgStr(){
		String mostRelevantUrl = "";
		try {
			
			URL url = new URL(queryStr + URLEncoder.encode(concept, charset));
			Reader reader = new InputStreamReader(url.openStream(), charset);
			Gson gson = new Gson();
			GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
			mostRelevantUrl = results.getResponseData().getResults().get(0).getUrl();
			
			this.height = results.getResponseData().getResults().get(0).getHeight();
			this.width = results.getResponseData().getResults().get(0).getWidth();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return mostRelevantUrl;
	}
	
	public void setConcept(String concept){
		this.concept = concept;
	}
}
