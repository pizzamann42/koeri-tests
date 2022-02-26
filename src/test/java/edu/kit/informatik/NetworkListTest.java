package edu.kit.informatik;

import edu.kit.informatik.util.KoeriTestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static edu.kit.informatik.util.KoeriTestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NetworkListTest {
    @ParameterizedTest
    @MethodSource("listProvider")
    void testList(List<IP> list, Stream<Network> networks) {
        networks.forEach(network -> assertEquals(list, network.list()));
    }

    static Stream<Arguments> listProvider() {
        return IntStream.range(0, 5).mapToObj(NetworkListTest::listArgs);
    }

    static Arguments listArgs(int id) {
        List<IP> list = lines("network/" + id + "/list")
            .map(KoeriTestUtils::ip)
            .collect(Collectors.toList());
        return arguments(list, networks(id, "a_sorted", "a_random", "b_sorted", "b_random"));
    }
}
