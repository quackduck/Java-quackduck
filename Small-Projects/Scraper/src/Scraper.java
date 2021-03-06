import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


public class Scraper {

	private String address = "";
	private String website = "";
	private String file = "";
	private Socket sock = new Socket();
	private BufferedReader reader;
	private PrintWriter writer;
	private String everything;

	public static void main(String[] args) {
		Scraper scraper = new Scraper();
		scraper.setAddress("www.dictionary.com");
		scraper.doRequest();
		System.out.println(scraper.everything());
	}

	public void doRequest() {
		parseAddress();
		try {
			everything = "";
			sock = new Socket(website, 80);
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			writer = new PrintWriter(sock.getOutputStream());
			writer.println("GET " + file + " HTTP/1.1");
			writer.println("Host: " + website);
			writer.println("Accept: */*");
			writer.println("Connection: close");
			writer.println("User-Agent: Mozilla/4.0 (compatible; MSIE 6.01; Windows NT 6.0)");
			writer.println();
			writer.flush();
			String str;
			while ((str = reader.readLine()) != null) {
				everything += str + System.lineSeparator();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String header() {
		return everything.split("\\r?\\n\\r?\\n")[0];
	}

	public String header(boolean updateValuesFirst) {
		if (updateValuesFirst) {
			doRequest();
		}
		return header();
	}

	public String data() {
		return everything.split("\\r?\\n\\r?\\n")[1];
	}

	public String data(boolean updateValuesFirst) {
		if (updateValuesFirst) {
			doRequest();
		}
		return data();
	}

	public String everything() {
		return everything;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String theAddress) {
		address = theAddress;
	}

	private void parseAddress() { 
		if (website.isEmpty() || file.isEmpty()) {
			String[] thing = address.split("/", 2);
			website = thing[0]; 
			if (thing.length < 2) { 
				file = "/";
			} 
			else {
				file = "/" + thing[1];
			}
		}
	}
}
