import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class P2PFileSharingSystem {

    private static final int PORT = 12345;
    private static final String SHARED_DIRECTORY = "shared-files/";
    private static final String DOWNLOADS_DIRECTORY = "Downloads/";

    public static void main(String[] args) {
         
        String senderIpAddress = "192.168.138.38";//"127.0.0.1";// Sumit Ip Adress
        String receiverIpAddress = "192.168.138.148"; //"127.0.0.1"; // OTP Ip Adress 
         

        
        Thread senderThread = new Thread(() -> startSender(senderIpAddress));
        Thread receiverThread = new Thread(() -> startReceiver(receiverIpAddress));

        senderThread.start();
        receiverThread.start();
    }

    private static void startSender(String receiverIpAddress) {
        try (Socket socket = new Socket(receiverIpAddress, PORT);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {

            
            String[] filesToSend = {"file1.txt", "file2.txt", "file3.txt","WhatsApp Image 2024-01-10 at 09.40.04_380b3092.jpg"};

            for (String fileName : filesToSend) {
                
                objectOutputStream.writeObject(fileName);

                
                try (FileInputStream fileInputStream = new FileInputStream(SHARED_DIRECTORY + fileName)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        objectOutputStream.write(buffer, 0, bytesRead);
                    }
                }

                System.out.println("File '" + fileName + "' sent successfully.");
            }

             objectOutputStream.writeObject("exit");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startReceiver(String senderIpAddress) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Waiting for sender to connect...");

            
            Socket senderSocket = serverSocket.accept();

            File downloadsDirectory = new File(DOWNLOADS_DIRECTORY);
            downloadsDirectory.mkdirs(); 

            try (ObjectInputStream objectInputStream = new ObjectInputStream(senderSocket.getInputStream())) {

                while (true) {
                     
                    String fileName;
                    try {
                        fileName = (String) objectInputStream.readObject();
                    } catch (EOFException eofException) {
                         
                        break;
                    }

                     
                    if ("exit".equalsIgnoreCase(fileName)) {
                        break;
                    }

                     
                    try (FileOutputStream fileOutputStream = new FileOutputStream(DOWNLOADS_DIRECTORY + fileName)) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = objectInputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }
                    }

                    System.out.println("File '" + fileName + "' received successfully and saved in Downloads directory.");
                }

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
