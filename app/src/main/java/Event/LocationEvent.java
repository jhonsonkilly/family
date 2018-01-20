package Event;

import utils.LocationManager;

/**
 * Created by mac on 18/1/20.
 */

public class LocationEvent {
     public LocationManager.MapLocation location;

    public LocationEvent(LocationManager.MapLocation location) {
        this.location = location;

    }
}
