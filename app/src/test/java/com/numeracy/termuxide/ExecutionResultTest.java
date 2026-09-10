package com.numeracy.termuxide;

import com.numeracy.termuxide.models.ExecutionResult;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExecutionResultTest {
    @Test public void zeroExitCodeIsSuccessful() {
        ExecutionResult result = new ExecutionResult("ok", "", 0, 12);
        assertTrue(result.isSuccess());
        assertEquals("ok", result.getOutput());
    }
    @Test public void nonZeroExitCodeIsFailure() {
        ExecutionResult result = new ExecutionResult("", "failed", 2, 12);
        assertFalse(result.isSuccess());
        assertEquals(2, result.getExitCode());
    }
}
