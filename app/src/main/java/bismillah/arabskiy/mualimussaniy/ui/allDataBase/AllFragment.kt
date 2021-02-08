package bismillah.arabskiy.mualimussaniy.ui.allDataBase

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import bismillah.arabskiy.mualimussaniy.MainActivity
import bismillah.arabskiy.mualimussaniy.R
import bismillah.arabskiy.mualimussaniy.Settings
import bismillah.arabskiy.mualimussaniy.core.dp
import bismillah.arabskiy.mualimussaniy.data.MuallimDatabase
import bismillah.arabskiy.mualimussaniy.data.model.Article
import bismillah.arabskiy.mualimussaniy.ui.OnTextSizeChangeListener
import kotlinx.android.synthetic.main.fragment_paklik.*


class AllFragment(private val typeId: Int = 1 ) : androidx.fragment.app.Fragment(R.layout.fragment_paklik),
    AllView,
    OnTextSizeChangeListener {

    private lateinit var presenter : AllPresenter
    private lateinit var settings: Settings
    private var textList = mutableListOf<TextView>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings = Settings(requireContext())
        val dao = MuallimDatabase.getInstance(requireContext()).articleDao()
        presenter = AllPresenter(dao, this)
        presenter.getAllArticle(typeId)
        allTitles()
    }

    private fun allTitles(){
        (requireActivity() as MainActivity).supportActionBar?.title =
            when(typeId) {
                1-> "dsd"
                2-> "Таҳәрат, таяммум ҳәм ғусыл"
                3-> "Намаздан кейинги зикирлер"
                4-> "Намаз бузылатуғын жағдайлар"
                5-> "Жаназа намазы тәртиби"
                6-> "Мүсәпирдиң намазы, имамға ериў, қаза намаз"
                7-> "Ҳайыт ҳәм жума намазлары"
                else -> "Null"
            }
    }


    override fun onTextSizeChanged(size: Float) {
        textList.forEach {
            it.textSize = size
        }

    }


    override fun setAllArticle(article: Article) {
       createDynamicViews(article)
    }



    private fun createDynamicViews(article: Article) {
        //////////////Qay jerde text qay jerde suwret ekenin tawip aliw//////////////////
        val textPair: MutableList<Pair<Int, Int>> = mutableListOf()
        val imagePair: MutableList<Pair<Int, Int>> = mutableListOf()
        var string = article.article
        var i1 = string?.indexOf('{')
        var i2 = -1
        while (i1 != -1) {
            if (i1 != null) {
                textPair.add(Pair(i2+1, i1-1))
            }
            if (string != null) {
                i2 = string.indexOf('}')
            }
            val chars = string?.toCharArray()
            if (i1 != null) {
                chars?.set(i1, '*')
            }
            chars?.set(i2, '*')
            string = chars?.let { String(it) }
            if (i1 != null) {
                imagePair.add(Pair(i1+1, i2))
            }
            if (string != null) {
                i1 = string.indexOf('{')
            }
        }
        if (string != null) {
            textPair.add(Pair(i2+1, string.length-1))
        }
        /////////////////////////////////////////////////////////////////////////////////
        for (i in 0 until textPair.size-1) {
            if (textPair[i].first < textPair[i].second) {
                val textView = TextView(requireContext())
                textList.add(textView)
                textView.setTextColor(Color.BLACK)
                val params: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(16.dp, 16.dp, 16.dp, 16.dp)
                textView.layoutParams = params
                textView.textSize = settings.getTextSize()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    if (string != null) {
                        textView.text = Html.fromHtml(string.substring(textPair[i].first, textPair[i].second), Html.FROM_HTML_MODE_COMPACT)
                    }
                } else {
                    if (string != null) {
                        textView.text = Html.fromHtml(string.substring(textPair[i].first, textPair[i].second))
                    }
                }
                linearLayout.addView(textView)
            }
            if (imagePair[i].first < imagePair[i].second) {
                val imageView = ImageView(requireContext())
                val params: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(getWidth()-32.dp, ((getWidth()-32.dp)*1.52).toInt())
                params.setMargins(16.dp, 16.dp, 16.dp, 16.dp)
                imageView.layoutParams = params
                val id = resources.getIdentifier(string?.substring(imagePair[i].first, imagePair[i].second), "drawable", requireContext().packageName)
                imageView.setBackgroundResource(id)
                linearLayout.addView(imageView)
            }
        }
        val i = textPair.size-1
        if (textPair[i].first < textPair[i].second) {
            val textView = TextView(requireContext())
            textView.setTextColor(Color.BLACK)
            val params: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(16.dp, 16.dp, 16.dp, 16.dp)
            textView.layoutParams = params
            textView.textSize = settings.getTextSize()
            textList.add(textView)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (string != null) {
                    textView.text = Html.fromHtml(string.substring(textPair[i].first, textPair[i].second), Html.FROM_HTML_MODE_COMPACT)
                }
            } else {
                if (string != null) {
                    textView.text = Html.fromHtml(string.substring(textPair[i].first, textPair[i].second))
                }
            }
            linearLayout.addView(textView)
        }

    }

    private fun getWidth() : Int {
        val display = Resources.getSystem().displayMetrics
        return display.widthPixels
    }
}