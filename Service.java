public class Service extends Entity
{
    public Service (int id, String name){
        setName(name);
        setID(id);
        setIsService(true);
    }
    //epistrfei tis plhrofories antikeimenwn Service

    public String getDetails(){
        return "This is the Service Department";    
    }
}