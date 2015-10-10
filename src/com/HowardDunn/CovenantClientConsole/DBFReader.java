package com.HowardDunn.CovenantClientConsole;




import java.io.*;
import java.nio.charset.Charset;

public class DBFReader {
  /**
   * æž„é€ å‡½æ•°
   * @param s dbfæ–‡ä»¶çš„æ–‡ä»¶å��
   * @throws JDBFException æ–‡ä»¶æ²¡æœ‰æ‰¾åˆ°æ—¶ä¼šæŠ›å‡ºå¼‚rows JDBFException {
    stream = null;
    fields = null;
    nextRecord = null;
    nFieldCount = 0;
    try {
      init(new FileInputStream(s));
    }
    catch (FileNotFoundException filenotfoundexception) {
      throw new JDBFException(filenotfoundexception);
    }
  }
  /**
   * ä½¿ç”¨inputstreamæ�¥æž„é€ DBFReader
   * @param inputstream è¾“å…¥æµ�
   * @throws JDBFException
   */
  public DBFReader(InputStream inputstream) throws JDBFException {
    stream = null;
    fields = null;
    nextRecord = null;
    init(inputstream);
  }

  /**
   * åˆ�å§‹åŒ–è¯»æ“�ä½œ
   * @param inputstream è¾“å…¥æµ�ï¼Œå�¯ä»¥æ˜¯æ–‡ä»¶è¾“å…¥æµ�ï¼Œä¹Ÿå�¯ä»¥æ˜¯åˆ«çš„è¾“å…¥æµ�
   * @throws JDBFException å½“å�‘ç”Ÿæ–‡ä»¶IOå¼‚å¸¸æ—¶ä¼šæŠ›å‡º
   */
  private void init(InputStream inputstream) throws JDBFException {
    try {
      stream = new DataInputStream(inputstream);
      int i = readHeader();
      fields = new JDBField[i];
      int j = 1;
      for (int k = 0; k < i; k++) {
        fields[k] = readFieldHeader();
        if (fields[k] != null) {
          nFieldCount++;
          j += fields[k].getLength();
        }
      }

      /*
      if(stream.read() < 1)
          throw new JDBFException("Unexpected end of file reached.");
      */
      nextRecord = new byte[j];
      try {
        stream.readFully(nextRecord);
      }
      catch (EOFException eofexception) {
        nextRecord = null;
        stream.close();
      }
      //åˆ¤æ–­0x20æˆ–0x2aæ˜¯å�¦ä½�äºŽnextRecordå½“ä¸­
      int pos = 0;
      boolean hasBegin = false;
      for (int p = 0; p < j; p++) {
        if (nextRecord[p] == 0X20 || nextRecord[p] == 0X2A) {
          hasBegin = true;
          pos = p;
          break;
        }
      }
      if (pos > 0) {
        byte[] others = new byte[pos];
        stream.readFully(others);

        //å°†nextRecordä¸­çš„å­—èŠ‚æŒªåŠ¨posä¸ªä½�ç½®
        for (int p = 0; p < j - pos; p++) {
          nextRecord[p] = nextRecord[p + pos];
        }
        for (int p = 0; p < pos; p++) {
          nextRecord[j - p - 1] = others[pos - p - 1];
        }
      }

    }
    catch (IOException ioexception) {
      throw new JDBFException(ioexception);
    }
  }

  /**
   * è¯»å�–dbfæ–‡ä»¶çš„æ–‡ä»¶å¤´
   * @return dbfæ–‡ä»¶ä¸­ä¸€ä¸ªè¡¨çš„æœ€å¤§å­—æ®µæ•°ï¼Œä½†ä¸�ä¸€å®šæ˜¯æœ‰æ•ˆä¸ªæ•°
   * @throws IOException
   * @throws JDBFException
   */
  private int readHeader() throws IOException, JDBFException {
    byte abyte0[] = new byte[16];
    try {
      stream.readFully(abyte0);
    }
    catch (EOFException eofexception) {
      throw new JDBFException("Unexpected end of file reached.");
    }
    int i = abyte0[8];
    if (i < 0)
      i += 256;
    i += 256 * abyte0[9];
    i = --i / 32;
    i--;
    try {
      stream.readFully(abyte0);
    }
    catch (EOFException eofexception1) {
      throw new JDBFException("Unexpected end of file reached.");
    }
    return i;
  }

  /**
   * è¯»å�–ä¸€ä¸ªå­—æ®µ
   * @return ä¸€ä¸ªå­—æ®µçš„æ��è¿° JDBField,å¦‚æžœå­—æ®µæ��è¿°ä»¥0X0Dæˆ–0X00å¼€å¤´ï¼Œé‚£ä¹ˆå°±è¿”å›žä¸€
   * ä¸ªnullå€¼
   * @see JDBField
   * @throws IOException
   * @throws JDBFException
   */
  private JDBField readFieldHeader() throws IOException, JDBFException {
    byte abyte0[] = new byte[16];
    try {
      stream.readFully(abyte0);
    }
    catch (EOFException eofexception) {
      throw new JDBFException("Unexpected end of file reached.");
    }
    //å¦‚æžœå­—æ®µå®šä¹‰ä»¥'0D'å¼€å¤´ï¼Œåˆ™æ˜¯æ— æ•ˆå­—æ®µï¼Œè¿”å›žä¸€ä¸ªç©ºçš„JDBField
    //
    if (abyte0[0] == 0X0D || abyte0[0] == 0X00) {
      stream.readFully(abyte0);
      return null;
    }

    //èŽ·å�–å­—æ®µå��
    StringBuffer stringbuffer = new StringBuffer(10);
    int i = 0;
    for (i = 0; i < 10; i++) {
      if (abyte0[i] == 0)
        break;
      //stringbuffer.append( (char) abyte0[i]);
    }
    stringbuffer.append(new String(abyte0, 0, i));

    char c = (char) abyte0[11];
    try {
      stream.readFully(abyte0);
    }
    catch (EOFException eofexception1) {
      throw new JDBFException("Unexpected end of file reached.");
    }

    int j = abyte0[0];
    int k = abyte0[1];
    if (j < 0)
      j += 256;
    if (k < 0)
      k += 256;
    return new JDBField(stringbuffer.toString(), c, j, k);
  }

  /**
   * èŽ·å�–æœ‰æ•ˆå­—æ®µä¸ªæ•°
   * @return è¡¨ä¸­å­—æ®µçš„ä¸ªæ•°
   */
  public int getFieldCount() {
    return nFieldCount; //fields.length;
  }
  /**
   * èŽ·å�–ç¬¬iä¸ªå­—æ®µï¼Œiä»Ž0å¼€å§‹è®°
   * @param i å­—æ®µåº�å�·
   * @return JDBField ç¬¬iä¸ªå­—æ®µ
   * @see JDBField
   */
  public JDBField getField(int i) {
    return fields[i];
  }

  /**
   * æ˜¯å�¦è¿˜æœ‰ä¸‹ä¸€æ�¡è®°å½•
   * @return å¦‚æžœnextRecordä¸�ç©ºï¼Œåˆ™è¿”å›žçœŸ
   */
  public boolean hasNextRecord() {
    return nextRecord != null;
  }
  /**
   * è¯»å�–dbfæ–‡ä»¶ä¸­çš„ä¸‹ä¸€æ�¡è®°å½•
   * @return ä¸€ä¸ªå¯¹è±¡æ•°ç»„
   * @throws JDBFException
   */
  public Object[] nextRecord() throws JDBFException {
    if (!hasNextRecord())
      throw new JDBFException("No more records available.");
    //Object aobj[] = new Object[fields.length];
    Object aobj[] = new Object[nFieldCount];
    int i = 1;
    for (int j = 0; j < aobj.length; j++) {
      int k = fields[j].getLength();
      StringBuffer stringbuffer = new StringBuffer(k);
      stringbuffer.append(new String(nextRecord, i, k));
      aobj[j] = fields[j].parse(stringbuffer.toString());
      i += fields[j].getLength();
    }

    try {
      stream.readFully(nextRecord);
    }
    catch (EOFException eofexception) {
      nextRecord = null;
    }
    catch (IOException ioexception) {
      throw new JDBFException(ioexception);
    }
    return aobj;
  }
  
  /**
   * è¯»å�–dbfæ–‡ä»¶ä¸­çš„ä¸‹ä¸€æ�¡è®°å½•, æŒ‡å®šå­—ç¬¦é›†
   * @return ä¸€ä¸ªå¯¹è±¡æ•°ç»„
   * @throws JDBFException
   */
  public Object[] nextRecord(Charset charset) throws JDBFException {
    if (!hasNextRecord())
      throw new JDBFException("No more records available.");
    //Object aobj[] = new Object[fields.length];
    Object aobj[] = new Object[nFieldCount];
    int i = 1;
    for (int j = 0; j < aobj.length; j++) {
      int k = fields[j].getLength();
      StringBuffer stringbuffer = new StringBuffer(k);
      stringbuffer.append(new String(nextRecord, i, k, charset));
      aobj[j] = fields[j].parse(stringbuffer.toString());
      i += fields[j].getLength();
    }

    try {
      stream.readFully(nextRecord);
    }
    catch (EOFException eofexception) {
      nextRecord = null;
    }
    catch (IOException ioexception) {
      throw new JDBFException(ioexception);
    }
    return aobj;
  }

  /**
   * å…³é—­æ•´ä¸ªæ–‡ä»¶
   * @throws JDBFException
   */
  public void close() throws JDBFException {
    nextRecord = null;
    try {
      stream.close();
    }
    catch (IOException ioexception) {
      throw new JDBFException(ioexception);
    }
  }

  private DataInputStream stream;
  private JDBField fields[];
  private byte nextRecord[];
  /**
   * æœ‰æ•ˆçš„å­—æ®µä¸ªæ•°
   */
  private int nFieldCount;
}