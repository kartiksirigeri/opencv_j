
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageDiff {

    public static void main(String[] args) {
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat m  = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("m = " + m.dump());
        
        Mat img1 = Imgcodecs.imread("/home/osboxes/img1.png");
	    Mat img2 = Imgcodecs.imread("/home/osboxes/img2.png");
	    
	    Mat diff = new Mat();
	    Core.absdiff(img1, img2, diff);
	    
	    int th = 10;  // 0
	    Mat mask = new Mat(img1.size(), CvType.CV_8UC1);
	    for(int j=0; j<diff.rows(); ++j) {
	        for(int i=0; i<diff.cols(); ++i){
	        	double[] ds = diff.get(j, i);
	        	int val = Double.valueOf(ds[0]).intValue() + Double.valueOf(ds[1]).intValue()+ Double.valueOf(ds[2]).intValue();
	        	if(val > th)
	        	{
	        		mask.put(j, i, 255, 255, 255);
	        	}
	        }
	    }
	    
	    Mat res = new Mat();
	    Core.bitwise_and(img1, img2, res, mask);
	    
	    Imgcodecs.imwrite("/home/osboxes/res.png", res);
	    
	    
    }

}
