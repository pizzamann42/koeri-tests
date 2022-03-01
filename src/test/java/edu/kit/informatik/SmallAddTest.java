package edu.kit.informatik;

import edu.kit.informatik.exceptions.ParseException;
import edu.kit.informatik.network.IP;
import edu.kit.informatik.network.Network;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SmallAddTest {

    /*
    This test adds 2 independent trees to a network with 1 existing trees
    The 2 trees have to be added to the same tree.
     */
    @Test
    public void testAdd() throws ParseException {
        Network net1 = new Network("(1.1.1.1 2.2.2.2 3.3.3.3)");
        Network net2 = new Network("(3.3.3.3 4.4.4.4 5.5.5.5)");
        Network net3 = new Network("(2.2.2.2 6.6.6.6 7.7.7.7)");
        net2.add(net3);
        net1.add(net2);
        assertEquals(net1.toString(new IP("1.1.1.1")), "(1.1.1.1 (2.2.2.2 6.6.6.6 7.7.7.7) (3.3.3.3 4.4.4.4 5.5.5.5))");
    }
}
