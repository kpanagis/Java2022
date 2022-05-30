public class Requests extends RequestDonationList{
    //exetazei an in isxyei to isValid
    public boolean validRequestDonation(Beneficiary b, double q){
        for (RequestDonation r : getRdEntities()){
            if(!r.isValid(b, q)){
                return false;
            }
        }
        return true;
    }

    //stoxos einai na prosthesei ena antikeimeno sth  RequestDonation
    public void add(Organization or, RequestDonation rd, Beneficiary b){
        double q = 0;
        try{
            if(or.isAvailable(rd)){
                if(validRequestDonation(b, q)){
                    add(rd, or);
                    System.out.println("The Request Has Been Succesfully Submitted To The System.");
                }else throw new LevelException("You are not allowed to ask for this much quantity");
            }
            else throw new OverQuantity("The organization doesn't have the capacity for that.");
        }catch (Exception m) {
            System.out.println(m.getMessage());
        }
    }
    //allazei to quantity enos antikeimenou requestDonation
    public void modify(Organization or, RequestDonation rd, Beneficiary b, double q){
        try{
            if(or.isAvailable(rd)){
                if(validRequestDonation(b, q)){
                    modify(rd, q);
                    System.out.println("The Quantity Of The Requested Item Has Been Changed.");
                }else throw new LevelException("You are not allowed to ask for this much quantity");
            }else throw new OverQuantity("The organization doesn't have the capacity for that ");
        }catch (Exception m){
                System.out.println(m.getMessage());
        }
    }

    //vazei sto receiveidList tou Beneficiary ta antikeimena requestDonation
    public boolean commit(Organization or, Beneficiary b){
        try{
            for(RequestDonation rd : b.getRequestsList().getRdEntities()){
                double q = 0;
                if(or.isAvailable(rd)){
                    if(validRequestDonation(b, q)){
                        for(RequestDonation rdl : or.currentDonations.getRdEntities()){
                            b.addReceived(rd, or);
                            b.getRequestsList().emptyList();
                            if(rdl.getName().equals(rd.getName())){
                                rdl.removeQuantity(rd.getQuantity());
                            }
                        }
                        System.out.println("Your Alrterations Have Been Successfull!");
                        return true;
                    }else throw new LevelException("You are not allowed to ask for this much quantity"); 
                }else throw new OverQuantity("The organization doesn't have the capacity for that"); 
            }return true;
        }catch (Exception m){
            System.out.println(m.getMessage());
            return false;
        }
    }
}