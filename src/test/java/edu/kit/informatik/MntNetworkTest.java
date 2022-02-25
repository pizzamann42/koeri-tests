package edu.kit.informatik;

import org.junit.jupiter.api.Disabled;
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
    @Disabled
    @MethodSource("treeFileDifferentNameCombinations")
    void testNotEquals(String first, String second) throws IOException, ParseException {
        var network1 = readNetwork(first);
        var network2 = readNetwork(second);
        assertNotEquals(network1, network2);
    }
}
