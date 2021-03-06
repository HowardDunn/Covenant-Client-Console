package com.HowardDunn.CovenantClientConsole;
/**
 * <p>Title: javaè®¿é—®DBFæ–‡ä»¶çš„æŽ¥å�£</p>
 * <p>Description: è¿™ä¸ªç±»ç”¨äºŽè¡¨ç¤ºDBFæ–‡ä»¶ä¸­çš„å†™æ“�ä½œ</p>
 * <p>Copyright: Copyright (c) 2004~2012~2012</p>
 * <p>Company: iihero.com</p>
 * @author : He Xiong
 * @version 1.1
 */



import java.io.*;
import java.util.Calendar;

/**
 * <p>Title: javaè®¿é—®DBFæ–‡ä»¶çš„æŽ¥å�£</p>
 * <p>Description: è¿™ä¸ªç±»ç”¨äºŽè¡¨ç¤ºDBFæ–‡ä»¶ä¸­çš„å†™æ“�ä½œ</p>
 * <p>Copyright: Copyright (c) 2004~2012~2012</p>
 * <p>Company: iihero.com</p>
 * @author : He Xiong
 * @version 1.1
 */
public class DBFWriter {
  /**
   * æž„é€ DBFWriter
   * @param s æ–‡ä»¶å��
   * @param ajdbfield å­—æ®µåˆ—è¡¨
   * @throws JDBFException å†™æ–‡ä»¶å‡ºé”™æ—¶æŠ›å‡ºå¼‚å¸¸
   */
  public DBFWriter(String s, JDBField ajdbfield[]) throws JDBFException {
    stream = null;
    recCount = 0;
    fields = null;
    fileName = null;
    dbfEncoding = null;
    fileName = s;
    try {
      init(new FileOutputStream(s), ajdbfield);
    }
    catch (FileNotFoundException filenotfoundexception) {
      throw new JDBFException(filenotfoundexception);
    }
  }
  /**
   * æž„é€ å‡½æ•°
   * @param outputstream è¾“å‡ºæµ�
   * @param ajdbfield å­—æ®µåˆ—è¡¨
   * @throws JDBFException å†™æ–‡ä»¶å‡ºé”™æ—¶æŠ›å‡ºå¼‚å¸¸
   */
  public DBFWriter(OutputStream outputstream, JDBField ajdbfield[]) throws
      JDBFException {
    stream = null;
    recCount = 0;
    fields = null;
    fileName = null;
    dbfEncoding = null;
    init(outputstream, ajdbfield);
  }
  /**
   * æž„é€ å‡½æ•°
   * @param s æ–‡ä»¶å��
   * @param ajdbfield å­—æ®µåˆ—è¡¨
   * @param s1 å­—ç¬¦é›†ç¼–ç �ç±»åž‹
   * @throws JDBFException
   * @see init
   */
  public DBFWriter(String s, JDBField ajdbfield[], String s1) throws
      JDBFException {
    stream = null;
    recCount = 0;
    fields = null;
    fileName = null;
    dbfEncoding = null;
    fileName = s;
    try {
      dbfEncoding = s1;
      init(new FileOutputStream(s), ajdbfield);
    }
    catch (FileNotFoundException filenotfoundexception) {
      throw new JDBFException(filenotfoundexception);
    }
  }

  /**
   * åˆ�å§‹åŒ–å†™æ“�ä½œ
   * @param outputstream è¾“å‡ºæµ�
   * @param ajdbfield å­—æ®µåˆ—è¡¨
   * @throws JDBFException å†™æ“�ä½œå¤±è´¥æ—¶æŠ›å‡º
   */
  private void init(OutputStream outputstream, JDBField ajdbfield[]) throws
      JDBFException {
    fields = ajdbfield;
    try {
      stream = new BufferedOutputStream(outputstream);
      writeHeader();
      for (int i = 0; i < ajdbfield.length; i++) {
        writeFieldHeader(ajdbfield[i]);

      }
      stream.write(13);
      stream.flush();
    }
    catch (Exception exception) {
      throw new JDBFException(exception);
    }
  }

  /**
   * å†™dbfæ–‡ä»¶å¤´
   * @throws IOException
   */
  private void writeHeader() throws IOException {
    byte abyte0[] = new byte[16];
    abyte0[0] = 3;
    Calendar calendar = Calendar.getInstance();
    abyte0[1] = (byte) (calendar.get(1) - 1900);
    abyte0[2] = (byte) calendar.get(2);
    abyte0[3] = (byte) calendar.get(5);
    abyte0[4] = 0;
    abyte0[5] = 0;
    abyte0[6] = 0;
    abyte0[7] = 0;
    int i = (fields.length + 1) * 32 + 1;
    abyte0[8] = (byte) (i % 256);
    abyte0[9] = (byte) (i / 256);
    int j = 1;
    for (int k = 0; k < fields.length; k++) {
      j += fields[k].getLength();

    }
    abyte0[10] = (byte) (j % 256);
    abyte0[11] = (byte) (j / 256);
    abyte0[12] = 0;
    abyte0[13] = 0;
    abyte0[14] = 0;
    abyte0[15] = 0;
    stream.write(abyte0, 0, abyte0.length);
    for (int l = 0; l < 16; l++) {
      abyte0[l] = 0;

    }
    stream.write(abyte0, 0, abyte0.length);
  }

  /**
   * å†™ä¸€ä¸ªå­—æ®µçš„å…ƒä¿¡æ�¯
   * @param jdbfield å­—æ®µå†…å®¹
   * @throws IOException å†™å¤±è´¥æ—¶æŠ›å‡º
   */
  private void writeFieldHeader(JDBField jdbfield) throws IOException {
    byte abyte0[] = new byte[16];
    String s = jdbfield.getName();
    int i = s.length();
    if (i > 10) {
      i = 10;
    }
    for (int j = 0; j < i; j++) {
      abyte0[j] = (byte) s.charAt(j);

    }
    for (int k = i; k <= 10; k++) {
      abyte0[k] = 0;

    }
    abyte0[11] = (byte) jdbfield.getType();
    abyte0[12] = 0;
    abyte0[13] = 0;
    abyte0[14] = 0;
    abyte0[15] = 0;
    stream.write(abyte0, 0, abyte0.length);
    for (int l = 0; l < 16; l++) {
      abyte0[l] = 0;

    }
    abyte0[0] = (byte) jdbfield.getLength();
    abyte0[1] = (byte) jdbfield.getDecimalCount();
    stream.write(abyte0, 0, abyte0.length);
  }

  /**
   * å†™ä¸€æ�¡è®°å½•
   * @param aobj ä»¥Objectè¡¨ç¤ºçš„è®°å½•å€¼
   * @throws JDBFException å†™æ“�ä½œå¤±è´¥æ—¶æŠ›å‡º,å¦‚æžœç¼–ç �ç±»åž‹ä¸�æ”¯æŒ�ï¼Œä¹ŸæŠ›å‡ºå¼‚å¸¸
   */
  public void addRecord(Object aobj[]) throws JDBFException {
    if (aobj.length != fields.length) {
      throw new JDBFException(
          "Error adding record: Wrong number of values. Expected " +
          fields.length + ", got " + aobj.length + ".");
    }
    int i = 0;
    for (int j = 0; j < fields.length; j++) {
      i += fields[j].getLength();

    }
    byte abyte0[] = new byte[i];
    int k = 0;
    for (int l = 0; l < fields.length; l++) {
      String s = fields[l].format(aobj[l]);
      byte abyte1[];
      try {
        if (dbfEncoding != null) {
          abyte1 = s.getBytes(dbfEncoding);
        }
        else {
          abyte1 = s.getBytes();
        }
      }
      catch (UnsupportedEncodingException unsupportedencodingexception) {
        throw new JDBFException(unsupportedencodingexception);
      }
      for (int i1 = 0; i1 < fields[l].getLength(); i1++) {
        abyte0[k + i1] = abyte1[i1];

      }
      k += fields[l].getLength();
    }

    try {
      stream.write(32);
      stream.write(abyte0, 0, abyte0.length);
      stream.flush();
    }
    catch (IOException ioexception) {
      throw new JDBFException(ioexception);
    }
    recCount++;
  }
  /**
   * å…³é—­æ–‡ä»¶å†™æ“�ä½œ
   * @throws JDBFException å‡ºçŽ°IOå¼‚å¸¸æ—¶æŠ›å‡º
   */
  public void close() throws JDBFException {
    try {
      stream.write(26);
      stream.close();
      RandomAccessFile randomaccessfile = new RandomAccessFile(fileName, "rw");
      randomaccessfile.seek(4L);
      byte abyte0[] = new byte[4];
      abyte0[0] = (byte) (recCount % 256);
      abyte0[1] = (byte) ( (recCount / 256) % 256);
      abyte0[2] = (byte) ( (recCount / 0x10000) % 256);
      abyte0[3] = (byte) ( (recCount / 0x1000000) % 256);
      randomaccessfile.write(abyte0, 0, abyte0.length);
      randomaccessfile.close();
    }
    catch (IOException ioexception) {
      throw new JDBFException(ioexception);
    }
  }

  /**
   * è¾“å‡ºæµ�
   */
  private BufferedOutputStream stream;
  /**
   * è®°å½•ä¸ªæ•°
   */
  private int recCount;
  /**
   * å­—æ®µåˆ—è¡¨
   */
  private JDBField fields[];
  /**
   * æ–‡ä»¶å��
   */
  private String fileName;
  /**
   * dbfæ–‡ä»¶çš„ç¼–ç �ç±»åž‹
   */
  private String dbfEncoding;
}
