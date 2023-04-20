import java.util.ArrayList;


public class customer {


    private ArrayList<ArrayList> credential = new ArrayList<ArrayList>();
    private ArrayList<String> usernames = new ArrayList<String>();
    private ArrayList<String> passwords = new ArrayList<String>();
    private ArrayList<String> premiumStatus = new ArrayList<String>();


    /**
     * method to add a user to the user list
     * @param newusr
     * @param newpswrd
     */
    public void adduser(String newusr, String newpswrd){

        usernames.add(newusr);
        passwords.add(newpswrd);
    }

    /**
     * creating 2d array of strings
     * setting user names in passwords
     */

    public customer(){

        usernames.add("banders37");
        usernames.add("jchan29");
        usernames.add("sketch");

        passwords.add("Blue42!");
        passwords.add("Yellow99!");
        passwords.add("june9982");

        premiumStatus.add("yes");
        premiumStatus.add("no");
        premiumStatus.add("yes");

        credential.add(usernames);
        credential.add(passwords);
        credential.add(premiumStatus);

    }
    /**
     * validating credentials of admin
     * @param usrnm
     * @param pswrd
     * @return boolean
     */
    public boolean validateAdmin(String usrnm, String pswrd){

        if (usrnm.equals( "Admin1") && pswrd.equals("Admin@bookstore")){
            return true;
        }
        else {
            return false;
        }
        
    }
    /**
     * validating credentials of user
     * @param usrnm
     * @param pswrd
     * @return boolean
     */
    public boolean validateUser(String usrnm, String pswrd){
        for (int row = 0; row < credential.get(0).size(); row++)
        if (usrnm.equals(credential.get(0).get(row)) && pswrd.equals(credential.get(1).get(row))){
            return true;
        }
        return false;      
    }

    public ArrayList getUsername(){
        return usernames;
    }

    public ArrayList getPremiumStatus(){
        return premiumStatus;
    }

    
}
