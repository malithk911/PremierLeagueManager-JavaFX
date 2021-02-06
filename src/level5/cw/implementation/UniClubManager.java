/*
 * Name : Malith Kulathilake
 * IIT number : 2018412
 * UOW number : w1761910
 *
 * I confirm that I understand what plagiarism /
    collusion / contract cheating is and have read and
    understood the section on Assessment Offences in the
    Essential Information for Students. The work that I
    have submitted is entirely my own. Any work from
    other authors is duly referenced and acknowledged.
 */

package level5.cw.implementation;

import java.io.*;
import java.util.ArrayList;

public class UniClubManager implements LeagueManager, Serializable{

    /**
     * Method to add a University football club
     * @param sportsClub UniversityFootballClub object
     */
    @Override
    public void add(SportsClub sportsClub) {
        boolean exists = false;
        for (SportsClub item : UNI_CLUBS) {
            if (item.getClubName().equals(sportsClub.getClubName())) {
                exists = true;
                System.out.println("\nClub already exists in university club manager.");
            }
        }
        // check if sportsClub is an instance of FootballClub
        if (sportsClub instanceof SportsClub && !exists) {
            UNI_CLUBS.add(sportsClub);
            System.out.println("\nClub added to university club manager.");
            System.out.println(UNI_CLUBS);
        }
    }

    /**
     *
     * @param sportsClubName sports club name
     */
    @Override
    public void delete(String sportsClubName) {
        boolean found = false;
        for (SportsClub item : UNI_CLUBS) {
            if (item.getClubName().equals(sportsClubName)) {
                UNI_CLUBS.remove(item);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\nClub not found.");
        }
        System.out.println("\nClub deletion successful.");
        System.out.println(UNI_CLUBS);
    }

    /**
     *
     * @param sportsClubName sports club name
     */
    @Override
    public void displayStats(String sportsClubName) {
        for (SportsClub item : UNI_CLUBS) {
            if (item.getClubName().equals(sportsClubName)) {
                System.out.println("\n" + item.toString());
            } else {
                System.out.println("\nClub does not exist.");
            }
        }
    }

    @Override
    public void displayTable() {

    }

    @Override
    public void addPlayedMatch(Match match) {

    }

    /**
     *
     */
    @Override
    public void saveToFile() {
        /*
         * Create file object using FileOutputStream
         * Save university club details
         */
        try{
            FileOutputStream schoolFile = new FileOutputStream("C:\\Users\\Malith\\IdeaProjects" +
                    "\\L5_CW_FXapp\\src\\level5\\cw\\implementation\\UniClubs.txt");

            // create object objectOutput from objectOutputStream
            ObjectOutput objectOutput = new ObjectOutputStream(schoolFile);
            objectOutput.writeObject(UNI_CLUBS);

            System.out.println("\nUniversity club details saved to file.");
            objectOutput.close();
            schoolFile.close();
        }
        catch (Exception e){
            System.out.println("Error while saving to file");
        }
    }

    /**
     *
     */
    @Override
    @SuppressWarnings("Unchecked")
    public void loadFromFile() {
        /*
         * Loading university club details from saved file onto program data structure
         */
        try {
            ArrayList<SportsClub> tempUniList;
            FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Malith\\IdeaProjects" +
                    "\\L5_CW_FXapp\\src\\level5\\cw\\implementation\\UniClubs.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            tempUniList = (ArrayList<SportsClub>) objectInputStream.readObject(); // type casting
            UNI_CLUBS.addAll(tempUniList);

            System.out.println(UNI_CLUBS);
            objectInputStream.close();
            inputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (EOFException e) {
            System.out.println("End of stream reached unexpectedly.");
        } catch (Exception e) {
            System.out.println("Error while loading file.");
        }
    }
}
