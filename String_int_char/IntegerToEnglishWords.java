package leetcode;

public class IntegerToEnglishWords {
  // fields and classes here.
  // private int count;

  public IntegerToEnglishWords() {
    // Initialization here.
    // this.count = 0;

  }

  // Divide and Conquer
  // O(N) time, O(1) space
  // Author: leetcode + kei
  // Date : December 6, 2020
  public String one(int num) {
    switch (num) {
      case 1:
        return "One";
      case 2:
        return "Two";
      case 3:
        return "Three";
      case 4:
        return "Four";
      case 5:
        return "Five";
      case 6:
        return "Six";
      case 7:
        return "Seven";
      case 8:
        return "Eight";
      case 9:
        return "Nine";
    }
    return "";
  }

  public String twoLessThan20(int num) {
    switch (num) {
      case 10:
        return "Ten";
      case 11:
        return "Eleven";
      case 12:
        return "Twelve";
      case 13:
        return "Thirteen";
      case 14:
        return "Fourteen";
      case 15:
        return "Fifteen";
      case 16:
        return "Sixteen";
      case 17:
        return "Seventeen";
      case 18:
        return "Eighteen";
      case 19:
        return "Nineteen";
    }
    return "";
  }

  public String ten(int num) {
    switch (num) {
      case 2:
        return "Twenty";
      case 3:
        return "Thirty";
      case 4:
        return "Forty";
      case 5:
        return "Fifty";
      case 6:
        return "Sixty";
      case 7:
        return "Seventy";
      case 8:
        return "Eighty";
      case 9:
        return "Ninety";
    }
    return "";
  }

  public String two(int num) {
    if (num == 0) {
      return "";
    } else if (num < 10) {
      return one(num);
    } else if (num < 20) {
      return twoLessThan20(num);
    } else {
      int tens = num / 10;
      int rest = num - tens * 10;
      if (rest != 0) {
        return ten(tens) + " " + one(rest);
      } else {
        return ten(tens);
      }
    }
  }

  public String three(int num) {
    int hundreds = num / 100;
    int rest = num - hundreds * 100;
    String res = "";
    if (hundreds * rest != 0) {
      res = one(hundreds) + " Hundreds " + two(rest);
    } else if ((hundreds == 0) && (rest != 0)) {
      res = two(rest);
    } else if ((hundreds != 0) && (rest == 0)) {
      res = one(hundreds) + " Hundred";
    }
    return res;
  }

  public String numberToWords(int num) {
    if (num == 0) {
      return "Zero";
    }

    // num: 1,234,567,890
    int billion = num / 1000000000; // 1
    int million = (num - billion * 1000000000) / 1000000; // 234
    int thousand = (num - billion * 1000000000 - million * 1000000) / 1000; // 567
    int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000; // 890

    String result = "";
    if (billion != 0) {
      result = three(billion) + " Billion";
    }
    if (million != 0) {
      if (!result.isEmpty()) {
        result += " ";
      }
      result += three(million) + " Million";
    }
    if (thousand != 0) {
      if (!result.isEmpty()) {
        result += " ";
      }
      result += three(thousand) + " Thousand";
    }
    if (rest != 0) {
      if (!result.isEmpty()) {
        result += " ";
      }
      result += three(rest);
    }
    return result;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    IntegerToEnglishWords solution = new IntegerToEnglishWords();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
