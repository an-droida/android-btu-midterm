package ge.androida.midterm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ge.androida.midterm.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnBuy.setOnClickListener {
            validateFields()
        }
    }

    private fun validateFields() {
        val type = binding.etCardType.text.toString()
        val name = binding.etCardName.text.toString()
        val number = binding.etCardNumber.text.toString()
        val month = binding.etMM.text.toString()
        val year = binding.etYY.text.toString()
        val cvv = binding.etCvvCode.text.toString()
        if (type != "Visa" && type != "MasterCard" && type != "Amex") {
            Toast.makeText(this, "ბარათის ტიპი არასწორია", Toast.LENGTH_LONG).show()
            return
        }
        if (name.isEmpty()) {
            Toast.makeText(this, "შეიყვანთ სახელი", Toast.LENGTH_LONG).show()
            return
        }
        if (validateCardNumber(cardType = type) != number.length) {
            Toast.makeText(this, "ბარათის ნომერი არასწორია", Toast.LENGTH_LONG).show()
            return
        }
        if (month.isEmpty() || month.toInt() > 12) {
            Toast.makeText(this, "შეცდომა", Toast.LENGTH_LONG).show()
            return
        }
        if (validateCardCvv(type) != cvv.length) {
            Toast.makeText(this, "კოდი არასწორია", Toast.LENGTH_LONG).show()
            return
        }
        if (getCurrentDate()[Calendar.YEAR] > "20$year".toInt()) {
            Toast.makeText(this, "მოქმედების ვადა ამოწურულია", Toast.LENGTH_LONG).show()
            return
        }
        if (getCurrentDate()[Calendar.YEAR] == "20$year".toInt() && getCurrentDate()[Calendar.MONTH] > month.toInt()){
            Toast.makeText(this, "მოქმედების ვადა ამოწურულია", Toast.LENGTH_LONG).show()
            return
        }

        Toast.makeText(this, "გადახდა წარმატებით შესრულდა", Toast.LENGTH_LONG).show()
        binding.etCardType.text.clear()
        binding.etCardName.text.clear()
        binding.etCardNumber.text.clear()
        binding.etMM.text.clear()
        binding.etYY.text.clear()
        binding.etCvvCode.text.clear()
    }


    private fun getCurrentDate(): Calendar {
        return Calendar.getInstance()
    }

    private fun validateCardNumber(cardType: String): Int? {
        return if (cardType == "Visa" || cardType == "MasterCard")
            16
        else if (cardType == "Amex")
            15
        else null
    }

    private fun validateCardCvv(cardType: String): Int? {
        return if (cardType == "Visa" || cardType == "MasterCard")
            3
        else if (cardType == "Amex")
            4
        else null
    }
}
