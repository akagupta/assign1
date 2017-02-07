import java.io.*;
import java.net.*;
import java.lang.*;
import javax.print.DocFlavor;
import java.net.ServerSocket;

public class serv1 
{
    public static void main(String[] args) throws Exception
    {
        ServerSocket server = new ServerSocket(1267);
        //System.out.println("Listening ..............");
        
        while (true)
        {

            Socket clientSocket = server.accept();
            try
            {

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                
                String s="";
                s=reader.readLine();
                int length=s.length();
                double result;                        
                int index1=s.indexOf("=");
                int index2=s.indexOf("&");
                String str=s.substring(index1+1,index2);
                int number1=Integer.parseInt(str);
                index1=s.lastIndexOf("=");
                if(s.contains("HTTP"))
                {
                    index2=s.indexOf("H");
                    str=s.substring(index1+1,index2-1);
                }
                else
                {
                    str=s.substring(index1+1,length);
                }
                int number2=Integer.parseInt(str);                
                String msg="";
                //(number2>0 && number1>0)
                //{
                    double x=Math.log(number1);
                    double y=Math.log(number2);
                    result=y/x;
                    msg = Double.toString(result);
                //}
                //else
                //{
                    //msg="Please enter valid inputs";
                //}                
                OutputStream os = clientSocket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(msg);
                System.out.println("Message sent to the client from server1 is "+msg);

                out.write(" ");
                out.println(msg);


                bw.flush();               
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

