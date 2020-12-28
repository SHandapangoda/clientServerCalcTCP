public class ServerTCP {
    //defining the port
    public static final int port=122;
public void ServerCalc(){
        // Step 1: Establish the socket connection.
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);

            // Step 2: Processing the request.
            Socket s = ss.accept();
            DataOutputStream dos=null;
            DataInputStream dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());



            while (true)
            {
                // wait for input
                String input = dis.readUTF();

                if(input.equals("bye"))
                    break;

                System.out.println("Equation received:-" + input);
                int result;

                // Use StringTokenizer to break the equation into operand and
                // operation
                StringTokenizer st = new StringTokenizer(input);


                int oprnd1 = Integer.parseInt(st.nextToken());
                String operation = st.nextToken();
                int oprnd2 = Integer.parseInt(st.nextToken());

                // perform the required operation.
                if (operation.equals("+"))
                {
                    result = oprnd1 + oprnd2;
                }

                else if (operation.equals("-"))
                {
                    result = oprnd1 - oprnd2;
                }
                else if (operation.equals("*"))
                {
                    result = oprnd1 * oprnd2;
                }
           
                else
                {
                    result = oprnd1 / oprnd2;
                }
                System.out.println("Sending the result...");

                // send the result back to the client.
                dos.writeUTF(Integer.toString(result));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}