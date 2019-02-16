package klient;

import java.io.*;
import java.net.*;
public class Klient
{

public static final int PORT=50010;
public static final String HOST = "127.0.0.1";
public static void main(String[] args) throws IOException
{
	Socket sock;	
	BufferedReader klaw;
	PrintWriter outp;
	BufferedReader inp;

while(true) {
		//nawiazanie polaczenia z serwerem
		sock=new Socket(HOST,PORT);
		System.out.println("Nawiazalem polaczenie: "+sock);
		//tworzenie strumieni danych pobieranych z klawiatury i dostarczanych do socketu
		klaw=new BufferedReader(new InputStreamReader(System.in));
		outp=new PrintWriter(sock.getOutputStream());
		//komunikacja - czytanie danych z klawiatury i przekazywanie ich do strumienia
		
		System.out.print("Podaj ci¹g liczb do sortowania:");
		String str1=klaw.readLine();
		outp.println(str1);
		outp.flush();
		
		System.out.print("Podaj czy sortowaæ tablice rosnaco czy malejaco:");
		String str2=klaw.readLine();
		outp.println(str2);
		outp.flush();
		
		inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));
		//komunikacja - czytanie danych ze strumienia
		String str3;
		str3=inp.readLine();
		System.out.println("<Odebrano:> " + str3);
		
		System.out.println("Koñczê dzia³anie klienta");
		if(str1.equalsIgnoreCase("exit")) break;
		
	}
	//zamykanie polaczenia
	inp.close();
	klaw.close();
	outp.close();
	sock.close();
}
}