package de.nikos410.feedibus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineReader implements AutoCloseable{

    private final BufferedReader bufferedStdInReader = new BufferedReader(new InputStreamReader(System.in));

    public String readLine(String prompt) {

        System.out.println(prompt);
        return readLine();
    }

    public String readLine() {

        System.out.print("> ");
        try {
            return bufferedStdInReader.readLine();
        } catch (IOException e) {
            throw new IllegalStateException("Could not read input. Please try again.", e);
        }
    }

    @Override
    public void close() throws IOException {
        bufferedStdInReader.close();
    }
}
