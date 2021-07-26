package etc.aesop;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

public class ReadData {

    public static void main(String[] args) throws IOException {
        int lineOfTheFirstFable = 1;
        int n = 290;

        String pathname = "aesop/aesop-compressed.bin";
        File file = new File(pathname);
        int size = (int) Files.size(Paths.get(pathname));

        try (InputStream is = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(is);) {

            bis.mark(size + 1); // important

            InputStreamReader in = new InputStreamReader(bis);
            LineNumberReader reader = new LineNumberReader(in);
            reader.readLine();
            while (reader.getLineNumber() < n + lineOfTheFirstFable) {
                reader.readLine();
            }
            String fableData = reader.readLine();
            System.out.println(fableData);

            int offset = Integer.parseInt(fableData.substring(0, 7).trim());
            int length = Integer.parseInt(fableData.substring(9, 16).trim());
            String title = fableData.substring(16);
            System.out.printf("%d %d %s\n", offset, length, title);

            bis.reset(); // important

            int skip = (int) bis.skip(offset);
            //System.out.println("# skip = " + skip);
            int totalSkip = skip;
            while (totalSkip < offset) {
                skip = (int) bis.skip(offset - totalSkip);
                totalSkip += skip;
            } // skip pattern
            System.out.println("# totalSkip = " + totalSkip);

            byte[] bytes = new byte[4096];
            int read = bis.read(bytes);

            ByteArrayInputStream bis2 = new ByteArrayInputStream(bytes, 0, length);
            GZIPInputStream gis = new GZIPInputStream(bis2);

            byte[] bytes2 = new byte[4096];
            int bytesDecompressed = gis.read(bytes2);
            String fableText = new String(bytes2, 0, bytesDecompressed);
            System.out.println(fableText);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
