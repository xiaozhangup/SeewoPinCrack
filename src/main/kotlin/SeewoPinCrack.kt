import com.google.zxing.*
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.awt.*
import java.awt.image.BufferedImage

object SeewoPinCrack {
    @JvmStatic
    fun main(args: Array<String>) {
        while (true) {
            try {
                val screenshot = captureScreenshot()
                val qrCodeText = decodeQRCode(screenshot)
                if (qrCodeText != null) {
                    println("QR Code detected: $qrCodeText")
                }
                Thread.sleep(1000)
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    private fun captureScreenshot(): BufferedImage {
        val robot = Robot()
        val screenRect = Rectangle(Toolkit.getDefaultToolkit().screenSize)
        return robot.createScreenCapture(screenRect)
    }

    private fun decodeQRCode(image: BufferedImage): String? {
        return try {
            val source = BufferedImageLuminanceSource(image)
            val bitmap = BinaryBitmap(HybridBinarizer(source))
            val result = MultiFormatReader().decode(bitmap)
            result.text
        } catch (e: NotFoundException) {
            null
        }
    }
}