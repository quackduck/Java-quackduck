import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Sender {

    public Socket senderSock = null;//
    public Scanner in = new Scanner(System.in);
    public OutputStream senderWriter = null;
    public FileInputStream fileReader = null;
    public File theFileToSend = null;
    String ip = "";
    int code = 0;
    String filePath = "";

    public static void main(String[] args) {
        new Sender().go();
    }

    public void go () {
        try {
            if (filePath.equals("")) {
                System.out.println("Enter the full path of the file you want to send");
                filePath = in.nextLine();
            }
            theFileToSend = new File(filePath);
            if (ip.equals("")) {
                System.out.println("Put in the IP please:");
                ip = in.nextLine();
            }
            if (code == 0) {
                System.out.println("Put in the Code please:");
                code = in.nextInt();
            }
            senderSock = new Socket(ip, code);
            senderWriter = senderSock.getOutputStream();
            fileReader = new FileInputStream(theFileToSend);
            sendMetadata();
            sendFile();
//            System.out.println("Boom! File sent :)");
            close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public Sender (String theIP, int theCode, String theFilePath) {
        ip = theIP;
        code = theCode;
        filePath = theFilePath;
    }

    public Sender() {}

    public static void progressPercentage(int done, int total, int estimatedTime) {
        int size = 10;
        String iconLeftBoundary = "[";
        String iconDone = "=";
        String iconRemain = ".";
        String iconRightBoundary = "]";

        if (done > total) {
            throw new IllegalArgumentException();
        }
        int donePercents = (100 * done) / total;
        int doneLength = size * donePercents / 100;

        StringBuilder bar = new StringBuilder(iconLeftBoundary);
        for (int i = 0; i < size; i++) {
            if (i < doneLength) {
                bar.append(iconDone);
            } else {
                bar.append(iconRemain);
            }
        }
        bar.append(iconRightBoundary);

        System.out.print("\r" + bar + " " + donePercents + "%" + "(" + done + "/" + total + ")" + " " + estimatedTime + "s left");

        if (done == total) {
            System.out.print("\n");
        }
    }

    public void sendMetadata () {
        try {
            String metadata = "FileSizeInBytes=" + theFileToSend.length() + "," + "FileName=" + theFileToSend.getName();
            metadata += "/"; // to signify end of metadata
            byte[] metadataBytes = metadata.getBytes(StandardCharsets.UTF_8);
            senderWriter.write(metadataBytes);
            senderWriter.flush();
        } catch (Exception e) {e.printStackTrace();}
    }

    public void sendFile () {
        try {
            byte[] buffer = new byte[4096];
            int count;
            while ((count = fileReader.read(buffer)) > 0) {
                senderWriter.write(buffer, 0, count);
            }
            senderWriter.flush();
        } catch (Exception e) {e.printStackTrace();}
    }

    public void close () {
        try {
            fileReader.close();
            senderWriter.close();
            senderSock.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}
