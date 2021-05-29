package utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Satunnaisten numeroiden generointi.
 */
public class RandomNumberGenerator {

    // TODO: Tee tähän Mersenne-Twister
    public static int getRandInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
