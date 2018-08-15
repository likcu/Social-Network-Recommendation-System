package edu.neu.ccs.cs5004;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class RunTest {

  private Run run;

  @Before
  public void setUp() throws Exception {
    run = new Run();
  }

  @Test(expected = RuntimeException.class)
  public void main() throws IOException {
    String args[] = {"test_files/nodes_test.csv", "test_files/edges_test.csv", "test_files/result_test.csv", "s", "1", "1"};
    String args2[] = {"ad.csv"};
    Run.main(args);
    try {
      Run.main(args2);
    } catch (FileNotFoundException e) {
      Assert.assertTrue(e.getClass().equals(FileNotFoundException.class));
    }
  }

  @Test(expected = RuntimeException.class)
  public void main2() throws IOException {
    String args[] = {"nodes_test.asd", "test_files/edges_test.csv", "test_files/result_test.csv", "s", "1", "1"};
    try {
      Run.main(args);
    } catch (FileNotFoundException e) {
      Assert.assertTrue(e.getClass().equals(FileNotFoundException.class));
    }
  }

  @Test(expected = RuntimeException.class)
  public void main3() throws IOException {
    String args[] = {"test_files/nodes_test.csv", "edges_test.asd", "test_files/result_test.csv", "s", "1", "1"};
    try {
      Run.main(args);
    } catch (FileNotFoundException e) {
      Assert.assertTrue(e.getClass().equals(FileNotFoundException.class));
    }
  }

  @Test(expected = RuntimeException.class)
  public void main4() throws IOException {
    String args[] = {"test_files/nodes_test.csv", "test_files/edges_test.csv", "result_test.asd", "s", "1", "1"};
    try {
      Run.main(args);
    } catch (FileNotFoundException e) {
      Assert.assertTrue(e.getClass().equals(FileNotFoundException.class));
    }
  }

  @Test
  public void main5() throws IOException {
    String args[] = {"nodes_small.csv", "edges_small.csv", "test_files/result_test.csv", "s"};
    try {
      Run.main(args);
    } catch (FileNotFoundException e) {
      Assert.assertTrue(e.getClass().equals(FileNotFoundException.class));
    }
  }
}