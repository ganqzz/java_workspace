package etc.aesop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class WriteData {

    public static void main(String[] args) throws IOException {
        AesopReader aesopReader = new AesopReader();
        List<Fable> fables = aesopReader.readFable("aesop/aesop.txt");

        System.out.println("# fable = " + fables.size());

        ByteArrayOutputStream aesopBos = new ByteArrayOutputStream();
        OutputStreamWriter out = new OutputStreamWriter(aesopBos);
        PrintWriter printer = new PrintWriter(out);
        printer.println("Aesop's Fables");
        printer.printf("%d\n", fables.size());
        for (Fable fable : fables) {
            printer.printf("%7d %7d %s\n", 0, 0, fable.getTitle());
        }
        printer.flush(); // important
        out.close();
        int textOffset = aesopBos.size();

        System.out.println("# textOffset = " + textOffset);

        //System.out.println(new String(aesopBos.toByteArray()));

        ByteArrayOutputStream textBos = new ByteArrayOutputStream();

        int offset = textOffset;
        List<FableData> fableDatas = new ArrayList<>();
        for (Fable fable : fables) {
            ByteArrayOutputStream fableBos = new ByteArrayOutputStream();
            try (GZIPOutputStream gzipOs = new GZIPOutputStream(fableBos);) {
                gzipOs.write(fable.getText().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            int length = fableBos.size();
            textBos.write(fableBos.toByteArray());

            FableData fableData = new FableData(fable, offset, length);
            offset += length;

            fableDatas.add(fableData);
        }
        textBos.close();

        aesopBos = new ByteArrayOutputStream();
        out = new OutputStreamWriter(aesopBos);
        printer = new PrintWriter(out);
        printer.println("Aesop's Fables");
        printer.printf("%d\n", fables.size());
        for (FableData fableData : fableDatas) {
            printer.printf("%7d %7d %s\n", fableData.getOffset(), fableData.getLength(),
                           fableData.getFable().getTitle());
        }
        printer.flush(); // important
        //out.close();

        System.out.println(new String(aesopBos.toByteArray()));
        System.out.println("# aesop size = " + aesopBos.size());

        aesopBos.write(textBos.toByteArray());
        aesopBos.close();

        File file = new File("aesop/aesop-compressed.bin");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(aesopBos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
