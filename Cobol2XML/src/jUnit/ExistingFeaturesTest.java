package jUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DateWrittenTest.class, DivisionParserTest.class, ProgramIDTest.class, SectionTest.class })
public class ExistingFeaturesTest {

}
