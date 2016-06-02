package com.example.har02.eiexercise;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by har02 on 6/2/2016.
 */
public class FileManagement implements Runnable {
    private BufferedWriter bw;

    public FileManagement() {
        String fileName = "LogData" + System.currentTimeMillis() + ".txt";
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileName);
            file.setWritable(true);
            file.setReadable(true);
            bw = new BufferedWriter(new FileWriter(file));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void writeDataBlocking(String line) {
        try {
            bw.write(line+"/n");
            bw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
