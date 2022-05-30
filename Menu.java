import java.util.*;
public class Menu{
    private String phonenumber;
    private String name;
    public Menu(){}

    //Enarxh tou menu
    public void start(Organization or, Menu mn){
        System.out.println("Welcome to the organization! ");
        System.out.println("Please enter your phone number: ");
        Scanner scanner = new Scanner(System.in);
        phonenumber = scanner.nextLine();
        try{Long.parseLong(phonenumber);}
        catch (Exception e){
            System.out.println("The Number was not correct. The program will now end functioning.");
            System.exit(0);
        }
        String answer;
        Beneficiary t1 = or.getBeneficiary(phonenumber);
        Donator s1 = or.getDonator(phonenumber);


        //exetazei an einai meros tis organosis mesw ths .equals vlepontas etsi ta stoixeia pou dinontai me tou admin
        if (phonenumber.equals(or.getAdmin().getphonenumber())){
            System.out.println("Welcome Admin! \n" 
                + "Username: " + or.getAdmin().getName() + "\n"
                + "Phone Number: " + or.getAdmin().getPhoneNumber() + "\n"
                + "You are part of the Organization " + or.getOrgName());
                adminMenu (or, mn);
        }


        //exetazei kai emfanizei ta stoixeia tou Beneficiary
        else if (t1 != null){
            System.out.println("Welcome Beneficiary! \n" 
                + "Username: " + t1.getName() + "\n"
                + "Phone Number: " + t1.getPhoneNumber() + "\n"
                + "Number Of The Family Members: " + t1.getnoPersons() + "\n"
                + "You are part of the Organization " + or.getOrgName());
                beneficiaryMenu(t1, or, mn, false);
        }


        //exetazei episis kai emfanizei ta stoixeia tou donator
        else if (s1 != null){
            System.out.println("Welcome Donator! \n" 
                + "Username: " + s1.getName() + "\n"
                + "Phone Number: " + s1.getPhoneNumber() + "\n"
                + "You are part of the Organization " + or.getOrgName());
                mn.donatorMenu(s1, or, mn, false);
        }

        //an meta ton elegxo den anhkei se kanena xrhsth tote emfanizei analogo prooidopoitiko mhnyma
        else{
            System.out.println ("This user has not be registered");
            System.out.println ("Please type 'yes' to continue and create an account or 'no' to end the program.");
            try{
                answer = scanner.nextLine();
                if (answer.equals("yes"))
                {
                    mn.signUp(or, mn);
                }
                else if (answer.equals("no"))
                {
                    System.exit(0);
                    scanner.close();
                }else throw new InvalidCharacterException("This is not a valid option. The program will now end functioning.");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        scanner.close();
    }


    //Pragmatopoihsh eggrafhs
    public void signUp(Organization or, Menu mn){
        String select;
        int person = 1;
        int id2 = 2;

        // dinoume eisodo ta stoixeia gia eggrafi
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Input your Name: ");
        name = scanner.nextLine();
        System.out.println("Please Input your Phone Number: ");
        phonenumber = scanner.nextLine();
        try{Long.parseLong(phonenumber);}
        catch (Exception e){
            System.out.println("The Number was not correct. The program will now end functioning.");
            scanner.close();
            return;
        }

        //Epilogh kathgorias xrhsth, Donator h Beneficiary
        System.out.println("Type '1' If you want to create a Donator Account or Type '2' If you want to create a Beneficiary Account");
        select = scanner.nextLine();
        try{

            //epilogh Donator
            if(select.equals("1")){
                int id = 2;
                Donator d = new Donator(id++, name, phonenumber);
                or.insertDonator(d);
                System.out.println("Welcome Donator! \n" 
                    + "Username: " + name + "\n"
                    + "Phone Number: " + phonenumber + "\n"
                    + "You are part of the Organization " + or.getOrgName());
                    mn.donatorMenu(d, or, mn, true);
            }
            //epilogh Beneficiary
            else if(select.equals("2")){
                System.out.println("Please enter the Number of Members of Your Family: ");
                person = scanner.nextInt();
                Beneficiary b = new Beneficiary((id2++), name, phonenumber, person);
                or.insertBeneficiary(b);
                System.out.println("Welcome Beneficiary! \n"
                    + "Username: " + name + "\n"
                    + "Phone Number: " + phonenumber + "\n"
                    + "The Number Of Family Members are: " + person + "\n"
                    + "You are part of the Organization " + or.getOrgName());
                    beneficiaryMenu(b, or, mn, true);
            }

            //analogo exception se periptwsi pou den dwsei epilogh
            else{
                throw new InvalidValueException("Invalid Option. Exiting the program...");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            scanner.close();
            System.exit(0);
        }
    }
    //leitourgies pou borei na epilexei enas Donator
    public void donatorMenu(Donator d, Organization or, Menu mn, boolean gt){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Given Options: ");
        System.out.println("1) Add an Offer \n" + 
        "2) Show Offers \n" + 
        "3) Commit \n" +
        "4) Back \n" +
        "5) Exit \n"); 
        "6) Logout \n" +
        select = scanner.nextInt();

        //me auton ton tropo bazw oria sto ti borei na epilexei na kanei sto menu o Donator
        while (select < 1 || select > 6){
            System.out.println("Please Select A Given Option: ");
            select = scanner.nextInt();
        }
        switch (select){
            case 1:
                addOffers(d, or);
                break;
            case 2:
                showOffers(d, or);
                break;
            case 3:
                d.getOffersList().commit(or);
                System.out.println("Your Changes Were Succesfully Saved!");
            break;

            case 4:
                if(gt) signUp(or, mn); else mn.start(or, mn);
            break;

            case 5:
              System.exit(0);
            break;
            default :
              
            case 6:
              System.out.println("Logging out.Goodbye! ");
                start(or, mn);
            break;
 
        }
        donatorMenu(d, or, mn, gt);
        scanner.close();
    }
    //Antistoixa leitourgies pou borei na epilexei enas Beneficiary
    public void beneficiaryMenu(Beneficiary b, Organization or, Menu mn, boolean gt){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Given Options: ");
        System.out.println("1) Add a Request \n" + 
        "2) Show Requests \n" + 
        "3) Commit \n" +
        "4) Back \n" +
        "5) Exit \n"); 
        "6) Logout \n" +
        select = scanner.nextInt();

        //idia logikh gia oria
        while (select < 1 || select > 6){
            System.out.println("Please Select A Given Option: ");
            select = scanner.nextInt();
        }
        switch (select){
            case 1:
                addRequest(b, or);
                break;

            case 2:
                showRequest(b, or);
                break;
            case 3:
            b.requestsList.commit(or, b);
            break;

            case 4:
                if(gt) signUp(or, mn); else mn.start(or, mn);
            break;

           case 5:
              System.exit(0);
            break;
            default :
              
            case 6:
              System.out.println("Logging out. Goodbye! ");
                start(or, mn);
            break;
 
        }
        mn.beneficiaryMenu(b, or, mn, gt);
        scanner.close();
    }


    //Leitourgies Admin
    public void adminMenu (Organization or, Menu mn){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Given Options: ");
        System.out.println("1) View \n" + 
        "2) Monitor Organization \n" + 
        "3) Back \n" +
        "4) Exit \n"); 
        "5) Logout \n" +
        select = scanner.nextInt();
        while (select < 1 || select > 6){
            System.out.println("Please Select A Given Option: ");
            select = scanner.nextInt();
        }
        switch (select){
            case 1:
                viewEntities(or);
            break;

            case 2:
                monitorOrg(or);
            break;

            case 3:
                start(or, mn);
            break;
    
            case 4:
              System.exit(0);
            break;
            default :
              
            case 5:
              System.out.println("Logging out.Goodbye! ");
                start(or, mn);
            break;
        }
        adminMenu(or, mn);
        scanner.close();
    }


    //prosthiki dwreas apo ton Donator
    public void addOffers(Donator d, Organization or){
        int id = 0;
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        int select1 = 0;
        String select2;
        double amount = 0;
        double hours = 0;
        System.out.println("Please Select One Of The Given Options: ");
        System.out.println("1) Materials 2) Services ");
        select1 = scanner1.nextInt();
        while (select1 < 1 || select1 > 2){
            System.out.println("Please Select A Given Option: ");
            select1 = scanner1.nextInt();
        }

        //gia na epilexei Material
        if(select1 == 1){
            or.listMaterials();
            System.out.println("Input the ID of the Material You Want to Donate: ");
            id = scanner1.nextInt();
            try{
                if(id <= 0){throw new InvalidValueException("There are no Materials With this ID");}
            System.out.println("Input the Amount You Want to Donate: ");
            amount = scanner1.nextDouble();
                if(amount <= 0){
                    throw new InvalidValueException("You Cannot enter this value.It has to be more than 0!");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Confirm the Donation (yes/no): ");
            select2 = scanner2.nextLine();


            //dhmiourgeitai antikeimeno requestDonation gia na prostethei sth lista GetOffersList
            if (select2.equals("yes")){
                RequestDonation req = new RequestDonation(or.getEntityById(id), amount);
                d.getOffersList().add(req, or);
                System.out.println("The Donation was submitted succesfully.");
            }else System.out.println("The Donation failed.");
        }
        //gia na epilexei Service
        if(select1 == 2){
            or.listServices();
            System.out.println("Input the ID of the Service You Want to Give: ");
            try{
                id = scanner1.nextInt();
                if(id <= 0){
                    throw new InvalidValueException("There are no Services With this ID");
                }
            System.out.println("Input the Amount of hours You Want to Help: ");
            hours = scanner1.nextDouble();
                if(hours <= 0){
                    throw new InvalidValueException("You Cannot enter this value.It has to be more than 0!");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Confirm the Donation (yes/no): ");
            select2 = scanner2.nextLine();
            if (select2.equals("yes")){
                System.out.println("The Donation was submitted succesfully.");
                RequestDonation req = new RequestDonation(or.getEntityById(id), hours);
                d.getOffersList().add(req, or);
            }else System.out.println("The Donation failed.");
        }
    }


    //Mesw ths addRequest epilegw me 1 an thelw Material h me 2 an thelw na Service
    public void addRequest(Beneficiary b, Organization or){
        int id = 0;
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        int select1 = 0;
        String select2;
        double amount = 0;
        double hours = 0;
        System.out.println("Please Select One Of The Given Options: ");
        System.out.println("1) Materials 2) Services ");
        select1 = scanner1.nextInt();
        while (select1 < 1 || select1 > 2){
            System.out.println("Please Select A Given Option: ");
            select1 = scanner1.nextInt();
        }

        //epilogh gia Material
        if(select1 == 1){
            or.listMaterials();
            System.out.println("Input the ID of Materials You Want to Request: ");
            try{
                id = scanner1.nextInt();
                if(id <= 0){
                    throw new InvalidValueException("There Are no Materials With this ID");
                }
            System.out.println("Input the Amount You Want to Request: ");
                amount = scanner1.nextDouble();
                if(amount <= 0){
                    throw new InvalidValueException("You Cannot enter this value.It has to be more than 0!");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Confirm the Request (yes/no): ");
            select2 = scanner2.nextLine();
            if (select2.equals("yes")){
                RequestDonation req = new RequestDonation(or.getEntityById(id), amount);
                b.getRequestsList().add(or, req, b);
            }else System.out.println("The Request failed.");
        }

        //epilogh gia Service
        if(select1 == 2){
            or.listServices();
            System.out.println("Input the ID of the Service You Want to Request: ");
            try{
                id = scanner1.nextInt();
                if(id <= 0){
                    throw new InvalidValueException("There are no Services With this ID");
                }
                System.out.println("Input the Amount You Want to Request: ");
                hours = scanner1.nextDouble();
                if(hours <= 0){
                    throw new InvalidValueException("You Cannot enter this value.It has to be more than 0!");
                }
            }catch (Exception e){
            System.out.println(e.getMessage());
            return;
            }
            System.out.println("Confirm the Request (yes/no): ");
            select2 = scanner2.nextLine();
            if (select2.equals("yes")){
                RequestDonation req = new RequestDonation(or.getEntityById(id), hours);
                b.getRequestsList().add(or, req, b);
            }else System.out.println("The Request was failed.");
        }
    }

    //An o xristis epilexei 2 kaleitai h showOffers
    public void showOffers(Donator d, Organization or){
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        int select3 = 0;
        String select4;
        String select5;
        double q = 0;
        boolean l = d.listOffers();
        if(!l) return; //an h listOffers einai kanh tote epistrefei sto menu tou Donator


        System.out.println("Please Select One Of The Given Options: \n" +
        "a) Edit or Delete a Donation \n" +
        "b) Delete All Your Donations \n" +
        "c) Commit ");
        select4 = scanner.nextLine();

        //epexergasia mia dwreas
        if(select4.equals("a")){
            System.out.println("Please Select One Of The Donations by the ID: ");
            try{
                select3 = scanner1.nextInt();
                if(select3 <= 0){
                    throw new InvalidValueException("There are no Donations With this ID");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Please type 'd' for Delete or 'e' For Edit: ");
            select5 = scanner2.nextLine();

            //diagrafh dwreas
            if (select5.equals("d")){
                d.getOffersList().removeById(select3);
                System.out.println("The Selected Donations Have Been Successfully Deleted.");

            }

            //epexergasia dwreas
            else if (select5.equals("e")){
                System.out.println("Please Enter the New Quantity You Wish to Donate: ");
                q = scanner2.nextDouble();
                d.getOffersList().modify(d.getOffersList().get(select3), q);
                System.out.println("The Quantity of The Donated Item Has been Succesfully Modified.");
            }else System.out.println("Ivalid Edit. Returning to the Main Menu!");
        }

        //diagrafh olwn twn dwrewn p exei kanei
        else if (select4.equals("b")){
            d.getOffersList().reset();
        }
        //telikh upovolh
        else if (select4.equals("c")){
            d.getOffersList().commit(or);
            System.out.println("Your Changes Have Been Successfully submitted! ");
        }
    }

    public void showRequest(Beneficiary b, Organization or){
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        int select3 = 0;
        String select4;
        String select5;
        double q = 0;
        boolean l = b.listRequests();
        if(!l) return;
        System.out.println("Please Select One Of The Given Options: \n" +
        "a) Edit or Delete a Request \n" +
        "b) Delete All Your Requests \n" +
        "c) Commit ");
        select4 = scanner.nextLine();
        if(select4.equals("a")){
            System.out.println("Please Select One Of The Requests by its ID: ");
            try{
                select3 = scanner1.nextInt();
                if(select3 <= 0){
                    throw new InvalidValueException("There is not a Request With this ID");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Please Press d for Delete or e For Edit: ");
            select5 = scanner2.nextLine();
            if (select5.equals("d")){
                b.getRequestsList().removeById(select3);
                System.out.println("The Selected Request Has Been Deleted.");
            }
            else if (select5.equals("e")){
                System.out.println("Please Enter the New Quantity You Wish to Receive: ");
                q = scanner2.nextDouble();
                b.requestsList.modify(or, b.getRequestsList().get(select3), b, q);
            }else System.out.println("Invalid Option. Returning to Main Menu.");
        }
        else if (select4.equals("b")){
            System.out.println("Your Request Has Been Succesfully Deleted");
            b.getRequestsList().reset();
        }
        else if (select4.equals("c")){
            b.requestsList.commit(or, b);
        }
    }

    //Epilogh tou xristi an thelei na epilexei Material me 1 h Service me 2
    public void viewEntities(Organization or){
        int select1 = 0;
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println("1) Materials 2) Services ");
        select1 = scanner1.nextInt();
        while (select1 < 1 || select1 > 2){
            System.out.println("Please Select A Valid Option: ");
            select1 = scanner1.nextInt();
        }
        if(select1 == 1){
            or.listOrgMat();
        }
        if(select1 == 2){
            or.listOrgSer();
        }
    }

    //Mesw auth emfanizontai oi listes analoga me thn epilogh tou xrhsth 1 h 2 h 3 antistoixa Beneficiary h Donator h katharismos listas
    public void monitorOrg(Organization or){
        int select = 0;
        int id = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println(
        "1) List Beneficiaries \n" + 
        "2) List Donators \n" + 
        "3) Reset Beneficiaries Lists \n");
        select = scanner.nextInt();
        while (select < 1 || select > 3){
            System.out.println("Please Select A Valid Option: ");
            select = scanner.nextInt();
        }
        switch (select){

            //elegxei an h lista twn Beneficiary einai kenh kai exei proidopoitiko mhnyma
        case 1:
            or.listBeneficiaries();
            if(or.beneficiaryList.isEmpty()){
                System.out.println("There Aren't Any Beneficiaries In The Organization");
                break;
            }
            else{
            System.out.println("Please Select One Beneficiary from the list above: ");
            id = scanner.nextInt();
            listBen(or, or.getBeneficiaryById(id));
            break;
            }


            //elegxei an h lista twn Donator einai kenh kai exei proidopoitiko mhnyma

            case 2:
        if(or.donatorList.isEmpty()){
            System.out.println("There Aren't Any Donators In The Organization");
            break;
        }
        else{
            or.listDonators();
            System.out.println("Please Select One Donator from the list above: ");
            id = scanner.nextInt();
            listDon(or, or.getDonatorById(id));
            break;
        }

        //katharizei thn lista twn beneficiary me reset method
        case 3:
        for(Beneficiary b : or.beneficiaryList){
            b.getReceivedList().reset();
        }
        System.out.println("All the Received Lists of All the Beneficiaries Are Cleared!");
            break;

        default:
        }
    }


    //An den einai adeia h listbeneficiary tote kaleitai h listBen pou emfanizei thn lista me tous Beneficiaries
    public void listBen(Organization or, Beneficiary b){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println(
        "1) View ReceivedList \n" + 
        "2) Clear ReceivedList  \n" + 
        "3) Remove Beneficiary \n");
        select = scanner.nextInt();
        while (select < 1 || select > 3){
            System.out.println("Please Select A Valid Option: ");
            select = scanner.nextInt();
        }
        switch (select){
        case 1:
            if(b.getReceivedList().getRdEntities().isEmpty()){
                System.out.println("This Beneficiary Hasn't Requested Any Material/Service");
            }else{
            for(int i=0; i<b.getReceivedList().getRdEntities().size(); i++){
                System.out.println(String.format("ID: %d Name: %s Quantity %.2f" 
                , b.getReceivedList().getRdEntities().get(i).getID()
                , b.getReceivedList().getRdEntities().get(i).getEntity().getName() 
                , b.getReceivedList().getRdEntities().get(i).getQuantity()));
            }
        }
            break;

        case 2:
            b.getReceivedList().reset();
            System.out.println(String.format("The Received List of the Beneficiary %s has been emptied ", b.getName()));
            break;

        case 3:
            or.removeBeneficiary(b);
            System.out.println(String.format("The Beneficiary with Name %s has been removed from the Organization ", b.getName()));
            break;

        default:
        }
    }


    //An den einai adeia h listDonator tote kaleitai h listDon pou emfanizei thn lista me tous Donators

    public void listDon(Organization or, Donator d){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println(
        "1) View Ongoing Offers \n" + 
        "2) Remove Donator \n");
        select = scanner.nextInt();
        while (select < 1 || select > 3){
            System.out.println("Please Select A Valid Option: ");
            select = scanner.nextInt();
        }
        if(select == 1 ){
            if(d.getOffersList().getRdEntities().isEmpty()){
                System.out.println("This Donator Is Not About To Offer Any Material/Service");
            }
            else{
                for(int i=0; i<d.getOffersList().getRdEntities().size(); i++){
                System.out.println(String.format("ID: %d Name: %s Quantity %.2f" 
                , d.getOffersList().getRdEntities().get(i).getID()
                , d.getOffersList().getRdEntities().get(i).getEntity().getName() 
                , d.getOffersList().getRdEntities().get(i).getQuantity()));
                }
            }
        }
        else if (select == 2){
            or.removeDonator(d);
            System.out.println(String.format("The Donator with Name %s has been removed from the Organization ", d.getName()));
        }
    }
}