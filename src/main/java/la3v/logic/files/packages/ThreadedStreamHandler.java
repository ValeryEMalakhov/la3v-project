package la3v.logic.files.packages;

import java.io.*;

/**
 * This class is intended to be used with the SystemCommandExecutor
 * class to let users execute system commands from Java applications.
 * <p>
 * This class is based on work that was shared in a JavaWorld article
 * named "When System.exec() won't". That article is available at this
 * url:
 * <p>
 * http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html
 * <p>
 * Documentation for this class is available at this URL:
 * <p>
 * http://devdaily.com/java/java-processbuilder-process-system-exec
 * <p>
 * <p>
 * Copyright 2010 alvin j. alexander, devdaily.com.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * Please ee the following page for the LGPL license:
 * http://www.gnu.org/licenses/lgpl.txt
 */
public class ThreadedStreamHandler extends Thread {
    InputStream inputStream;
    String adminPassword;
    OutputStream outputStream;
    PrintWriter printWriter;
    StringBuilder outputBuffer = new StringBuilder();
    private boolean sudoIsRequested = false;


    ThreadedStreamHandler(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    ThreadedStreamHandler(InputStream inputStream, OutputStream outputStream, String adminPassword) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.printWriter = new PrintWriter(outputStream);
        this.adminPassword = adminPassword;
        this.sudoIsRequested = true;
    }

    public void run() {
        // on mac os x 10.5.x, when i run a 'sudo' command, i need to write
        // the admin password out immediately; that's why this code is
        // here.
        if (sudoIsRequested) {
            //doSleep(500);
            printWriter.println(adminPassword);
            printWriter.flush();
        }

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                outputBuffer.append(line + "\n");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                // ignore this one
            }
        }
    }

    private void doSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public StringBuilder getOutputBuffer() {
        return outputBuffer;
    }

}
