import javax.print.DocFlavor;
import java.io.*;
import java.net.ServerSocket;
import java.net.*;

public class serv2 
{
    public static void main(String[] args) throws Exception
    {
        ServerSocket server = new ServerSocket(80);
        System.out.println("Listening .............");
       
        while (true)
        {

            Socket clientSocket = server.accept();
            try
            {

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
                PrintWriter   out = new PrintWriter(clientSocket.getOutputStream(), true);

                String line="";
                String line1 = "";
                line1 = reader.readLine();
                int length=line1.length();
                System.out.println(line1);

                int index1=line1.indexOf("=");
                int index2=line1.indexOf("&");
                String str=line1.substring(index1+1,index2);
                int number1=Integer.parseInt(str);
                index1=line1.lastIndexOf("=");

                if(line1.contains("HTTP"))
                {
                    index2=line1.indexOf("H");
                    str=line1.substring(index1+1,index2-1);
                }
                else
                {
                    str=line1.substring(index1+1,length);
                }

                //str=line1.substring(index1+1,length);
                int number2=Integer.parseInt(str);
                String tosend="";
                if(number2>0 && number1>0)
                {
                    double x=Math.log(number1);
                    double y=Math.log(number2);
                    double result=y/x;
                    tosend = Double.toString(result);
                }
                else
                {
                    tosend="Please enter valid inputs";
                }

               
               //out.println("haan mil gya i m server 2");
                //String tosend = "haan mil gya i m server 2";
                 
                OutputStream os = clientSocket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                bw.write(tosend);
                System.out.println("Message sent to the client from server 2 is "+tosend);
                bw.flush();
                // int x1 = fileName.lastIndexOf("/");
                // int x2 = fileName.indexOf("/");
/*                if (x1 == x2)
                {
                    fileName = fileName.substring(1);
                }
                File file = new File(fileName);
                int numOfBytes = (int) file.length();
                FileInputStream inFile = new FileInputStream (fileName);
                byte[] fileInBytes = new byte[numOfBytes];
// */                
                // inFile.read(fileInBytes);
/*                outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\n\r\n");
                outToClient.write(fileInBytes, 0, numOfBytes);
*/                clientSocket.close();

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
