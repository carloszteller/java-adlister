import java.sql.SQLException;

public class DaoFactory {
    private static Ads adsDao;

//    public static Ads getAdsDao() {
//        if (adsDao == null) {
//            adsDao = new ListAdsDao();
//        }
//        return adsDao;
//    }

    public static Ads getAdsDao() throws SQLException {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao();
        }
        return adsDao;
    }
}
