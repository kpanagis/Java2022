abstract public class Entity 
{
   private String name;
   private int id;
   private String description;
   public boolean isService;

   // Ploirofories ths dwreas
   public String getEntityInfo()
    {  
        return "Name is: " + this.name + " Description is: " + this.description + " Id is: " + id;
    }

    //abstract method getDetails opou thn metatrepoume diaforetika se alles klaseis
   abstract String getDetails();
   //kalw tis alles duo parapanw sinartiseis
   public String to_string()
    {
        return "The Info Of the Entity: " + this.getEntityInfo() + "The Details of the Material OR Service: " + this.getDetails();
    }

       public int getID()
   {
       return id; 
   }
   public void setID(int ID)
   {
        id = ID;
   }
   public void setName(String name1)
   {
       name = name1;
   }
   public String getName()
   {
        return name;   
   }

   public boolean isService()
    {
       return isService;
    }
       
   public void setIsService(boolean x)
   {
       isService = x;
   }

   public double getLevelA()
   {
       return 0;
   }
    public double getLevelB()
   {
       return 0;
   }
    public double getLevelC()
   {
       return 0;
   }
}
