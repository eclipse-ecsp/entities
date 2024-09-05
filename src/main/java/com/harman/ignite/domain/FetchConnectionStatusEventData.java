package com.harman.ignite.domain;

import com.harman.ignite.annotations.EventMapping;
import com.harman.ignite.entities.AbstractEventData;

/**
 * Fetch Connection StatusEventData Class.
 */
@EventMapping(id = EventID.FETCH_CONN_STATUS, version = Version.V1_0)
public class FetchConnectionStatusEventData extends AbstractEventData {
    private static final long serialVersionUID = -818536456087837L;

    private String vehicleId;
    private String platformId;
    
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    @Override
    public String toString() {
        return "FetchConnectionStatusEventData [vehicleId=" + vehicleId + ", platformId=" + platformId + "]";
    }

    @Override
     public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((platformId == null) ? 0 : platformId.hashCode());
        result = prime * result + ((vehicleId == null) ? 0 : vehicleId.hashCode());
        return result;
    }

    @Override
     public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FetchConnectionStatusEventData other = (FetchConnectionStatusEventData) obj;
        if (platformId == null) {
            if (other.platformId != null) {
                return false;
            }
        } else if (!platformId.equals(other.platformId)) {
            return false;
        }
        if (vehicleId == null) {
            if (other.vehicleId != null) {
                return false;
            }
        } else if (!vehicleId.equals(other.vehicleId)) {
            return false;
        }
        return true;
    }
}
