package com.example.javalib.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class IODemo {
    public static void main(String[] args) {
//        io1();
//        io2();
//        io3();
//        io4();
//        io5();
//        io6();
//        io7();
//        io8();
        Runnable a=new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world a");
            }
        };
        Runnable b=()-> System.out.println("hello world b");
        b=()-> System.out.println("hello world bb");
        Thread thread=new Thread(b);
        thread.start();
        System.gc();
    }

    private static void io1() {
        //closeable
        try (OutputStream outputStream = new FileOutputStream("./javaLib/text.txt");) {

            outputStream.write('a');
            outputStream.write('b');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io2() {
        try (InputStream inputStream = new FileInputStream("./javaLib/text.txt")) {

            System.out.println((char) inputStream.read());
            System.out.println((char) inputStream.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io3() {
        try (InputStream inputStream = new FileInputStream("./javaLib/text.txt");
             Reader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader);) {
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io4() {
        try (OutputStream outputStream = new FileOutputStream("./javaLib/text.txt");
             Writer writer = new OutputStreamWriter(outputStream);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write('c');
            bufferedWriter.write('d');
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //file copy
    private static void io5() {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream("./javaLib/text.txt"));
             OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("./javaLib/new_text.txt"))) {
            byte[] data = new byte[1024];
            int read;
            while ((read = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, read);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io6() {
        try (Socket socket = new Socket("hencoder.com", 80);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            writer.write("GET / HTTP/1.1\n" +
                    "Host: www.example.com\n\n");
            writer.flush();
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io7() {

        try (ServerSocket serverSocket = new ServerSocket(90);
             Socket socket = serverSocket.accept();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            writer.write("HTTP/1.1 200 OK\n" +
                    "Date: Mon, 23 May 2005 22:38:34 GMT\n" +
                    "Content-Type: text/html; charset=UTF-8\n" +
                    "Content-Length: 138\n" +
                    "Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT\n" +
                    "Server: Apache/1.3.3.7 (Unix) (Red-Hat/Linux)\n" +
                    "ETag: \"3f80f-1b6-3e1cb03b\"\n" +
                    "Accept-Ranges: bytes\n" +
                    "Connection: close\n" +
                    "\n" +
                    "<html>\n" +
                    "  <head>\n" +
                    "    <title>An Example Page</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <p>Hello World, this is a very simple HTML document.</p>\n" +
                    "  </body>\n" +
                    "</html>\n\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io8() {
        try {
            RandomAccessFile file = new RandomAccessFile("./javaLib/text.txt", "r");
            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            channel.read(byteBuffer);
            byteBuffer.flip();
            System.out.println(Charset.defaultCharset().decode(byteBuffer));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
