import javax.print.DocFlavor;
import java.io.*;
import java.net.ServerSocket;
import java.net.*;
import java.lang.*;

public class serv1 
{
    public static void main(String[] args) throws Exception
    {
        ServerSocket server = new ServerSocket(80);
        System.out.println("Listening on port 1267 ....");
        int i = 1;
        int serverChoose = 0;
        while (true)
        {

            Socket clientSocket = server.accept();
            try
            {

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String line="";
                String line1 = "";
                double result;
                line1 = reader.readLine();
                int length=line1.length();
                //System.out.println(line1);
                int index1=line1.indexOf("=");
                int index2=line1.indexOf("&");
                String str=line1.substring(index1+1,index2);
                int number1=Integer.parseInt(str);
                index1=line1.lastIndexOf("=");
                str=line1.substring(index1+1,length);
                int number2=Integer.parseInt(str);
                String tosend="";
                if(number2>0 && number1>0)
                {
                    double x=Math.log(number1);
                    double y=Math.log(number2);
                    result=y/x;
                    /*if(x>0)
                    {
                        result=y/x;
                    }
                    else
                    {
                        result=y;
                    }*/
                    tosend = Double.toString(result);
                }
                else
                {
                    tosend="Please enter valid inputs";
                }
                //result=y/x;
                

                //String tosend = "haan mil gya i m server 1";
                 
                OutputStream os = clientSocket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);


                bw.write(tosend);
                System.out.println("Message sent to the client from server1 is "+tosend);
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

