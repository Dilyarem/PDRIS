package ru.mipt.report;

import java.io.OutputStream;

interface Report {
    byte[] asBytes();

    void writeTo(OutputStream os);
}
