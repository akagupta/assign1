import java.io.*;
import java.net.*;
import javax.print.DocFlavor;
import java.net.ServerSocket;

public class loadbalancer 
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket server = new ServerSocket(80);
		//System.out.println("Listening ............");
		int n=0;
		while (true)
		{

			Socket clientSocket = server.accept();
			try
			{

				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
				String s1="";
				String s = "";
				s = reader.readLine();				
				while(true){

					s1 = reader.readLine();
					if(s1.isEmpty()) break;
					
				}
				int end = s.indexOf("HTTP");				
				String xyz = s.substring(4,end-1);              
                String[] ip= {"10.0.0.2","10.0.0.3"}; 
                if(n%2==0)
                {
                	
                    Socket socket1 = new Socket(ip[n%2], 80);
                	PrintWriter out = new PrintWriter(socket1.getOutputStream(), true);
     				BufferedReader in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                	
                    String address = "";
                	address = ip[n%2];
                	n++;
                	address += xyz;
                	out.println(xyz);
                	String recv = in.readLine();
                	System.out.println("Reply from server1: " + recv);
                    out.println(xyz);
                	outToClient.write("server 1");
                	socket1.close();
                	clientSocket.close();

                }
                else
                {
                	Socket socket2 = new Socket(ip[n%2], 80);
                	PrintWriter   out2 = new PrintWriter(socket2.getOutputStream(), true);
                	BufferedReader  in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
   	                String address = "";
                	address=ip[n%2];
                	n++;
                	address+=xyz;
                	out2.println(xyz);
                	String recv2 = in2.readLine();
                    double d=Double.parseDouble(recv2);
                    d=d*2;
                    recv2=Double.toString(d);
                	System.out.println("Reply from server2: " + recv2);              	
                	outToClient.write("server 2");                	
                	socket2.close();
                	clientSocket.close();
                }                
                   clientSocket.close();

            }
            catch(Exception e)
            {
            	System.out.printf("File not found");
            	e.printStackTrace();
            	clientSocket.close();
            }


        }
    }
}
