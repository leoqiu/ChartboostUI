package unittest.com.chartboost.modules.factories;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class ResourceBundleFactoryTest {
	
	
//	@Test(dataProvider = "dp")
//	public void f(Integer n, String s) {
//	}
//
//	@DataProvider
//	public Object[][] dp() {
//		return new Object[][] { new Object[] { 1, "a" },
//				new Object[] { 2, "b" }, };
//	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}
	
    @Test
    public void testGetInstance() throws Exception {

        //ResourceBundle locatorBundle = ResourceBundleFactory.getInstance()
    	//System.out.println("testGetInstance");
    }

    @Test
    public void testGetDefaultLocatorInstance() throws Exception {

    }

    @Test
    public void testGetDefaultResInstance() throws Exception {

    }

}
