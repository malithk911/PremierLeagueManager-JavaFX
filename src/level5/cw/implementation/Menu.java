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

import java.io.IOException;
import java.util.Scanner;
 
public class Menu {
    protected static PremiereLeagueManager premiereLeagueManager = new PremiereLeagueManager();
    protected static SchoolClubManager schoolManager = new SchoolClubManager();
    protected static UniClubManager uniManager = new UniClubManager();
    protected static int premiereLeagueYear;

    /**
     * Console Menu
     */
    public static void runMenu() throws IOException, ClassNotFoundException {
        boolean exit = false;
        while(!exit){
            try{
System.out.println("\n—————————————————————————————————— PREMIERE LEAGUE ——————————————————————————————————");
                System.out.println("\n-- Choose an option -- \n");
                System.out.println("⬤ Press 1 to add league club.");
                System.out.println("⬤ Press 2 delete league club.");
                System.out.println("⬤ Press 3 view statistics of league club.");
                System.out.println("⬤ Press 4 to display premiere league table.");
                System.out.println("⬤ Press 5 to a played match to league.");
                System.out.println("⬤ Press 6 to add school club.");
                System.out.println("⬤ Press 7 to add university club.");
                System.out.println("⬤ Press 8 to save to file.");
                System.out.println("⬤ Press 9 to load from file.");
                System.out.println("⬤ Press 10 to open GUI.");
                System.out.println("⬤ Press 11 to quit.");

                Scanner keyboardInput = new Scanner (System.in);
                int choice = keyboardInput.nextInt();

                switch(choice){
                    case 1: // get club details

System.out.println("\n—————————————————————————————————— Add a club to league ——————————————————————————————————");

                        System.out.println("\n⯁ Enter club name : ");
                        keyboardInput.nextLine();
                        String clubName = keyboardInput.nextLine().toLowerCase();

                        if(!clubName.isEmpty()){
                            System.out.println("⯁ Enter club country : ");
                            String clubCountry = keyboardInput.nextLine().toLowerCase();

                            if(!clubCountry.isEmpty()){
                                FootballClub newFootballClub = new FootballClub(clubName, clubCountry, 0,
                        0, 0, 0, 0, 0, 0);

                                premiereLeagueManager.add(newFootballClub); // add a football club to league

                            } else {
                                System.out.println("\n⚠ Error: Must enter club country! ⚠");
                                exit = true;
                                runMenu();
                            }
                        } else {
                            System.out.println("\n⚠ Error: Must enter club name! ⚠");
                            exit = true;
                            runMenu();
                        }

                        break;
                    case 2:

System.out.println("\n—————————————————————————————————— Delete a club from league ——————————————————————————————————");
                        System.out.println("\n⯁ Enter club name to delete : ");
                        keyboardInput.nextLine();
                        String deleteClubName = keyboardInput.nextLine().toLowerCase();

                        premiereLeagueManager.delete(deleteClubName); // delete a football club from league

                        break;
                    case 3:

System.out.println("\n—————————————————————————————————— View club statistics ——————————————————————————————————");
                        System.out.println("\n⯁ Enter club name to view statistics : ");
                        keyboardInput.nextLine();
                        String viewClubStats = keyboardInput.nextLine().toLowerCase();

                        premiereLeagueManager.displayStats(viewClubStats); // view stats of a club

                        break;
                    case 4:

                        premiereLeagueManager.displayTable(); // display premiere league table

                        break;
                    case 5:

System.out.println("\n—————————————————————————————————— Add a played match ——————————————————————————————————");
                        System.out.println("\n---- Premiere League " + premiereLeagueManager.getLeagueYear() + " ----");

                            Date dateOfMatch = new Date();
                            System.out.println("\n⯁ Enter date of match (DD.MM.YYYY) : ");
                            String userInputDate = keyboardInput.next();

                            String [] arr = userInputDate.split("\\.", 3);

                            if((Integer.parseInt(arr[0]) > 0 && Integer.parseInt(arr[0]) <= 31) &&
                                    (Integer.parseInt(arr[1]) > 0 && Integer.parseInt(arr[1]) <= 12) &&
                                    (Integer.parseInt(arr[2]) == premiereLeagueManager.getLeagueYear())){
                                dateOfMatch.setDay(Integer.parseInt(arr[0]));
                                dateOfMatch.setMonth(Integer.parseInt(arr[1]));
                                dateOfMatch.setYear(Integer.parseInt(arr[2]));
                            } else {
                                System.out.println("\n⚠ Error: invalid date. Enter a valid date ⚠");
                                break;
                            }
                            System.out.println(dateOfMatch.toString());

                            System.out.println("\n⯁ Enter home club name : ");
                            keyboardInput.nextLine();
                            String homeClubName = keyboardInput.nextLine().toLowerCase();

                            System.out.println("⯁ Enter visiting club name : ");
                            String visitingClubName = keyboardInput.nextLine().toLowerCase();

                            System.out.println("⯁ Enter home club score : ");
                            int homeClubScore = keyboardInput.nextInt();

                            System.out.println("⯁ Enter visiting club score : ");
                            int visitingClubScore = keyboardInput.nextInt();

                            Match match = new Match(dateOfMatch, homeClubName, visitingClubName, homeClubScore,
                                    visitingClubScore);

                            premiereLeagueManager.addPlayedMatch(match);

                        break;
                    case 6:

System.out.println("\n—————————————————————————————————— Add school club ——————————————————————————————————");
                        System.out.println("\n⯁ Enter school club name : ");
                        keyboardInput.nextLine();
                        String schoolClubName = keyboardInput.nextLine().toLowerCase();

                        if(!schoolClubName.isEmpty()) {
                            System.out.println("⯁ Enter school club location : ");
                            String schoolClubLocation = keyboardInput.nextLine().toLowerCase();

                            if (!schoolClubLocation.isEmpty()) {
                                SportsClub schoolClub = new SportsClub(schoolClubName, schoolClubLocation,
                                        0, 0, 0, 0);

                                schoolManager.add(schoolClub);

                            } else {
                                System.out.println("\n⚠ Error: Must enter school location! ⚠");
                                exit = true;
                                runMenu();
                            }
                        } else {
                            System.out.println("\n⚠ Error: Must enter school club name! ⚠");
                            exit = true;
                            runMenu();
                        }

                        break;
                    case 7:

System.out.println("\n—————————————————————————————————— Add university club ——————————————————————————————————");
                        System.out.println("\n⯁ Enter university club name : ");
                        keyboardInput.nextLine();
                        String uniClubName = keyboardInput.nextLine().toLowerCase();

                        if(!uniClubName.isEmpty()) {
                            System.out.println("⯁ Enter university club location : ");
                            String uniClubLocation = keyboardInput.nextLine().toLowerCase();

                            if (!uniClubLocation.isEmpty()) {
                                SportsClub uniClub = new SportsClub(uniClubName, uniClubLocation, 0,
                                        0, 0, 0);

                                uniManager.add(uniClub);

                            } else {
                                System.out.println("\n⚠ Error: Must enter university location! ⚠");
                                exit = true;
                                runMenu();
                            }
                        } else {
                            System.out.println("\n⚠ Error: Must enter university club name! ⚠");
                            exit = true;
                            runMenu();
                        }

                        break;
                    case 8:

                        premiereLeagueManager.saveToFile(); // save league details to file

                        break;
                    case 9:

                        premiereLeagueManager.loadFromFile(); // load league details from file

                        break;
                    case 10:

                        exit = true;
                        Main.window.setScene(Main.menuScene);
                        Main.window.show();

                        break;
                    case 11:

                        exit = true;
                        System.out.println("\n\uD83D\uDD14 Program exiting....");
                        System.exit(0);
                        break;
                }
            }
            catch (Exception e){
                System.out.println("\n⚠ Error! Enter valid input! ⚠");
            }
        }
    }
}
