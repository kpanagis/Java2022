abstract public class User
{
    private String name;
    private String phonenumber;
    private int id;

    public String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return phonenumber;
    }

    public void setPhoneNumber(String phonenumber){
        this.phonenumber = phonenumber;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setID(int ID){
        id = ID;
    }

    public int getID(){
        return id;
    }
}