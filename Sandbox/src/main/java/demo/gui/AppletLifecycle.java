package demo.gui;

import java.awt.Graphics;
import javax.swing.JApplet;

/*
    <applet code="demo.gui.AppletLifecycle" width=400 height=200>
    <param name="param1" value="hoge">
    </applet>
*/

public class AppletLifecycle extends JApplet {
    String str = "";

    @Override
    public void init() {
        str += "init; ";
        System.out.println("init");
        System.out.println(getParameter("param1")); // <param>からパラメータを読み込む
    }

    @Override
    public void start() {
        str += "start; ";
    }

    @Override
    public void stop() {
        str += "stop; ";
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    @Override
    public void update(Graphics g) {
        paint(g);
        System.out.println("update");
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(str, 10, 25);
        System.out.println("paint");
    }
}
