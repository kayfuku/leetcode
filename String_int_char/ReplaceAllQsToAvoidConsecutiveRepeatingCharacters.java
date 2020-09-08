//
// Author:
// Date : September ?, 2020

package leetcode;

public class ReplaceAllQsToAvoidConsecutiveRepeatingCharacters {
  // fields and classes here.
  // private int count;

  public ReplaceAllQsToAvoidConsecutiveRepeatingCharacters() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: uwi + hank55663 + kei
  // Date  : September 7, 2020
  public String modifyString(String S) {
    // Use char[] not StringBuilder if the length is fixed. 
    char[] s = S.toCharArray();
    for (int i = 0; i < s.length; i++) {
      if (s[i] == '?') {
        // Iterate lowercase alphabets. 
        for (int c = 'a'; c <= 'z'; c++) {
          // Look at how nicely Handled the edge cases are. 
          if ((i == 0 || c != s[i - 1]) && (i == s.length - 1 || c != s[i + 1])) {
            s[i] = (char) c;
            break;
          }
        }
      }
    }

    return String.valueOf(s);
  }


  // Not good solution at all. 
  // Author: kei (AC)
  // Date  : September 5, 2020
  public String modifyStringNG(String s) {
    if (s.length() == 1) {
      return (s.charAt(0) == '?') ? String.valueOf('a') : s;
    }

    StringBuilder sb = new StringBuilder();
    if (s.charAt(0) == '?') {
      char c = (s.charAt(1) == '?') ? 'a' : (char) ((s.charAt(1) + 1 - 'a') % 26 + 'a');
      sb.append(c);
    } else {
      sb.append(s.charAt(0));
    }
    for (int i = 1; i + 1 < s.length(); i++) {
      if (s.charAt(i) != '?') {
        sb.append(s.charAt(i));
        continue;
      }
      char c = sb.charAt(i - 1);
      if (c + 1 == s.charAt(i + 1)) {
        sb.append((char) (((c + 2) - 'a') % 26 + 'a'));
      } else {
        sb.append((char) (((c + 1) - 'a') % 26 + 'a'));
      }
    }
    if (s.charAt(s.length() - 1) == '?') {
      sb.append((char) ((s.charAt(s.length() - 2) + 1 - 'a') % 26 + 'a'));
    } else {
      sb.append(s.charAt(s.length() - 1));
    }

    return sb.toString();
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Solution solution = new Solution();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


