package bismillah.arabskiy.mualimussaniy.ui.about

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import bismillah.arabskiy.mualimussaniy.MainActivity
import bismillah.arabskiy.mualimussaniy.R
import bismillah.arabskiy.mualimussaniy.Settings
import bismillah.arabskiy.mualimussaniy.data.MuallimDatabase
import bismillah.arabskiy.mualimussaniy.data.model.Article
import bismillah.arabskiy.mualimussaniy.ui.OnTextSizeChangeListener
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment: androidx.fragment.app.Fragment(R.layout.fragment_about),
    AboutView,
    OnTextSizeChangeListener {

    private lateinit var presenter : AboutPresenter
    private lateinit var settings: Settings


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = MuallimDatabase.getInstance(requireContext()).articleDao()
        settings = Settings(requireContext())
        presenter = AboutPresenter(dao, this)
        presenter.getAllArticle(13)
        (requireActivity() as MainActivity).supportActionBar?.title = "Биз хаққымизда"
    }

    override fun setAllArticle(article: Article) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textAbout.text = Html.fromHtml(article.article, Html.FROM_HTML_MODE_COMPACT)
            textAbout.textSize = settings.getTextSize()
        } else {
            textAbout.text = Html.fromHtml(article.article)
            textAbout.textSize = settings.getTextSize()
        }
    }

    override fun onTextSizeChanged(size: Float) {
        textAbout?.textSize = size
    }

}