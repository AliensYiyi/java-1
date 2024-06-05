import java.text.DecimalFormat;
public class Order
{
    private String userID;
    private String name;
    private String address;
    private String numberPhone;
    private char item ; //  A - Golden mini pretzel B - Coco ball crunch C - Coco jar D - cookie
    private char flavor;  //  A - Caramel B - Matcha C - Milk chocolate D – Tiramisu
    private char topping; //  A - Oreo crunch B - KitKat Stick C - Original D - Milo Sprinkle
    private boolean newUser;//true or false
    private int quantity;   
    private boolean trialPack;// true or false
    private int paymentMethod;// 1-Online Banking 2-Cash on Delivery
    private double price;
    DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
    
    public Order(String userID,String name,String address,String numberPhone,char item,char flavor,char topping,boolean newUser,int quantity,boolean trialPack,int paymentMethod)
    {
        this.userID = userID;
        this.name = name;
        this.address = address;
        this.numberPhone = numberPhone;
        this.item = item;
        this.flavor = flavor;
        this.topping = topping;
        this.newUser = newUser;
        this.quantity = quantity;
        this.trialPack = trialPack;
        this.paymentMethod = paymentMethod;
    }
    
    public void setUserID(String userID) {this.userID = userID;}
    public void setName(String name) {this.name = name;}
    public void setAddress(String address) {this.address = address;}
    public void setNumberPhone(String numberPhone) {this.numberPhone = numberPhone;}
    public void setItem(char item) {this.item = item;}
    public void setFlavour(char flavour) {this.flavor = flavor;}
    public void setTopping(char topping) {this.topping = topping;}
    public void setNewUser(boolean newUser) {this.newUser = newUser;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setTrialPack(boolean trialPack){this.trialPack = trialPack;}
    public void setPaymentMethod(int paymentMethod) {this.paymentMethod = paymentMethod;}
    
    public String getUserID() {return userID;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getNumberPhone() {return numberPhone;}
    public char getItem() {return item;}
    public char getFlavour() {return flavor;}
    public char getTopping() {return topping;}
    public boolean getNewUser() {return newUser;}
    public int getQuantity() {return quantity;}
    public boolean getTrialPack(){return trialPack;}
    public int getPaymentMethod() {return paymentMethod;}
    
    public String getTopingName () 
    {
        String topingName = null;
        
        if (topping == 'A' || topping == 'a')
        {
            topingName = "OREO CRUNCH" ;
        }
        else if (topping == 'B' || topping == 'b')
        {
            topingName = "KITKAT STICK" ;
        }
        else if (topping == 'C' || topping == 'c')
        {
            topingName = "ORIGINAL" ;
        }
        else if (topping == 'D' || topping == 'd')
        {
            topingName = "MILO SPRINKLE" ;
        }
        return topingName ;
    }
    
    public String getFlavorName () 
    {
        String flavourName = null;
        
        if (flavor == 'A' || flavor == 'a')
        {
            flavourName = "CARAMEL" ;
        }
        else if (flavor == 'B' || flavor == 'b')
        {
            flavourName = "MATCHA" ;
        }
        else if (flavor == 'C' || flavor == 'c')
        {
            flavourName = "CHOCOLATE" ;
        }
        else if (flavor == 'D' || flavor == 'd')
        {
            flavourName = "TIRAMISU" ;
        }
        return flavourName ;
    }
    
    public String getItemName ()
    {
        String itemName = null;
        
        if (item == 'A' || item == 'a')
        {
            itemName = "MINI PRETZEL" ;
        }
        else if (item == 'B' || item == 'b')
        {
            itemName = "COCO BALL" ;
        }
        else if (item == 'C' || item == 'c')
        {
            itemName = "COCO JAR" ;
        }
        else if (item == 'D' || item == 'd')
        {
            itemName = "COOKIE" ;
        }
        return itemName ;
    }
     
    public double discount()
    {
        double discount = 0 ;
        if (newUser)
        {
            discount = (calcTotal()*0.15) ; 
        }
        return discount ;
    }
    
    public double calcTotal()
    {
        double price = 0 ;
        
        if (price == 0)
        {
                if (item == 'A' || item == 'a')
            {
                price = 15 * quantity;
            }
            else if (item == 'B' || item == 'b')
            {
                price = 13 * quantity;
            }
            else if (item == 'C' || item == 'c')
            {
                price = 13 * quantity;
            }
            else if (item == 'D' || item == 'd')
            {
                price = 15 * quantity ;
            }
            
            if (trialPack)
            {
                price = price / 2 ;
            }
        }
    
        return price ;
    }
    
    public double calcFinalTotal()
    {
        double finalTotal = 0 ;
        
        finalTotal = calcTotal() - discount () ;
        
        return finalTotal ;
    }
    
    public String toString() {
        return String.format("%-7s %-17s %-20s %-14s %-16s %-10s %-13s %-8s %-10s %-10s %-8s %-8s %-8s %-8s ",
        userID, name, address, numberPhone, getItemName(), getFlavorName(), getTopingName(),
        newUser, quantity, trialPack, paymentMethod, decimalFormat.format(calcTotal()), decimalFormat.format(discount()),decimalFormat.format(calcFinalTotal()));
    }
    
    public String print()
    {
        return "User ID: "+userID+
               "\nName: "+name+
               "\nAddress: "+address+
               "\nPhone Number: "+numberPhone+
               "\nItem: "+getItemName()+
               "\nFlavor: "+getFlavorName()+
               "\nTopping: "+getTopingName()+
               "\nNew User: "+newUser+
               "\nQuantity: "+quantity+
               "\nTrial Pack: "+trialPack+
               "\nPayment Method: "+paymentMethod+
               "\nTotal: "+calcTotal()+
               "\nDiscount: "+discount()+
               "\nFinal Total: "+calcFinalTotal();
    }

    
    public String custInformation()
    {
        return String.format ("%-12s %-15s %-20s %-15s" , userID , name , address , numberPhone) ; 
    }
    
    public String custOrder ()
    {
          return "s" ;  
    }
}
