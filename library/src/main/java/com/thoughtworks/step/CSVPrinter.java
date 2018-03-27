package com.thoughtworks.step;

import java.io.FileWriter;
import java.io.IOException;

public class CSVPrinter {
    private final FileWriter fileWriter;
    private final String headers;
    private final String LINE_SEPARATOR = "\n";
    private static final String COMMA_SEPARATOR = ",";

    public CSVPrinter(FileWriter fileWriter, String headers) {
        this.fileWriter = fileWriter;
        this.headers = headers;
    }

    public void writeHeaders() throws IOException {
        fileWriter.append(headers);
        fileWriter.append(LINE_SEPARATOR);
    }

    public void writeTransactions(Transaction transaction) throws IOException {
        fileWriter.append(transaction.getDate().toString());
        fileWriter.append(COMMA_SEPARATOR);
        fileWriter.append(String.valueOf(transaction.getAmount()));
        fileWriter.append(COMMA_SEPARATOR);
        fileWriter.append(transaction.getSource());
        fileWriter.append(LINE_SEPARATOR);
    }

    public void close() throws IOException {
        fileWriter.flush();
        fileWriter.close();
    }
}
