package com.example.testthu

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var textView_bang: TextView
    private var A: String = ""
    private var dau: String = ""
    private var B: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // textView
        textView_bang = findViewById(R.id.textView_bang)

        // khởi tạo mảng đối tượng của các số
        val buttons_so = arrayOf(
            R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        )
        buttons_so.forEach { id ->
            findViewById<Button>(id).setOnClickListener { ngheSo(it) }
        }

        // số 0: đặc biệt
        val button0 = findViewById<Button>(R.id.button0)
        button0.setOnClickListener { nghe0() }

        // dấu
        val buttons_dau =
            arrayOf(R.id.button_cong, R.id.button_tru, R.id.button_nhan, R.id.button_chia)
        buttons_dau.forEach { id ->
            findViewById<Button>(id).setOnClickListener { nghe_dau(it) }
        }

        // dấu bằng
        val button_bang = findViewById<Button>(R.id.button_bang)
        button_bang.setOnClickListener { ketqua() }

        // xóa hết
        val button_xoahet = findViewById<Button>(R.id.button_xoatoanbo)
        button_xoahet.setOnClickListener {
            textView_bang.setText("")
            A = ""
            B = ""
            dau = ""
        }

        // xóa 1 ký tự
        val button_BS = findViewById<Button>(R.id.button_xoakytu)
        button_BS.setOnClickListener { xoakytu() }

        // xóa 1 phần tử phía trươc
        val button_CE = findViewById<Button>(R.id.button_gannhat)
        button_CE.setOnClickListener { xoagannhat() }

        // đổi dấu
        val button_amduong = findViewById<Button>(R.id.button_nguocdau)
        button_amduong.setOnClickListener { doi_dau() }
    }

    // đổi dấu
    private fun doi_dau() {
        if (B != "")// đổi dấu của B
        {
            if (B[0] == '-') {
                B = B.substring(1)
                textView_bang.text=getString(R.string.result,A,dau,B)
            } else {
                B = "-" + B
                textView_bang.text=getString(R.string.result,A,dau,B)
            }
        } else {
            if (dau != "") {
                if (dau == "+") {
                    dau = "-"
                    textView_bang.text=getString(R.string.result,A,dau,B)
                } else {
                    dau = "+"
                    textView_bang.text=getString(R.string.result,A,dau,B)
                }
            } else {
                if (A[0] == '-') {
                    A = A.substring(1)
                    textView_bang.text=getString(R.string.result,A,dau,B)
                } else {
                    A = "-" + A
                    textView_bang.text=getString(R.string.result,A,dau,B)
                }
            }

        }
    }

    // xóa 1 phần tử trc
    private fun xoagannhat() {
        if (B !="") {
            B = ""
            textView_bang.text=getString(R.string.result,A,dau,B)
            return
        }
        if (dau != "") {
            dau = ""
            textView_bang.text=getString(R.string.result,A,dau,B)
        } else {
            A = ""
            textView_bang.text=getString(R.string.result,A,dau,B)
        }

    }


    // xóa 1 ký tự
    private fun xoakytu() {
        if (textView_bang.text.toString() == "") return
        if (B != "") {
            val sizeB: Int = B.length
            if (B[0] == '-') {
                if (sizeB == 2) {
                    B = ""
                    textView_bang.text=getString(R.string.result,A,dau,B)
                } else {
                    B = B.substring(0, sizeB - 1)
                    textView_bang.text=getString(R.string.result,A,dau,B)

                }
            } else {
                B = B.substring(0, sizeB - 1)
                textView_bang.text=getString(R.string.result,A,dau,B)
            }
            return
        }
        if (dau != "") {
            dau = ""
            textView_bang.text=getString(R.string.result,A,dau,B)
            return
        }
        if (A != "") {
            val sizeA: Int = A.length
            if (A[0] == '-') {
                if (sizeA == 2) {
                    A = ""
                    textView_bang.text=getString(R.string.result,A,dau,B)
                } else {
                    A = A.substring(0, sizeA - 1)
                    textView_bang.text=getString(R.string.result,A,dau,B)

                }
            } else {
                A = A.substring(0, sizeA - 1)
                textView_bang.text=getString(R.string.result2,A,dau)
            }
        }

    }

    // nhập dấu
    private fun nghe_dau(button_dau: View) {
        val nut = button_dau as Button
        if (textView_bang.text.toString() == "") return
        if (B != "") ketqua()
        dau = nut.text.toString()
        textView_bang.text=getString(R.string.result2,A,dau)
    }


    // tính kết quả
    private fun ketqua() {
        if (B != "") {
            if (dau == "+") {
                val ketqual = cong(A, B)
                A = ketqual
                dau = ""
                B = ""
                textView_bang.text=getString(R.string.result2,ketqual,dau)
                return
            }
            if (dau == "-") {
                val ketqual = tru(A, B)
                A = ketqual
                dau = ""
                B = ""
                textView_bang.text=getString(R.string.result2,ketqual,dau)
                return
            }
            if (dau == "x") {
                val ketqual = nhan(A, B)
                A = ketqual
                dau = ""
                B = ""
                textView_bang.text=getString(R.string.result2,ketqual,dau)
                return
            }
            if (dau == "/") {
                val ketqual = chia(A, B)
                A = ketqual
                dau = ""
                B = ""
                textView_bang.text=getString(R.string.result2,ketqual,dau)
            }
        }
    }

    private fun cong_2_duong(x: String, y: String): String {
        val a = x.toInt()
        val b = y.toInt()
        val c = a + b
        return c.toString()
    }

    private fun tru_2_duong(x: String, y: String): String //x-y
    {
        val a = x.toInt()
        val b = y.toInt()
        val c = a - b
        return c.toString()
    }

    private fun cong(x: String, y: String): String {
        if (x[0] != '-' && y[0] != '-') return cong_2_duong(x, y) // 2+3
        if (x[0] == '-' && y[0] == '-') return "-" + cong_2_duong(x.substring(1),y.substring(1)) // -2+-3
        if (x[0] != '-' && y[0] == '-') return tru_2_duong(x, y.substring(1))  // x+-y
        else return tru_2_duong(y, x.substring(1))  // -x+y =y-x
    }

    private fun tru(x: String, y: String): String {
        if (x[0] != '-' && y[0] != '-') return tru_2_duong(x, y) // 2-3
        if (x[0] == '-' && y[0] == '-') return tru_2_duong(y.substring(1),x.substring(1))//-2--3=3-2 or -(2-3)
        if (x[0] != '-' && y[0] == '-') return cong_2_duong(x, y.substring(1)) // 4--3=4+3
        else return "-" + cong_2_duong(x.substring(1), y)// -3-2=-(3+2)
    }


    private fun nhan_2_duong(x: String, y: String): String
    {
        val a: Int=x.toInt()
        val b: Int=y.toInt()
        val tic: Int=a*b
        return tic.toString()
    }

    private fun nhan(x: String, y: String): String
    {
        if(x[0] != '-' && y[0] != '-') return nhan_2_duong(x,y) // 2x3
        if(x[0] == '-' && y[0] == '-') return nhan_2_duong(x.substring(1),y.substring(1))
        if(x[0] != '-' && y[0] == '-') return "-"+nhan_2_duong(x,y.substring(1))
        else return "-"+nhan_2_duong(x.substring(1),y)
    }

    private fun chia_2_duong(x: String, y: String): String
    {
        val a: Int=x.toInt()
        val b: Int=y.toInt()
        if(b==0) return "0"
        val th: Int=(a/b)
        return th.toString()
    }
    private fun chia(x: String, y: String): String
    {
        if(x[0] != '-' && y[0] != '-') return chia_2_duong(x,y) // 2x3
        if(x[0] == '-' && y[0] == '-') return chia_2_duong(x.substring(1),y.substring(1))
        if(x[0] != '-' && y[0] == '-') return "-"+chia_2_duong(x,y.substring(1))
        else return "-"+chia_2_duong(x.substring(1),y)
    }

    // bấm số 0
    private fun nghe0()
    {
        if(textView_bang.text.toString()=="0") return
        else
        {
            if(dau=="")// đag nhập A
            {
                A+="0"
                textView_bang.text=getString(R.string.result2,A,dau)
                return
            }
            // nếu đang nhập ở A+_
            if(B=="0") return
            else
            {
                B+="0"
                textView_bang.text=getString(R.string.result,A,dau,B)
            }
        }
    }

    // bấm số bình thường
    private fun ngheSo(so: View)
    {
        val nut= so as Button

        if(textView_bang.text.toString()=="0")// ban đầu chưa nhập gì
        {
            A=nut.text.toString()
            textView_bang.setText(A)
            return
        }

        // ban đầu đã có gì
        //chỉ đag nhập A
        if(dau=="")
        {
            A+=nut.text.toString()
            textView_bang.text=getString(R.string.result2,A,dau)
            return
        }
        else // đang nhập B
        {
            B+=nut.text.toString()
            textView_bang.text=getString(R.string.result,A,dau,B)
        }
    }


}