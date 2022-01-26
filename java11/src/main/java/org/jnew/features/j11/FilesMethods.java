package org.jnew.features.j11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesMethods {

    public static void main(String[] args) throws IOException {
        // readString
        var singleText = Files.readString(Paths.get(""));
        System.out.println(singleText);

        // writeString
        var emptyText = "     ";
        System.out.println("Are you blank? = " + emptyText.isEmpty());

    }

}
