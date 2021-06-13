package utils;

/**
 * Satunnaisten numeroiden generointi.
 */
public class RandomNumberGenerator {

    /**
     * Pseudosatunnaislukugeneraattori, joka palauttaa halutun int luvun annetulta lukuväliltä.
     *
     * @return Palauttaa halutun luvun annetulta lukuväliltä,
     */
    public static int getRandInt(int min, int max) {
        double fraction = 1.0 / 32768;
        return (int) (min + (max - min + 1) *  PRNG() * fraction);
    }

    /**
     * Pseudosatunnaislukugeneraattori perustuu seuraavaan malliin:
     * https://www.learncpp.com/cpp-tutorial/random-number-generation/
     *
     * @return Luku väliltä 0 - 32768.
     */
    private static long PRNG() {
        long seed =  System.nanoTime();
        seed = 8253729 * seed + 2396403;
        return Math.abs(seed % 32768);
    }
}
