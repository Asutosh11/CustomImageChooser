# CustomImageChooser
This is a custom image chooser Android app that has a <em>custom camera built using Camera2 API</em> and a <em>custom image gallery</em>.

<p style="color:red"><h4>(Please note that I'm working on coverting the architecture of this library to MVVM and changing the programming language to Kotlin, will notify when its done)</font></h4></b></p>

1. In the custom gallery, I'm doing query for all images in the device. I'm populating all the Image folders in a RecyclerView  Grid layout. On selecting any folder, it loads all the images in that folder.

2. The custom camera, I have developed using Android's camera2 API. It has support for both front and back camera.
The images taken from this camera are saved inside the files folder of the app on the device.    

<br>
<b>Screenshots</b><br><br>

<kbd>
<img src="https://github.com/Asutosh11/CustomImageChooser/blob/master/screenshots/1.jpg" alt="Screenshot1" width="280px"/>
</kbd>
<kbd>
<img src="https://github.com/Asutosh11/CustomImageChooser/blob/master/screenshots/2.jpg" alt="Screenshot2" width="280px"/>
</kbd>
<kbd>
<img src="https://github.com/Asutosh11/CustomImageChooser/blob/master/screenshots/Screenshot_20170808-095034.jpg" alt="Screenshot3" width="280px"/>
</kbd>

<br>
<br><br>
Clone the repo and give it a try. 
Min API level is 21.

Thanks.


