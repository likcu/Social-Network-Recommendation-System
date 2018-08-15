package edu.neu.ccs.cs5004;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.plaf.synth.SynthEditorPaneUI;

import static org.junit.Assert.*;

public class FileProcesserTest {

  private File file1;
  private File file2;
  private File file3;
  private File file4;
  private File file5;
  private File file6;
  private File file7;
  private File file8;
  private File file9;
  private File file10;
  private File file11;
  private File file12;
  private Relation relation;
  private Relation relation2;
  private RecommendationSystem recommendationSystem;
  private RecommendationSystem recommendationSystem2;
  private FileProcesser fileProcesser;
  private FileProcesser fileProcesser2;
  private FileProcesser sameRefFileProcesser;
  private FileProcesser sameStateAsFileProcesser;
  private FileProcesser diffFileProcesser;
  private FileProcesser yetAnotherFileProcesser;
  private FileProcesser nullFileProcesser = null;

  @Before
  public void setUp() throws Exception {

    file1 = new File("test_files/nodes_test.csv");
    file2 = new File("test_files/edges_test.csv");
    file3 = new File("test_files/result_test.csv");
    file4 = new File("test_files/nodes_small.csv");
    file5 = new File("test_files/edges_small.csv");
    file6 = new File("none.doc");
    file7 = new File("test_files/test_file.csv");
    file8 = new File("test_files/test_file2.csv");
    file9 = new File("test_files/test_file3.csv");
    file10 = new File("test_files/test_file4.csv");
    file11 = new File("test_files/test_file5.csv");
    file12 = new File("test_files/test_file6.csv");
    relation = new Relation(new HashMap<>(), new HashMap<>());
    recommendationSystem = new RecommendationSystem(new ArrayList<>(), relation);
    relation2 = new Relation(new HashMap<>(), new HashMap<>());
    recommendationSystem2 = new RecommendationSystem(new ArrayList<>(), relation2);
    fileProcesser = new FileProcesser(recommendationSystem);
    fileProcesser2 = new FileProcesser(recommendationSystem2);
    sameRefFileProcesser = fileProcesser;
    sameStateAsFileProcesser = new FileProcesser(recommendationSystem);
    diffFileProcesser = new FileProcesser(null);
    yetAnotherFileProcesser = new FileProcesser(recommendationSystem);
  }

  @Test
  public void writeFile() throws IOException {
    fileProcesser.readFile(file1, file2);
    fileProcesser.writeFile(file3, recommendationSystem.recommend("s", 1, 1));
  }

  @Test
  public void readFile() throws IOException {
    fileProcesser.readFile(file1, file2);
    fileProcesser2.readFile(file4, file5);
    try {
      fileProcesser2.readFile(file6, file6);
    } catch (FileNotFoundException e) {
      Assert.assertTrue(e.getClass().equals(FileNotFoundException.class));
    }
  }

  @Test(expected = RuntimeException.class)
  public void checkFile() throws IOException {
    fileProcesser.checkFile(file1, file2);
    fileProcesser2.checkFile(file7, file7);
  }

  @Test(expected = RuntimeException.class)
  public void checkFile2() throws IOException {
    fileProcesser2.checkFile(file8, file8);
  }

  @Test(expected = RuntimeException.class)
  public void checkFile3() throws IOException {
    fileProcesser2.checkFile(file1, file7);
  }

  @Test(expected = RuntimeException.class)
  public void checkFile7() throws IOException {
    fileProcesser2.checkFile(file1, file12);
  }

  @Test(expected = RuntimeException.class)
  public void checkFile4() throws IOException {
    fileProcesser2.checkFile(file10, file7);
  }

  @Test(expected = RuntimeException.class)
  public void checkFile5() throws IOException {
    fileProcesser2.checkFile(file9, file7);
  }

  @Test(expected = RuntimeException.class)
  public void checkFile6() throws IOException {
    fileProcesser2.checkFile(file11, file7);
  }

  @Test
  public void equals() {
    Assert.assertTrue(fileProcesser.equals(fileProcesser));
    Assert.assertTrue(fileProcesser.equals(sameRefFileProcesser));
    Assert.assertTrue(fileProcesser.equals(sameStateAsFileProcesser));
    Assert.assertTrue(sameStateAsFileProcesser.equals(fileProcesser));
    Assert.assertEquals(fileProcesser.equals(sameStateAsFileProcesser) && sameStateAsFileProcesser.equals(yetAnotherFileProcesser), yetAnotherFileProcesser.equals(fileProcesser));
    Assert.assertFalse(fileProcesser.equals(diffFileProcesser));
    Assert.assertFalse(fileProcesser.equals(nullFileProcesser));
  }

  @Test
  public void testhashCode() {
    Assert.assertEquals(fileProcesser.equals(sameRefFileProcesser), fileProcesser.hashCode() == sameRefFileProcesser.hashCode());
    Assert.assertEquals(fileProcesser.equals(sameStateAsFileProcesser), fileProcesser.hashCode() == sameStateAsFileProcesser.hashCode());
  }
}