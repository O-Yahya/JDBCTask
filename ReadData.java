import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;

//Class for methods to read data from database
public class ReadData {

    //Method for connecting to postgres database
    public Connection connect(){
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Football", "postgres", "<admin123>");
        }
        catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return c;
    }

    //Method to get all leagues from database and adding them to array list
    public ArrayList<League> getLeagues(){
        ArrayList<League> leagues = new ArrayList<>();
        Connection c = connect();
        try{
            String query = "select * from leagues";
            PreparedStatement p = c.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            while (rs.next()){
                League l = new League(rs.getString(2), rs.getInt(1) );
                leagues.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return leagues;
    }

    //Method to get all players from database and adding them to array list
    public ArrayList<Player> getPlayers(ArrayList<Team> teams){
        ArrayList<Player> players = new ArrayList<>();
        Connection c = connect();
        try{
            String query = "select * from players";
            PreparedStatement p = c.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            while (rs.next()){
                Player player = new Player(rs.getString(2), rs.getInt(3), rs.getInt(4));
                players.add(player);
                for (Team t : teams){
                    if (t.getId() == rs.getInt(5)){
                        t.getPlayers().add(player);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return players;
    }

    //Method to get all teams from database and adding them to array list
    public ArrayList<Team> getTeams(ArrayList<League> leagues){
        ArrayList<Team> teams = new ArrayList<>();
        Connection c = connect();
        try{
            String query = "select * from teams";
            PreparedStatement p = c.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            while (rs.next()){
                Team t = new Team(rs.getString(6), rs.getInt(5), new ArrayList<Player>(), rs.getInt(1), rs.getInt(2), rs.getInt(3));
                teams.add(t);
                for (League l : leagues){
                    if (l.id == rs.getInt(7)){
                        l.teams.add(t);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return teams;
    }

    //Method to get all coaches from database and store them in array list
    public ArrayList<Coach> getCoaches(ArrayList<Team> teams){
        ArrayList<Coach> coaches = new ArrayList<>();
        Connection c = connect();
        try{
            String query = "select * from coaches";
            PreparedStatement p = c.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            while (rs.next()){
                Coach coach = new Coach(rs.getString(3), rs.getInt(1));
                coaches.add(coach);
                for (Team t : teams){
                    if (t.getId() == rs.getInt(2)){
                        t.setCoach(coach);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return coaches;
    }
}
