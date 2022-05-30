public class Main
{
    public static void main (String [] args)
    {
        Organization org = new Organization("myNewOrg");
        //entities
        Entity milk = new Material(1, "Milk" , 5, 5, 5);
        Entity sugar = new Material(2, "Sugar" , 5, 5, 5);
        Entity rice = new Material(3, "Rice" , 5, 5, 5);
        org.addEntity(milk);
        org.addEntity(sugar);
        org.addEntity(rice);
        Entity medicalSupport = new Service(4, "Medical Support");
        Entity nurserySupport = new Service(5, "Nursery Support");
        Entity babysitting = new Service(6, "Babysitting");
        org.addEntity(medicalSupport);
        org.addEntity(nurserySupport);
        org.addEntity(babysitting);
        Admin ad = new Admin(1, "Kostas" ,"6969696969");
        org.setAdmin(ad);
        Beneficiary b1 = new Beneficiary(1, "Makis", "6976767667", 2);
        org.insertBeneficiary(b1);
        Beneficiary b2 = new Beneficiary(2, "Basilis" , "6990878890", 1);
        org.insertBeneficiary(b2);
        Donator d1 = new Donator(1, "Manos", "6902383930");
        org.insertDonator(d1);
        Menu m1 = new Menu();
        m1.start(org, m1);
    }
}