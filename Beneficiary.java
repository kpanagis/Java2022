class Beneficiary extends User
{
    //default timh noPersons
    private int noPersons = 1;
    public RequestDonationList receivedList = new RequestDonationList();
    public Requests requestsList = new Requests();

    //xaraktiristika Beneficiary
    public Beneficiary(int id, String name, String phone, int person){
        setName(name);
        setPhone(phone);
        setID(id);
        setnoPersons(person);
    }

    //wrappers
    
    public int getnoPersons(){
        return noPersons;
    }

    public void setnoPersons(int p){
        noPersons = p;
    }

    public void addRequest(RequestDonation rd, Organization o){
        requestsList.add(rd, o);
    }

    public void remove(RequestDonation rd){
        requestsList.remove(rd);
    }

    public void addReceived(RequestDonation rd, Organization o){
        receivedList.add(rd, o);
    }

    public RequestDonationList getReceivedList(){
        return receivedList;
    }

    public Requests getRequestsList(){
        return requestsList;
    }


    //periptwsi pou h listRequests einai kenh
    public boolean listRequests(){
        if (requestsList.getRdEntities().isEmpty()){
            System.out.println("The requestsList is empty"); 
            return false;
        }
        for (RequestDonation rd : requestsList.getRdEntities()){
            System.out.println(String.format("ID: %d Name: %s Quantity: %.2f Type: %s", rd.getID(), rd.getName(), rd.getQuantity(), 
            rd.getEntity().isService() ? "Service" : "Material"));
        }
        return true;
    }
}