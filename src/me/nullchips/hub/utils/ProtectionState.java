package me.nullchips.hub.utils;

public enum ProtectionState {
	
    ENABLED(true), DISABLED(false);

    public boolean enabled;

    private static ProtectionState currentState;

    ProtectionState(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean enabled() {
        return enabled;
    }

    public static void setState(ProtectionState state) {
        ProtectionState.currentState = state;
    }

    public static boolean isState(ProtectionState state) {
        return ProtectionState.currentState == state;
    }

    public static ProtectionState getState() {
        return currentState;
    }
	
}
