package com.masayukikaburagi.controller;

import com.masayukikaburagi.model.db.DbManager;
import com.masayukikaburagi.model.entity.Loclog;
import com.masayukikaburagi.model.DataAccessException;
import com.masayukikaburagi.model.dao.LoclogDao;
import com.masayukikaburagi.model.dao.LoclogRdbDao;
import com.masayukikaburagi.model.validation.LoclogValidator;
import com.masayukikaburagi.util.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Map;

/**
 * Loclog Controller
 *
 * @author 鏑木雅之
 */
public class LoclogController {

    private LoclogDao dao;

    /**
     * Constructor
     * テスト用
     */
    public LoclogController() {
        dao = new LoclogRdbDao(DbManager.getInstance());
    }

    /**
     * Constructor
     *
     * @param sctx
     */
    public LoclogController(ServletContext sctx) {
        // RDBMS
        dao = new LoclogRdbDao((DbManager) sctx.getAttribute("DbManager"));
    }

    /**
     * GET Loclogs list
     *
     * @param userName
     * @param page
     * @return
     */
    public String getLoclogsList(String userName, int page, int per) {
        if (page < 1) page = 1;
        int offset = (page - 1) * per;
        ArrayList<Loclog> loclogsList = dao.getLoclogsList(userName, offset, per);
        int total = dao.getLoclogsCountByName(userName);

        // JSON変換
        JSONObject jo = new JSONObject();
        jo.put("total", total)
          .put("page", page)
          .put("count", loclogsList.size())
          .put("list", new JSONArray(loclogsList));

        return jo.toString();
    }

    /**
     * GET Loclogs total count
     *
     * @param userName
     * @return
     */
    public String getLoclogsTotal(String userName) {
        int total = dao.getLoclogsCountByName(userName);

        // JSON変換
        return new JSONObject().put("total", total).toString();
    }

    /**
     * GET last modified time
     *
     * @param userName
     * @return
     */
    public String getLastMtime(String userName) {
        long mtime = dao.getLastMtime(userName);

        // JSON変換
        return new JSONObject().put("mtime", mtime).toString();
    }

    /**
     * GET Loclog details
     *
     * @param userName
     * @param logId
     * @return Loclog JSON String<br>
     * null: Not found
     */
    public String getLoclogDetails(String userName, int logId) {
        Loclog loclog = dao.getLoclogById(userName, logId);
        if (loclog == null) {
            // Not found
            return null;
        }

        // JSON変換
        return new JSONObject(loclog).toString();
    }

    /**
     * GET Loclog image by JSON String
     *
     * @param userName
     * @param logId
     * @return Loclog JSON String<br>
     * null: Not found
     */
    public String getLoclogImage(String userName, int logId) {
        String image = dao.getLoclogImageById(userName, logId);
        if (image == null) {
            // Not found
            return null;
        }

        // JSON変換
        return new JSONObject().put("image", image).toString();
    }

    /**
     * GET Loclog image binary
     *
     * @param userName
     * @param logId
     * @return Loclog<br>
     * null: Not found
     */
    public byte[] getLoclogImage2(String userName, int logId) {
        String image = dao.getLoclogImageById(userName, logId);
        if (image == null) {
            // Not found
            return null;
        }

        // Base64 decode
        return Base64.decode(image, Base64.DEFAULT);
    }

    /**
     * GET Loclog search
     *
     * @param userName
     * @param json
     * @return
     */
    public String searchLoclog(String userName, String json) {
        return null;
    }

    /**
     * CREATE Loclog
     *
     * @param userName
     * @param json
     * @return
     */
    public String createLoclog(String userName, String json) {
        Loclog loclog = new Loclog();
        JSONObject errorsJson = new JSONObject();

        // JSON parse
        try {
            JSONObject jsonObj = new JSONObject(json);
            loclog.setUserName(userName); // 他人のを作成できないようにする
            loclog.setTag(jsonObj.optString("tag", ""));
            loclog.setAddress(jsonObj.optString("address", ""));
            loclog.setLatitude(jsonObj.optDouble("latitude")); // パースできなかったら、NaN
            loclog.setLongitude(jsonObj.optDouble("longitude")); // パースできなかったら、NaN
            loclog.setFixTime(jsonObj.optLong("fixTime")); // パースできなかったら、0
            loclog.setNote(jsonObj.optString("note", ""));
            loclog.setImageUri(jsonObj.optString("imageUri", ""));
            loclog.setImage(jsonObj.optString("image", ""));
            loclog.setOpen(jsonObj.optBoolean("open", false));

        } catch (JSONException e) {
            e.printStackTrace();
            return errorsJson.put("kind", "request")
                             .put("message", "JSON parse error: " + e.getMessage())
                             .toString();
        }

        // validation
        Map<String, Object> errors = LoclogValidator.validate(loclog);
        if (!errors.isEmpty()) {
            // error
            return errorsJson.put("kind", "request")
                             .put("errors", errors)
                             .toString();
        }

        // create
        try {
            if (dao.createLoclog(loclog)) {
                // success
                return null;
            }
            // not created
            return errorsJson.put("kind", "creation")
                             .put("message", "failed creation")
                             .toString();

        } catch (DataAccessException e) {
            e.printStackTrace();
            return errorsJson.put("kind", "server")
                             .put("message", "data access error: ")
                             .toString();

        }
    }

    /**
     * UPDATE the Loclog
     *
     * @param userName
     * @param json
     * @return
     */
    public String updateLoclog(String userName, String json) {
        Loclog loclog = new Loclog();
        JSONObject errorsJson = new JSONObject();

        // JSON parse
        try {
            JSONObject jsonObj = new JSONObject(json);
            loclog.setUserName(userName); // 他人のを更新できないようにする
            loclog.setLogId(jsonObj.optInt("logId")); // 0のとき、Not foundになる => エラー扱い
            loclog.setTag(jsonObj.optString("tag", null)); // null: 更新なし
            loclog.setNote(jsonObj.optString("note", null)); // null: 更新なし
            loclog.setImageUri(jsonObj.optString("imageUri", null)); // null: 更新なし
            loclog.setImage(jsonObj.optString("image", null)); // null: 更新なし
            loclog.setOpen(jsonObj.optBoolean("open", false));

        } catch (JSONException e) {
            e.printStackTrace();
            return errorsJson.put("kind", "request")
                             .put("message", "JSON parse error: " + e.getMessage())
                             .toString();
        }

        // validation
        Map<String, Object> errors = LoclogValidator.validate(loclog);
        if (!errors.isEmpty()) {
            // error
            return errorsJson.put("kind", "request")
                             .put("errors", errors)
                             .toString();
        }

        // update
        try {
            if (dao.updateLoclog(loclog)) {
                // success
                return null;
            }
            // not updated
            return errorsJson.put("kind", "update")
                             .put("message", "failed update")
                             .toString();

        } catch (DataAccessException e) {
            e.printStackTrace();
            return errorsJson.put("kind", "server")
                             .put("message", "data access error: ")
                             .toString();

        }

    }

    /**
     * DELETE the Loclog
     *
     * @param userName
     * @param logId
     * @return
     */
    public boolean deleteLoclogById(String userName, int logId) {
        try {
            return dao.deleteLoclogById(userName, logId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Export ALl data
     *
     * @return
     */
    public String export() {
        ArrayList<Loclog> loclogsList = dao.export();

        // JSON変換
        JSONArray jo = new JSONArray(loclogsList);

        return jo.toString();
    }
}
