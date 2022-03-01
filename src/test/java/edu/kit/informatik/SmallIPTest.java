package edu.kit.informatik;

import edu.kit.informatik.exceptions.ParseException;
import edu.kit.informatik.network.IP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SmallIPTest {
    @Test
    public void testIPParser() {
        // All of these are invalid IPs and should throw a ParseException
        tryParsingInvalidIP("");
        tryParsingInvalidIP("0");
        tryParsingInvalidIP("01.1.1.1");
        tryParsingInvalidIP("1.01.1.1");
        tryParsingInvalidIP("1.1.01.01");
        tryParsingInvalidIP("1.1.1.1.");
        tryParsingInvalidIP("1.1..1");
        tryParsingInvalidIP("1a.1.1.1");
        tryParsingInvalidIP("IP");
        tryParsingInvalidIP(".1.1.1.1");
        tryParsingInvalidIP("2147483648.1.1.1");
        tryParsingInvalidIP("257.1.1.1");
        tryParsingInvalidIP("1111.1.1.1");
        tryParsingInvalidIP(".1.1.1.1");
        tryParsingInvalidIP(null);
        tryParsingInvalidIP("koeriLiebeIstWahreLiebe");
        tryParsingInvalidIP("x.x.x.x");
    }

    private static void tryParsingInvalidIP(String ipString) {
        ParseException thrown = assertThrowsExactly(ParseException.class, () -> {
            IP ip = new IP(ipString);
        });
    }
}
