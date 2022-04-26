package jUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExistingFeaturesTest.class, ExtendedFeaturesTest.class })
public class AllFeaturesTest {

}
