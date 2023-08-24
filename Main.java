import java.util.ArrayList;

public class Main {
    public static void main (String[] args){
        //ReadData object to read all data from database and storing in array lists.
        ReadData reader = new ReadData();
        ArrayList<League> leagues = new ArrayList<>();
        leagues = reader.getLeagues();

        ArrayList<Team> teams = new ArrayList<>();
        teams = reader.getTeams(leagues);

        ArrayList<Player> players = new ArrayList<>();
        players = reader.getPlayers(teams);

        ArrayList<Coach> coaches = new ArrayList<>();
        coaches = reader.getCoaches(teams);

        //Printing all leagues in database
        System.out.println("\nLeagues" + "\n" + "----------------------");
        for (League league : leagues) {
            System.out.println(league.name);
        }
        //Printing all teams in league
        System.out.println("\nTeams" + "\n" + "----------------------");
        for (Team team : teams) {
            System.out.println(team.getName());
        }
        //Printing all players for team
        System.out.println("\nPlayers for " + teams.get(6).getName() + "\n" + "----------------------");
        for (Player player : teams.get(6).getPlayers()) {
            System.out.println(player.getName());
        }
        //Printing all coaches in league
        System.out.println("\nCoaches" + "\n" + "----------------------");
        for (Team team : teams) {
            System.out.println(team.getCoach().getName() + " (" + team.getName() + ")");
        }
        //Printing all info of a player
        Player z = teams.get(2).getPlayers().get(0);
        System.out.println("\n" + z.getName() + "'s information\n" + "----------------------");
        System.out.println("Name: " + z.getName());
        System.out.println("Age: " + z.getAge());
        System.out.println("Kit Number: " + z.getKitNum());
        System.out.println("Team: " + teams.get(2).getName());

        //Choosing random opponents in first league
        League l = leagues.get(0);
        Team [] opponents1 = l.selectTeams();
        Team [] opponents2 = l.selectTeams();
        Team [] opponents3 = l.selectTeams();
        Team [] opponents4 = l.selectTeams();

        //Simulating 4 matches using the random opponents
        Match m = new Match(opponents1[0], opponents1[1], "12:30", "22/8/2023");
        Match m2 = new Match(opponents2[0], opponents2[1], "11:30", "23/8/2023");
        Match m3 = new Match(opponents3[0], opponents3[1], "3:00", "21/8/2023");
        Match m4 = new Match(opponents4[0], opponents4[1], "3:00", "21/8/2023");

        //Updating database with new information
        UpdateData u = new UpdateData();
        u.addMatchAndResult(m);
        u.addMatchAndResult(m2);
        u.addMatchAndResult(m3);
        u.addMatchAndResult(m4);
        u.updateTeams(teams);

        //Printing match results and final league standings
        System.out.println("\nMatch Results and Information\n----------------------------------");
        System.out.println(m.toString());
        System.out.println(m2.toString());
        System.out.println(m3.toString());
        System.out.println(m4.toString());
        System.out.println(l.toString());
    }
}
