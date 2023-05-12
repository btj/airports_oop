package airports;

import java.util.HashSet;
import java.util.Set;

import logicalcollections.LogicalSet;

/**
 * Each instance of this class represents an airport in an airport
 * connections graph.
 * 
 * @invar The bidirectional association is consistent. 
 *     | getConnectedAirports().stream().allMatch(a ->
 *     |     a.getConnectedAirports().contains(this))
 */
public class Airport {
	
	/**
	 * @invar | connectedAirports != null
	 * @invar The bidirectional association is consistent
	 *     | connectedAirports.stream().allMatch(a -> a != null && a.connectedAirports.contains(this))
	 *
	 * @representationObject
	 * @peerObjects
	 */
	private HashSet<Airport> connectedAirports = new HashSet<>();
	
	/**
	 * @post | result != null
	 * @post | result.stream().allMatch(a -> a != null)
	 * 
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Airport> getConnectedAirports() { return Set.copyOf(connectedAirports); }

	/**
	 * @post | getConnectedAirports().isEmpty()
	 */
	public Airport() {}
	
	/**
	 * @pre | other != null
	 * @mutates_properties | this.getConnectedAirports(), other.getConnectedAirports()
	 * @post | this.getConnectedAirports().equals(LogicalSet.plus(old(this.getConnectedAirports()), other))
	 * @post | other.getConnectedAirports().equals(LogicalSet.plus(old(other.getConnectedAirports()), this))
	 */
	public void connectTo(Airport other) {
		this.connectedAirports.add(other);
		other.connectedAirports.add(this);
	}
	
	/**
	 * @pre | other != null
	 * @mutates_properties | this.getConnectedAirports(), other.getConnectedAirports()
	 * @post | this.getConnectedAirports().equals(LogicalSet.minus(old(this.getConnectedAirports()), other))
	 * @post | other.getConnectedAirports().equals(LogicalSet.minus(old(other.getConnectedAirports()), this))
	 */
	public void disconnectFrom(Airport other) {
		this.connectedAirports.remove(other);
		other.connectedAirports.remove(this);
	}
}
