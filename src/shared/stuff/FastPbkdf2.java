package shared.stuff;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Fast PBKDF2-style computation
 *
 * Performance Improvements: 1. Removed redundant md.reset() in inner loop
 * (reset once per outer iteration) 2. Reuse single MessageDigest instance per
 * compute
 */
public class FastPbkdf2 {

  private static final int OUTER_ITERATIONS = 1000;
  private static final int INNER_ITERATIONS = 10;

  public static int compute(int input) throws NoSuchAlgorithmException {

    byte[] value = intToBytes(input);

    MessageDigest md = MessageDigest.getInstance("SHA-256");

    for (int i = 0; i < OUTER_ITERATIONS; i++) {

      for (int j = 0; j < INNER_ITERATIONS; j++) {
        // completely removed redundant .reset() method upon learning more about
        // MessageDigest, i leanred its alreaady handeled by .digest
        md.update(value);
        value = md.digest();
      }
    }

    return normalizeResult(value);
  }

  private static byte[] intToBytes(int input) {
    return ByteBuffer.allocate(4).putInt(input).array();
  }

  private static int normalizeResult(byte[] value) {
    int result = ByteBuffer.wrap(value, 0, 4).getInt();
    return Math.floorMod(result, Integer.MAX_VALUE - 1) + 1;
  }
}
