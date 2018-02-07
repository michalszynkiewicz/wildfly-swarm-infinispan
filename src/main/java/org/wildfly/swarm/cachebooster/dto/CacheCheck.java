package org.wildfly.swarm.cachebooster.dto;

/**
 * mstodo: Header
 *
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 2/1/18
 */
public class CacheCheck {
    private final boolean cached;

    public CacheCheck(boolean cached) {
        this.cached = cached;
    }

    public boolean isCached() {
        return cached;
    }
}
