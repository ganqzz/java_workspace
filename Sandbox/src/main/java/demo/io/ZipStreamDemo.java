package demo.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipStreamDemo {

    public static final String ZIP_FILE = "./temp/zipdemo.zip";
    public static final String DEST_DIR = "./temp/zipdemo";

    public static void main(String[] args) {

        // 1 Zip
        zipIt(ZIP_FILE,
              new File("./temp/1.dat"),
              new File("./temp/2.dat"),
              new File("./temp/3.dat"));

        // 2 Unzip
        unZipIt(ZIP_FILE, DEST_DIR);
    }

    // Zip
    public static void zipIt(String zipfile, File... files) {
        try {
            // create byte buffer
            byte[] buffer = new byte[1024];

            // create object of ZipOutputStream from FileOutputStream
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipfile));

            for (File file : files) {
                System.out.println("Adding: " + file);
                // create object of FileInputStream for source file
                FileInputStream fin = new FileInputStream(file);

                // begins writing a new Zip entry to the zip file
                zout.putNextEntry(new ZipEntry(file.getName()));

                // After creating entry in the zip file, actually write the contents
                int length;
                while ((length = fin.read(buffer)) > 0) {
                    zout.write(buffer, 0, length);
                }

                // close the current entry and position the stream to write the next entry.
                zout.closeEntry();
                // close the InputStream
                fin.close();
            }
            // close the ZipOutputStream
            zout.close();
            System.out.println("Zip file has been created!");
        } catch (IOException ioe) {
            System.out.println("IOException :" + ioe);
        }
    }

    // Unzip file
    public static void unZipIt(String zipfile, String dest) {
        byte[] buffer = new byte[1024];

        try {
            // create output directory is not exists
            File folder = new File(dest);
            if (!folder.exists()) {
                folder.mkdir();
            }

            // get the zip file content
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipfile));
            // get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {
                File newFile = new File(dest + File.separator + ze.getName());

                System.out.println("Unzipping : " + newFile.getAbsoluteFile());

                // create all non exists folders
                // else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();

            System.out.println("Done");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
