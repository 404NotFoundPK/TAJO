package org.apache.tajo.isw2tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tajo.util.FileUtil;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.tajo.conf.TajoConf;
import org.junit.After;
import org.junit.Test;

public class FileUtilTest {

    private static String pathTestFile = "example.txt";

    @After
    public void tearDown() throws Exception {
        File file = new File(pathTestFile);
        if(file.exists() && !file.isDirectory()) { 
          file.delete();
        }
    }
    
    @Test
    public void readTextFileTest() throws IOException {
      createAndWriteFile();
      File file = new File(pathTestFile);

      String str = FileUtil.readTextFile(file);
      assertEquals("Hello world", str);
      
    }

  @Test
  public void writeTextToFileTest() throws IOException {
    createAndWriteFile();
    File file = new File(pathTestFile);
    Path path = new Path(pathTestFile);
    FileUtil.writeTextToFile("Hello world", path);
    String str = FileUtil.readTextFile(file);
    assertEquals("Hello world", str);  
  }

  /*@Test
  public void writeTextToFileWithNotFoundExceptionTest() throws IOException {
    File file = new File("/Files/FilesLogged.txt");
    Path path = new Path("/Files/FilesLogged.txt");
    FileSystem fs = path.getFileSystem(new TajoConf());
    if (fs.exists(path.getParent())) {
      fs.delete(path.getParent(), true);
    }
    FileUtil.writeTextToFile("Hello world", path);
    String str = FileUtil.readTextFile(file);
    assertEquals("Hello world", str);  
  }*/

  @Test
  public void readTextFromStreamTest() throws IOException {
    createAndWriteFile();
    File file = new File(pathTestFile);
    InputStream targetStream = new FileInputStream(file);
    String str = FileUtil.readTextFromStream(targetStream);
    assertEquals("Hello world", str);
  }

  @Test
  public void writeTextToStream() throws IOException {
    File file = new File(pathTestFile);
    OutputStream targetStream = new FileOutputStream(file);
    FileUtil.writeTextToStream("Hello world", targetStream);

    String str1 = FileUtil.readTextFile(file);
    assertEquals("Hello world", str1);
   
  }

    private void createAndWriteFile() throws IOException {
        String text = "Hello world";
        BufferedWriter output = null;
        try {
            File file = new File(pathTestFile);
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
            output.close();
          }
        }
    }
}
