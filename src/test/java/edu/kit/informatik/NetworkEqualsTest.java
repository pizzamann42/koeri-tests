package edu.kit.informatik;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static edu.kit.informatik.util.KoeriTestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NetworkEqualsTest {
    @ParameterizedTest
    @MethodSource("equalNetworksProvider")
    void testEquals(Network aSorted, Network aRandom, Network bSorted, Network bRandom) {
        assertEquals(aSorted, aSorted);
        assertBothEquals(aSorted, aRandom);
        assertBothEquals(aSorted, bSorted);
        assertBothEquals(aSorted, bRandom);
        assertEquals(aRandom, aRandom);
        assertBothEquals(aRandom, bSorted);
        assertBothEquals(aRandom, bRandom);
        assertEquals(bSorted, bSorted);
        assertBothEquals(bSorted, bRandom);
        assertEquals(bRandom, bRandom);
    }

    void assertBothEquals(Object o1, Object o2) {
        assertEquals(o1, o2);
        assertEquals(o2, o1);
    }

    static Stream<Arguments> equalNetworksProvider() {
        return IntStream.range(0, 5).mapToObj(NetworkEqualsTest::equalNetworksArgs);
    }

    static Arguments equalNetworksArgs(int networkId) {
        Network aSorted = network(singleLine("network/" + networkId + "/a_sorted"));
        Network aRandom = network(singleLine("network/" + networkId + "/a_random"));
        Network bSorted = network(singleLine("network/" + networkId + "/b_sorted"));
        Network bRandom = network(singleLine("network/" + networkId + "/b_random"));
        return arguments(aSorted, aRandom, bSorted, bRandom);
    }
}
