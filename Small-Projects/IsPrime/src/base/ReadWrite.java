package base;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadWrite {

	private String path = "";
	private String content = "";
	public String newline = System.lineSeparator();

	public static void main(String[] args) {

	}

	public void setPath(String x) {
		path = x;
	}

	public void setContent(String x) {
		content = x;
	}

	public String getPath() {
		return path;
	}

	public String getContent() {
		return content;
	}

	public void create() {
		try {
			File file = new File(path);
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			FileWriter writer = new FileWriter(path);
			writer.write(content);
			writer.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

//	public void delete() {
//		File file = new File(path);
//		if(file.delete()) { 
//			System.out.println("File deleted successfully"); 
//		} else { 
//			System.out.println("Failed to delete the file"); 
//		} 
//	}

	public String read() {
		String data = "";
		try {
			File file = new File(path);
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
}
