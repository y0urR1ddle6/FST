package factory.insite.server.io;

import factory.insite.server.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class DirectoryHelper {
    public static void listDirectory(final File folder, OutputStream out){
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listDirectory(fileEntry,out);
            } else {
                try {
                    out.write((fileEntry.getAbsolutePath().replace(Configuration.getInstance().getPwd(),"") + "\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
