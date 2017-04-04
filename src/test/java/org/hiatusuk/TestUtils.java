package org.hiatusuk;

import com.google.common.collect.Sets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SuppressWarnings("nls")
public final class TestUtils {

    /**
     * Pointless
     */
    private TestUtils() {
    }

    /**
     * Added for PIT-testing - a simple way to get good coverage and a high mutation score for your object
     *
     * @param inObj
     * @param inCopy
     * @param inDifferentObjects
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void testEqualsHashcode(final Object inObj, final Object inCopy, final Object... inDifferentObjects) {
        // First up, check the types match...
        assertThat((Class) inObj.getClass(), allOf(equalTo((Class) inCopy.getClass())));

        assertThat(inObj.equals(null), is(false));  // NOPMD
        assertThat(inObj.equals(new java.util.StringTokenizer("")), is(false));
        assertThat(inObj, equalTo(inObj));
        assertThat("Copy object should not == source", inCopy, not(sameInstance(inObj)));
        assertThat("Source object should equal the Copy object", inObj, equalTo(inCopy));

        assertThat("Source.hashCode() should equal itself", inObj.hashCode(), equalTo(inObj.hashCode()));
        assertThat("Source.hashCode() should equal the Copy.hashCode()", inObj.hashCode(), equalTo(inCopy.hashCode()));

        assertThat("Duplicate Different objects are present: counts differ", inDifferentObjects.length, is(Sets.newHashSet(inDifferentObjects).size()));

        for (final Object eachDiffObject : inDifferentObjects) {
            assertThat((Class) inObj.getClass(), equalTo((Class) eachDiffObject.getClass()));
            assertThat("Different object should not == source", eachDiffObject, not(sameInstance(inObj)));
            assertThat("Different object should not equal the Source/Copy object", inObj, not(equalTo(eachDiffObject)));
            assertThat("Source.hashCode() should (probably) not equal the Different.hashCode()", inObj.hashCode(),
                    not(equalTo(eachDiffObject.hashCode()))); // (AGR) ;-)
        }
    }
}