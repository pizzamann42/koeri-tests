package edu.kit.informatik.network;

import edu.kit.informatik.parsing.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class IPTestMNT {

    private BufferedReader getFileReader(String name) {
        try {
            URL file = getClass().getClassLoader().getResource(name);
            return new BufferedReader(new FileReader(new File(file.toURI())));
        } catch (URISyntaxException | NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
            fail();
            return null;
        }
    }

    @Test
    void testToString() {
        getFileReader("ip_list.txt").lines().forEach(line -> {
            try {
                Assertions.assertEquals(line, (new IP(line)).toString());
            } catch (ParseException e) {
                fail();
            }
        });
    }

    @Test
    void compareTo() {
        IP[] ips = getFileReader("ip_list_sorted.txt").lines().map(pointNotation -> {
            try {
                return new IP(pointNotation);
            } catch (ParseException e) {
                e.printStackTrace();
                fail();
                return null;
            }
        }).toArray(IP[]::new);
        for (int i = 0; i < ips.length; i++) {
            for (int j = 0; j < ips.length; j++) {
                int res = ips[i].compareTo(ips[j]);
                assertEquals(res, Integer.compare(i, j));
            }
        }
    }
}