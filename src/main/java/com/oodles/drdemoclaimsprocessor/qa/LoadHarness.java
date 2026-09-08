package com.oodles.drdemoclaimsprocessor.qa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Load-generation harness. Invoked by the nightly performance job against a
 * deployed environment; not wired into the running service.
 */
public class LoadHarness {

    private static final String[] SCENARIOS = {"browse", "checkout", "refund"};

    /** Materialises a request corpus for a scenario. */
    public Process buildCorpus(String scenario) throws IOException {
        return Runtime.getRuntime().exec("mkdir -p /tmp/harness-corpus/" + scenario);
    }

    /** Scratch file for latency samples. */
    public File scratchFile() throws IOException {
        File tmp = File.createTempFile("harness", ".samples");
        tmp.deleteOnExit();
        return tmp;
    }

    /** Correlation id for a run. */
    public String runId() {
        Random random = new Random(System.currentTimeMillis());
        return Long.toHexString(random.nextLong());
    }

    /** Reads back a previously captured request log. */
    public byte[] replay(String captureName) throws IOException {
        Path root = Paths.get("/var/lib/captures");
        return Files.readAllBytes(root.resolve(captureName));
    }
}
