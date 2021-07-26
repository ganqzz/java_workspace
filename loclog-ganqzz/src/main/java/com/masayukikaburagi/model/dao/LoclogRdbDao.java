package com.masayukikaburagi.model.dao;

import com.masayukikaburagi.model.DataAccessException;
import com.masayukikaburagi.model.db.DbManager;
import com.masayukikaburagi.model.db.queries.LoclogQueries;
import com.masayukikaburagi.model.entity.Loclog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Loclog RDBMS汎用（JDBC） DAO
 *
 * @author 鏑木雅之
 */
public class LoclogRdbDao implements LoclogDao {

    private DbManager dbm; // DbManagerインスタンス
    private LoclogQueries llq; // Loclogモデル用クエリ

    /**
     * Constructor
     *
     * @param dbm
     */
    public LoclogRdbDao(DbManager dbm) {
        this.dbm = dbm;
        llq = dbm.getDbi().getLlq();
    }

    /**
     * Loclogs List with range
     *
     * @param userName
     * @param offset
     * @param limit
     * @return loclogsList
     */
    @Override
    public ArrayList<Loclog> getLoclogsList(String userName, int offset, int limit) {
        String query = llq.getLoclogsList();
        ArrayList<Loclog> loclogsList = new ArrayList<Loclog>();

        try (Connection conn = dbm.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setString(1, userName); // 他人のを読めないようにする
            stmt.setInt(2, limit);
            stmt.setInt(3, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                // TODO: NULLの扱いが面倒
                while (rs.next()) {
                    Loclog loclog = new Loclog();
                    loclog.setLogId(rs.getInt("log_id"));
                    loclog.setTag(rs.getString("tag"));
                    loclog.setAddress(rs.getString("address"));
                    loclog.setImageUri(rs.getString("image_uri"));
                    loclog.setOpen(rs.getBoolean("open"));
                    loclog.setUpdatedAt(rs.getTimestamp("updated_at").getTime());
                    loclogsList.add(loclog);
                }
            }
        } catch (Exception e) {
            // log it
            e.printStackTrace();
            return null;
        }

        return loclogsList;
    }

    /**
     * GET Loclogs count
     *
     * @param userName
     * @return
     */
    @Override
    public int getLoclogsCountByName(String userName) {
        String query = llq.getLoclogsCount();
        int count = 0;

        try (Connection conn = dbm.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setString(1, userName); // 他人のを読めないようにする

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) count = rs.getInt(1);
            }
        } catch (Exception e) {
            // log it
            e.printStackTrace();
        }

        return count;
    }

    /**
     * GET last modified time
     *
     * @param userName
     * @return
     */
    @Override
    public long getLastMtime(String userName) {
        String query = llq.getLastMtime();
        long mtime = 0;

        try (Connection conn = dbm.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setString(1, userName); // 他人のを読めないようにする

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) mtime = rs.getLong(1);
            }
        } catch (Exception e) {
            // log it
            e.printStackTrace();
        }

        return mtime;
    }

    /**
     * GET the Loclog
     *
     * @param logId
     * @return loclog
     */
    @Override
    public Loclog getLoclogById(String userName, int logId) {
        String query = llq.getLoclogById();
        Loclog loclog = new Loclog();

        try (Connection conn = dbm.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setString(1, userName); // 他人のを読めないようにする
            stmt.setInt(2, logId);

            try (ResultSet rs = stmt.executeQuery()) {

                // TODO: NULLの扱いが面倒
                if (rs.next()) {
                    loclog.setLogId(rs.getInt("log_id"));
                    //loclog.setUserName(rs.getString("user_name"));
                    loclog.setTag(rs.getString("tag"));
                    loclog.setAddress(rs.getString("address"));
                    loclog.setLatitude(rs.getDouble("latitude"));
                    loclog.setLongitude(rs.getDouble("longitude"));
                    loclog.setFixTime(rs.getLong("fix_time"));
                    loclog.setNote(rs.getString("note"));
                    loclog.setImageUri(rs.getString("image_uri"));
                    //loclog.setImage(rs.getString("image"));
                    loclog.setOpen(rs.getBoolean("open"));
                    loclog.setCreatedAt(rs.getTimestamp("created_at").getTime());
                    loclog.setUpdatedAt(rs.getTimestamp("updated_at").getTime());

                    return loclog;
                } else {
                    // not found
                    return null;
                }
            }
        } catch (Exception e) {
            // log it
            e.printStackTrace();
            return null;
        }
    }

    /**
     * GET the Loclog Image
     *
     * @param logId
     * @return String Loclog Image String
     */
    @Override
    public String getLoclogImageById(String userName, int logId) {
        String query = llq.getLoclogImageById();
        String image = null;

        try (Connection conn = dbm.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setString(1, userName); // 他人のを読めないようにする
            stmt.setInt(2, logId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) image = rs.getString(1);
            }
        } catch (Exception e) {
            // log it
            e.printStackTrace();
        }

        return image;
    }

    /**
     * CREATE a Loclog
     *
     * @param loclog
     * @return success or not
     */
    @Override
    public boolean createLoclog(Loclog loclog) throws DataAccessException {
        String query = llq.createLoclog();

        try (Connection conn = dbm.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                int cnt = 1;
                stmt.setString(cnt, loclog.getUserName()); // nullにはならない。
                stmt.setString(++cnt, loclog.getTag());
                stmt.setString(++cnt, loclog.getAddress());
                stmt.setDouble(++cnt, loclog.getLatitude());
                stmt.setDouble(++cnt, loclog.getLongitude());
                stmt.setLong(++cnt, loclog.getFixTime());
                stmt.setString(++cnt, loclog.getNote());
                stmt.setString(++cnt, loclog.getImageUri());
                stmt.setString(++cnt, loclog.getImage());
                stmt.setBoolean(++cnt, loclog.getOpen());
                int rs = stmt.executeUpdate();

                if (rs == 0) {
                    // not created
                    conn.rollback();
                    return false;
                } else {
                    conn.commit();
                    return true;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw new Exception(e);
            }
        } catch (Exception e) {
            // log it
            e.printStackTrace();
            throw new DataAccessException(e);
        }
    }

    /**
     * UPDATE the Loclog
     */
    @Override
    public boolean updateLoclog(Loclog loclog) throws DataAccessException {
        LinkedHashMap<String, String> param = new LinkedHashMap<>();
        if (loclog.getTag() != null) {
            param.put("tag", loclog.getTag());
        }
        if (loclog.getNote() != null) {
            param.put("note", loclog.getNote());
        }
        if (loclog.getImageUri() != null) {
            param.put("image_uri", loclog.getImageUri());
        }
        if (loclog.getImage() != null) {
            param.put("image", loclog.getImage());
        }

        String query = llq.updateLoclog(param);

        try (Connection conn = dbm.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                int cnt = 1;
                for (String key : param.keySet()) {
                    stmt.setString(cnt++, param.get(key));
                }
                stmt.setBoolean(cnt++, loclog.getOpen());
                stmt.setString(cnt++, loclog.getUserName()); // 他人のを更新できないようにする。nullにはならない。
                stmt.setInt(cnt, loclog.getLogId()); // nullにはならない。
                int rs = stmt.executeUpdate();

                if (rs == 1) {
                    conn.commit();
                    return true;
                } else {
                    // not found or multi updated
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw new Exception(e);
            }
        } catch (Exception e) {
            // log it
            e.printStackTrace();
            throw new DataAccessException(e);
        }
    }

    /**
     * DELETE the Loclog
     */
    @Override
    public boolean deleteLoclogById(String userName, int logId) throws DataAccessException {
        String query = llq.deleteLoclog();

        try (Connection conn = dbm.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, userName); // 他人のを読めないようにする
                stmt.setInt(2, logId);

                int rs = stmt.executeUpdate();
                if (rs == 1) {
                    conn.commit();
                    return true;
                } else {
                    // not found or multi deleted
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw new Exception(e);
            }
        } catch (Exception e) {
            // log it
            e.printStackTrace();
            throw new DataAccessException(e);
        }
    }

    /**
     * Export
     * ユーザ制限なし
     *
     * @return
     */
    @Override
    public ArrayList<Loclog> export() {
        String query = llq.exportLoclogDb();
        ArrayList<Loclog> loclogsList = new ArrayList<>();

        try (Connection conn = dbm.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Loclog loclog = new Loclog();
                    loclog.setLogId(rs.getInt("log_id"));
                    loclog.setUserName(rs.getString("user_name"));
                    loclog.setTag(rs.getString("tag"));
                    loclog.setAddress(rs.getString("address"));
                    loclog.setLatitude(rs.getDouble("latitude"));
                    loclog.setLongitude(rs.getDouble("longitude"));
                    loclog.setFixTime(rs.getLong("fix_time"));
                    loclog.setNote(rs.getString("note"));
                    loclog.setImageUri(rs.getString("image_uri"));
                    loclog.setImage(rs.getString("image"));
                    loclog.setThumbnail(rs.getString("thumbnail"));
                    loclog.setOpen(rs.getBoolean("open"));
                    loclog.setCreatedAt(rs.getTimestamp("created_at").getTime());
                    loclog.setUpdatedAt(rs.getTimestamp("updated_at").getTime());
                    loclogsList.add(loclog);
                }
            }
        } catch (Exception e) {
            // log it
            e.printStackTrace();
            return null;
        }

        return loclogsList;
    }
}
