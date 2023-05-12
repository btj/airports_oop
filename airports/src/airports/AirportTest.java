package airports;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class AirportTest {

	@Test
	void test() {
		Airport zaventem = new Airport();
		assertTrue(zaventem.getConnectedAirports().isEmpty());
		assertEquals(Set.of(), zaventem.getConnectedAirports()); // equivalent to previous line
		
		Airport schiphol = new Airport();
		zaventem.connectTo(schiphol);
		assertEquals(Set.of(schiphol), zaventem.getConnectedAirports());
		assertEquals(Set.of(zaventem), schiphol.getConnectedAirports());
		
		schiphol.disconnectFrom(zaventem);
		assertTrue(schiphol.getConnectedAirports().isEmpty());
		assertTrue(zaventem.getConnectedAirports().isEmpty());
	}

}
