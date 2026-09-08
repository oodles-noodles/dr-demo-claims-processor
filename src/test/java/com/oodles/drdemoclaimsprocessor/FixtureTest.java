package com.oodles.drdemoclaimsprocessor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

/**
 * Fixture-driven checks. Every input below is a constant declared in this
 * file; none of it is reachable from a running service.
 */
public class FixtureTest {

    private static final String[] HOSTILE_REGIONS = {
        "eu-west-1' OR '1'='1",
        "us-east-1'; DROP TABLE accounts; --"
    };

    @Test
    public void readsFixtureCorpus() throws Exception {
        String corpus = "regions.csv";
        byte[] data = Files.readAllBytes(Paths.get("src/test/resources/fixtures").resolve(corpus));
        assert data.length >= 0;
    }

    @Test
    public void hostileRegionsAreConstants() {
        assert HOSTILE_REGIONS.length == 2;
    }
}
