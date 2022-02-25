package edu.kit.informatik;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.Preconditions;

import java.util.Iterator;
import java.util.stream.Stream;

import static edu.kit.informatik.util.KoeriTestUtils.lines;
import static edu.kit.informatik.util.KoeriTestUtils.network;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NetworkAddTest {
    @ParameterizedTest
    @MethodSource("unionProvider")
    void testAdd(String network0, String network1, Network union) {
        assertUnion(network(network0), network(network1), union);
        assertUnion(network(network1), network(network0), union);
    }

    void assertUnion(Network network, Network subnet, Network union) {
        assertTrue(network.add(subnet));
        assertEquals(union, network);
    }

    static Stream<Arguments> unionProvider() {
        return Stream.of(unionArgs("union_0"));
    }

    static Arguments unionArgs(String resource) {
        Iterator<String> it = lines("network/" + resource).iterator();
        Arguments args = arguments(it.next(), it.next(), network(it.next()));
        Preconditions.condition(!it.hasNext(), "Expected 3 lines: two networks and their union");
        return args;
    }
}
