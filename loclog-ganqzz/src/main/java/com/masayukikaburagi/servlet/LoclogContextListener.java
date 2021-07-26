package com.masayukikaburagi.servlet;

import com.masayukikaburagi.model.db.DbInfo;
import com.masayukikaburagi.model.db.DbManager;
import com.masayukikaburagi.model.db.HerokuPgDbInfo;
import com.masayukikaburagi.model.db.LocalPgDbInfo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class LoclogContextListener
 */
@WebListener
public class LoclogContextListener implements ServletContextListener {

    private DbManager dbm;

    /**
     * Default constructor.
     */
    public LoclogContextListener() {
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // 後始末
        if (dbm != null) {
            dbm.closeConnection();
            dbm = null;
        }
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext sc = arg0.getServletContext();
        // DbInfo（DB設定情報）
        // ここでは簡易的に環境変数の有無で、Herokuかどうかを判断
        DbInfo dbi = System.getenv("DATABASE_URL") == null ? new LocalPgDbInfo()
                                                           : new HerokuPgDbInfo();

        // DbManager
        dbm = DbManager.getInstance(dbi);
        sc.setAttribute("DbManager", dbm);
    }
}
