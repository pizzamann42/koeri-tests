package edu.kit.informatik;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MntNetworkTest {
    private static Stream<String> treeFileNames() {
        return IntStream.range(1, 6).mapToObj((i) -> "bracket_notation" + String.valueOf(i) + ".txt");
    }

    private static List<Arguments> treeFileDifferentNameCombinations() {
        List<String> names = treeFileNames().collect(Collectors.toList());
        ArrayList<Arguments> args = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            for (int j = 0; j < i; j++) {
                args.add(Arguments.of(names.get(i), names.get(j)));
            }
        }
        return args;
    }

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

    private Network readNetwork(String filename) throws IOException, ParseException {
        var inreader = getFileReader(filename);
        String instr = inreader.readLine();
        return new Network(instr);
    }

    @ParameterizedTest
    @MethodSource("treeFileNames")
    void testNetworkConstructor(String filename) throws IOException, ParseException {
        var inreader = getFileReader(filename);
        String instr = inreader.readLine();
        Network network = new Network(instr);
        String rootIp = instr.split(" ")[0].substring(1);
        assertEquals(instr, network.toString(new IP(rootIp)));
    }

    @Test
    void testAdd() throws IOException, ParseException {
        Network network_a = readNetwork("tree_a.txt");
        Network network_b = readNetwork("tree_b.txt");

        Network network_u = readNetwork("tree_union.txt");

        assertTrue(network_a.add(network_b));

        assertEquals(network_u, network_a);
    }

    @ParameterizedTest
    @MethodSource("treeFileNames")
    void equalsTrueTest(String file) throws IOException, ParseException {
        var network1 = readNetwork(file);
        var network2 = readNetwork(file);

        assertEquals(network1, network2);
    }

    @ParameterizedTest
    @MethodSource("treeFileDifferentNameCombinations")
    void testNotEquals(String first, String second) throws IOException, ParseException {
        var network1 = readNetwork(first);
        var network2 = readNetwork(second);
        assertNotEquals(network1, network2);
    }

    @Test
    void testList() {
        // TODO: Make sure that the list is sorted
    }

    @Test
    void testToString() {
    }
}
