<h1>How the program Work</h1>
this is a simple watermarking program using HWT-LSB Algorithm, the purpose from combining both method is to make watermarked
Doenst Reduced in quality, HWT algorithm is an image compressing methode used for reduced the image size, the image itself were converted
from Image file to RGBA matrix, then the RGBA matrix were processed using HWT algorithm, as you may know, LSB (Least Significant Bit)
is an algorithm used to embed some text inside the image, so i have this i dea, the output from processing image RGBA matrix using HWT
were converted from matrix to String, the size of the matrix itself were saved and used to make the key, this roundabout way may doesnt
too efficient but it make the matrix convertion least complex, the output string then got embedded inside the image using LSB methode
</br>
<p>Note that, Some Of the code is not originally mine, so i not claiming the code mine, feel free to use it as referrence</p>
