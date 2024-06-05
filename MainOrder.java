import java.io.*;
import java.util.StringTokenizer ;
import java.util.Scanner ;
import java.text.DecimalFormat;
public class MainOrder
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
            LinkedList list = new LinkedList () ;
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
                list.insertAtFront(data);
            }
            System.out.println("\n***********************************************************************************************************************************************************************");
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\tSWEETYMUNCH STORES\t\t\t\t\t\t");
            System.out.println("\n***********************************************************************************************************************************************************************\n");
            System.out.println("\n===========================================================================ORDER DETAILS=============================================================================\n");
            System.out.println(String.format("%-7s %-17s %-20s %-14s %-16s %-10s %-13s %-6s %-8s %-6s %-8s %-8s %-8s %-8s ","User ID","Name","Address","Number Phone","Item","Flavor","Topping","User","Quantity","Trial-Pack","Pay-Method","Discount","Total","Final Total"));
            list.print();

            System.out.println("\n*********************************************");
            System.out.println("\nNumber Of Order :" + " " + list.size());
            System.out.println("\n*********************************************");

            System.out.println("\n=============================================================================TOP PRODUCT=============================================================================\n");
            int pretzel =0 , ball =0 , jar =0 , cookies =0  ;
            Order current = (Order)list.getFirst ();
            while(current != null)
            {
                if (current.getItemName().equalsIgnoreCase("MINI PRETZEL"))
                {
                    pretzel ++ ;
                }
                else if (current.getItemName().equalsIgnoreCase("COCO BALL"))
                {
                    ball ++ ;
                }
                else if (current.getItemName().equalsIgnoreCase("Coco jar"))
                {
                    jar ++ ;
                }
                else if (current.getItemName().equalsIgnoreCase("COOKIE"))
                {
                    cookies ++ ;
                }
                current = (Order)list.getNext ();
            }

            System.out.println("Pretzel count: " + pretzel);
            System.out.println("Coco Ball count: " + ball);
            System.out.println("Jar count: " + jar);
            System.out.println("Cookies count: " + cookies);

            String highestProduct = "";
            String lowestProduct = "";

            int maxSales = 0;
            int minSales = pretzel;

            if (pretzel > maxSales) {
                maxSales = pretzel;
                highestProduct = "Mini Pretzel ";
            }

            if (ball > maxSales) {
                maxSales = ball;
                highestProduct = "Coco Ball ";
            }

            if (jar > maxSales) {
                maxSales = jar;
                highestProduct = "Coco Jar ";
            }

            if (cookies > maxSales) {
                maxSales = cookies;
                highestProduct = "Cookie ";
            }

            if (pretzel < minSales) {
                minSales = pretzel;
                lowestProduct = "Mini Pretzel ";
            }

            if (ball < minSales) {
                minSales = ball;
                lowestProduct = "Coco Ball ";
            }

            if (jar < minSales) {
                minSales = jar;
                lowestProduct = "Coco Jar ";
            }

            if (cookies < minSales) {
                minSales = cookies;
                lowestProduct = "Cookie ";
            }

            System.out.println("Highest Selling Product: " + highestProduct );
            System.out.println("Lowest Selling Product: " + lowestProduct );

            System.out.println("\n=============================================================================TOP CUSTOMER=============================================================================\n");

            current = (Order)list.getFirst ();
            current = (Order)list.getNext();
            Order highOrder = (Order)list.getFirst ();

            while (current != null)
            {
                if(current.getQuantity() > highOrder.getQuantity())
                    highOrder = current;
                current = (Order)list.getNext(); 
            }

            System.out.println(highOrder.toString());
            
            System.out.println("\n=============================================================================AVERAGE CUSTOMER SPENDING=============================================================================\n");

            double average = 0;
            current = (Order)list.getFirst ();
            while (current != null)
            {
                average += current.calcFinalTotal();
                current = (Order)list.getNext(); 
            }
            average = average / list.size();

            System.out.println("Average Customer Spending : RM " + decimalFormat.format(average));

            System.out.print("\nENTER A- CANCEL ORDER B- CHANGE ADDRESS or X OR ELSE - [EXIT] : ");
            char process = sc.next().charAt(0);

            LinkedList cancelOrder = new LinkedList () ;
            LinkedList temp = new LinkedList () ;

            while (process == 'A' || process == 'a' || process == 'B' || process == 'b')
            {
                if(process == 'A' || process == 'a')
                {
                    System.out.println("\n=============================================================================CANCEL ORDER=============================================================================\n");
 
                    System.out.print("\nENTER CUSTOMER ID THAT WANT TO CANCEL ORDER : ");
                    String id = sc.next();
                    boolean flag = false ;
                    
                    Order obj = null;
                    Order cancel = (Order)list.getFirst ();
                    while(cancel != null)
                    {
                        temp.insertAtBack(cancel);
                        if (cancel.getUserID().equalsIgnoreCase(id))
                        {
                            obj = cancel;
                            cancelOrder.insertAtFront(cancel);
                            flag = true ;
                        }
                        cancel = (Order)list.getNext ();
                    }
                    
                    if(flag)
                    {
                        System.out.println("\nCUSTOMER fOUND ");
                        list.removeAnywhere(obj);
                    }

                    else
                        System.out.println("\nCUSTOMER NOT fOUND ");

                    System.out.println("\n============================================================================= Order List=============================================================================\n");
                    list.print();
                    System.out.println("\n=============================================================================Cancel Order List=============================================================================\n");
                    cancelOrder.print();
                }
                else if(process == 'B' || process == 'b')
                {
                    System.out.println("\n=============================================================================CHANGE ADDRESS=============================================================================\n");
                    System.out.print("ENTER CUSTOMER ID THAT WANT TO CHANGE ADDRESS : ");
                    String id = sc.next();
                    System.out.print("ENTER NEW CUSTOMER ADDRESS : ");
                    String address = sc.next();
                    boolean flag = false ;

                    Order update = (Order)list.getFirst ();
                    while(update != null)
                    {
                        if (update.getUserID().equalsIgnoreCase(id))
                        {
                            update.setAddress(address);
                            flag = true ;
                            System.out.println("\n"+update.toString());
                        }
                        update = (Order)list.getNext ();
                    }

                    if(flag)
                    {}
                    else
                        System.out.println("\nCUSTOMER NOT fOUND ");
                }
                System.out.println("\nSELECT A-[CANCEL ORDER] B-[CHANGE ADDRESS] or X OR ELSE - [EXIT] : ");
                process = sc.next().charAt(0);
            }

            System.out.println("\n=============================================================================Customer list sorting=============================================================================\n");
            list.bubbleSortByid();
            list.print();
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
