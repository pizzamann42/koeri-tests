package edu.kit.informatik;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static edu.kit.informatik.util.KoeriTestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkConstructorParseTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
        " ",
        "$%?!§%*ÄÜÖöäü:;-_",
        "(0.0.0.0)",
        "(0.0.0.0 (1.1.1.1)",
        "(0.0.0.0 (1.1.1.1 2.2.2.2 )3.3.3.3 4.4.4.4()",
        "(0.0.0.0  1.1.1.1)",
        " (0.0.0.0 1.1.1.1)",
        "( 0.0.0.0 1.1.1.1)",
        "(0 0.0.0 1.1.1.1)",
        "(0,0.0.0 1.1.1.1)",
        "(0:0.0.0 1.1.1.1)",
        "(0:0.0.0 1.1.1.1) ",
        "(0:0.0.0 1.1.1.1))",
        "(0.0.0.0,1.1.1.1)",
        "(0.0.0.0 0.0.0.0)",
        "(1.1.1.1 0.0.0.0 0.0.0.0)",
        "(1.1.1.1 (0.0.0.0 2.2.2.2) 0.0.0.0)",
        "(0.0.0.0 (0.0.0.0 1.1.1.1)",
        "(1.1.1.1 (2.2.2.2 0.0.0.0) (3.3.3.3 0.0.0.0)",
        "(0.0.0.0 (1.1.1.1 0.0.0.0)",
        "((0.0.0.0 1.1.1.1) 2.2.2.2)",
        "👨‍👩‍👦📽🗣🥯🋿🊬😕🙁🏍🮲🷾🷣📽🳋🌨📨🴜🚠🔉🐸🂛🫠🔇🅊🯿🎒🗷😹🉰🩱🕉🪵🬴🝌🤼🁳📸🙓🄭🦉🪌🖹🔣🌂🄦🵇🠭🎈🔳🙚🠙🶇🢱🺚🮱🎐🻁🇦🱴👇🡒🝡🺎🫩🺘🁮🙠🶇🪂🃶🊸🴒",
    })
    void testInvalidArgs(String bracketNotation) {
        assertThrows(ParseException.class, () -> new Network(bracketNotation));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "(0.0.0.0 1.1.1.1 2.2.2.2)",
        SMALL_NET,
        SMALL_NET_ALTERNATIVE,
        SMALL_NET_ALTERNATIVE_SORTED,
        SMALL_NET_ALTERNATIVE_SORTED_2,
        SMALL_NET_EXTENDED,
        SMALL_NET_EXTENDED_2,
        MEDIUM_NET,
    })
    void testValidArgs(String bracketNotation) {
        assertDoesNotThrow(() -> new Network(bracketNotation));
    }
}
