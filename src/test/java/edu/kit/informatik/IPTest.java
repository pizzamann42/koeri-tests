package edu.kit.informatik;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static edu.kit.informatik.KoeriTestUtils.ip;
import static edu.kit.informatik.KoeriTestUtils.reader;
import static org.junit.jupiter.api.Assertions.*;

public class IPTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/ip/valid")
    void testValidIpParsing(String validIp) {
        assertEquals(validIp, ip(validIp).toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("invalidIpProvider")
    void testInvalidIpParsing(String invalidIp) {
        assertThrows(ParseException.class, () -> new IP(invalidIp));
    }

    @ParameterizedTest
    @MethodSource("sortedIpProvider")
    void testIpCompare(Stream<String> ipStrings) {
        List<IP> ips = ipStrings.map(KoeriTestUtils::ip).collect(Collectors.toList());
        int idx = 0;
        for (IP ip : ips) {
            assertEquals(ip, ip);
            assertEquals(0, ip.compareTo(ip));
            for (IP lower : ips.subList(0, idx)) {
                assertNotEquals(ip, lower);
                assertTrue(lower.compareTo(ip) < 0);
            }
            for (IP higher : ips.subList(idx + 1, ips.size())) {
                assertNotEquals(ip, higher);
                assertTrue(higher.compareTo(ip) > 0);
            }
            ++idx;
        }
    }

    // Don't use CSV source to allow all characters except newline
    static Stream<String> invalidIpProvider() {
        return reader("ip/invalid").lines();
    }

    static List<Stream<String>> sortedIpProvider() {
        return List.of(reader("ip/sorted_0").lines(), reader("ip/sorted_1").lines());
    }
}
