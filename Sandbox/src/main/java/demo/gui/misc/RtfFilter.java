package demo.gui.misc;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class RtfFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return false;
        }

        /* 拡張子を取り出す */
        String ext = "";
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }

        if (ext.equals("rtf")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "RTF Only";
    }
}
