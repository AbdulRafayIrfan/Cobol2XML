package jUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CallTest.class, CommentLineTest.class, ConstantValueTest.class, DisplayTest.class, MoveTest.class })
public class ExtendedFeaturesTest {

}
