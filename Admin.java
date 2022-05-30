public class Admin extends User
{
    private boolean isAdmin = true;

    // Xaraktiristika admin
    public Admin(int id, String name, String phone){
            setName(name);
            setPhone(phone);
            setID(id);

        }
    //Epistrofi analogou uparktou admin
    public boolean getIsAdmin(){
        return isAdmin;
    }

    public String getphone(){
        return getPhone();
    }
}