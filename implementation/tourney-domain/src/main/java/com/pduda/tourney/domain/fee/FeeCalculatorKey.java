package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import java.util.Objects;

public class FeeCalculatorKey {

    private RankClass rankClass;
    private Licensed licenseType;

    public FeeCalculatorKey(RankClass rankClass, Licensed licenseType) {
        this.rankClass = rankClass;
        this.licenseType = licenseType;
    }

    public Licensed getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(Licensed licenseType) {
        this.licenseType = licenseType;
    }

    public RankClass getRankClass() {
        return rankClass;
    }

    public void setRankClass(RankClass rankClass) {
        this.rankClass = rankClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FeeCalculatorKey other = (FeeCalculatorKey) obj;
        if (!Objects.equals(this.rankClass, other.rankClass)) {
            return false;
        }
        if (this.licenseType != other.licenseType) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.rankClass);
        hash = 29 * hash + (this.licenseType != null ? this.licenseType.hashCode() : 0);
        return hash;
    }

    public enum Licensed {

        LICENSED_AT_THIS_CLUB, LICENSED_AT_OTHER_CLUB, NOT_LICENCED;
    }
}
