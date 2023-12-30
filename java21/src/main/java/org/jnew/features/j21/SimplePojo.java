package org.jnew.features.j21;

import lombok.Builder;

import java.time.Duration;

@Builder
public record SimplePojo(String name, Duration length) {

}
