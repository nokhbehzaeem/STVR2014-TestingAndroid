public class P0Test extends
		ActivityInstrumentationTestCase2<MainActivity> {
private Solo robotium;
private LinkedList<Boolean> assertionsList = new LinkedList<Boolean>();
private static Bitmap imageBefore, imageAfter;
@Override
public void setUp() throws Exception {
	super.setUp();
	robotium = new Solo(getInstrumentation(), getActivity());
}
@Override
public void tearDown() throws Exception {
	robotium.finishOpenedActivities();
}
public P0Test(){
	super(MainActivity.class);
}	
@Smoke
public void testEventSequence() throws Exception {
	saveState();
	robotium.clickOnMenuItem("Info"); 
	rotate(); 
	for(Boolean b : assertionsList)	assertTrue(b);
}	
private void rotate() throws Exception {
	saveState();
	robotium.setActivityOrientation(Solo.LANDSCAPE);
	Activity mainActivity = robotium.getCurrentActivity();
	robotium.waitForActivity(mainActivity.toString());
	robotium.setActivityOrientation(Solo.PORTRAIT);
	robotium.waitForActivity(mainActivity.toString());
	compare();
}
private void saveState() throws Exception {
	imageBefore = captureImages();
}
private void compare() throws Exception {
	imageAfter = captureImages();
	assertionsList.add(imagesEqual());
}
private boolean imagesEqual(){
    int columns = imageBefore.getWidth();
    int rows = imageBefore.getHeight();
    int diff = 0;
    for (int row = 0; row < rows; row++)
        for (int col = 0; col < columns; col++){
           int rgbBefore = imageBefore.getPixel(col, row);
           int rgbAfter = imageAfter.getPixel(col, row);
           if(Math.abs(rgbBefore - rgbAfter) > 0)
        	   diff++; }
    double diffRatio = 1.0 * diff / (columns * rows);
    if(diffRatio > 0.01)//allowed threshold for change of clock, etc.
    	return false;
    return true;
}