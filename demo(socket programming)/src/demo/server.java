package demo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {

	public static void main(String args[]) throws IOException
	{
		
		//Creating server socket
		ServerSocket s1= new ServerSocket(8080);
		
		//Create Simple Socket
		Socket ss= s1.accept(); //to accept the incoming request to socket s1
		System.out.println("Waiting for Client to connect....");
		
		Scanner sc=new Scanner(ss.getInputStream()); //Accepts the client's number
		int number= sc.nextInt();
		
		//Modifying the number
		int temp= (number) % 10;
		
		//Passing message to Client
		PrintStream p= new PrintStream(ss.getOutputStream());
		p.println(temp);
				
	}
	
}
