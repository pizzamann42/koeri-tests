package edu.kit.informatik;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static edu.kit.informatik.util.KoeriTestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NetworkGetRouteTest {
    @ParameterizedTest
    @MethodSource("validArgsProvider")
    void testValidRoute(Network network, IP start, IP end, List<IP> route) {
        assertEquals(route, network.getRoute(start, end));
    }

    @ParameterizedTest
    @MethodSource("invalidArgsProvider")
    void testInvalidRoute(Network network, IP start, IP end) {
        // Don't use `isEmpty` for better error messages
        assertEquals(List.of(), network.getRoute(start, end));
    }

    static Stream<Arguments> validArgsProvider() {
        Network network = network(SMALL_NET);
        return Stream.of(
            arguments(
                network,
                ip("85.193.148.81"),
                ip("122.117.67.158"),
                ips("85.193.148.81", "141.255.1.133", "122.117.67.158")
            ),
            arguments(
                network,
                ip("122.117.67.158"),
                ip("39.20.222.120"),
                ips("122.117.67.158", "141.255.1.133", "85.193.148.81", "231.189.0.127", "39.20.222.120")
            )
        );
    }

    static Stream<Arguments> invalidArgsProvider() {
        Network network = network(SMALL_NET);
        network.add(network("(1.1.1.1 2.2.2.2)"));
        return Stream.of(
            arguments(network, null, null),
            arguments(network, ip("141.255.1.133"), null),
            arguments(network, null, ip("141.255.1.133")),
            // one missing
            arguments(network, ip("141.255.1.133"), ip("0.0.0.0")),
            arguments(network, ip("0.0.0.0"), ip("141.255.1.133")),
            // same vertex
            arguments(network, ip("85.193.148.81"), ip("85.193.148.81")),
            // no path
            arguments(network, ip("85.193.148.81"), ip("1.1.1.1"))
        );
    }
}
