package edu.kit.informatik;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IPTest {
    @Test
    void testIpParsing() {
        assertValidIp("0.0.0.0");
        assertValidIp("192.0.2.235");
        assertValidIp("255.255.7.255");
        assertInvalidIp("127.0.0.01");
        assertInvalidIp("127.00.0.1");
        assertInvalidIp("256.1.1.1");
        assertInvalidIp("-2.1.1.1");
        assertInvalidIp(" 1.1.1.1");
        assertInvalidIp("1.1 .1.1");
        assertInvalidIp("0.0.0.0.");
        assertInvalidIp("");
    }

    private void assertValidIp(String ipString) {
        IP ip = assertDoesNotThrow(() -> new IP(ipString));
        assertEquals(ipString, ip.toString());
    }

    private void assertInvalidIp(String ip) {
        assertThrows(ParseException.class, () -> new IP(ip));
    }

    @Test
    void testIpCompare() throws ParseException {
        assertIpOrdering(
            "0.0.0.0",
            "0.0.0.1",
            "0.0.0.187",
            "0.0.255.0",
            "0.1.0.0",
            "0.1.0.1",
            "0.255.0.1",
            "1.0.0.0",
            "5.0.148.204",
            "25.90.225.168",
            "70.126.1.23",
            "85.104.248.225",
            "97.123.104.250",
            "100.66.0.1",
            "123.65.17.0",
            "156.48.45.40",
            "185.123.173.123",
            "212.204.174.105",
            "228.233.182.60",
            "238.182.219.0",
            "255.0.24.98",
            "255.255.255.255"
        );
    }

    private void assertIpOrdering(String... ipStrings) throws ParseException {
        List<IP> ips = new ArrayList<>(ipStrings.length);
        for (String ipString : ipStrings) {
            ips.add(new IP(ipString));
        }
        for (int i = 0; i < ips.size(); ++i) {
            IP ip = ips.get(i);
            assertEquals(ip, ip);
            assertEquals(0, ip.compareTo(ip));
            for (IP lower : ips.subList(0, i)) {
                assertNotEquals(ip, lower);
                assertTrue(lower.compareTo(ip) < 0);
            }
            for (IP higher : ips.subList(i + 1, ips.size())) {
                assertNotEquals(ip, higher);
                assertTrue(higher.compareTo(ip) > 0);
            }
        }
    }
}
