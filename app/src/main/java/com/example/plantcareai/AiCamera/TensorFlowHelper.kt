package com.example.plantcareai.AiCamera

import android.content.Context
import android.graphics.Bitmap
import com.example.plantcareai.AiCamera.BitmapConverter.convertBitmapToString
import com.example.plantcareai.ml.PlantDiseaseModel
import com.example.plantcareai.model.ClassifiedImage
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

object TensorFLowHelper {

    const val IMAGESIZE = 256

    fun classifyImage(
        image: Bitmap,
        context: Context,
        callback: ((classifiedImage: ClassifiedImage) -> Unit)
    ) {
        // Load the TFLite model.
        val model = PlantDiseaseModel.newInstance(context)

        // Creates inputs for reference.
        val inputFeature0: TensorBuffer =
            TensorBuffer.createFixedSize(intArrayOf(1, IMAGESIZE, IMAGESIZE, 3), DataType.FLOAT32)

        val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(4 * IMAGESIZE * IMAGESIZE * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        val intValues = IntArray(IMAGESIZE * IMAGESIZE)
        image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)

        var pixel = 0
        // Iterate over each pixel and extract R, G, and B values.
        // Add those values individually to the byte buffer.
        for (i in 0 until IMAGESIZE) {
            for (j in 0 until IMAGESIZE) {
                val `val` = intValues[pixel++] // RGB
                byteBuffer.putFloat((`val` shr 16 and 0xFF) * (1f / 1))
                byteBuffer.putFloat((`val` shr 8 and 0xFF) * (1f / 1))
                byteBuffer.putFloat((`val` and 0xFF) * (1f / 1))
            }
        }
        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0: TensorBuffer = outputs.getOutputFeature0AsTensorBuffer()
        val confidences = outputFeature0.floatArray

        // Find the index of the class with the biggest confidence.
        var maxPos = 0
        var maxConfidence = 0f
        for (i in confidences.indices) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i]
                maxPos = i
            }
        }

        val classLabels =
            arrayOf("Apple Black Rot", "Apple Cedar Rust", "Healthy Apple", "Apple Scab", "Blueberry healthy", "Cherry (including_sour) Powdery_mildew", "Cherry (including_sour) healthy", "Corn Cercospora_leaf_spot Gray_leaf_spot", "Corn_(maize)___Common_rust_", "Corn_(maize)___Northern_Leaf_Blight", "Corn_(maize)___healthy", "Grape___Black_rot", "Grape___Esca_(Black_Measles)", "Grape___Leaf_blight_(Isariopsis_Leaf_Spot)", "Grape___healthy", "Orange___Haunglongbing_(Citrus_greening)", "Peach___Bacterial_spot")

        val classifiedImage = ClassifiedImage(
            name = convertBitmapToString(image),
            label = classLabels[maxPos],
            confidence = maxConfidence
        )

        callback.invoke(classifiedImage)

        // Releases model resources if no longer used.
        model.close()
    }
}
