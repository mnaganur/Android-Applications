package demo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {

	public static void main (String args[]) throws UnknownHostException, IOException
	{
	
	int number;
	Scanner sc= new Scanner(System.in);	//to accept input from user
	
	//Creating Simple Socket
	Socket s= new Socket ("192.168.1.4",8080);
	Scanner sc1= new Scanner (s.getInputStream());
	
	//Using this socket to accept something from user
	System.out.println("Enter the number: ");
	number= sc.nextInt();
	
	//pass this object to server
	PrintStream p= new PrintStream(s.getOutputStream());
	p.println(number);
	
	//To accept the result from the server
	int temp= sc1.nextInt();
	System.out.println("Result: " +temp);
	
	}
	
}
