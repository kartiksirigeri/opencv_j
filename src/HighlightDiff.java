import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class HighlightDiff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		Mat img1 = Imgcodecs.imread("/home/osboxes/aero1.jpg");
//		Mat img2 = Imgcodecs.imread("/home/osboxes/aero3.jpg");
		
		Mat img1 = Imgcodecs.imread("/home/osboxes/img1.png");
		Mat img2 = Imgcodecs.imread("/home/osboxes/img2.png");

		Mat img_gray1 = new Mat();
		Imgproc.cvtColor(img1, img_gray1, Imgproc.COLOR_BGR2GRAY);
		Mat img_gray2 = new Mat();
		Imgproc.cvtColor(img2, img_gray2, Imgproc.COLOR_BGR2GRAY);


		Mat diff = new Mat();
		Core.absdiff(img_gray1, img_gray2, diff);
		
//		Imgcodecs.imwrite("/home/osboxes/res_diff.jpg", diff);
		Imgcodecs.imwrite("/home/osboxes/res_diff.png", diff);

		List<MatOfPoint> countours = new ArrayList<MatOfPoint> ();
		Mat hrch = new Mat();
		Imgproc.findContours(diff, countours, hrch, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

		for(MatOfPoint mp : countours)
		{
			Rect boundingRect = Imgproc.boundingRect(mp);
			if( boundingRect.height > 1 && boundingRect.width > 1 )
			{
				Imgproc.rectangle(img1, boundingRect, new Scalar(0,0,255), 2);
				Imgproc.rectangle(img2, boundingRect, new Scalar(0,0,255), 2);
			}
		}

//		Imgcodecs.imwrite("/home/osboxes/res_aero1.jpg", img1);
//		Imgcodecs.imwrite("/home/osboxes/res_aero3.jpg", img2);
		
		Imgcodecs.imwrite("/home/osboxes/res_img1.png", img1);
		Imgcodecs.imwrite("/home/osboxes/res_img2.png", img2);

	}

}
