class Donator extends User
{
    private Offers offerslist = new Offers();


    //xaraktiristika Donator
    public Donator(int id, String name, String phonenumber){
        setName(name);
        setPhone(phonenumber);
        setID(id);
    }

    public void add(RequestDonation rd, Organization o){
        offerslist.add(rd, o);
    }
    
       public Offers getOfferslist(){
        return offerslist;
    }

    public void remove(RequestDonation rd){
        offerslist.remove(rd);
    }

    //periptwsi pou listOffers() einai kenh
    public boolean listOffers(){
        if (offerslist.getRdEntities().isEmpty()){
            System.out.println("The offerslist is empty"); 
            return false;
        }
        for (RequestDonation rd : offerslist.getRdEntities()){
            System.out.println(String.format("ID: %d Name: %s Quantity: %.2f Type: %s", rd.getID(), rd.getName(), rd.getQuantity(), 
            rd.getEntity().isService() ? "Service" : "Material"));
        }
        return true;
    }
}
