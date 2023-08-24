import java.sql.*;
import java.util.ArrayList;

public class UpdateData {

    //Update matches and results database after each randomized match
    public void addMatchAndResult(Match match){
        ReadData x = new ReadData();
        Connection c = x.connect();

        try {
            String time = match.getTime();
            String date = match.getDate();
            int homeID = match.getHome().getId();
            int awayID = match.getAway().getId();
            String query = "INSERT INTO matches (home_id, away_id, time, date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = c.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
                preparedStatement.setInt(1, homeID);
                preparedStatement.setInt(2, awayID);
                preparedStatement.setString(3, time);
                preparedStatement.setString(4, date);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()){
                    match.id = rs.getInt(1);
                }
            }

            int matchID = match.id;
            int winnerID = match.getResult().winner.getId();
            int loserID = match.getResult().loser.getId();
            String score = match.getResult().score;

            query = "INSERT INTO results (winner_id, loser_id, match_id, score) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = c.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
                preparedStatement.setInt(1, winnerID);
                preparedStatement.setInt(2, loserID);
                preparedStatement.setInt(3, matchID);
                preparedStatement.setString(4, score);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()){
                    match.getResult().id = rs.getInt(1);
                }
            }

            query = "UPDATE matches SET result_id = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = c.prepareStatement(query)){
                preparedStatement.setInt(1, match.getResult().id);
                preparedStatement.setInt(2, match.id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to update team record after each simulation in database
    public void updateTeams(ArrayList<Team> teams)  {
        ReadData x = new ReadData();
        Connection c = x.connect();
        for (Team t : teams){
            String query = "UPDATE teams SET wins = ?, losses = ?, draws = ?, points = ? WHERE id = ?;";
            try (PreparedStatement preparedStatement = c.prepareStatement(query)){
                preparedStatement.setInt(1, t.wins);
                preparedStatement.setInt(2, t.losses);
                preparedStatement.setInt(3, t.draws);
                preparedStatement.setInt(4, t.points);
                preparedStatement.setInt(5, t.getId());
                preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
