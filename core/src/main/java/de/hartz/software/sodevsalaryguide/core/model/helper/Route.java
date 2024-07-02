package de.hartz.software.sodevsalaryguide.core.model.helper;

public record Route(
        String host,
        int port
) {

    @Override
    public String toString() {
        return host + ":" + port;
    }
}
