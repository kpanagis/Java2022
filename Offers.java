public class Offers extends RequestDonationList{

    //prosthetei sthn lista currentDonations ta antikeimena tis listas rdEntities enw parallila ta afairei apo thn rdEntities
    public void commit(Organization o){
        int i = getRdEntities().size();
        while (i != 0){
            o.currentDonations.add(getRdEntities().get(i-1), o);
            getRdEntities().remove(i-1);
            i -= 1;
        }
    }
}