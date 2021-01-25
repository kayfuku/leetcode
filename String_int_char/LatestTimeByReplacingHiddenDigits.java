package leetcode;

public class LatestTimeByReplacingHiddenDigits {
  // fields and classes here.
  // private int count;

  public LatestTimeByReplacingHiddenDigits() {
    // Initialization here.
    // this.count = 0;

  }

  // 1.
  // Author: kei (AC)
  // Date : January 23, 2021
  public String maximumTime(String time) {
    char[] chars = time.toCharArray();
    if (chars[0] == '?') {
      if (chars[1] == '?' || Character.getNumericValue(chars[1]) <= 3) {
        chars[0] = '2';
      } else {
        chars[0] = '1';
      }
    }
    if (chars[1] == '?') {
      if (chars[0] == '2') {
        chars[1] = '3';
      } else {
        chars[1] = '9';
      }
    }
    if (chars[3] == '?') {
      chars[3] = '5';
    }
    if (chars[4] == '?') {
      chars[4] = '9';
    }

    return new String(chars);
  }

  // 2.
  // Latest time => Iterate from 23:59 then decrease by one minute.
  // Return the time if all the digits are the same except '?'.
  // Author: uwi + kei
  // Date : January 24, 2021
  public String maximumTime2(String time) {
    char[] t = time.toCharArray();
    for (int h = 23; h >= 0; h--) {
      minute: for (int m = 59; m >= 0; m--) {
        String u = String.format("%02d:%02d", h, m);
        for (int i = 0; i < 5; i++) {
          // No need to check if the digit is '?'.
          if (t[i] == '?') {
            continue;
          }
          if (t[i] != u.charAt(i)) {
            // Ignore both the rest of this for loop and the rest of minute for loop.
            continue minute;
          }
        }
        return u;
      }
    }
    return null;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    LatestTimeByReplacingHiddenDigits solution = new LatestTimeByReplacingHiddenDigits();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
