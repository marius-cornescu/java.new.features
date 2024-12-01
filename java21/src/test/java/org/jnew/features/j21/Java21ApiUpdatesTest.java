package org.jnew.features.j21;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class Java21ApiUpdatesTest {

    @Test
    void repeatOnStringAndStringBuilder() {
        // given
        var simpleString = "Mojo";

        // when
        var repeatString = simpleString.repeat(2);

        // then
        log.atInfo().log(repeatString);
    }

}
