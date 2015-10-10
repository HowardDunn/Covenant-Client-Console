package com.HowardDunn.CovenantClientConsole;
/**
 * <p>Title: javaè®¿é—®DBFæ–‡ä»¶çš„æŽ¥å�£</p>
 * <p>Description: è¿™ä¸ªç±»ç”¨äºŽè¡¨ç¤ºDBFæ–‡ä»¶ä¸­çš„è¯»å†™å¼‚å¸¸</p>
 * <p>Copyright: Copyright (c) 2004~2012~2012</p>
 * <p>Company: iihero.com</p>
 * @author : He Xiong
 * @version 1.1
 */



import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * <p>Title: javaè®¿é—®DBFæ–‡ä»¶çš„æŽ¥å�£</p>
 * <p>Description: è¿™ä¸ªç±»ç”¨äºŽè¡¨ç¤ºDBFæ–‡ä»¶ä¸­çš„è¯»å†™å¼‚å¸¸</p>
 * <p>Copyright: Copyright (c) 2004~2012~2012</p>
 * <p>Company: iihero.com</p>
 * @author : He Xiong
 * @version 1.1
 */
public class JDBFException
    extends Exception {
  /**
   * ä½¿ç”¨ä¸€ä¸ªå­—ç¬¦ä¸²æ�¥æž„é€ JDBFException
   * @param s å¼‚å¸¸çš„å†…å®¹
   */
  public JDBFException(String s) {
    this(s, null);
  }

  /**
   * ä½¿ç”¨ä¸€ä¸ªå¼‚å¸¸æ�¥æž„é€ JDBFException
   * @param throwable è¦�æŠ›å‡ºçš„å¼‚å¸¸
   */
  public JDBFException(Throwable throwable) {
    this(throwable.getMessage(), throwable);
  }

  /**
   * æž„é€ å‡½æ•°
   * @param s å¼‚å¸¸çš„å†…å®¹
   * @param throwable ä¸€ç§�å¼‚å¸¸
   */
  public JDBFException(String s, Throwable throwable) {
    super(s);
    detail = throwable;
  }

  /**
   * èŽ·å�–å¼‚å¸¸çš„å…·ä½“å†…å®¹
   * @return å¼‚å¸¸JDBFExceptionçš„å…·ä½“å†…å®¹
   */
  public String getMessage() {
    if (detail == null) {
      return super.getMessage();
    }
    else {
      return super.getMessage();
    }
  }

  /**
   * è¾“å‡ºå¼‚å¸¸è‡³å±�å¹•
   * @param printstream
   */
  public void printStackTrace(PrintStream printstream) {
    if (detail == null) {
      super.printStackTrace(printstream);
      return;
    }
    PrintStream printstream1 = printstream;
    printstream1.println(this);
    detail.printStackTrace(printstream);
    return;
  }

  /**
   * @see printStackTrace(PrintStream printstream)
   */
  public void printStackTrace() {
    printStackTrace(System.err);
  }
  /**
   * @see printStackTrace(PrintStream printstream)
   * @param printwriter
   */
  public void printStackTrace(PrintWriter printwriter) {
    if (detail == null) {
      super.printStackTrace(printwriter);
      return;
    }
    PrintWriter printwriter1 = printwriter;

    printwriter1.println(this);
    detail.printStackTrace(printwriter);
    return;
  }

  /**
   * å¼‚å¸¸ç»†èŠ‚
   */
  private Throwable detail;
}
