import models.Config;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.Driver;

public class MySQLAdsDao implements Ads {
    private Connection connection;

    public MySQLAdsDao() throws SQLException {
        Config config = new Config();
        DriverManager.registerDriver(new Driver());
        this.connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );
    }

    @Override
    public List<Ad> all() {
        List<Ad> allAds = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from ads");

            while(rs.next()) {
                allAds.add(
                    new Ad(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description")
                    )
                );
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }

        return allAds;
    }

    @Override
    public Long insert(Ad ad) {
        long newAdId = 0;

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(
                String.format("insert into ads (user_id, title, description) values (%d, '%s', '%s')",
                    // ad.getUserId(),
                    1,
                    ad.getTitle(),
                    ad.getDescription()
                ), statement.RETURN_GENERATED_KEYS
            );

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                newAdId = rs.getLong(1);
            }

            if(newAdId != 0) {
                ad.setId(newAdId);
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }

        return newAdId;
    }
}