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

public class SchoolClubManager implements LeagueManager, Serializable {

    /**
     * Method to add a school football club
     * @param sportsClub SchoolFootballClub object
     */
    @Override
    public void add(SportsClub sportsClub) {
        boolean exists = false;
        for (SportsClub item : SCHOOL_CLUBS) {
            if (item.getClubName().equals(sportsClub.getClubName())) {
                exists = true;
                System.out.println("\nClub already exists in School club manager.");
            }
        }
        // check if sportsClub is an instance of FootballClub
        if (sportsClub instanceof SportsClub && !exists) {
            SCHOOL_CLUBS.add(sportsClub);
            System.out.println("\nClub added to School club manager.");
            System.out.println(SCHOOL_CLUBS);
        }
    }

    /**
     * @param sportsClubName sports club name
     */
    @Override
    public void delete(String sportsClubName) {
        boolean found = false;
        for (SportsClub item : SCHOOL_CLUBS) {
            if (item.getClubName().equals(sportsClubName)) {
                SCHOOL_CLUBS.remove(item);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\nClub not found.");
            ;
        }
        System.out.println("\nClub deletion successful.");
        System.out.println(SCHOOL_CLUBS);
    }

    /**
     *
     * @param sportsClubName sports club name
     */
    @Override
    public void displayStats(String sportsClubName) {
        for (SportsClub item : SCHOOL_CLUBS) {
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

    @Override
    public void saveToFile() throws IOException {
        /*
         * Create file object using FileOutputStream
         * Save school club details
         */
        try{
            FileOutputStream schoolFile = new FileOutputStream(new File("C:\\Users\\Malith\\IdeaProjects" +
                    "\\L5_CW_FXapp\\src\\level5\\cw\\implementation\\SchoolClubs.txt"));

            // create object objectOutput from objectOutputStream
            ObjectOutput objectOutput = new ObjectOutputStream(schoolFile);
            objectOutput.writeObject(SCHOOL_CLUBS);

            System.out.println("\nSchool club details saved to file.");
            objectOutput.close();
            schoolFile.close();
        }
        catch (Exception e){
            System.out.println("Error while saving to file");
        }
    }

    @Override
    @SuppressWarnings("Unchecked")
    public void loadFromFile() {
            /*
             * Loading school club details from saved file onto program data structure
             */
        try {
            ArrayList<SportsClub> tempSchoolList;
            FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Malith\\IdeaProjects" +
                    "\\L5_CW_FXapp\\src\\level5\\cw\\implementation\\SchoolClubs.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            tempSchoolList = (ArrayList<SportsClub>) objectInputStream.readObject(); // type casting
            SCHOOL_CLUBS.addAll(tempSchoolList);

            System.out.println(SCHOOL_CLUBS);
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
