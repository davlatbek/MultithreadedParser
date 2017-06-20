import com.innopolis.parser.ParserManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davlet on 6/20/17.
 */
public class TestClass {
    private File testFile;
    private File testFile2;
    private File testFile3;
    private File resultFile;
    private List<File> files;

    @Before
    public void setUp() throws Exception {
        testFile = new File("./src/test/testfile.txt");
        testFile2 = new File("./src/test/testfile2.txt");
        testFile3 = new File("./src/test/testfile3.txt");
        resultFile = new File("./src/test/testresult.txt");
        files = new ArrayList<>();
    }

    @Test
    public void checkWordOccurenceTest() {
        files.add(testFile2);
        ParserManager manager = new ParserManager(files, " ", resultFile);
        manager.start();
        assert manager.wordToNumberOfOccurences.get("мир") == 30;
    }

    @Test
    public void stopForEnglishLetterTest() {
        files = new ArrayList<>();
        files.add(testFile3);
        ParserManager manager = new ParserManager(files, " ", resultFile);
        manager.start();
        assert manager.wordToNumberOfOccurences.get("мир") == 1;
    }

    @Test
    public void doesContainEnglishWordInMapTest() {
        files = new ArrayList<>();
        files.add(testFile3);
        ParserManager manager = new ParserManager(files, " ", resultFile);
        manager.start();
        assert !manager.wordToNumberOfOccurences.containsKey("world");
    }

    @Test
    public void checkFilesExistenceTest(){
        files = new ArrayList<>();
        files.add(testFile);
        ParserManager manager = new ParserManager(files, " ", resultFile);
        assert manager.checkFiles(files);
    }

    @After
    public void tearDown() throws Exception {
        testFile = null;
        resultFile = null;
    }
}
