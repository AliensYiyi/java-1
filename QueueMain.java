import java.io.*;
import java.util.StringTokenizer ;
import java.util.Scanner ;
import java.text.DecimalFormat;
public class QueueMain
{
    public static void main(String[]args) throws IOException
    {
        try
        {
            FileReader fr = new FileReader ("customerr.txt");
            BufferedReader br = new BufferedReader (fr) ;
            DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
            
            Scanner sc = new Scanner (System.in);
            sc.useDelimiter("\n");
            Queue list = new Queue  ();
            Queue temp = new Queue  ();
            Queue cancelOrder = new Queue ();
            Queue cancelTemp = new Queue ();
            String inData = null ;
            while((inData = br.readLine())!= null)
            {
                StringTokenizer st = new StringTokenizer (inData,";");

                String userID = st.nextToken();
                String name = st.nextToken();
                String address = st.nextToken();
                String numberPhone = st.nextToken();
                char item = st.nextToken().charAt(0);
                char flavor = st.nextToken().charAt(0); 
                char topping = st.nextToken().charAt(0);
                boolean newUser = Boolean.parseBoolean(st.nextToken()) ;
                int quantity = Integer.parseInt(st.nextToken()) ;
                boolean trialPack = Boolean.parseBoolean(st.nextToken()) ;
                int paymentMethod = Integer.parseInt(st.nextToken()) ;

                Order data = new Order (userID, name, address, numberPhone, item, flavor, topping, newUser, quantity, trialPack, paymentMethod);
                list.enqueue(data);
            }

            System.out.println("\n***********************************************************************************************************************************************************************");
            System.out.println("\n\t\t\t\t\t\t\t\t\tSWEETYMUNCH STORES\t\t\t\t\t\t");
            System.out.println("\n***********************************************************************************************************************************************************************\n");
            System.out.println("\n===========================================================================ORDER DETAILS=============================================================================\n");
            System.out.println(String.format("%-7s %-17s %-20s %-14s %-16s %-10s %-13s %-6s %-8s %-6s %-8s %-8s %-8s %-8s ","User ID","Name","Address","Number Phone","Item","Flavor","Topping","User","Quantity","Trial-Pack","Pay-Method","Discount","Total","Final Total"));
            
            while(!list.isEmpty())
            {
                Order odr = (Order)list.dequeue();
                System.out.println(odr.toString());

                temp.enqueue(odr);
            }

            while(!temp.isEmpty())
            {
                Order odr = (Order)temp.dequeue();
                list.enqueue(odr);
            }
            
            System.out.println("\n*********************************************");
            System.out.println("\nNumber Of Order :" + " " + list.size());
            System.out.println("\n*********************************************");

            System.out.println("\n=============================================================================TOP PRODUCT=============================================================================\n");
            int pretzel =0 , ball =0 , jar =0 , cookies =0  ;
            while(!list.isEmpty())
            {
                Order odr = (Order)list.dequeue();
                if (odr.getItemName().equalsIgnoreCase("MINI PRETZEL"))
                {
                    pretzel ++ ;
                }
                else if (odr.getItemName().equalsIgnoreCase("COCO BALL"))
                {
                    ball ++ ;
                }
                else if (odr.getItemName().equalsIgnoreCase("Coco jar"))
                {
                    jar ++ ;
                }
                else if (odr.getItemName().equalsIgnoreCase("COOKIE"))
                {
                    cookies ++ ;
                }
                temp.enqueue(odr);
            }

            while(!temp.isEmpty())
            {
                Order odr = (Order)temp.dequeue();
                list.enqueue(odr);
            }

            System.out.println("Pretzel count: " + pretzel);
            System.out.println("Coco Ball count: " + ball);
            System.out.println("Coco Jar count: " + jar);
            System.out.println("Cookies count: " + cookies);

            String highestProduct = "";
            String lowestProduct = "";

            int maxSales = 0;
            int minSales = Integer.MAX_VALUE;

            if (pretzel > maxSales) {
                maxSales = pretzel;
                highestProduct = "Mini Pretzel";
            }

            if (ball > maxSales) {
                maxSales = ball;
                highestProduct = "Coco Ball";
            }

            if (jar > maxSales) {
                maxSales = jar;
                highestProduct = "Coco Jar";
            }

            if (cookies > maxSales) {
                maxSales = cookies;
                highestProduct = "Cookie";
            }

            if (pretzel < minSales) {
                minSales = pretzel;
                lowestProduct = "Mini Pretzel";
            }

            if (ball < minSales) {
                minSales = ball;
                lowestProduct = "Coco Ball";
            }

            if (jar < minSales) {
                minSales = jar;
                lowestProduct = "Coco Jar";
            }

            if (cookies < minSales) {
                minSales = cookies;
                lowestProduct = "Cookie";
            }

            System.out.println("Highest Selling Product: " + highestProduct );
            System.out.println("Lowest Selling Product: " + lowestProduct );

            System.out.println("\n=============================================================================TOP CUSTOMER=============================================================================\n");

            Order highOrder = (Order)list.front ();
            while (!list.isEmpty())
            {
                Order current = (Order)list.dequeue ();
                if(current.getQuantity() > highOrder.getQuantity())
                    highOrder = current;
                temp.enqueue(current);
            }

            while(!temp.isEmpty())
            {
                Order odr = (Order)temp.dequeue();
                list.enqueue(odr);
            }

            System.out.println(highOrder.toString());
             
            System.out.println("\n=============================================================================AVERAGE CUSTOMER SPENDING=============================================================================\n");

            double average = 0;
            while (!list.isEmpty()){
                Order odr = (Order)list.dequeue();
                average += odr.calcFinalTotal();
                temp.enqueue(odr);
            }
            
            while(!temp.isEmpty())
            {
                Order odr = (Order)temp.dequeue();
                list.enqueue(odr);
            }
            average = average / list.size();
            
            System.out.println("Average Customer Spending : RM " + decimalFormat.format(average));
          

            System.out.print("\nENTER A- CANCEL ORDER B- CHANGE ADDRESS or X OR ELSE - [EXIT] : ");
            char process = sc.next().charAt(0);

            //System.out.println("\n============================================================================= CANCEL ORDER =============================================================================\n");

            while (process == 'A' || process == 'a' || process == 'B' || process == 'b')
            {
                if(process == 'A' || process == 'a')
                {
                    System.out.println("\n============================================================================= CANCEL ORDER =============================================================================\n");
                    System.out.print("ENTER CUSTOMER ID THAT WANT TO CANCEL ORDER : ");
                    String id = sc.next();
                    boolean flag = false ;

                    while(!list.isEmpty())
                    {
                        Order cancel = (Order)list.dequeue ();
                        if (cancel.getUserID().equalsIgnoreCase(id))
                        {
                            cancelOrder.enqueue(cancel);
                            flag = true ;
                        }
                        else
                            temp.enqueue(cancel);
                    }

                    if(flag)
                    {
                        System.out.println("\nCUSTOMER fOUND ");
                        System.out.println("\n============================================================================= Order List=============================================================================\n");
                        while(!temp.isEmpty())
                        {
                            Order odr = (Order)temp.dequeue ();
                            System.out.println(odr.toString());
                            list.enqueue(odr);
                        }

                        System.out.println("\n=============================================================================Cancel Order List=============================================================================\n");
                        while(!cancelOrder.isEmpty())
                        {
                            Order odr = (Order)cancelOrder.dequeue ();
                            System.out.println(odr.toString());
                            cancelTemp.enqueue(odr);
                        }
                    }
                    else
                        System.out.println("\nCUSTOMER not fOUND ");

                    while(!cancelTemp.isEmpty())
                    {
                        Order odr = (Order)cancelTemp.dequeue();
                        cancelOrder.enqueue(odr);
                    }

                }
                else if(process == 'B' || process == 'b')
                {
                    System.out.println("\n=============================================================================CHANGE ADDRESS=============================================================================\n");
                    System.out.print("ENTER CUSTOMER ID THAT WANT TO CHANGE ADDRESS : ");
                    String custId = sc.next();
                    boolean flag = false ;
                    Order update = (Order)temp.front();

                    while(!list.isEmpty())
                    {
                        Order current = (Order)list.dequeue ();
                        if (current.getUserID().equalsIgnoreCase(custId))
                        {
                            flag = true ;
                            System.out.print("ENTER NEW cUSTOMER ADDRESS : ");
                            String address = sc.next();
                            current.setAddress(address);
                            update = current ;
                        }
                        temp.enqueue(current);
                    }

                    if(flag)
                        System.out.println("\n"+update.toString());
                    else
                        System.out.println("\nCUSTOMER not fOUND ");

                    while(!temp.isEmpty())
                    {
                        Order odr = (Order)temp.dequeue();
                        list.enqueue(odr);
                    }

                }
                System.out.print("\nENTER A- CANCEL ORDER B- CHANGE ADDRESS or X OR ELSE - [EXIT] : ");
                process = sc.next().charAt(0);
            }

            list.bubleSortId();
            System.out.println("\n=============================================================================Customer list sorting=============================================================================\n");
            while(!list.isEmpty())
            {
                Order odr = (Order)list.dequeue ();
                System.out.println(odr.toString());
                temp.enqueue(odr);
            }

            while(!temp.isEmpty())
            {
                Order odr = (Order)temp.dequeue();
                list.enqueue(odr);
            }

        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        catch(IOException io)
        {
            System.out.println(io.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}