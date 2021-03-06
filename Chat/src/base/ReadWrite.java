package base;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class ReadWrite {

	private String path = "";
	private String content = "";
	private boolean delete = false;
	private File file;
	public static String newline = System.lineSeparator();
	

	public static void main(String[] args) {

	}
	
	public ReadWrite(boolean disableDelete) {
		delete = !disableDelete;
	}
	
	public ReadWrite(String thePath, String theContent, boolean togo) {
		path = thePath;
		content = theContent;
		if (togo) {
			create();
		}
	}
	
	public ReadWrite(String thePath, String theContent) {
		path = thePath;
		content = theContent;
	}
	
	public ReadWrite(String thePath) {
		path = thePath;
	}
	
	public ReadWrite() {}

	public void setPath(String x) {
		path = x;
	}

	public void setContent(String x) {
		content = x;
	}
	
	public void setDelete(boolean set) {
		delete = set;
	}

	public String getPath() {
		return path;
	}

	public String getContent() {
		return content;
	}
	
	public boolean getDelete() {
		return delete;
	}

	public void create() {
		try {
			file = new File(path);
			if (file.createNewFile()) {
				//System.out.println("File created: " + file.getName());
			} else {
				//System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			FileWriter writer = new FileWriter(path);
			writer.write(content);
			writer.close();
			//System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public void delete() {
		if (delete) {
			file = new File(path);
			if(file.delete()) { 
				//System.out.println("File deleted successfully"); 
			} else { 
				System.out.println("Failed to delete the file"); 
			}	
		} 
	}

	public String read() {
		String data = "";
		try {
			file = new File(path);
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				data += scan.nextLine() + newline;	
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return data;
	}
	
	public boolean exists() {
		file = new File(path);
		return file.exists();
	}
}
