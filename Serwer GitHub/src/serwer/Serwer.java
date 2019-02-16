package serwer;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.awt.List;

public class Serwer
{
public static final int PORT=50010;
public static void main(String args[]) throws IOException
{
    ServerSocket serv;
    Socket sock;
    BufferedReader inp;
    PrintWriter outp;
    
while(true){    
    
//tworzenie gniazda serwerowego
serv=new ServerSocket(PORT);
//oczekiwanie na polaczenie i tworzenie gniazda sieciowego
System.out.println("Nasluchuje: "+serv);
sock=serv.accept();
System.out.println("Jest polaczenie: "+sock);
//tworzenie strumienia dganych pobieranych z gniazda sieciowego
inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));
//komunikacja - czytanie danych ze strumienia
String str1;
str1=inp.readLine();
System.out.println("<Odebrano:> " + str1);

String str2;
str2=inp.readLine();
System.out.println("<Odebrano:> " + str2);

//sortowanie
String [] numbers = str1.split(" ");
Integer[] intValues = new Integer[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            intValues[i] = Integer.parseInt(numbers[i].trim());
        }
Collections.sort(Arrays.asList(intValues));
String str3;
if(str2.equals("malejaco")){
ArrayList<Integer> intList = new ArrayList<Integer>();
for (int i : intValues)
{
    intList.add(i);
}

Collections.reverse(intList);
str3 = Arrays.toString(intList.toArray());
System.out.println( Arrays.toString(intList.toArray()));
}
else{
str3 = Arrays.toString(intValues);
System.out.println( Arrays.toString(intValues));
}

//wysylanie powrotne
outp=new PrintWriter(sock.getOutputStream());
outp.println(str3);
outp.flush();

System.out.println("Koñczê dzia³anie serwera");
//zamykanie polaczenia
outp.close();
inp.close();
sock.close();
serv.close();
if(str1.equalsIgnoreCase("exit")) break;
}

}
}