package edu.kit.informatik;

import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.kit.informatik.util.KoeriTestUtils.SMALL_NET;
import static edu.kit.informatik.util.KoeriTestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkGetRouteTest {
    @Test
    public void testGetRoute() {
        Network net = network(SMALL_NET);
        assertEquals(List.of(), net.getRoute(null, null));
        assertEquals(List.of(), net.getRoute(ip("141.255.1.133"), null));
        assertEquals(List.of(), net.getRoute(null, ip("141.255.1.133")));
        assertEquals(List.of(), net.getRoute(ip("141.255.1.133"), ip("0.0.0.0")));
        assertEquals(List.of(), net.getRoute(ip("0.0.0.0"), ip("141.255.1.133")));
        assertEquals(List.of(), net.getRoute(ip("85.193.148.81"), ip("85.193.148.81")));

        assertEquals(ips("85.193.148.81", "141.255.1.133", "122.117.67.158"),
            net.getRoute(ip("85.193.148.81"), ip("122.117.67.158")));
        assertEquals(ips("122.117.67.158", "141.255.1.133", "85.193.148.81", "231.189.0.127", "39.20.222.120"),
            net.getRoute(ip("122.117.67.158"), ip("39.20.222.120")));

        assertTrue(() -> net.add(network("(0.0.0.0 1.1.1.1 2.2.2.2 (3.3.3.3 4.4.4.4))")));
        assertEquals(List.of(), net.getRoute(ip("39.20.222.120"), ip("4.4.4.4")));
    }
}
